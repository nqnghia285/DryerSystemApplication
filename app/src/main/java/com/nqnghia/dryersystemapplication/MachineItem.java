package com.nqnghia.dryersystemapplication;

public class MachineItem {
    private int mImageResource;
    private String mTitle;
    private int mStatusTitle;
    private int mStatusTextView;
    private int mBeginTimeTitle;
    private String mBeginTimeTextView;
    private int mCompletedTimeTitle;
    private String mCompletedTimeTextView;
    private int mFoodTypeTitle;
    private int mFoodTypeTextView;
    private int mWeighTitle;
    private String mWeighTextView;
    private int mCurrentTemperatureTitle;
    private String mCurrentTemperatureTextView;
    private int mCurrentHumidityTitle;
    private String mCurrentHumidityTextView;
    private Boolean mSelected;

    public MachineItem(int imageResource,
                       String title,
                       int statusTitle,
                       int statusTextView,
                       int beginTimeTitle,
                       String beginTimeTextView,
                       int completedTimeTitle,
                       String completedTimeTextView,
                       int foodTypeTitle,
                       int foodTypeTextView,
                       int weighTitle,
                       String weighTextView,
                       int currentTemperatureTitle,
                       String currentTemperatureTextView,
                       int currentHumidityTitle,
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

    public int getBeginTimeTitle() {
        return mBeginTimeTitle;
    }

    public String getCompletedTimeTextView() {
        return mCompletedTimeTextView;
    }

    public int getCompletedTimeTitle() {
        return mCompletedTimeTitle;
    }

    public int getFoodTypeTextView() {
        return mFoodTypeTextView;
    }

    public int getStatusTextView() {
        return mStatusTextView;
    }

    public int getStatusTitle() {
        return mStatusTitle;
    }

    public int getFoodTypeTitle() {
        return mFoodTypeTitle;
    }

    public String getCurrentHumidityTextView() {
        return mCurrentHumidityTextView;
    }

    public int getCurrentHumidityTitle() {
        return mCurrentHumidityTitle;
    }

    public int getWeighTitle() {
        return mWeighTitle;
    }

    public String getWeighTextView() {
        return mWeighTextView;
    }

    public int getCurrentTemperatureTitle() {
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

    public void setBeginTimeTitle(int beginTimeTitle) {
        mBeginTimeTitle = beginTimeTitle;
    }

    public void setCompletedTimeTextView(String completedTimeTextView) {
        mCompletedTimeTextView = completedTimeTextView;
    }

    public void setCompletedTimeTitle(int completedTimeTitle) {
       mCompletedTimeTitle = completedTimeTitle;
    }

    public void setFoodTypeTextView(int foodTypeTextView) {
        mFoodTypeTextView = foodTypeTextView;
    }

    public void setFoodTypeTitle(int foodTypeTitle) {
        mFoodTypeTitle = foodTypeTitle;
    }

    public void setCurrentTemperatureTitle(int currentTemperatureTitle) {
        mCurrentTemperatureTitle = currentTemperatureTitle;
    }

    public void setCurrentHumidityTextView(String currentHumidityTextView) {
        mCurrentHumidityTextView = currentHumidityTextView;
    }

    public void setCurrentHumidityTitle(int currentHumidityTitle) {
        mCurrentHumidityTitle = currentHumidityTitle;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public void setStatusTextView(int statusTextView) {
        mStatusTextView = statusTextView;
    }

    public void setStatusTitle(int statusTitle) {
        mStatusTitle = statusTitle;
    }

    public void setWeighTitle(int weighTitle) {
        mWeighTitle = weighTitle;
    }

    public void setCurrentTemperatureTextView(String currentTemperatureTextView) {
        this.mCurrentTemperatureTextView = currentTemperatureTextView;
    }

    public void setWeighTextView(String weighTextView) {
        mWeighTextView = weighTextView;
    }
}
