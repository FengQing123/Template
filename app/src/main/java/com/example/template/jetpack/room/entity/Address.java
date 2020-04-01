package com.example.template.jetpack.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 功能描述：
 * Created by gfq on 2020/4/1.
 */
@Entity
public class Address {

    @PrimaryKey(autoGenerate = true)
    private int addressId;

    @ColumnInfo(name = "addressName")
    private String addressName;

    public Address(int addressId, String addressName) {
        this.addressId = addressId;
        this.addressName = addressName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", addressName='" + addressName + '\'' +
                '}';
    }
}
