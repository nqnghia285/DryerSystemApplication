package com.nqnghia.dryersystemapplication.ui.machine_info;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.nqnghia.dryersystemapplication.MainActivity;
import com.nqnghia.dryersystemapplication.R;
import com.nqnghia.dryersystemapplication.RecipeAdapter;
import com.nqnghia.dryersystemapplication.RecipeItem;

import java.util.ArrayList;
import java.util.List;

public class MachineInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private View root;
    private MachineInfoViewModel machineInfoViewModel;

    private ImageView imageView;
    private TextView machineTitle;
    private Button runningButton;
    private TextView recipeTitleList;
    private Spinner recipeSpinner;
    private TextView machineInfo;
    private TextView beginTimeTitle;
    private TextView beginTimeTextView;
    private TextView completedTimeTitle;
    private TextView completedTimeTextView;
    private TextView temperatureTitle;
    private TextView temperatureTextView;
    private TextView humidityTitle;
    private TextView humidityTextView;
    private TextView foodTypeTitle;
    private TextView foodTypeTextView;
    private TextView weighTitle;
    private TextView weighTextView;
    private TextView currentTemperatureHumidityTitle;
    private ImageButton chartImageButton;
    private TextView currentTemperatureTitle;
    private TextView currentTemperatureTextView;
    private TextView currentHumidityTitle;
    private TextView currentHumidityTextView;
    private TextView statusFanTitle;
    private TextView blowerFanTitle;
    private TextView closedBlowerFanTextView;
    private Switch blowerFanSwitch;
    private TextView openedBlowerFanTextView;
    private TextView exhaustFanTitle;
    private TextView closedExhaustFanTextView;
    private Switch exhaustFanSwitch;
    private TextView openedExhaustFanTextView;

    private List<RecipeItem> mItems;

    private MainActivity mainActivity;

    private volatile int pos = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call setHasOptionsMenu() to Fragment can setup menu
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        machineInfoViewModel = ViewModelProviders.of(this).get(MachineInfoViewModel.class);
        root = inflater.inflate(R.layout.fragment_machine_info, container, false);

        imageView = root.findViewById(R.id.machine_image);
        machineTitle = root.findViewById(R.id.machine_title);
        runningButton = root.findViewById(R.id.running_button);
        recipeTitleList = root.findViewById(R.id.recipe_title_list);
        recipeSpinner = root.findViewById(R.id.recipe_spinner);
        machineInfo = root.findViewById(R.id.machine_info);
        beginTimeTitle = root.findViewById(R.id.begin_time_title);
        beginTimeTextView = root.findViewById(R.id.begin_time_text_view);
        completedTimeTitle = root.findViewById(R.id.completed_time_title);
        completedTimeTextView = root.findViewById(R.id.completed_time_text_view);
        temperatureTitle = root.findViewById(R.id.temperature_title);
        temperatureTextView = root.findViewById(R.id.temperature_text_view);
        humidityTitle = root.findViewById(R.id.humidity_title);
        humidityTextView = root.findViewById(R.id.humidity_text_view);
        foodTypeTitle = root.findViewById(R.id.food_type_title);
        foodTypeTextView = root.findViewById(R.id.food_type_text_view);
        weighTitle = root.findViewById(R.id.weigh_title);
        weighTextView = root.findViewById(R.id.weigh_text_view);
        currentTemperatureHumidityTitle = root.findViewById(R.id.current_temperature_humidity_title);
        chartImageButton = root.findViewById(R.id.chart_image_button);
        currentTemperatureTitle = root.findViewById(R.id.current_temperature_title);
        currentTemperatureTextView = root.findViewById(R.id.current_temperature_text_view);
        currentHumidityTitle = root.findViewById(R.id.current_humidity_title);
        currentHumidityTextView = root.findViewById(R.id.current_humidity_text_view);
        statusFanTitle = root.findViewById(R.id.status_fan_title);
        blowerFanTitle = root.findViewById(R.id.blower_fan_title);
        closedBlowerFanTextView = root.findViewById(R.id.closed_blower_fan_text_view);
        blowerFanSwitch = root.findViewById(R.id.blower_fan_switch);
        openedBlowerFanTextView = root.findViewById(R.id.opened_blower_fan_text_view);
        exhaustFanTitle = root.findViewById(R.id.exhaust_fan_title);
        closedExhaustFanTextView = root.findViewById(R.id.closed_exhaust_fan_text_view);
        exhaustFanSwitch = root.findViewById(R.id.exhaust_fan_switch);
        openedExhaustFanTextView = root.findViewById(R.id.opened_exhaust_fan_text_view);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            MachineInfoFragmentArgs args = MachineInfoFragmentArgs.fromBundle(getArguments());
            imageView.setImageResource(args.getImageResource());
            machineTitle.setText(args.getMachineTitle());
            recipeTitleList.setText(args.getRecipeTitleList());
            machineInfo.setText(args.getMachineInfo());
            beginTimeTitle.setText(args.getBeginTimeTitle());
            beginTimeTextView.setText(args.getBeginTimeTextView());
            completedTimeTitle.setText(args.getCompletedTimeTitle());
            completedTimeTextView.setText(args.getCompletedTimeTextView());
            temperatureTitle.setText(args.getTemperatureTitle());
            temperatureTextView.setText(args.getTemperatureTextView());
            humidityTitle.setText(args.getHumidityTitle());
            humidityTextView.setText(args.getHumidityTextView());
            foodTypeTitle.setText(args.getFoodTypeTitle());
            foodTypeTextView.setText(args.getFoodTypeTextView());
            weighTitle.setText(args.getWeighTitle());
            weighTextView.setText(args.getWeighTextView());
            currentTemperatureHumidityTitle.setText(args.getCurrentTemperatureHumidityTitle());
            currentTemperatureTitle.setText(args.getCurrentTemperatureTitle());
            currentTemperatureTextView.setText(args.getCurrentTemperatureTextView());
            currentHumidityTitle.setText(args.getCurrentHumidityTitle());
            currentHumidityTextView.setText(args.getCurrentHumidityTextView());
            statusFanTitle.setText(args.getStatusFanTitle());
            blowerFanTitle.setText(args.getBlowerFanTitle());
            closedBlowerFanTextView.setText(args.getClosedBlowerFanTextView());
            blowerFanSwitch.setChecked(args.getBlowerFanSwitch());
            openedBlowerFanTextView.setText(args.getOpenedBlowerFanTextView());
            exhaustFanTitle.setText(args.getExhaustFanTitle());
            closedExhaustFanTextView.setText(args.getClosedExhaustFanTextView());
            exhaustFanSwitch.setChecked(args.getExhaustFanSwitch());
            openedExhaustFanTextView.setText(args.getOpenedExhaustFanTextView());

            chartImageButton.setOnClickListener(v -> {
                changeDestination(args.getPosition());
            });

            runningButton.setOnClickListener(v -> {
                RecipeItem item = mItems.get(pos);
                beginTimeTextView.setText(item.getDryingTimeTextView());
                completedTimeTextView.setText(item.getDryingTimeTextView());
                temperatureTextView.setText(item.getTemperatureTextView());
                humidityTextView.setText(item.getHumidityTextView());
                foodTypeTextView.setText(item.getFoodTypeTextView());
                weighTextView.setText(item.getWeighTextView());

                RelativeLayout layout = root.findViewById(R.id.layout_parent);
                Snackbar.make(layout, getString(R.string.running), Snackbar.LENGTH_LONG).show();
            });

            createItemList();

            RecipeAdapter adapter = new RecipeAdapter(mainActivity, R.layout.selected_recipe_layout, mItems);

            recipeSpinner.setAdapter(adapter);
            recipeSpinner.setOnItemSelectedListener(this);
        }
    }

    private void createItemList() {
        mItems = new ArrayList<>();
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title, "Củ cải trắng", R.string.weigh_title, "300", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title, "Khoai lan", R.string.weigh_title, "500", R.string.temperature_title, "70", R.string.humidity_title, "80", R.string.drying_time_title, "03:40:00"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title, "Mít tố nữ", R.string.weigh_title, "120", R.string.temperature_title, "65", R.string.humidity_title, "60", R.string.drying_time_title, "01:00:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title, "lúa", R.string.weigh_title, "1000", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "07:00:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title, "Chuối lá xiêm", R.string.weigh_title, "200", R.string.temperature_title, "60", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title, "Củ cải trắng", R.string.weigh_title, "300", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title, "Khoai lan", R.string.weigh_title, "500", R.string.temperature_title, "70", R.string.humidity_title, "80", R.string.drying_time_title, "03:40:00"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title, "Mít tố nữ", R.string.weigh_title, "120", R.string.temperature_title, "65", R.string.humidity_title, "60", R.string.drying_time_title, "01:00:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title, "lúa", R.string.weigh_title, "1000", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "07:00:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title, "Chuối lá xiêm", R.string.weigh_title, "200", R.string.temperature_title, "60", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title, "Củ cải trắng", R.string.weigh_title, "300", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title, "Khoai lan", R.string.weigh_title, "500", R.string.temperature_title, "70", R.string.humidity_title, "80", R.string.drying_time_title, "03:40:00"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title, "Mít tố nữ", R.string.weigh_title, "120", R.string.temperature_title, "65", R.string.humidity_title, "60", R.string.drying_time_title, "01:00:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title, "lúa", R.string.weigh_title, "1000", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "07:00:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title, "Chuối lá xiêm", R.string.weigh_title, "200", R.string.temperature_title, "60", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title, "Củ cải trắng", R.string.weigh_title, "300", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title, "Khoai lan", R.string.weigh_title, "500", R.string.temperature_title, "70", R.string.humidity_title, "80", R.string.drying_time_title, "03:40:00"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title, "Mít tố nữ", R.string.weigh_title, "120", R.string.temperature_title, "65", R.string.humidity_title, "60", R.string.drying_time_title, "01:00:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title, "lúa", R.string.weigh_title, "1000", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "07:00:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title, "Chuối lá xiêm", R.string.weigh_title, "200", R.string.temperature_title, "60", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title, "Củ cải trắng", R.string.weigh_title, "300", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title, "Khoai lan", R.string.weigh_title, "500", R.string.temperature_title, "70", R.string.humidity_title, "80", R.string.drying_time_title, "03:40:00"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title, "Mít tố nữ", R.string.weigh_title, "120", R.string.temperature_title, "65", R.string.humidity_title, "60", R.string.drying_time_title, "01:00:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title, "lúa", R.string.weigh_title, "1000", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "07:00:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title, "Chuối lá xiêm", R.string.weigh_title, "200", R.string.temperature_title, "60", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title, "Củ cải trắng", R.string.weigh_title, "300", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title, "Khoai lan", R.string.weigh_title, "500", R.string.temperature_title, "70", R.string.humidity_title, "80", R.string.drying_time_title, "03:40:00"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title, "Mít tố nữ", R.string.weigh_title, "120", R.string.temperature_title, "65", R.string.humidity_title, "60", R.string.drying_time_title, "01:00:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title, "lúa", R.string.weigh_title, "1000", R.string.temperature_title, "65", R.string.humidity_title, "80", R.string.drying_time_title, "07:00:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title, "Chuối lá xiêm", R.string.weigh_title, "200", R.string.temperature_title, "60", R.string.humidity_title, "80", R.string.drying_time_title, "02:00:00"));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        //TODO
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void changeDestination(int position) {
        NavController navController = Navigation.findNavController(mainActivity, R.id.nav_host_fragment);
        MachineInfoFragmentDirections.ActionNavMachineInfoToHumidityTemperatureFragment action = MachineInfoFragmentDirections.actionNavMachineInfoToHumidityTemperatureFragment();
        action.setPosition(position);
        navController.navigate(action);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        RecipeItem item = mItems.get(position);
        //////////////////////////////////////////////////
        RelativeLayout layout = root.findViewById(R.id.layout_parent);
        Snackbar.make(layout, item.getRecipeTitle(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}