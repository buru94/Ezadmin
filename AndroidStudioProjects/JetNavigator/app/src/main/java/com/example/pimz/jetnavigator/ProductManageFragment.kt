package com.example.pimz.jetnavigator

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_product_manage.*

class ProductManageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_manage, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        PRODUCT_MANAGE_FRAGMENT_LOC_APPOINT_BTN.setOnClickListener {
            val intent =  Intent (context, Product_LOC_Activity::class.java)
            intent.putExtra("title","LOC지정")
            startActivity(intent)
        }
        PRODUCT_MANAGE_FRAGMENT_LOC_MOVE_BTN.setOnClickListener {
            val intent =  Intent (context, Product_LOC_Activity::class.java)
            intent.putExtra("title","LOC이동")
            startActivity(intent)
        }
        PRODUCT_MANAGE_FRAGMENT_LOC_CONFIRM_BTN.setOnClickListener {
            val intent =  Intent (context, Product_LOC_ConfirmActivity::class.java)
            intent.putExtra("title","LOC확인")
            startActivity(intent)
        }
        PRODUCT_MANAGE_FRAGMENT_PRODUCT_LIST_BTN.setOnClickListener {
            val intent =  Intent (context, ProductListActivity::class.java)
            intent.putExtra("title","상품목록")
            startActivity(intent)
        }

        PRODUCT_MANAGE_FRAGMENT_PRODUCT_SEARCH_BTN.setOnClickListener {
            val intent =  Intent (context, ProductSearchActivity::class.java)
            intent.putExtra("title","상품조회")
            startActivity(intent)
        }
        }
    }
