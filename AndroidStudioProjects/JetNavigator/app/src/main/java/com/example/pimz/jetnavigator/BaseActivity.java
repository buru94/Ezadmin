package com.example.pimz.jetnavigator;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.rtp.RtpStream;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.PointMobile.PMSyncService.BluetoothChatService;
import com.PointMobile.PMSyncService.SendCommand;
import kr.co.ezapps.ezsmarty.Data;
import kr.co.ezapps.ezsmarty.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;

import static com.example.pimz.jetnavigator.MainActivity.mChatService;


public class BaseActivity extends AppCompatActivity{

    AppCompatDialog progressDialog;


    private long time = 0;
    private static final boolean D = true;
    public static final int MESSAGE_BARCODE = 2;
    protected final String TAG = getClass().getSimpleName();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URLFactory.serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (D)
            Log.e(TAG, "--- ON PAUSE ---");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (D) Log.e(TAG, "+++ ON RESUME +++");
        SendCommand.SendCommandInit(mChatService, mHandler);
        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mChatService != null) {

            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
        if (D) Log.e(TAG, "--- ON RESUME ---");
    }

    public static final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_BARCODE:
                    byte[] BarcodeBuff = (byte[]) msg.obj;
                    String Barcode = "";
                    Barcode = new String(BarcodeBuff, 0, msg.arg1);
                    if (Barcode.length() != 0) {
                        //   textview.setText(Barcode);
                    }
                    break;
            }
        }
    };

    public void progressON(Activity activity, String message) {

        if (activity == null || activity.isFinishing()) {
            return;
        }



        if (progressDialog != null && progressDialog.isShowing()) {
            progressSET(message);
        } else {

            progressDialog = new AppCompatDialog(activity);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.activity_progress_dialog);
            progressDialog.show();
            Log.d("PROGRESS", "11111111111111");
        }


        final ImageView img_loading_frame = (ImageView) progressDialog.findViewById(R.id.iv_frame_loading);
        final AnimationDrawable frameAnimation = (AnimationDrawable) img_loading_frame.getBackground();
        img_loading_frame.post(new Runnable() {
            @Override
            public void run() {
                frameAnimation.start();
            }
        });

        TextView tv_progress_message = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }


    }

    public void progressSET(String message) {

        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }


        TextView tv_progress_message = (TextView) progressDialog.findViewById(R.id.tv_progress_message);
        if (!TextUtils.isEmpty(message)) {
            tv_progress_message.setText(message);
        }

    }

    public void progressOFF() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public static String toNumFormat(String num) {
        if (num.equals(""))
            num = "0";

        int number = Integer.parseInt(num.replace(",", ""));

        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(number);
    }

    public static String toString(String num) {
        if (num.equals(""))
            num = "0";

     String  number = String.valueOf(num.replace(",", ""));


        return number;
    }
}

