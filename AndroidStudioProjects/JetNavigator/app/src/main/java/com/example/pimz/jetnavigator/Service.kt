package kr.co.ezapps.ezsmarty

import retrofit2.Call
import retrofit2.http.*
import java.util.HashMap

interface Service {

    @FormUrlEncoded
    @POST
    fun postData(@FieldMap param: HashMap<String, String>, @Url path : String):Call<Data>


    @FormUrlEncoded
    @POST("kyj.php")
    fun postField(@Field("key") param: String): Call<Data>

}
