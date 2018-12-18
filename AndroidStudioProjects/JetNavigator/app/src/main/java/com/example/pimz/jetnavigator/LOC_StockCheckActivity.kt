package com.example.pimz.jetnavigator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.invoice_listview.*

class LOC_StockCheckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invoice_listview)

        var value = intent.extras.get("title")
        title = "LOC재고관리 > " + value

        val item = Array(20,{ i -> "$i + list" })


    }
}