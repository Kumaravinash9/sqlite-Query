package de.lukaskoerfer.p2pchat.helper;

import android.graphics.Bitmap;

public class UserModel {
     private String name;
     private byte[] image;
     private String Wifi_mac_address;
     private String bluetooth_ssid;
     private int id;
     // constructor
    public UserModel(String name , byte[] image, String Wifi_mac_address, String bluetooth_ssid,int id)
    {
        this.name =name ;
        this.image=image;
        this.Wifi_mac_address=Wifi_mac_address;
        this.bluetooth_ssid=bluetooth_ssid;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getBluetooth_ssid(){
        return bluetooth_ssid;
    }

    public void setBluetooth_ssid(String bluetooth_ssid){
        this.bluetooth_ssid= bluetooth_ssid;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image=image;
    }

    public void setWifi_mac_address(String wifi_mac_address) {
        Wifi_mac_address = wifi_mac_address;
    }

    public String getWifi_mac_address() {
        return Wifi_mac_address;
    }
}

