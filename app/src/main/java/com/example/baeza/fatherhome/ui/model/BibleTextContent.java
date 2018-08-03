package com.example.baeza.fatherhome.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BibleTextContent implements Parcelable{

    private String description;
    private String date;
    private String address;
    private int id;

    public BibleTextContent(){}

    public BibleTextContent(String address, String description, int id, String date){
        this.description = description;
        this.date = date;
        this.address = address;
        this.id = id;
    }

    protected BibleTextContent(Parcel in) {
        description = in.readString();
        date = in.readString();
        address = in.readString();
        id = in.readInt();
    }

    public static final Creator<BibleTextContent> CREATOR = new Creator<BibleTextContent>() {
        @Override
        public BibleTextContent createFromParcel(Parcel in) {
            return new BibleTextContent(in);
        }

        @Override
        public BibleTextContent[] newArray(int size) {
            return new BibleTextContent[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String addressBook) {
        this.address = addressBook;
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
        parcel.writeString(date);
        parcel.writeString(address);
        parcel.writeInt(id);
    }
}
