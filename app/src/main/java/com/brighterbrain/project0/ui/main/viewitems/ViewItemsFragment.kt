package com.brighterbrain.project0.ui.main.viewitems

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.brighterbrain.project0.R
import com.brighterbrain.project0.data.model.Item
import com.brighterbrain.project0.ui.base.BaseFragment
import com.brighterbrain.project0.ui.main.MainActivity
import com.brighterbrain.project0.ui.main.saveitem.SaveItemFragment
import javax.inject.Inject

class ViewItemsFragment : BaseFragment(), ViewItemsMvpView {

    var rootView: View? = null
    @BindView(R.id.rv_items)
    lateinit var rvItems: RecyclerView

    @Inject
    lateinit var viewItemsPresenter: ViewItemsPresenter

    private val itemClickListener = object : ItemClickListener {
        override fun onItemClick(item: Item) {
            val saveItemFragment = SaveItemFragment()
            val arguments = Bundle()
            arguments.putLong("id", item.id!!)
            saveItemFragment.arguments = arguments
            (activity as MainActivity).displayFragment(saveItemFragment, true)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_items, container, false)
            ButterKnife.bind(this, rootView!!)
            rvItems.layoutManager = LinearLayoutManager(context)
            getComponent().inject(this)
            viewItemsPresenter.attachView(this)
        }
        viewItemsPresenter.loadItems()
        return rootView
    }

    override fun displayError(errorMessage: String?) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun displayItems(items: List<Item>) {
        val itemAdapter = ItemAdapter(items, context, itemClickListener)
        rvItems.adapter = itemAdapter
    }

    @OnClick(R.id.fab_add_item)
    fun addItem() {
        (activity as MainActivity).displayFragment(SaveItemFragment())
    }

    override fun onDestroy() {
        viewItemsPresenter.detachView()
        super.onDestroy()
    }

}