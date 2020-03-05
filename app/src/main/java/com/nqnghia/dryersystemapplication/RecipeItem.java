package com.nqnghia.dryersystemapplication;

public class RecipeItem {
    private String mRecipeTitle;
    private int mFoodTypeTitle;
    private String mFoodTypeTextView;
    private int mWeighTitle;
    private String mWeighTextView;
    private int mTemperatureTitle;
    private String mTemperatureTextView;
    private int mHumidityTitle;
    private String mHumidityTextView;
    private int mDryingTimeTitle;
    private String mDryingTimeTextView;
    private Boolean mSelected;

    public RecipeItem(String recipeTitle,
                      int foodTypeTitle,
                      String foodTypeTextView,
                      int weighTitle,
                      String weighTextView,
                      int temperatureTitle,
                      String temperatureTextView,
                      int humidityTitle,
                      String humidityTextView,
                      int dryingTimeTitle,
                      String dryingTimeTextView) {
        mRecipeTitle = recipeTitle;
        mFoodTypeTitle = foodTypeTitle;
        mFoodTypeTextView = foodTypeTextView;
        mWeighTitle = weighTitle;
        mWeighTextView = weighTextView;
        mTemperatureTitle = temperatureTitle;
        mTemperatureTextView = temperatureTextView;
        mHumidityTitle = humidityTitle;
        mHumidityTextView = humidityTextView;
        mDryingTimeTitle = dryingTimeTitle;
        mDryingTimeTextView = dryingTimeTextView;
        mSelected = false;
    }

    public String getFoodTypeTextView() {
        return mFoodTypeTextView;
    }

    public String getWeighTextView() {
        return mWeighTextView;
    }

    public int getFoodTypeTitle() {
        return mFoodTypeTitle;
    }

    public int getHumidityTitle() {
        return mHumidityTitle;
    }

    public int getTemperatureTitle() {
        return mTemperatureTitle;
    }

    public int getWeighTitle() {
        return mWeighTitle;
    }

    public String getRecipeTitle() {
        return mRecipeTitle;
    }

    public String getTemperatureTextView() {
        return mTemperatureTextView;
    }

    public Boolean getSelected() {
        return mSelected;
    }

    public int getDryingTimeTitle() {
        return mDryingTimeTitle;
    }

    public String getDryingTimeTextView() {
        return mDryingTimeTextView;
    }

    public String getHumidityTextView() {
        return mHumidityTextView;
    }

    public void setWeighTextView(String weighTextView) {
        mWeighTextView = weighTextView;
    }

    public void setWeighTitle(int weighTitle) {
        mWeighTitle = weighTitle;
    }

    public void setFoodTypeTitle(int foodTypeTitle) {
        mFoodTypeTitle = foodTypeTitle;
    }

    public void setFoodTypeTextView(String foodTypeTextView) {
        mFoodTypeTextView = foodTypeTextView;
    }

    public void setRecipeTitle(String recipeTitle) {
        mRecipeTitle = recipeTitle;
    }

    public void setTemperatureTextView(String temperatureTextView) {
        mTemperatureTextView = temperatureTextView;
    }

    public void setDryingTimeTitle(int dryingTimeTitle) {
        mDryingTimeTitle = dryingTimeTitle;
    }

    public void setTemperatureTitle(int temperatureTitle) {
        mTemperatureTitle = temperatureTitle;
    }

    public void setHumidityTitle(int humidityTitle) {
        mHumidityTitle = humidityTitle;
    }

    public void setHumidityTextView(String humidityTextView) {
        mHumidityTextView = humidityTextView;
    }

    public void setDryingTimeTextView(String dryingTimeTextView) {
        mDryingTimeTextView = dryingTimeTextView;
    }

    public void setSelected(Boolean selected) {
        mSelected = selected;
    }
}
