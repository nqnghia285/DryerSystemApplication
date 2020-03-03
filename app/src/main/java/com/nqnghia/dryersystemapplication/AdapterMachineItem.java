package com.nqnghia.dryersystemapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterMachineItem extends RecyclerView.Adapter<AdapterMachineItem.ViewHolderItem> {
    private static final String TAG = "AdapterMachineItem";
    private ArrayList<MachineItem> mItems;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder {
        ImageButton mImageResource;
        TextView mTitle;
        TextView mStatusTitle;
        TextView mStatusTextView;
        TextView mBeginTimeTitle;
        TextView mBeginTimeTextView;
        TextView mCompletedTimeTitle;
        TextView mCompletedTimeTextView;
        TextView mFoodTypeTitle;
        TextView mFoodTypeTextView;
        TextView mWeighTitle;
        TextView mWeighTextView;
        TextView mCurrentTemperatureTitle;
        TextView mCurrentTemperatureTextView;
        TextView mCurrentHumidityTitle;
        TextView mCurrentHumidityTextView;

        ViewHolderItem(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageResource = itemView.findViewById(R.id.image_card_view);
            mTitle = itemView.findViewById(R.id.title_text_view);
            mStatusTitle = itemView.findViewById(R.id.status_title);
            mStatusTextView = itemView.findViewById(R.id.status_text_view);
            mBeginTimeTitle = itemView.findViewById(R.id.begin_time_title);
            mBeginTimeTextView = itemView.findViewById(R.id.begin_time_text_view);
            mCompletedTimeTitle = itemView.findViewById(R.id.completed_time_title);
            mCompletedTimeTextView = itemView.findViewById(R.id.completed_time_text_view);
            mFoodTypeTitle = itemView.findViewById(R.id.food_type_title);
            mFoodTypeTextView = itemView.findViewById(R.id.food_type_text_view);
            mWeighTitle = itemView.findViewById(R.id.weigh_title);
            mWeighTextView = itemView.findViewById(R.id.weigh_text_view);
            mCurrentTemperatureTitle = itemView.findViewById(R.id.current_temperature_title);
            mCurrentTemperatureTextView = itemView.findViewById(R.id.current_temperature_text_view);
            mCurrentHumidityTitle = itemView.findViewById(R.id.current_humidity_title);
            mCurrentHumidityTextView = itemView.findViewById(R.id.current_humidity_text_view);

            mImageResource.setOnClickListener((v) -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public AdapterMachineItem(ArrayList<MachineItem> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li =LayoutInflater.from(parent.getContext());
        return new ViewHolderItem(li.inflate(R.layout.machine_item, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderItem holder, int position) {
        MachineItem currentItem = mItems.get(position);

        holder.mImageResource.setImageResource(currentItem.getImageResource());
        holder.mTitle.setText(currentItem.getTitle());
        holder.mStatusTitle.setText(currentItem.getStatusTitle());
        holder.mStatusTextView.setText(currentItem.getStatusTextView());
        holder.mBeginTimeTitle.setText(currentItem.getBeginTimeTitle());
        holder.mBeginTimeTextView.setText(currentItem.getBeginTimeTextView());
        holder.mCompletedTimeTitle.setText(currentItem.getCompletedTimeTitle());
        holder.mCompletedTimeTextView.setText(currentItem.getCompletedTimeTextView());
        holder.mFoodTypeTitle.setText(currentItem.getFoodTypeTitle());
        holder.mFoodTypeTextView.setText(currentItem.getFoodTypeTextView());
        holder.mWeighTitle.setText(currentItem.getWeighTitle());
        holder.mWeighTextView.setText(currentItem.getWeighTextView());
        holder.mCurrentTemperatureTitle.setText(currentItem.getCurrentTemperatureTitle());
        holder.mCurrentTemperatureTextView.setText(currentItem.getCurrentTemperatureTextView());
        holder.mCurrentHumidityTitle.setText(currentItem.getCurrentHumidityTitle());
        holder.mCurrentHumidityTextView.setText(currentItem.getCurrentHumidityTextView());
        if (currentItem.getSelected()) {
            holder.itemView.setBackgroundResource(R.drawable.item_blue_purple_background);
        } else {
            holder.itemView.setBackgroundResource(R.drawable.item_white_background);
        }

        holder.itemView.setOnClickListener(v -> {
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
        return mItems.size();
    }
}
