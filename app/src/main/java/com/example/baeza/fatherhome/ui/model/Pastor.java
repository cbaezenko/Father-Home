package com.example.baeza.fatherhome.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pastor implements Parcelable {
    private String description, imageUrl, name;
    private int id;

    public Pastor(){}

    public Pastor(String description, int id, String imageUrl, String name){
        this.description = description;
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    protected Pastor(Parcel in) {
        description = in.readString();
        imageUrl = in.readString();
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<Pastor> CREATOR = new Creator<Pastor>() {
        @Override
        public Pastor createFromParcel(Parcel in) {
            return new Pastor(in);
        }

        @Override
        public Pastor[] newArray(int size) {
            return new Pastor[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeString(name);
        parcel.writeInt(id);
    }
}
