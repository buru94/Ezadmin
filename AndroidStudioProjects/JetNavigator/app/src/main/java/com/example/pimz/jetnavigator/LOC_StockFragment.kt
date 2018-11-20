package com.example.pimz.jetnavigator

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_loc_stock_manage.*

class LOC_StockFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loc_stock_manage, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     LOC_STOCK_FRAGMENT_LOC_IN_DIRECTON_BTN.setOnClickListener{
         val intent = Intent(context, LOC_StockInDirectionActivity::class.java)
         intent.putExtra("title","LOC입고지시")
         startActivity(intent)
     }
     LOC_STOCK_FRAGMENT_LOC_OUT_DIRECTION_BTN.setOnClickListener{
         val intent = Intent(context, LOC_StockOutDirectionActivity::class.java)
         intent.putExtra("title","LOC출고지시")
         startActivity(intent)
     }
     LOC_STOCK_FRAGMENT_LOC_STOCK_IN_BTN.setOnClickListener {
         val intent = Intent(context, LOC_StockInActivity::class.java)
         intent.putExtra("title","LOC재고입고")
         startActivity(intent)
     }
     LOC_STOCK_FRAGMENT_LOC_STOCK_OUT_BTN.setOnClickListener{
         val intent = Intent(context, LOC_StockInActivity::class.java)
         intent.putExtra("title","LOC재고출고")
         startActivity(intent)
     }
     LOC_STOCK_FRAGMENT_LOC_STOCK_CHECK_BTN.setOnClickListener {
         val intent = Intent(context, LOC_StockCheckActivity::class.java)
         intent.putExtra("title","LOC재고실사")
         startActivity(intent)
     }

    }
}