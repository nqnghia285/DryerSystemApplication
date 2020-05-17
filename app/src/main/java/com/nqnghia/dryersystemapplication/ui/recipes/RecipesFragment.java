package com.nqnghia.dryersystemapplication.ui.recipes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nqnghia.dryersystemapplication.AdapterMachineItem;
import com.nqnghia.dryersystemapplication.AdapterRecipeItem;
import com.nqnghia.dryersystemapplication.MachineItem;
import com.nqnghia.dryersystemapplication.MainActivity;
import com.nqnghia.dryersystemapplication.R;
import com.nqnghia.dryersystemapplication.RecipeItem;

import java.util.ArrayList;

public class RecipesFragment extends Fragment {
    private static final String TAG = "RecipesFragment";
    private RecipesViewModel recipesViewModel;
    private MainActivity mainActivity;
    private View root;

    private RecyclerView mRecyclerView;
    private AdapterRecipeItem mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<RecipeItem> mItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call setHasOptionsMenu() to Fragment can setup menu
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        root = inflater.inflate(R.layout.fragment_recipes, container, false);

        createItemList();
        buildRecyclerView();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mainActivity != null) {
            mainActivity.getFab().setOnClickListener(v -> {
                showRecipeItemDialog();
            });
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        menu.removeItem(R.id.action_edit_item);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_select_all:
                for (RecipeItem recipeItem : mItems) {
                    recipeItem.setSelected(true);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.action_uncheck_all:
                for (RecipeItem recipeItem : mItems) {
                    recipeItem.setSelected(false);
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.action_remove:
                int position = 0;
                while (position < mItems.size()) {
                    if (mItems.get(position).getSelected()) {
                        mItems.remove(position);
                    } else {
                        position++;
                    }
                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.action_add_item:
                showRecipeItemDialog();
                break;
            case R.id.action_edit_item:

//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRecipeItemDialog() {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        @SuppressLint("InflateParams") View vi = inflater.inflate(R.layout.recipe_item_dialog, null);

        EditText recipeTitleTextView = vi.findViewById(R.id.recipe_title_text_dialog);
        EditText foodTypeTextView = vi.findViewById(R.id.food_type_text_dialog);
        EditText weighTextView = vi.findViewById(R.id.weigh_text_dialog);
        EditText temperatureTextView = vi.findViewById(R.id.temperature_text_dialog);
        EditText humidityTextView = vi.findViewById(R.id.humidity_text_dialog);
        EditText dryingTimeTextView = vi.findViewById(R.id.drying_time_text_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.recipe_title_alert_dialog_vi);
        builder.setView(vi);
        builder.setIcon(R.drawable.ic_add_box_black_24dp);
        builder.setPositiveButton(R.string.ok_vi, (dialog, which) -> {
            if (!recipeTitleTextView.getText().toString().equals("") &&
                !foodTypeTextView.getText().toString().equals("") &&
                !weighTextView.getText().toString().equals("") &&
                !temperatureTextView.getText().toString().equals("") &&
                !humidityTextView.getText().toString().equals("") &&
                !dryingTimeTextView.getText().toString().equals("")) {

                mItems.add(new RecipeItem(
                        recipeTitleTextView.getText().toString(),
                        R.string.food_type_title_vi,
                        foodTypeTextView.getText().toString(),
                        R.string.weigh_title_vi,
                        weighTextView.getText().toString(),
                        R.string.temperature_title_vi,
                        temperatureTextView.getText().toString(),
                        R.string.humidity_title_vi,
                        humidityTextView.getText().toString(),
                        R.string.drying_time_title_vi,
                        dryingTimeTextView.getText().toString()));

                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.cancel_vi, (dialog, which) -> {

        });
        builder.create().show();
    }

    private void createItemList() {
        mItems = new ArrayList<>();
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00:00"));
    }

    private void buildRecyclerView() {
        mRecyclerView = root.findViewById(R.id.recipe_recycle_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new AdapterRecipeItem(mItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(this::changeDestination);
    }

    private void changeDestination(int position) {
        Log.e(TAG, "Pressed position: " + position);
    }
}