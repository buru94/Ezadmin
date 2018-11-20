package com.example.pimz.jetnavigator;


import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

public class Session  extends AppCompatActivity {
    private static Session session;


    private Session() {
    }

    public static Session getInstance() {
        if (session == null) {
            session = new Session();
        }
        return session;
    }

    private String AuthCode = null;
    private Boolean Pos_product_trans = null;
    private Boolean Pos_ignore_hold = null;
    private String Svc_enddate = null;
    private String Svc_version = null;
    private String UserName = null;
    private ArrayList ImageUrl = null;
    private ArrayList Name = null;
    private ArrayList Product_id = null;
    private ArrayList Options = null;
    private Integer Page= null;
    private ArrayList Enable_sale = null;




    public String getAuthCode() {
        return AuthCode;
    }

    public void setAuthCode(String AuthCode) {
        this.AuthCode = AuthCode;
    }

    public Boolean getPos_product_trans() {
        return Pos_product_trans;
    }

    public void setPos_product_trans(Boolean pos_product_trans) {
        this.Pos_product_trans = Pos_product_trans;
    }

    public Boolean getPos_ignore_hold() {
        return Pos_ignore_hold;
    }

    public void setPos_ignore_hold(Boolean Pos_ignore_hold) {
        this.Pos_ignore_hold = Pos_ignore_hold;
    }

    public String getSvc_enddate() {
        return Svc_enddate;
    }

    public void setSvc_enddate(String Svc_enddate) {
        this.Svc_enddate = Svc_enddate;
    }

    public String getSvc_version() {
        return Svc_version;
    }

    public void setSvc_version(String Svc_version) {
        this.Svc_version = Svc_version;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) { this.UserName = UserName; }

    public ArrayList getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(ArrayList ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public ArrayList getName() {
        return Name;
    }

    public void setName(ArrayList Name) {
        this.Name = Name;
    }

    public ArrayList getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(ArrayList Product_id) {
        this.Product_id = Product_id;
    }

    public Integer getPage() {
        return Page;
    }

    public void setPage(Integer Page) {
        this.Page = Page;
    }

    public ArrayList getEnable_sale() {
        return Enable_sale;
    }

    public void setEnable_sale(ArrayList Enable_sale) {
        this.Enable_sale = Enable_sale;
    }




}