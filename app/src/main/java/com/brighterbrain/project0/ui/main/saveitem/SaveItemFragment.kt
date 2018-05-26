package com.brighterbrain.project0.ui.main.saveitem

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.brighterbrain.project0.MainApplication
import com.brighterbrain.project0.R
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.ui.base.BaseFragment
import com.brighterbrain.project0.utils.CommonUtils
import com.brighterbrain.project0.utils.FileUtils
import com.bumptech.glide.Glide
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.common.internal.service.Common
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnFailureListener
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.IOException
import javax.inject.Inject


open class SaveItemFragment : BaseFragment(), SaveItemView {


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object {
        const val RC_CAMERA: Int = 101
        const val RC_LOCATION: Int = 102
        const val RC_CAPTURE_IMAGE = 1001
        const val RC_GALLERY_IMAGE = 1002
        const val REQUEST_CHECK_SETTINGS = 1003
        const val RC_READ_SD_CARD = 103
    }

    private var rootView: View? = null
    private var lastLocation: Location? = null
    @BindView(R.id.et_name)
    lateinit var etName: EditText

    @BindView(R.id.et_desc)
    lateinit var etDesc: EditText

    @BindView(R.id.et_amount)
    lateinit var etAmount: EditText

    @BindView(R.id.iv_image)
    lateinit var ivImage: ImageView

    @BindView(R.id.sp_currency)
    lateinit var spCurrency: Spinner

    @Inject
    lateinit var saveItemPresenter: SaveItemPresenter

    private var imageUri: Uri? = null
    private var imagePath: String? = null
    private var itemId: Long? = null

    private val currencies = arrayOf("USD", "INR", "GBP")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_add_item, container, false)
            ButterKnife.bind(this, rootView!!)
            getComponent().inject(this)
            saveItemPresenter.attachView(this)

            spCurrency.adapter = ArrayAdapter<String>(context,
                    android.R.layout.simple_expandable_list_item_1, currencies)
            itemId = arguments?.getLong("id", -1L)
            if(itemId!=null &&itemId!=-1L) {
                saveItemPresenter.fillData(itemId!!)
            }
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
        return rootView!!
    }


    override fun fillData(item: Item) {
        etName.setText(item.name)
        etDesc.setText(item.description)
        etAmount.setText(item.amount.toString())
        spCurrency.setSelection(currencies.indexOf(item.currency))
        Glide.with(context!!).load(CommonUtils._IMAGE_URLS+item.imageName)
                .into(ivImage)

    }


    override fun displayMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.fab_save)
    @AfterPermissionGranted(RC_LOCATION)
    fun checkLocation() {

        saveItemPresenter.checkPermissions(activity!!, RC_LOCATION, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))


    }

    @AfterPermissionGranted(RC_READ_SD_CARD)
    override fun saveItem() {
        saveItemPresenter.addItem(etName.text.toString(), etDesc.text.toString(),
                etAmount.text.toString(), spCurrency.selectedItem.toString(),
                imagePath!!, lastLocation,itemId)
    }

    @OnClick(R.id.iv_image)
    fun selectImage() {
        val dialog = Dialog(context)
        dialog.setContentView(R.layout.choose_image)
        dialog.findViewById<ImageView>(R.id.iv_camera).setOnClickListener {
            run {
                dialog.dismiss()
                checkCameraPermission()
            }
        }
        dialog.findViewById<ImageView>(R.id.iv_gallery).setOnClickListener {
            run {
                dialog.dismiss()
                getImageFromGallery()
            }
        }
        dialog.show()
    }


    @AfterPermissionGranted(SaveItemFragment.RC_CAMERA)
    private fun checkCameraPermission() {
        saveItemPresenter.checkPermissions(activity!!, RC_CAMERA, arrayOf(Manifest.permission.CAMERA))
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    override fun captureImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity?.packageManager) != null) {
            try {
                val photoFile = FileUtils.createImageFile(context!!)
                imagePath = photoFile.absolutePath
                imageUri = FileProvider.getUriForFile(context!!,
                        MainApplication.get(activity!!).applicationContext.packageName + ".fileprovider",
                        photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

                startActivityForResult(takePictureIntent, RC_CAPTURE_IMAGE)
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, getString(R.string.no_camera_application_found)
                    , Toast.LENGTH_LONG).show()
        }
    }

    override fun getImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, RC_GALLERY_IMAGE)
    }

    override fun requestPermission(rationaleText: String?, requestCode: Int, perms: Array<String>) {
        EasyPermissions.requestPermissions(this, rationaleText!!, requestCode, *perms)
    }

    override fun displayPermissionAlertDialog() {
        AppSettingsDialog.Builder(this).build().show()
    }


    override fun popBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_CAPTURE_IMAGE -> {
                    Glide.with(this).load(imageUri).into(ivImage)
                }
                RC_GALLERY_IMAGE -> {
                    if (data != null) {
                        imageUri = data.data
                        imagePath = FileUtils.getRealPathFromURI(context!!, imageUri!!)
                        Glide.with(this).load(imageUri).into(ivImage)

                    }
                }
                REQUEST_CHECK_SETTINGS -> fetchLocation()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    internal var mLocationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            if (locationResult == null) {
                return
            }
            for (location in locationResult.locations) {
                if (location.accuracy < 200) {

                    lastLocation = location
                    saveItemPresenter.checkPermissions(activity = activity!!, requestCode = RC_READ_SD_CARD,
                            perms = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
                    fusedLocationClient.removeLocationUpdates(this)
                    break
                }
            }
        }
    }

    override fun fetchLocation() {
        fusedLocationClient.requestLocationUpdates(CommonUtils.getLocationRequest(),
                mLocationCallback,
                null)
    }

    override fun checkLocationSettings() {
        val client = LocationServices.getSettingsClient(activity!!)
        val task = client.checkLocationSettings(
                CommonUtils.getLocationSettingBuilder())


        task.addOnSuccessListener {
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
            fetchLocation()
        }

        task.addOnFailureListener(activity!!, OnFailureListener { e ->
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(activity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }

            }
        })

    }


}