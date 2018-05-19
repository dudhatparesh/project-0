package com.birghterbrain.project0.ui.main.additem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.birghterbrain.project0.R
import com.birghterbrain.project0.ui.base.BaseFragment
import javax.inject.Inject

class AddItemFragment: BaseFragment(),AddItemView{
    override fun popBack() {
        activity!!.supportFragmentManager.popBackStack()
    }

    var rootView: View? = null

    @BindView(R.id.et_name)
    lateinit var etName: EditText

    @BindView(R.id.et_desc)
    lateinit var etDesc: EditText

    @BindView(R.id.et_amount)
    lateinit var etAmount: EditText

    @Inject
    lateinit var addItemPresenter: AddItemPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_add_item,container,false)
            ButterKnife.bind(this,rootView!!)
            getComponent().inject(this)
            addItemPresenter.attachView(this)
        }
        return rootView!!
    }
    override fun displayMessage(message: String?) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    @OnClick(R.id.fab_save)
    fun saveItem(){
        addItemPresenter.addItem(etName.text.toString(),etDesc.text.toString(),
                etAmount.text.toString())
    }
}