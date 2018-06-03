package com.example.erikson.restaurant.ItemMenu;

import android.graphics.Bitmap;

public class ItemMenuStructure {
    private String foodname;
    private String urlimg;
    private int quantity;
    private Boolean deleteui;
    private String id;
    private Bitmap img;
    public ItemMenuStructure (String foodname, String urlimg, int quantity, String id){
        this.foodname = foodname;
        this.urlimg = urlimg;
        this.quantity = quantity;
        this.id = id;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public void setQuanty(int quantity) {
        this.quantity = quantity;
    }

    public void setDeleteui(Boolean deleteui) {
        this.deleteui = deleteui;
    }

    public Boolean getDeleteui() {
        return this.deleteui;
    }

    public Bitmap getImg() {
        return this.img;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getFoodname() {
        return this.foodname;
    }

    public String getUrlimg() {
        return this.urlimg;
    }

    public String getId() {
        return this.id;
    }
}
