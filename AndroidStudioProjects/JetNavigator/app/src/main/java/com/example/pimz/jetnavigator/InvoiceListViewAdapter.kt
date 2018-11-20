package com.example.pimz.jetnavigator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class InvoiceListViewAdapter(context: Context, item : Array<String>) : BaseAdapter(){
    private val mContext = context
    private val mItem = item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        lateinit var viewHolder : ViewHolder
        var view = convertView
        if (view == null){
            viewHolder = ViewHolder()
            view = LayoutInflater.from(mContext).inflate(R.layout.invoice_listview_item,parent,false)
            viewHolder.mMemo= view.findViewById(R.id.INVOICE_LISTVIEW_ITEM_CONTENT)
            viewHolder.mCrdate = view.findViewById(R.id.INVOICE_LISTVIEW_ITEM_STATUS)
            viewHolder.mOwner = view.findViewById(R.id.INVOICE_LISTVIEW_OWNER)
            viewHolder.mSeq = view.findViewById(R.id.INVOICE_LISTVIEW_ITEM_SEQ)
            view.tag = viewHolder
            viewHolder.mMemo.text = mItem[position]
            viewHolder.mCrdate.text = mItem[position]
            viewHolder.mOwner.text = mItem[position]
            viewHolder.mSeq.text = mItem[position]
            return view
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.mMemo.text = mItem[position]
        viewHolder.mCrdate.text = mItem[position]
        viewHolder.mOwner.text = mItem[position]
        viewHolder.mSeq.text = mItem[position]
        return  view
    }

    override fun getItem(position: Int) = mItem[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mItem.size

    inner class ViewHolder{
        lateinit var mMemo : TextView
        lateinit var mCrdate: TextView
        lateinit var mOwner : TextView
        lateinit var mSeq : TextView

        lateinit var button : Button
    }
}
