package com.naat.fingerprint.common;

import android.content.Context;

import com.naat.fingerprint.common.model.FingerprintCaptureRequest;
import com.naat.fingerprint.common.model.FingerprintData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Alex on 09/07/2018.
 */
public interface FingerprintScanner {

    /***
     * Capture error enumerations
     */
    enum CaptureErrorEvent {
        DEVICE_DISCONNECTED,
        CAPTURE_ERROR,
        READ_ERROR,
        DEVICE_BUSSY,
        INVALID_NFIQ_SCORE,
        CAPTURE_TIMEOUT,
        FINGERPRINT_DUPLICATED,
        FINGERPRINT_ALREADY_CAPTURED
    }

    /***
     * Connection error enumerations
     */
    enum ConnectionError {
        DEVICE_NOT_CONNECTED,
        OPEN_DEVICE_FAILED,
        PERMISSION_NOT_GRANTED
    }

    /***
     * Indicates whether or not the device is ready to capture
     */
    boolean isReady();

    /***
     * Indicates whether or not the device is connected
     */
    boolean isConnected();

    /***
     * Start device initialization
     */
    void initScanner();

    /***
     * Set the device connection listener
     */
    void setOnDeviceConnectionListener(OnDeviceConnectionListener onScannerStateListener);

    /***
     * Start device fingerprint capture with a capture listener
     */
    void capture(FingerprintCaptureRequest captureRequest, OnCaptureListener scannerListener);

    /***
     * Stop the device capture
     */
    void cancelCapture();

    /***
     * Reset all captures states
     */
    void reset();

    /***
     * Reset only the reference captures states
     */
    void reset(String reference);

    interface OnCaptureListener {
        void onCaptureComplete(String reference, List<FingerprintData> data);
        void onCaptureError(CaptureErrorEvent error);
    }

    interface OnDeviceConnectionListener {
        void onDeviceReady();
        void onConnectionFailed(ConnectionError error);
        void onConnected();
        void onDisconnected();
    }

    class Builder {

        enum Type {
            WATSON //,OTHER_SCANNER
        }

        private Type type;
        private OnDeviceConnectionListener onDeviceConnectionListener;

        public Builder(Type type) {
            this.type = type;
        }

        public Builder onDeviceConnectionListener(OnDeviceConnectionListener onDeviceConnectionListener) {
            this.onDeviceConnectionListener = onDeviceConnectionListener;
            return this;
        }

        public FingerprintScanner create(Context context) {
            FingerprintScanner scanner = null;
            if (type.equals(Type.WATSON)) {
                //scanner = WatsonMiniScanner.get(context);
                //scanner.setOnDeviceConnectionListener(onDeviceConnectionListener);
                //return scanner;

                Class<?> clazz;
                try {
                    clazz = Class.forName("com.naat.fingerprint.watsonmini.WatsonMiniScanner");
                    //clazz.newInstance();
                    Method method = clazz.getMethod("get", Context.class);
                    scanner = (FingerprintScanner) method.invoke(null, context);
                    scanner.setOnDeviceConnectionListener(onDeviceConnectionListener);
                    return scanner;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new IllegalStateException("Cannot initialize fingerprint scanner!");
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    throw new IllegalStateException("Cannot initialize fingerprint scanner!");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new IllegalStateException("Cannot initialize fingerprint scanner!");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    throw new IllegalStateException("Cannot initialize fingerprint scanner!");
                }
            }
            throw new IllegalArgumentException(type.name().concat(" not implemented!"));
        }

    }

}
