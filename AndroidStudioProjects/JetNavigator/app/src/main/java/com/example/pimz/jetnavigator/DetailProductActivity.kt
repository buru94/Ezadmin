package com.example.pimz.jetnavigator

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_product.*
import java.lang.Exception


class DetailProductActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)


        /*
        val display = this.windowManager.defaultDisplay
        val pt = Point()
        display.getSize(pt)

        var height = pt.x
        var width = pt.y

        DETAIL_PRODUCT_ACTIVITY_APP_BAR_LAYOUT.layoutParams.height = height
        DETAIL_PRODUCT_ACTIVITY_APP_BAR_LAYOUT.layoutParams.width = width
        */

        var mPosition = intent.extras.get("position") as String
        var mProductName = intent.extras.get("mProductName") as String
        var mResponse = intent.extras.get("mProductInfo") as? String
        var mStatus = intent.extras.get("mStatus") as? String
        var mImageUrl = Session.getInstance().imageUrl[mPosition.toInt()].toString()

        DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME.text = mProductName

        Picasso.get().load(mImageUrl).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW, object : Callback {
            override fun onSuccess() {
                if (mStatus != "1") {
                    Picasso.get().load(mImageUrl).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
                    DETAIL_PRODUCT_ACTIVITY_SOLDOUT_TEXTVIEW.visibility = View.VISIBLE
                    DETAIL_PRODUCT_MAIN_IMAGEVIEW.setColorFilter(Color.parseColor("#6E6E6E"), PorterDuff.Mode.MULTIPLY);
                }
                Picasso.get().load(mImageUrl).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
            }

            override fun onError(e: Exception?) {
                Picasso.get().load(R.drawable.ezadmin_title).resize(100, 100).into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
            }

        })
        //Picasso.get().load(mImageUrl).error(R.drawable.ezadmin_title_logo).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
        Log.d("Asdasd", mImageUrl)

        val parser = JsonParser()
        val mProductInfo: JsonObject? = parser.parse(mResponse) as JsonObject?
        val mOptionsArray: JsonArray = mProductInfo!!.get("options") as JsonArray

        DETAIL_PRODUCT_ACTIVITY_PRODUCT_ID_TEXTVIEW.text = mProductInfo.get("product_id").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME_TEXTVIEW.text = mProductInfo.get("name").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_LINK_TEXTVIEW.text = mProductInfo.get("link_id").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_ORIGIN_TEXTVIEW.text = mProductInfo.get("origin").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_SUPPLY_TEXTVIEW.text =
                mProductInfo.get("supply_name").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_LOC_TEXTVIEW.text = mProductInfo.get("location").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_MADE_TEXTVIEW.text = mProductInfo.get("maker").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_TAG_TEXTVIEW.text = mProductInfo.get("tags").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_CATEGORY_TEXTVIEW.text =
                mProductInfo.get("str_category").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME.text = mProductInfo.get("name").toString().replace("\"", "")
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_PRICE.text = mProductInfo.get("shop_price").toString().replace("\"", "") + "원"
        DETAIL_PRODUCT_ACTIVITY_PRODUCT_DATE.text = mProductInfo.get("reg_date").toString().replace("\"", "")



        for (i in 0 until mOptionsArray!!.size()) {
            var mOptionsObj: JsonObject = mOptionsArray.get(i).asJsonObject

            var array: ArrayList<Any> = arrayListOf(
                mOptionsObj.get("product_id").toString()
                , mOptionsObj.get("options").toString()
                , mOptionsObj.get("enable_sale").toString()
                , mOptionsObj.get("use_temp_soldout").toString()
                , mOptionsObj.get("barcode").toString()
                , mOptionsObj.get("stock").toString()
            )

            item!!.add(array)

        }

        Log.d("옵션배열~~~~~", item.toString())

        val header = layoutInflater.inflate(R.layout.activity_detail_product_list_header, null, false)
        DETAIL_PRODUCT_ACTIVITY_LISTVIEW.addHeaderView(header)

        DETAIL_PRODUCT_ACTIVITY_LISTVIEW.adapter = DetailProductListAdapter(this, item)
        DETAIL_PRODUCT_ACTIVITY_LISTVIEW.setOnTouchListener { v, event ->
            DETAIL_PRODUCT_ACTIVITY_SCROLLVIEW.requestDisallowInterceptTouchEvent(true)
            false
        }
    }

    companion object {
        var item: ArrayList<ArrayList<Any>?> = ArrayList()
    }
}