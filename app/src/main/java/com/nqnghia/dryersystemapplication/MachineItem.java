package com.nqnghia.dryersystemapplication;

public class MachineItem {
    private int mImageResource;
    private String mTitle;
    private String mStatusTitle;
    private String mStatusTextView;
    private String mBeginTimeTitle;
    private String mBeginTimeTextView;
    private String mCompletedTimeTitle;
    private String mCompletedTimeTextView;
    private String mFoodTypeTitle;
    private String mFoodTypeTextView;
    private String mWeighTitle;
    private String mWeighTextView;
    private String mCurrentTemperatureTitle;
    private String mCurrentTemperatureTextView;
    private String mCurrentHumidityTitle;
    private String mCurrentHumidityTextView;
    private Boolean mSelected;

    public MachineItem(int imageResource,
                       String title,
                       String statusTitle,
                       String statusTextView,
                       String beginTimeTitle,
                       String beginTimeTextView,
                       String completedTimeTitle,
                       String completedTimeTextView,
                       String foodTypeTitle,
                       String foodTypeTextView,
                       String weighTitle,
                       String weighTextView,
                       String currentTemperatureTitle,
                       String currentTemperatureTextView,
                       String currentHumidityTitle,
                       String currentHumidityTextView) {
        mImageResource = imageResource;
        mTitle = title;
        mStatusTitle = statusTitle;
        mStatusTextView = statusTextView;
        mBeginTimeTitle = beginTimeTitle;
        mBeginTimeTextView = beginTimeTextView;
        mCompletedTimeTitle = completedTimeTitle;
        mCompletedTimeTextView = completedTimeTextView;
        mFoodTypeTitle = foodTypeTitle;
        mFoodTypeTextView = foodTypeTextView;
        mWeighTitle = weighTitle;
        mWeighTextView = weighTextView;
        mCurrentTemperatureTitle = currentTemperatureTitle;
        mCurrentTemperatureTextView = currentTemperatureTextView;
        mCurrentHumidityTitle = currentHumidityTitle;
        mCurrentHumidityTextView = currentHumidityTextView;
        mSelected = false;
    }

    public MachineItem(int imageResource, String...args) {
        if (args.length == 15) {
            mImageResource = imageResource;
            mTitle = args[0];
            mStatusTitle = args[1];
            mStatusTextView = args[2];
            mBeginTimeTitle = args[3];
            mBeginTimeTextView = args[4];
            mCompletedTimeTitle = args[5];
            mCompletedTimeTextView = args[6];
            mFoodTypeTitle = args[7];
            mFoodTypeTextView = args[8];
            mWeighTitle = args[9];
            mWeighTextView = args[10];
            mCurrentTemperatureTitle = args[11];
            mCurrentTemperatureTextView = args[12];
            mCurrentHumidityTitle = args[13];
            mCurrentHumidityTextView = args[14];
            mSelected = false;
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public Boolean getSelected() {
        return mSelected;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getBeginTimeTextView() {
        return mBeginTimeTextView;
    }

    public String getBeginTimeTitle() {
        return mBeginTimeTitle;
    }

    public String getCompletedTimeTextView() {
        return mCompletedTimeTextView;
    }

    public String getCompletedTimeTitle() {
        return mCompletedTimeTitle;
    }

    public String getFoodTypeTextView() {
        return mFoodTypeTextView;
    }

    public String getStatusTextView() {
        return mStatusTextView;
    }

    public String getStatusTitle() {
        return mStatusTitle;
    }

    public String getFoodTypeTitle() {
        return mFoodTypeTitle;
    }

    public String getCurrentHumidityTextView() {
        return mCurrentHumidityTextView;
    }

    public String getCurrentHumidityTitle() {
        return mCurrentHumidityTitle;
    }

    public String getWeighTitle() {
        return mWeighTitle;
    }

    public String getWeighTextView() {
        return mWeighTextView;
    }

    public String getCurrentTemperatureTitle() {
        return mCurrentTemperatureTitle;
    }

    public String getCurrentTemperatureTextView() {
        return mCurrentTemperatureTextView;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setSelected(Boolean selected) {
        mSelected = selected;
    }

    public void setBeginTimeTextView(String beginTimeTextView) {
        mBeginTimeTextView = beginTimeTextView;
    }

    public void setBeginTimeTitle(String beginTimeTitle) {
        mBeginTimeTitle = beginTimeTitle;
    }

    public void setCompletedTimeTextView(String completedTimeTextView) {
        mCompletedTimeTextView = completedTimeTextView;
    }

    public void setCompletedTimeTitle(String completedTimeTitle) {
       mCompletedTimeTitle = completedTimeTitle;
    }

    public void setFoodTypeTextView(String foodTypeTextView) {
        mFoodTypeTextView = foodTypeTextView;
    }

    public void setFoodTypeTitle(String foodTypeTitle) {
        mFoodTypeTitle = foodTypeTitle;
    }

    public void setCurrentTemperatureTitle(String currentTemperatureTitle) {
        mCurrentTemperatureTitle = currentTemperatureTitle;
    }

    public void setCurrentHumidityTextView(String currentHumidityTextView) {
        mCurrentHumidityTextView = currentHumidityTextView;
    }

    public void setCurrentHumidityTitle(String currentHumidityTitle) {
        mCurrentHumidityTitle = currentHumidityTitle;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public void setStatusTextView(String statusTextView) {
        mStatusTextView = statusTextView;
    }

    public void setStatusTitle(String statusTitle) {
        mStatusTitle = statusTitle;
    }

    public void setWeighTitle(String weighTitle) {
        mWeighTitle = weighTitle;
    }

    public void setCurrentTemperatureTextView(String currentTemperatureTextView) {
        this.mCurrentTemperatureTextView = currentTemperatureTextView;
    }

    public void setWeighTextView(String weighTextView) {
        mWeighTextView = weighTextView;
    }
}
