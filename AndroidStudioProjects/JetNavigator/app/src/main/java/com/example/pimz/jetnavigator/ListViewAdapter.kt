package com.example.pimz.jetnavigator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ListViewAdapter(context: Context, item : Array<String>) : BaseAdapter(){
    private val mContext = context
    private val mItem = item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        lateinit var viewHolder : ViewHolder
        var view = convertView
        if (view == null){
            viewHolder = ViewHolder()
            view = LayoutInflater.from(mContext).inflate(R.layout.search_listview_item,parent,false)
            viewHolder.mPrname= view.findViewById(R.id.SEARCH_LIST_PRNAME_TEXTVIEW)
            viewHolder.mBarcode = view.findViewById(R.id.SEARCH_LIST_BARCODE_TEXTVIEW)
            viewHolder.mOption = view.findViewById(R.id.SEARCH_LIST_OPTION_TEXTVIEW)
            view.tag = viewHolder
            viewHolder.mPrname.text = mItem[position]
            viewHolder.mBarcode.text = mItem[position]
            viewHolder.mOption.text = mItem[position]
            return view
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.mPrname.text = mItem[position]
        viewHolder.mBarcode.text = mItem[position]
        viewHolder.mOption.text = mItem[position]
        return  view
    }

    override fun getItem(position: Int) = mItem[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mItem.size

    inner class ViewHolder{
        lateinit var mPrname : TextView
        lateinit var mBarcode: TextView
        lateinit var mOption : TextView

        lateinit var button : Button
    }
}
