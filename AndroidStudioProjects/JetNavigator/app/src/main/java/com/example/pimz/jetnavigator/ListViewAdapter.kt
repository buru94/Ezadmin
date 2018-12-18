package com.example.pimz.jetnavigator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class ListViewAdapter(context: Context, item: ArrayList<ArrayList<Any>?>) : BaseAdapter(){
    private val mContext = context
    private val mItem = item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        lateinit var viewHolder : ViewHolder
        var view = convertView
        if (view == null){
            viewHolder = ViewHolder()
            view = LayoutInflater.from(mContext).inflate(R.layout.search_listview_item,parent,false)
            viewHolder.mSeq = view.findViewById(R.id.SEARCH_LIST_PRSEQ_TEXTVIEW)
            viewHolder.mPrId = view.findViewById(R.id.SEARCH_LIST_PRID_TEXTVIEW)
            viewHolder.mPrname= view.findViewById(R.id.SEARCH_LIST_PRNAME_TEXTVIEW)
            viewHolder.mBarcode = view.findViewById(R.id.SEARCH_LIST_BARCODE_TEXTVIEW)
            viewHolder.mOption = view.findViewById(R.id.SEARCH_LIST_OPTION_TEXTVIEW)
            view.tag = viewHolder
            viewHolder.mSeq.text = (position + 1).toString()
            viewHolder.mPrId.text = mItem[position]!![6].toString()
            viewHolder.mPrname.text = mItem[position]!![7].toString()
            viewHolder.mBarcode.text = mItem[position]!![0].toString()
            viewHolder.mOption.text = mItem[position]!![5].toString()
            return view
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.mSeq.text = (position + 1).toString()
        viewHolder.mPrId.text = mItem[position]!![6].toString()
        viewHolder.mPrname.text = mItem[position]!![7].toString()
        viewHolder.mBarcode.text = mItem[position]!![0].toString()
        viewHolder.mOption.text = mItem[position]!![5].toString()
        return  view
    }

    override fun getItem(position: Int) = mItem[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mItem.size

    inner class ViewHolder{
        lateinit var mSeq : TextView
        lateinit var mPrId: TextView
        lateinit var mPrname : TextView
        lateinit var mBarcode: TextView
        lateinit var mOption : TextView

        lateinit var button : Button
    }
}
