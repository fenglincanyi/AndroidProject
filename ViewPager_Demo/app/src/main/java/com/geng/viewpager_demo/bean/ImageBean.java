package com.geng.viewpager_demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: gengjiarong
 * Date: 2016/7/28
 */
public class ImageBean implements Parcelable {

    private String name;
    private String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    protected ImageBean(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
        @Override
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        @Override
        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
}
