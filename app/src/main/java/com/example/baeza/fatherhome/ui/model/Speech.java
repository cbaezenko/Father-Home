package com.example.baeza.fatherhome.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Speech implements Parcelable {

    private String pastorName;
    private int pastorCode;
    private String imageUrl;
    private String title;
    private String description;
    private String audiolink;
    private String date;
    private int id;


    public Speech(String pastorName, int pastorCode, String imageUrl, String title,
                  String description, String audiolink, String date, int id){
        this.pastorName = pastorName;
        this.pastorCode = pastorCode;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.audiolink = audiolink;
        this.date = date;
        this.id = id;
    }

    public Speech(){}

    protected Speech(Parcel in) {
        pastorName = in.readString();
        pastorCode = in.readInt();
        imageUrl = in.readString();
        title = in.readString();
        description = in.readString();
        audiolink = in.readString();
        date = in.readString();
        id = in.readInt();
    }

    public static final Creator<Speech> CREATOR = new Creator<Speech>() {
        @Override
        public Speech createFromParcel(Parcel in) {
            return new Speech(in);
        }

        @Override
        public Speech[] newArray(int size) {
            return new Speech[size];
        }
    };

    public String getPastorName() {
        return pastorName;
    }

    public void setPastorName(String pastorName) {
        this.pastorName = pastorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAudiolink() {
        return audiolink;
    }

    public void setAudiolink(String audiolink) {
        this.audiolink = audiolink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPastorCode() {
        return pastorCode;
    }

    public void setPastorCode(int pastorCode) {
        this.pastorCode = pastorCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pastorName);
        parcel.writeInt(pastorCode);
        parcel.writeString(imageUrl);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(audiolink);
        parcel.writeString(date);
        parcel.writeInt(id);
    }
}
