package com.example.pimz.jetnavigator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.invoice_listview.*
import kotlinx.android.synthetic.main.invoice_listview_item.*


class StockCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invoice_listview)

        var value = intent.extras.get("title")
        title = "재고관리 > " + value


        val item = Array(20,{ i -> "$i + list" })

        INVOICE_LISTVIEW.adapter = ListViewAdapter(this,item)
    }
}