package localizae.net.br.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import localizae.net.br.model.BaseEntity;

public class Beacon extends BaseEntity implements Serializable {

    @SerializedName("xCoordinate")
    @Expose
    private double xCoordinate;
    @SerializedName("yCoordinate")
    @Expose
    private double yCoordinate;
    @SerializedName("mac")
    @Expose
    private String mac;
    private byte[] scanRecord;
    private int txPower;
    private int RSSI;

    public Beacon(double xCoordinate, double yCoordinate, String mac) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.mac = mac;
    }

    public String getMAC() {
        return mac;
    }

    public void setMAC(String mac) {
        this.mac = mac;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getRSSI() {
        return RSSI;
    }

    public void setRSSI(int RSSI) {
        this.RSSI = RSSI;
    }

    public int getTxPower() {
        return txPower;
    }

    public void setTxPower(int txPower) {
        this.txPower = txPower;
    }

    public byte[] getScanRecord() {
        return scanRecord;
    }

    public void setScanRecord(byte[] scanRecord) {
        this.scanRecord = scanRecord;
    }
}
