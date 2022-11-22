package com.example.mygym_moSamy.DataBasesAndModels;

import android.os.Parcel;
import android.os.Parcelable;

public class Training implements Parcelable {
    private int id;
    private String name ;
    private String longDesc;
    private String shortDesc;
    private String imageUrl;

    public Training(int id, String name, String longDesc, String shortDesc, String imageUrl) {
        this.id = id;
        this.name = name;
        this.longDesc = longDesc;
        this.shortDesc = shortDesc;
        this.imageUrl = imageUrl;
    }

    public Training(String name, String longDesc, String shortDesc, String imageUrl) {
        this.name = name;
        this.longDesc = longDesc;
        this.shortDesc = shortDesc;
        this.imageUrl = imageUrl;
    }

    protected Training(Parcel in) {
        id = in.readInt();
        name = in.readString();
        longDesc = in.readString();
        shortDesc = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(longDesc);
        dest.writeString(shortDesc);
        dest.writeString(imageUrl);
    }
}
