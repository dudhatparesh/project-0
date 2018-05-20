package com.brighterbrain.project0.ui.main.additem

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.brighterbrain.project0.R
import com.brighterbrain.project0.ui.base.BaseFragment
import com.brighterbrain.project0.utils.FileUtils
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import javax.inject.Inject
import pub.devrel.easypermissions.EasyPermissions
import java.io.IOException
import android.graphics.BitmapFactory
import com.brighterbrain.project0.MainApplication


class AddItemFragment : BaseFragment(), AddItemView {

    companion object {
        const val RC_CAMERA:Int = 101
        const val RC_LOCATION:Int = 102
        const val RC_CAPTURE_IMAGE =1001
        const val RC_GALLARY_IMAGE = 1002
    }
    var rootView: View? = null

    @BindView(R.id.et_name)
    lateinit var etName: EditText

    @BindView(R.id.et_desc)
    lateinit var etDesc: EditText

    @BindView(R.id.et_amount)
    lateinit var etAmount: EditText

    @BindView(R.id.iv_image)
    lateinit var ivImage: ImageView

    @Inject
    lateinit var addItemPresenter: AddItemPresenter

    var photoImagePath: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_add_item, container, false)
            ButterKnife.bind(this, rootView!!)
            getComponent().inject(this)
            addItemPresenter.attachView(this)
        }
        return rootView!!
    }

    override fun displayMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.fab_save)
    fun saveItem() {
        addItemPresenter.addItem(etName.text.toString(), etDesc.text.toString(),
                etAmount.text.toString())
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
            run{
                dialog.dismiss()
                getImageFromGallary()
            }
        }
        dialog.show()
    }




    @AfterPermissionGranted(AddItemFragment.RC_CAMERA)
    private fun checkCameraPermission() {
        addItemPresenter.checkPermissions(activity!!, RC_CAMERA, arrayOf(Manifest.permission.CAMERA))
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    override fun captureImage() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(takePictureIntent.resolveActivity(activity?.packageManager)!=null){
            try{
                val photoFile = FileUtils.createImageFile(context!!)
                photoImagePath = photoFile.absolutePath
                val photoUri = FileProvider.getUriForFile(context!!,
                        MainApplication.get(activity!!).applicationContext.packageName+".fileprovider",
                        photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)

                startActivityForResult(takePictureIntent,RC_CAPTURE_IMAGE)
            }catch (e:IOException){
                e.printStackTrace()
                Toast.makeText(context,e.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(context,getString(R.string.no_camera_application_found)
                    ,Toast.LENGTH_LONG).show()
        }
    }

    override fun getImageFromGallary(){
        val galleryIntent = Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, RC_GALLARY_IMAGE)
    }

    override fun requestPermission(rationaleText: String?, requestCode: Int, perms: Array<String>) {
        EasyPermissions.requestPermissions(this,rationaleText!!,requestCode,*perms)
    }

    override fun displayPermissionAlertDialog() {
        AppSettingsDialog.Builder(this).build().show()
    }


    override fun popBack() {
        activity!!.supportFragmentManager.popBackStack()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            if(resultCode == Activity.RESULT_OK){
                when(requestCode){
                    RC_CAPTURE_IMAGE->setPicFromCamera()
                    RC_GALLARY_IMAGE->setPicFromGallery(data)
                }
            }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setPicFromGallery(data: Intent?) {
            if (data != null) {
                val contentUri = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, contentUri)
                    ivImage.setImageBitmap(bitmap)

                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun setPicFromCamera() {
        // Get the dimensions of the View
        val targetW = ivImage.width
        val targetH = ivImage.height

        // Get the dimensions of the bitmap
        val bmOptions = BitmapFactory.Options()
        bmOptions.inJustDecodeBounds = true
        BitmapFactory.decodeFile(photoImagePath, bmOptions)
        val photoW = bmOptions.outWidth
        val photoH = bmOptions.outHeight

        // Determine how much to scale down the image
        val scaleFactor = Math.min(photoW / targetW, photoH / targetH)

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false
        bmOptions.inSampleSize = scaleFactor
        bmOptions.inPurgeable = true

        val bitmap = BitmapFactory.decodeFile(photoImagePath, bmOptions)
        ivImage.setImageBitmap(bitmap)
    }
}