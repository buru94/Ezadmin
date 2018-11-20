package com.example.pimz.jetnavigator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StockEzChainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invoice_listview)

        var value = intent.extras.get("title")
        title = "재고관리 > " + value
    }
}