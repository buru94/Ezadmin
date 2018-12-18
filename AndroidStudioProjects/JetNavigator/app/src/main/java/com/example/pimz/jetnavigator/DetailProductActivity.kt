package com.example.pimz.jetnavigator

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.View
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_product.*
import java.lang.Exception
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import kotlinx.android.synthetic.main.activity_trans.*
import kr.co.ezapps.ezsmarty.Data
import kr.co.ezapps.ezsmarty.Service
import retrofit2.Call
import retrofit2.Response
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class DetailProductActivity : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        var mProductId = intent.extras.get("mProductId") as String
        var mEnable_sale = intent.extras.get("mEnable_sale") as String
        var mImageUrl = intent.extras.get("mImageUrl") as String


        doGetImage(mImageUrl, mEnable_sale)

        doGetDeatilPost(mProductId)


        DETAIL_PRODUCT_ACTIVITY_SWIFE_REFRESH_LAYOUT.setOnRefreshListener(object :
            SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {


                val intent = getIntent()
                finish()
                startActivity(intent)

                DETAIL_PRODUCT_ACTIVITY_SWIFE_REFRESH_LAYOUT.isRefreshing = false
            }
        })

        DETAIL_PRODUCT_ACTIVITY_APP_BAR_LAYOUT.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { p0, p1 ->
            DETAIL_PRODUCT_ACTIVITY_SWIFE_REFRESH_LAYOUT.isEnabled = p1 == 0
        })

        DETAIL_PRODUCT_ACTIVITY_LISTVIEW.setOnTouchListener { v, event ->
            DETAIL_PRODUCT_ACTIVITY_SCROLLVIEW.requestDisallowInterceptTouchEvent(true)
            false
        }
    }

    fun doGetImage(mImageUrl: String, mEnable_sale: String) {
        Picasso.get().load(mImageUrl).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW, object : Callback {
            override fun onSuccess() {
                Picasso.get().load(mImageUrl).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
                DETAIL_PRODUCT_ACTIVITY_SOLDOUT_TEXTVIEW.visibility = View.GONE
                DETAIL_PRODUCT_MAIN_IMAGEVIEW.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY);

                if (mEnable_sale == "0") {
                    DETAIL_PRODUCT_ACTIVITY_SOLDOUT_TEXTVIEW.visibility = View.VISIBLE
                    DETAIL_PRODUCT_MAIN_IMAGEVIEW.setColorFilter(Color.parseColor("#6E6E6E"), PorterDuff.Mode.MULTIPLY);
                }

            }

            override fun onError(e: Exception?) {
                Picasso.get().load(R.drawable.ezadmin_title).resize(100, 100).into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
                DETAIL_PRODUCT_ACTIVITY_SOLDOUT_TEXTVIEW.visibility = View.GONE
                DETAIL_PRODUCT_MAIN_IMAGEVIEW.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY);

                if (mEnable_sale == "0") {
                    DETAIL_PRODUCT_ACTIVITY_SOLDOUT_TEXTVIEW.visibility = View.VISIBLE
                    DETAIL_PRODUCT_MAIN_IMAGEVIEW.setColorFilter(Color.parseColor("#6E6E6E"), PorterDuff.Mode.MULTIPLY);
                }

            }

        })
        //Picasso.get().load(mImageUrl).error(R.drawable.ezadmin_title_logo).fit().into(DETAIL_PRODUCT_MAIN_IMAGEVIEW)
    }

    private fun doGetDeatilPost(PrId: String) {
        progressON(this, "Loading...")
        try {
            val mProductId = PrId
            val mUrlAuthcode = URLEncoder.encode(Session.getInstance().authCode, "UTF-8")
            val mUrlProductId = URLEncoder.encode(mProductId, "UTF-8")
            val mURL = "api/function.php"
            val map = HashMap<String, String>()




            map["ACTION"] = URLFactory.GetProductDetail
            map["AUTHCODE"] = mUrlAuthcode
            map["VERSION"] = "v1"
            map["PRODUCT_ID"] = mUrlProductId

            doGetProductDetail(map, mURL)

        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }

    fun doGetProductDetail(input: HashMap<String, String>, Url: String) {

        val retrofitService = retrofit.create(Service::class.java)
        val call = retrofitService.postData(input, Url)
        call.enqueue(object : retrofit2.Callback<Data> {

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                val mProduct = response.body()!!.productInfo.toString()

                val parser = JsonParser()
                val mProductInfo: JsonObject? = parser.parse(mProduct) as JsonObject?
                val mOptionsArray: JsonArray = mProductInfo!!.get("options") as JsonArray

                DETAIL_PRODUCT_ACTIVITY_PRODUCT_ID_TEXTVIEW.text =
                        mProductInfo.get("product_id").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME_TEXTVIEW.text =
                        mProductInfo.get("name").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME_TEXTVIEW.isSelected = true
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME.text = mProductInfo.get("name").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_LINK_TEXTVIEW.text =
                        mProductInfo.get("link_id").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_ORIGIN_TEXTVIEW.text =
                        mProductInfo.get("origin").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_SUPPLY_TEXTVIEW.text =
                        mProductInfo.get("supply_name").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_LOC_TEXTVIEW.text =
                        mProductInfo.get("location").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_MADE_TEXTVIEW.text =
                        mProductInfo.get("maker").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_TAG_TEXTVIEW.text =
                        mProductInfo.get("tags").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_CATEGORY_TEXTVIEW.text =
                        mProductInfo.get("str_category").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_NAME.text = mProductInfo.get("name").toString().replace("\"", "")
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_PRICE.text = toNumFormat(mProductInfo.get("shop_price").toString().replace("\"", "")) + "원"
                DETAIL_PRODUCT_ACTIVITY_PRODUCT_DATE.text = mProductInfo.get("reg_date").toString().replace("\"", "")


                var item: ArrayList<ArrayList<Any>?> = ArrayList()
                for (i in 0 until mOptionsArray.size()) {
                    var mOptionsObj: JsonObject = mOptionsArray.get(i).asJsonObject

                    var array: ArrayList<Any> = arrayListOf(
                        mOptionsObj.get("bacode").toString().replace("\"", "")
                        , mOptionsObj.get("enable_stock").toString().replace("\"", "")
                        , mOptionsObj.get("is_cancel").toString().replace("\"", "")
                        , mOptionsObj.get("is_change").toString().replace("\"", "")
                        , mOptionsObj.get("location").toString().replace("\"", "")
                        , mOptionsObj.get("options").toString().replace("\"", "")
                        , mOptionsObj.get("product_id").toString().replace("\"", "")
                        , mOptionsObj.get("product_name").toString().replace("\"", "")
                        , mOptionsObj.get("qty").toString().replace("\"", "")
                    )

                    item.add(array)

                }
                TRANS_ACTIVITY_LIST_VIEW.adapter = DetailProductListAdapter(applicationContext, item)

                progressOFF()
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                // 실패

                Log.getStackTraceString(t)
            }
        })

    }
}