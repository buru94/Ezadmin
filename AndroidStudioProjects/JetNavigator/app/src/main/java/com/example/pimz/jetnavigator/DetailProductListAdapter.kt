package com.example.pimz.jetnavigator

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class DetailProductListAdapter(context: Context, item: ArrayList<ArrayList<Any>?>) : BaseAdapter() {
    private val mContext = context
    private val mItem = item
    override fun getCount(): Int {
            return mItem!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any? {
        return mItem!![position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        lateinit var viewHolder : ViewHolder
        var view = convertView
        Log.d("asdasd", mItem.size.toString())

        if (view == null){
            viewHolder = ViewHolder()
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_detail_product_list_item,parent,false)
            viewHolder.mPrId = view.findViewById(R.id.DETAIL_PRODUCT_ACTIVITY_LIST_PRODUCT_ID)
            viewHolder.mPrSoldOut = view.findViewById(R.id.DETAIL_PRODUCT_ACTIVITY_LIST_PRODUCT_SOLDOUT)
            viewHolder.mPrTemporary = view.findViewById(R.id.DETAIL_PRODUCT_ACTIVITY_LIST_PRODUCT_TEMPORARILY_SOLDOUT)
            viewHolder.mBarcode = view.findViewById(R.id.DETAIL_PRODUCT_ACTIVITY_LIST_PRODUCT_BARCODE)
            viewHolder.mOption = view.findViewById(R.id.DETAIL_PRODUCT_ACTIVITY_LIST_PRODUCT_OPTION)
            viewHolder.mStock = view.findViewById(R.id.DETAIL_PRODUCT_ACTIVITY_LIST_PRODUCT_CURRENT_STOCK)

            view.tag = viewHolder

            viewHolder.mPrTemporary.text = mItem!![position]!![2].toString()
            viewHolder.mPrId.text = mItem[position]!![0].toString()
            viewHolder.mBarcode.text = mItem[position]!![4].toString()
            viewHolder.mOption.text = mItem[position]!![1].toString()
            viewHolder.mPrSoldOut.text = mItem[position]!![3].toString()
            viewHolder.mStock.text = mItem[position]!![5].toString()

            return view
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.mPrTemporary.text = mItem!![position]!![2].toString()
        viewHolder.mPrId.text = mItem[position]!![0].toString()
        viewHolder.mBarcode.text = mItem[position]!![4].toString()
        viewHolder.mOption.text = mItem[position]!![1].toString()
        viewHolder.mPrSoldOut.text = mItem[position]!![3].toString()
        viewHolder.mStock.text = mItem[position]!![5].toString()
        return  view
    }
    inner class ViewHolder{
        lateinit var mPrId : TextView
        lateinit var mPrSoldOut : TextView
        lateinit var mPrTemporary : TextView
        lateinit var mBarcode: TextView
        lateinit var mOption : TextView
        lateinit var mStock : TextView
        lateinit var button : Button
    }

}
