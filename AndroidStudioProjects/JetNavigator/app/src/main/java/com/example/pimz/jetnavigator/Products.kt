package com.example.pimz.jetnavigator

import android.support.v7.app.AppCompatActivity


class Products : AppCompatActivity(){
    private var Name: String? = null
    private var Product_id: String? = null
    private var Options: String? = null
    private var Enable_sale: String? = null
    private var Imageurl: String? = null
    private var barcode: String? = null

    fun getName(): String? { return Name }
    fun setName(Name: String){ this.Name = Name }

    fun getProduct_id(): String? { return Product_id }
    fun setProduct_id(Product_id: String){ this.Product_id = Product_id }

    fun getOptions():String? { return Options }
    fun setOptions(Options: String){ this.Options = Options }

    fun getEnable_sale(): String? { return Enable_sale }
    fun setEnable_sale(Enable_sale: String){ this.Enable_sale = Enable_sale }

    fun getImageurl(): String? { return Imageurl }
    fun setImageurl(Imageurl: String){ this.Imageurl = Imageurl }

    fun getbarcode(): String? { return barcode }
    fun setbarcode(barcode : String){ this.barcode = barcode }




    }