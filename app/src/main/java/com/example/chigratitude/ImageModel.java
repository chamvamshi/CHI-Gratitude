package com.example.chigratitude;

import android.net.Uri;

public class ImageModel {

    String imageurl;
    String document;
    public ImageModel() {

    }

    public ImageModel(String document) {
        this.document = document;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setImageURI(Uri result) {

    }
}
