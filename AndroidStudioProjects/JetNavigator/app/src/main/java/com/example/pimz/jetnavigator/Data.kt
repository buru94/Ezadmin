package kr.co.ezapps.ezsmarty

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject
import java.util.*


public class Data {

    var authcode: String? = null
    var errorcode: Int? = null
    var message: String? = null
    var username: String? = null
    var config: Any ?= null
    var svc: Any? = null
    var img_500: Any? = null
    var name: Any?= null
    var product_id: Any? = null
    lateinit var products: JsonArray
    lateinit var productInfo: JsonObject
    var barcode: Any? = null


}
