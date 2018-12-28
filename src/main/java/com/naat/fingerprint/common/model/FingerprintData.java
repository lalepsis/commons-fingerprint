package com.naat.fingerprint.common.model;

public class FingerprintData {

    private Fingerprint position;
    private int nfiqScore;

    //do not travel
    private transient byte[] wsqData;
    private transient byte[] imgData;

    public FingerprintData(Fingerprint position, int nfiqScore, byte[] wsqData, byte[] imgData) {
        this.position = position;
        this.nfiqScore = nfiqScore;
        this.wsqData = wsqData;
        this.imgData = imgData;
    }

    public Fingerprint getPosition() {
        return position;
    }

    public void setPosition(Fingerprint position) {
        this.position = position;
    }

    public byte[] getWsqData() {
        return wsqData;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public int getNfiqScore() {
        return nfiqScore;
    }

}