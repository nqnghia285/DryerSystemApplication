package com.nqnghia.dryersystemapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecipeItem extends RecyclerView.Adapter<AdapterRecipeItem.ViewHolderItem> {
    private static final String TAG = "AdapterRecipeItem";
    private ArrayList<RecipeItem> mItems;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AdapterRecipeItem(ArrayList<RecipeItem> items) {
        mItems = items;
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView mRecipeTitle;
        TextView mFoodTypeTitle;
        TextView mFoodTypeTextView;
        TextView mWeighTitle;
        TextView mWeighTextView;
        TextView mTemperatureTitle;
        TextView mTemperatureTextView;
        TextView mHumidityTitle;
        TextView mHumidityTextView;
        TextView mDryingTimeTitle;
        TextView mDryingTimeTextView;

        ViewHolderItem(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mRecipeTitle = itemView.findViewById(R.id.recipe_title);
            mFoodTypeTitle = itemView.findViewById(R.id.food_type_title_recipe);
            mFoodTypeTextView = itemView.findViewById(R.id.food_type_text_view_recipe);
            mWeighTitle = itemView.findViewById(R.id.weigh_title_recipe);
            mWeighTextView = itemView.findViewById(R.id.weigh_text_view_recipe);
            mTemperatureTitle = itemView.findViewById(R.id.temperature_title_recipe);
            mTemperatureTextView = itemView.findViewById(R.id.temperature_text_view_recipe);
            mHumidityTitle = itemView.findViewById(R.id.humidity_title_recipe);
            mHumidityTextView = itemView.findViewById(R.id.humidity_text_view_recipe);
            mDryingTimeTitle = itemView.findViewById(R.id.drying_time_title_recipe);
            mDryingTimeTextView = itemView.findViewById(R.id.drying_time_text_view_recipe);
        }
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li =LayoutInflater.from(parent.getContext());
        return new ViewHolderItem(li.inflate(R.layout.recipe_item, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem holder, int position) {
        RecipeItem currentItem = mItems.get(position);

        holder.mRecipeTitle.setText(currentItem.getRecipeTitle());
        holder.mFoodTypeTitle.setText(currentItem.getFoodTypeTitle());
        holder.mFoodTypeTextView.setText(currentItem.getFoodTypeTextView());
        holder.mWeighTitle.setText(currentItem.getWeighTitle());
        holder.mWeighTextView.setText(currentItem.getWeighTextView());
        holder.mTemperatureTitle.setText(currentItem.getTemperatureTitle());
        holder.mTemperatureTextView.setText(currentItem.getTemperatureTextView());
        holder.mHumidityTitle.setText(currentItem.getHumidityTitle());
        holder.mHumidityTextView.setText(currentItem.getHumidityTextView());
        holder.mDryingTimeTitle.setText(currentItem.getDryingTimeTitle());
        holder.mDryingTimeTextView.setText(currentItem.getDryingTimeTextView());

        if (currentItem.getSelected()) {
            holder.itemView.setBackgroundResource(R.drawable.item_blue_purple_background);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_white_background);
        }

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(position);
            }

            if (currentItem.getSelected()) {
                currentItem.setSelected(false);
                holder.itemView.setBackgroundResource(R.drawable.item_white_background);
            } else {
                currentItem.setSelected(true);
                holder.itemView.setBackgroundResource(R.drawable.item_blue_purple_background);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        } else {
            return 0;
        }
    }


}
