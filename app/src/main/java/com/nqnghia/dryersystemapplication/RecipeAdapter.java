package com.nqnghia.dryersystemapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<RecipeItem> {
    LayoutInflater inflater;
    int resId;
    List<RecipeItem> objects;
    Context context;

    public RecipeAdapter(Activity context, int resId, List<RecipeItem> list) {
        super(context, resId, list);
        inflater = context.getLayoutInflater();
        this.resId = resId;
        objects = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RecipeItem item = getItem(position);

        View rowView = inflater.inflate(resId, null, true);

        TextView recipeTitleOption = rowView.findViewById(R.id.recipe_title_option);
        recipeTitleOption.setText(item.getRecipeTitle() + ": " + item.getWeighTextView() + "Kg");

        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomDropDownView(position, convertView, parent);
    }

    public View getCustomDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.selecte_recipes_layout, parent, false);

        RecipeItem item = getItem(position);

        TextView recipeTitleOption = row.findViewById(R.id.recipe_title_option);
        recipeTitleOption.setText(item.getRecipeTitle() + ": " + item.getWeighTextView() + "Kg");

        return row;
    }
}
