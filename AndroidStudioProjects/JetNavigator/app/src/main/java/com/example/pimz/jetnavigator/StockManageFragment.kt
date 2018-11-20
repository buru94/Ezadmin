package com.example.pimz.jetnavigator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.view.*
import com.PointMobile.PMSyncService.BluetoothChatService
import com.PointMobile.PMSyncService.SendCommand
import kotlinx.android.synthetic.main.fragment_stock_manage.*

class StockManageFragment: Fragment() {



    val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {

                MESSAGE_BARCODE -> {

                    val BarcodeBuff = msg.obj as ByteArray

                    var Barcode = ""


                    Barcode = String(BarcodeBuff, 0, msg.arg1)
                    if (Barcode.length != 0) {
                      //  LIST_TEXT_VIEW.text = Barcode
                    }
                }
            }


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        MainActivity.mChatService = BluetoothChatService(context, mHandler)
        SendCommand.SendCommandInit(MainActivity.mChatService, mHandler)

        return inflater.inflate(R.layout.fragment_stock_manage, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        STOCK_FRAGMENT_STOCK_IN_BTN.setOnClickListener{
            val intent =  Intent (context, StockInOutActivity::class.java)
            intent.putExtra("title","재고입고")
            startActivity(intent)
        }
        STOCK_FRAGMENT_STOCK_OUT_BTN.setOnClickListener{
            val intent =  Intent (context, StockInOutActivity::class.java)
            intent.putExtra("title","재고출고")
            startActivity(intent)
        }
        STOCK_FRAGMENT_STOCK_CHECK_BTN.setOnClickListener{
            val intent =  Intent (context, StockCheckActivity::class.java)
            intent.putExtra("title","재고실사")
            startActivity(intent)
        }
        STOCK_FRAGMENT_STOCK_ARRANGE_BTN.setOnClickListener{
            val intent =  Intent (context, StockArrangeActivity::class.java)
            intent.putExtra("title","재고조정")
            startActivity(intent)

        }
        STOCK_FRAGMENT_STOCK_LOG_BTN.setOnClickListener{
            val intent =  Intent (context, StockLogActivity::class.java)
            intent.putExtra("title","재고로그조회")
            startActivity(intent)
        }
        STOCK_FRAGMENT_EZCHAIN_TRANS_BTN.setOnClickListener{
            val intent =  Intent (context, StockEzChainActivity::class.java)
            intent.putExtra("title","이지체인배송처리")
            startActivity(intent)
        }

    }



     companion object {


        val MESSAGE_BARCODE = 2




    }


}



