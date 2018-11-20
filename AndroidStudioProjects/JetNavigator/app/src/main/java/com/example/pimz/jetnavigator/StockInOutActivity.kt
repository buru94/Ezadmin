package com.example.pimz.jetnavigator;

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log

import android.widget.ArrayAdapter
import com.PointMobile.PMSyncService.BluetoothChatService
import com.PointMobile.PMSyncService.SendCommand
import kotlinx.android.synthetic.main.activity_stock_in_out.*
import kotlinx.android.synthetic.main.activity_trans.*
import kotlinx.android.synthetic.main.app_bar_main.*


class StockInOutActivity:AppCompatActivity(){

    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

                StockManageFragment.MESSAGE_BARCODE -> {

                    val BarcodeBuff = msg.obj as ByteArray

                    var Barcode= ""

                    Barcode = String(BarcodeBuff, 0, msg.arg1)
                    if (Barcode.length != 0) {
                        STOCK_IN_OUT_ACTIVITY_INVOICE_SEARCH_EDIT_TEXT.setText(Barcode)
                    }
                }
            }


        }
    }

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_in_out)



        SendCommand.SendCommandInit(MainActivity.mChatService, mHandler)

        var value = intent.extras.get("title")
        title = "재고관리 > " + value
//        actionBar.subtitle = "pimz"

        val item = Array(20,{ i -> "$i + list" })

        STOCK_ACTIVITY_LIST_VIEW.adapter = ListViewAdapter(this,item)
        val header = layoutInflater.inflate(R.layout.search_list_view_header,null,false)

        STOCK_ACTIVITY_LIST_VIEW.addHeaderView(header)

    }
    public override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        if (D)
            Log.e(TAG, "--- ON PAUSE ---")
    }

    public override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
        if (D) Log.e(TAG, "+++ ON RESUME +++")
        SendCommand.SendCommandInit(MainActivity.mChatService, mHandler)
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (MainActivity.mChatService != null) {

            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (MainActivity.mChatService!!.getState() === BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                MainActivity.mChatService!!.start()
            }
        }
        if (D) Log.e(TAG, "--- ON RESUME ---")
    }

    companion object {
        private val D = true
        private val TAG = "MainActivity"
    }
}
