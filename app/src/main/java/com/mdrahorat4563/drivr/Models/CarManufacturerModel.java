package com.mdrahorat4563.drivr.Models;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michal Drahorat on 10/18/2017.
 */

public class CarManufacturerModel {

    public int getCarManufacturerId() {
        return manufacturerId;
    }

    public void setCarManufacturerId(int carId) {
        this.manufacturerId = carId;
    }

    public String getCarManufacturerName() {
        return manufacturerName;
    }

    public void setCarManufacturerName(String carName) {
        this.manufacturerName = carName;
    }

    private int manufacturerId;
    private String manufacturerName;
    private byte[] carPicture;

    public CarManufacturerModel() {}

    public CarManufacturerModel(int manufacturerId, String manufacturerName, byte[] carPicture)
    {
        this.manufacturerId = manufacturerId;
        this.manufacturerName = manufacturerName;
        this.carPicture = carPicture;
    }
}
