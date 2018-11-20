package com.example.pimz.jetnavigator

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import com.PointMobile.PMSyncService.BluetoothChatService
import com.PointMobile.PMSyncService.SendCommand
import com.example.pimz.jetnavigator.MainActivity.Companion.mChatService
import kotlinx.android.synthetic.main.activity_loc_stock_in.*


class LOC_StockInActivity : AppCompatActivity() {



    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

                StockManageFragment.MESSAGE_BARCODE -> {

                    val BarcodeBuff = msg.obj as ByteArray

                    var Barcode= ""

                    Barcode = String(BarcodeBuff, 0, msg.arg1)
                    if (Barcode.length != 0) {
                        STOCK_LOC_STOCK_IN_BARCODE_EDITTEXT.setText(Barcode)
                    }
                }
            }


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loc_stock_in)

        SendCommand.SendCommandInit(mChatService, mHandler)

        var value = intent.extras.get("title")
        title = "LOC재고관리 > " + value

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
        SendCommand.SendCommandInit(mChatService, mHandler)
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mChatService != null) {

            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService!!.getState() === BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService!!.start()
            }
        }
        if (D) Log.e(TAG, "--- ON RESUME ---")
    }

companion object {
    private val D = true
    private val TAG = "MainActivity"
}

}