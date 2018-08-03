package com.example.baeza.fatherhome.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favoriteEntry")
public class FavoriteEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private String address;
    private String data;

    @Ignore
    public FavoriteEntry(String description, String address, String data){
        this.description = description;
        this.address = address;
        this.data = data;
    }

    public FavoriteEntry(int id, String description, String address, String data){
        this.id = id;
        this.description = description;
        this.address = address;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
