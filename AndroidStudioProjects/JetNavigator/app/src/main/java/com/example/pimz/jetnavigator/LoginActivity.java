package com.example.pimz.jetnavigator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kr.co.ezapps.ezsmarty.Data;
import kr.co.ezapps.ezsmarty.Service;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private EditText LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT;
    private EditText LOGIN_ACTIVITY_ID_EDIT_TEXT;
    private EditText LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT;
    private Button LOGIN_ACTIVITY_LOGIN_BTN;
    private ImageView LOGIN_ACTIVITY_LOGO_IMAGE_VIEW;


    private View.OnFocusChangeListener listener;

    private CustomDialogActivity dialog;
    private BaseActivity baseActivity;
    Gson gson = new GsonBuilder() .setLenient() .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URLFactory.serverUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT = (EditText) findViewById(R.id.LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT);
        LOGIN_ACTIVITY_ID_EDIT_TEXT = (EditText) findViewById(R.id.LOGIN_ACTIVITY_ID_EDIT_TEXT);
        LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT = (EditText) findViewById(R.id.LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT);
        LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        LOGIN_ACTIVITY_LOGIN_BTN = (Button) findViewById(R.id.LOGIN_ACTIVITY_LOGIN_BTN);
        LOGIN_ACTIVITY_LOGO_IMAGE_VIEW = (ImageView) findViewById(R.id.LOGIN_ACTIVITY_LOGO_IMAGE_VIEW);

        doOpen();



    }



    public void doClick(View v) {
        try {
            String mDomain = LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT.getText().toString();
            String mId = LOGIN_ACTIVITY_ID_EDIT_TEXT.getText().toString();
            String mPassword = LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT.getText().toString();
            String mUUID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

            final String mUrlDomain = URLEncoder.encode(mDomain, "UTF-8");
            final String mUrlId = URLEncoder.encode(mId, "UTF-8");
            final String mUrlPassword = URLEncoder.encode(mPassword, "UTF-8");
            final String mToken = "domain=" + mUrlDomain + "|id=" + mUrlId + "|password=" + mUrlPassword + "|uuid=" + mUUID;
            final HashMap<String, String> map = new HashMap<>();
            map.put("ACTION", URLFactory.CheckLogin);
            map.put("VERSION", "v1");
            map.put("TOKEN", mToken);
            final String mURL = "api/function.php";

            doPost(map, mURL);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    public void doPost(final HashMap<String, String> input, final String Url) {

        final Service retrofitService = retrofit.create(Service.class);
        final Call<Data> call = retrofitService.postData(input, Url);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                // 성공

                if (response.body().getErrorcode() == 0) {

                    JsonParser parser = new JsonParser();
                    JsonElement mConfigElement;
                    JsonElement mSvcElement;
                    mConfigElement = parser.parse(response.body().getConfig().toString());

                    JsonObject mConfigJson = mConfigElement.getAsJsonObject();
                    Session.getInstance().setPos_product_trans(mConfigJson.get("pos_product_trans").getAsBoolean());
                    Session.getInstance().setPos_ignore_hold(mConfigJson.get("pos_ignore_hold").getAsBoolean());


                    String mConvertSvc = response.body().getSvc().toString().replaceFirst(" ", "");
                    Log.d("mSvc", response.body().getSvc().toString());
                    String mConvertSvc2 = mConvertSvc.replaceAll(" ", "-");
                    String mFinalSvc = mConvertSvc2.replaceAll(":", "-");

                    mSvcElement = parser.parse(mFinalSvc);
                    Log.d("element", mSvcElement.toString());
                    JsonObject mSvcJson = mSvcElement.getAsJsonObject();
                    Session.getInstance().setSvc_enddate(mSvcJson.get("svc_enddate").toString());
                    Session.getInstance().setSvc_version((mSvcJson.get("svc_version").toString()));
                    Log.d("mSvcJson", mSvcJson.toString());

                    Session.getInstance().setAuthCode(response.body().getAuthcode());
                    Session.getInstance().setUserName(response.body().getUsername());
                    doSaveDomain();

                    String mDomain = LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT.getText().toString();
                    String mId = LOGIN_ACTIVITY_ID_EDIT_TEXT.getText().toString();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Domain", mDomain);
                    intent.putExtra("Id", mId);
                    startActivity(intent);
                    finish();

                }


                if (response.body().getErrorcode() > 0) {
                    String ID = "WRONG_INFO";
                    Dialog(ID);
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                // 실패
                String ID = "NOT_CONNECT_NETWORK";
                Dialog(ID);
                Log.d("Fail", "FAIL");
            }
        });
    }

    //public void onBackPressed(){
      //  baseActivity.onBackPressed();
    //}

    public void Dialog(String ID){
        CustomDialogActivity customDialog = new CustomDialogActivity(LoginActivity.this);

        // 커스텀 다이얼로그를 호출한다.
        // 커스텀 다이얼로그의 결과를 출력할 TextView를 매개변수로 같이 넘겨준다.
        customDialog.callFunction(ID);
    }

    private void doOpen(){

        SharedPreferences pref=getSharedPreferences("pref",Activity.MODE_PRIVATE);
        LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT.setText(pref.getString("domain_save",""));
        LOGIN_ACTIVITY_ID_EDIT_TEXT.setText(pref.getString("id_save",""));
        LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT.setText(pref.getString("pw_save",""));
    }
    private void doSaveDomain(){
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        EditText mEXDomain=(EditText)findViewById(R.id.LOGIN_ACTIVITY_DOMAIN_EDIT_TEXT);
        EditText mEXId=(EditText)findViewById(R.id.LOGIN_ACTIVITY_ID_EDIT_TEXT);
        EditText mEXPassword=(EditText)findViewById(R.id.LOGIN_ACTIVITY_PASSWORD_EDIT_TEXT);

        //SharedPreferences에 각 아이디를 지정하고 EditText 내용을 저장한다.
        editor.putString("domain_save", mEXDomain.getText().toString());
        editor.putString("id_save", mEXId.getText().toString());
        editor.putString("pw_save", mEXPassword.getText().toString());
        editor.commit();

    }

    public void onStop(){
        super.onStop();


    }

}
