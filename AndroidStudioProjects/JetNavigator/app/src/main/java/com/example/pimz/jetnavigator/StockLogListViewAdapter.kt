package com.example.pimz.jetnavigator

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

class StockLogListViewAdapter(context: Context, item : Array<String>) : BaseAdapter(){
    private val mContext = context
    private val mItem = item

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        lateinit var viewHolder : ViewHolder
        var view = convertView
        if (view == null){
            viewHolder = ViewHolder()
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_stock_log_list_item,parent,false)
            viewHolder.mCrdate= view.findViewById(R.id.STOCK_LOG_ACTIVITY_LIST_CRDATE)
            viewHolder.mWork = view.findViewById(R.id.STOCK_LOG_ACTIVITY_LIST_WORK)
            viewHolder.mStock = view.findViewById(R.id.STOCK_LOG_ACTIVITY_LIST_STOCK)
            viewHolder.mMemo = view.findViewById(R.id.STOCK_LOG_ACTIVITY_LIST_MEMO)
            view.tag = viewHolder
            viewHolder.mMemo.text = mItem[position]
            viewHolder.mCrdate.text = mItem[position]
            viewHolder.mWork.text = mItem[position]
            viewHolder.mStock.text = mItem[position]
            return view
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.mMemo.text = mItem[position]
        viewHolder.mCrdate.text = mItem[position]
        viewHolder.mWork.text = mItem[position]
        viewHolder.mStock.text = mItem[position]
        return  view
    }

    override fun getItem(position: Int) = mItem[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mItem.size

    inner class ViewHolder{
        lateinit var mMemo : TextView
        lateinit var mCrdate: TextView
        lateinit var mWork : TextView
        lateinit var mStock : TextView

        lateinit var button : Button
    }
}
