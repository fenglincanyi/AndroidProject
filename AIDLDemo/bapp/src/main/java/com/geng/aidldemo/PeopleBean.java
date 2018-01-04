package com.geng.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by geng on 2018/1/3.
 */

public class PeopleBean implements Parcelable {

    public String name;
    public int age;
    public String sex;

    @Override
    public String toString() {
        return "PeopleBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeString(this.sex);
    }

    public PeopleBean() {
    }

    protected PeopleBean(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
        this.sex = in.readString();
    }

    public static final Creator<PeopleBean> CREATOR = new Creator<PeopleBean>() {
        @Override
        public PeopleBean createFromParcel(Parcel source) {
            return new PeopleBean(source);
        }

        @Override
        public PeopleBean[] newArray(int size) {
            return new PeopleBean[size];
        }
    };
}
