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
import android.net.Uri
import android.widget.*
import com.brighterbrain.project0.MainApplication
import com.bumptech.glide.Glide


class AddItemFragment : BaseFragment(), AddItemView {

    companion object {
        const val RC_CAMERA:Int = 101
        const val RC_LOCATION:Int = 102
        const val RC_CAPTURE_IMAGE =1001
        const val RC_GALLARY_IMAGE = 1002
    }
    private var rootView: View? = null

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
    lateinit var addItemPresenter: AddItemPresenter

    var photoImageUri: Uri? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_add_item, container, false)
            ButterKnife.bind(this, rootView!!)
            getComponent().inject(this)
            addItemPresenter.attachView(this)

            spCurrency.adapter = ArrayAdapter<String>(context,
                    android.R.layout.simple_expandable_list_item_1,arrayOf("USD","INR","GBP"))
        }
        return rootView!!
    }


    override fun displayMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.fab_save)
    fun saveItem() {
        addItemPresenter.addItem(etName.text.toString(), etDesc.text.toString(),
                etAmount.text.toString(),spCurrency.selectedItem.toString(),photoImageUri?.toString())
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
                photoImageUri = FileProvider.getUriForFile(context!!,
                        MainApplication.get(activity!!).applicationContext.packageName+".fileprovider",
                        photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoImageUri)

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
                    RC_CAPTURE_IMAGE->{
                        Glide.with(this).load(photoImageUri).into(ivImage)
                    }
                    RC_GALLARY_IMAGE->{
                        if (data != null) {
                            photoImageUri = data.data
                            Glide.with(this).load(photoImageUri).into(ivImage)

                        }
                    }
                }
            }

        super.onActivityResult(requestCode, resultCode, data)
    }
}