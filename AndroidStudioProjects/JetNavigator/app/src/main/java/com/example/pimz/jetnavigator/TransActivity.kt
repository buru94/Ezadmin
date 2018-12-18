package com.example.pimz.jetnavigator

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Adapter
import com.PointMobile.PMSyncService.BluetoothChatService
import com.PointMobile.PMSyncService.SendCommand
import com.google.gson.JsonObject
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
import kotlinx.android.synthetic.main.activity_trans.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kr.co.ezapps.ezsmarty.Data
import kr.co.ezapps.ezsmarty.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class TransActivity: BaseActivity() {

    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

                StockManageFragment.MESSAGE_BARCODE -> {

                    val BarcodeBuff = msg.obj as ByteArray

                    var Barcode= ""

                    Barcode = String(BarcodeBuff, 0, msg.arg1)
                    if (Barcode.length != 0) {
                        TRANS_ACTIVITY_INVOICE_SEARCH_EDIT_TEXT.setText(Barcode)
                    }
                }
            }

        }
    }


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans)

        val toolbar = toolbar
        setSupportActionBar(toolbar)

        SendCommand.SendCommandInit(MainActivity.mChatService, mHandler)





        TRANS_ACTIVITY_INVOICE_SEARCH_BTN.setOnClickListener {
            var mTransNo = TRANS_ACTIVITY_INVOICE_SEARCH_EDIT_TEXT.text.toString()
            doTransNoPost(mTransNo)
        }

        TRANS_ACTIVITY_RESET_BTN.setOnClickListener {
            TRANS_ACTIVITY_INVOICE_SEARCH_EDIT_TEXT.setText("")
            item = ArrayList()
            TRANS_ACTIVITY_LIST_VIEW.adapter = null
        }


    }

    private fun doTransNoPost(mTransNo: String){
        try {

            val mUUID = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
            val mUrlAuthcode = URLEncoder.encode(Session.getInstance().authCode, "UTF-8")
            val mUrlType = 0
            val mUrlUserName = URLEncoder.encode(Session.getInstance().userName, "UTF-8")
            val mUrlTransNo = URLEncoder.encode(mTransNo, "UTF-8")
            val mURL = "api/function.php"
            val map = HashMap<String, String>()


            val mToken = "TYPE=$mUrlType|TRANSNO=$mUrlTransNo|WORKER=$mUrlUserName|UUID=$mUUID"


            map["ACTION"] = URLFactory.GetTransNoInfo
            map["AUTHCODE"] = mUrlAuthcode
            map["VERSION"] = "v1"
            map["TOKEN"] = mToken
        doGetTransNo(mTransNo,map,mURL)

        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
    }

    fun doGetTransNo(mTransNo: String, input: HashMap<String, String>, Url: String) {



        val retrofitService = retrofit.create(Service::class.java)
        val call = retrofitService.postData(input, Url)
        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                // 성공
                val TransProducts = response.body()!!.products


                item = ArrayList()
                for (i in 0 until TransProducts.size()) {
                    var mTransObj: JsonObject = TransProducts[i].asJsonObject

                    var array: ArrayList<Any> = arrayListOf(
                        mTransObj.get("barcode").toString().replace("\"", "")
                        , mTransObj.get("enable_stock").toString().replace("\"", "")
                        , mTransObj.get("is_cancel").toString().replace("\"", "")
                        , mTransObj.get("is_change").toString().replace("\"", "")
                        , mTransObj.get("location").toString().replace("\"", "")
                        , mTransObj.get("options").toString().replace("\"", "")
                        , mTransObj.get("product_id").toString().replace("\"", "")
                        , mTransObj.get("product_name").toString().replace("\"", "")
                        , mTransObj.get("qty").toString().replace("\"", "")

                    )

                    item.add(array)

                }
                TRANS_ACTIVITY_LIST_VIEW.adapter = ListViewAdapter(applicationContext, item)

                progressOFF()

            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                // 실패
                Log.getStackTraceString(t)
            }
        })
    }


    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        if (D)
            Log.e("MAINACTIVITY", "--- ON PAUSE ---")
    }

    override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
        if (D) Log.e("MainActivity", "+++ ON RESUME +++")
        SendCommand.SendCommandInit(MainActivity.mChatService, mHandler)
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (MainActivity.mChatService != null) {

            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (MainActivity.mChatService!!.state === BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                MainActivity.mChatService!!.start()
            }
        }
        if (D) Log.e("MainActivity", "--- ON RESUME ---")
    }

    companion object {
        private val D = true
        var item: ArrayList<ArrayList<Any>?> = ArrayList()
    }
}