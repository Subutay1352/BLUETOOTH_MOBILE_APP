package com.clj.blesample;
import android.content.SharedPreferences;

import com.clj.fastble.data.BleDevice;

public class variables {

    public static String getMaxRange() {
        return maxRange;
    }

    public static void setMaxRange(String maxRange) {
        variables.maxRange = maxRange;
    }

    public static String getMinRange() {
        return minRange;
    }

    public static void setMinRange(String minRange) {
        variables.minRange = minRange;
    }

    public static String maxRange;

    public static String minRange;

    public static String getPercentage() {
        return percentage;
    }

    public static void setPercentage(String percentage) {
        variables.percentage = percentage;
    }

    public static String percentage;

    public static BleDevice getBleDevice() {
        return bleDevice;
    }

    public static void setBleDevice(BleDevice bleDevice) {
        variables.bleDevice = bleDevice;
    }

    public static BleDevice bleDevice;


    public static int getRssi() {
        return rssi;
    }

    public static void setRssi(int rssi) {
        variables.rssi = rssi;
    }

    public static int rssi;


    public String getKind() {
        return kind;
    }

    public static void setKind(String kind) {
        variables.kind = kind;
    }

    public static String getmAVal() {
        return mAVal;
    }

    public static void setmAVal(String mAVal) {
        variables.mAVal = mAVal;
    }

    public static String getSignalStrenght() {
        return signalStrenght;
    }

    public static void setSignalStrenght(String signalStrenght) {
        variables.signalStrenght = signalStrenght;
    }

    public static String getDistance() {
        return distance;
    }

    public static void setDistance(String distance) {
        variables.distance = distance;
    }

    public static String kind;

    public static String mAVal;

    public static String signalStrenght;

    public static String distance;

    public static SharedPreferences getLanguagePreferences() {
        return languagePreferences;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        variables.language = language;
    }

    public static String language;
    public static void setLanguagePreferences(SharedPreferences languagePreferences) {
        variables.languagePreferences = languagePreferences;
    }

    public static SharedPreferences languagePreferences;

    public static boolean isSaveLanguage() {
        return saveLanguage;
    }

    public static void setSaveLanguage(boolean saveLanguage) {
        variables.saveLanguage = saveLanguage;
    }

    public static boolean saveLanguage;

    public static SharedPreferences.Editor getLanguageEditor() {
        return languageEditor;
    }

    public static void setLanguageEditor(SharedPreferences.Editor languageEditor) {
        variables.languageEditor = languageEditor;
    }

    public static SharedPreferences.Editor languageEditor;
}
