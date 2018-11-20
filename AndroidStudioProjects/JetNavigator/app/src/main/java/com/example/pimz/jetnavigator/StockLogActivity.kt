package com.example.pimz.jetnavigator

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_stock_log.*
import kotlinx.android.synthetic.main.activity_stock_log_list_item.*
import kotlinx.android.synthetic.main.invoice_listview.*

class StockLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_log)

        var value = intent.extras.get("title")
        title = "재고관리 > " + value


        val item = Array(20,{ i -> "$i + list" })

        val header = layoutInflater.inflate(R.layout.activity_stock_log_list_header,null,false)

        STOCK_LOG_ACTIVITY_LIST_VIEW.adapter = ListViewAdapter(this,item)
        STOCK_LOG_ACTIVITY_LIST_VIEW.addHeaderView(header)


    }

}