package com.nqnghia.dryersystemapplication.ui.machine_info;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
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
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.google.android.material.snackbar.Snackbar;
import com.nqnghia.dryersystemapplication.MainActivity;
import com.nqnghia.dryersystemapplication.R;
import com.nqnghia.dryersystemapplication.RecipeAdapter;
import com.nqnghia.dryersystemapplication.RecipeItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MachineInfoFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private View root;
    private MachineInfoViewModel machineInfoViewModel;
    private Boolean langVi;
    private ImageView imageView;
    private TextView machineTitle;
    private TextView enableManualLabel;
    private Switch enableManual;
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
    private TextView currentTemperatureTitle;
    private TextView currentTemperatureTextView;
    private TextView currentHumidityTitle;
    private TextView currentHumidityTextView;
    private TextView heatingMachineTitle;
    private TextView valueOfProgress;
    private SeekBar heatingMachineSeekBar;
    private TextView min;
    private TextView max;
    private TextView statusFanTitle;
    private TextView blowerFanTitle;
    private TextView closedBlowerFanTitle;
    private Switch blowerFanSwitch;
    private TextView openedBlowerFanTitle;
    private TextView exhaustFanTitle;
    private TextView closedExhaustFanTitle;
    private Switch exhaustFanSwitch;
    private TextView openedExhaustFanTitle;

    private List<RecipeItem> mItems;

    private MainActivity mainActivity;

    private volatile int pos = 0;

    private volatile int temp = 0;
    private volatile int hum = 0;

    ////////////////////////////
    private List<DataEntry> seriesData;
    private Set set;

    private Timer timer;
    private Random random = new Random();
    private int year = 0;

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
        init(inflater, container);

        ///////////////////////////////////////////////////
        // Line Chart - AnyChart
        initAnyChart();

        return root;
    }

    private void initAnyChart() {
        AnyChartView anyChartView = root.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.line();

//        cartesian.credits().text(" ");

        cartesian.xAxis(true);

        cartesian.xScroller().enabled(true);

        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title(getString(R.string.humidity_temperature_chart_vi));
        cartesian.title().fontColor("green");

        cartesian.yAxis(0).title(getString(R.string.humidity_temperature_unit_vi));
        cartesian.xAxis(0).title(getString(R.string.time_vi));
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("0", 65, 80));

        set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");

        Line series1 = cartesian.line(series1Mapping);
        series1.name(getString(R.string.temperature_vi));
        series1.stroke("2 red");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        Line series2 = cartesian.line(series2Mapping);
        series2.name(getString(R.string.humidity_vi));
        series2.stroke("2 blue");
        series2.hovered().markers().enabled(true);
        series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        anyChartView.setChart(cartesian);
    }

    private void init(LayoutInflater inflater, ViewGroup container) {
        SharedPreferences sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        langVi = sharedPreferences.getBoolean(getString(R.string.lang_vi), true);

        machineInfoViewModel = new ViewModelProvider(requireActivity()).get(MachineInfoViewModel.class);

        root = inflater.inflate(R.layout.fragment_machine_info, container, false);

        imageView = root.findViewById(R.id.machine_image);
        machineTitle = root.findViewById(R.id.machine_title);
        enableManualLabel = root.findViewById(R.id.enable_manual_label);
        enableManual = root.findViewById(R.id.enable_manual);
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
        currentTemperatureTitle = root.findViewById(R.id.current_temperature_title);
        currentTemperatureTextView = root.findViewById(R.id.current_temperature_text_view);
        currentHumidityTitle = root.findViewById(R.id.current_humidity_title);
        currentHumidityTextView = root.findViewById(R.id.current_humidity_text_view);
        heatingMachineTitle = root.findViewById(R.id.heating_machine_title);
        valueOfProgress = root.findViewById(R.id.value_of_heating_machine);
        heatingMachineSeekBar = root.findViewById(R.id.heating_machine_seek_bar);
        min = root.findViewById(R.id.level_low);
        max = root.findViewById(R.id.level_high);
        statusFanTitle = root.findViewById(R.id.status_fan_title);
        blowerFanTitle = root.findViewById(R.id.blower_fan_title);
        closedBlowerFanTitle = root.findViewById(R.id.closed_blower_fan_title);
        blowerFanSwitch = root.findViewById(R.id.blower_fan_switch);
        openedBlowerFanTitle = root.findViewById(R.id.opened_blower_fan_title);
        exhaustFanTitle = root.findViewById(R.id.exhaust_fan_title);
        closedExhaustFanTitle = root.findViewById(R.id.closed_exhaust_fan_title);
        exhaustFanSwitch = root.findViewById(R.id.exhaust_fan_switch);
        openedExhaustFanTitle = root.findViewById(R.id.opened_exhaust_fan_title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.purge();
        timer.cancel();
    }

    private static class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        Handler handler = new Handler(Looper.getMainLooper());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    temp = 65 + random.nextInt(30);
                    hum = 65 + random.nextInt(30);
                    HashMap<String, String> constantlyChangingData = new HashMap<>();
                    constantlyChangingData.put(getString(R.string.current_temperature_text_view), String.valueOf(temp));
                    constantlyChangingData.put(getString(R.string.current_humidity_text_view), String.valueOf(hum));
                    machineInfoViewModel.setConstantlyChangingData(constantlyChangingData);
                    seriesData.add(new CustomDataEntry(String.valueOf(year), temp, hum));
                    set.data(seriesData);
                    year++;
                });
            }
        }, 3000, 2000);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        machineInfoViewModel.getLabel().observe(getViewLifecycleOwner(), stringStringHashMap -> {
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.image_resource), imageView);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.machine_title), machineTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.enable_manual_label), enableManualLabel);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.running_button), runningButton);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.recipe_title_list), recipeTitleList);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.machine_info), machineInfo);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.begin_time_title), beginTimeTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.completed_time_title), completedTimeTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.temperature_title), temperatureTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.humidity_title), humidityTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.food_type_title), foodTypeTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.weigh_title), weighTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.current_temperature_humidity_title), currentTemperatureHumidityTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.current_temperature_title), currentTemperatureTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.current_humidity_title), currentHumidityTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.heating_machine_title), heatingMachineTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.min), min);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.max), max);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.status_fan_title), statusFanTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.blower_fan_title), blowerFanTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.closed_blower_fan_title), closedBlowerFanTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.opened_blower_fan_title), openedBlowerFanTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.exhaust_fan_title), exhaustFanTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.closed_exhaust_fan_title), closedExhaustFanTitle);
            machineInfoViewModel.onChangeValueOfLabel(getString(R.string.opened_exhaust_fan_title), openedExhaustFanTitle);
        });

        machineInfoViewModel.getEstablishedData().observe(getViewLifecycleOwner(), stringStringHashMap -> {
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.running_button), runningButton);
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.begin_time_text_view), beginTimeTextView);
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.completed_time_text_view), completedTimeTextView);
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.temperature_text_view), temperatureTextView);
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.humidity_text_view), humidityTextView);
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.food_type_text_view), foodTypeTextView);
            machineInfoViewModel.onChangeValueOfEstablishedData(getString(R.string.weigh_text_view), weighTextView);
        });

        machineInfoViewModel.getChangedData().observe(getViewLifecycleOwner(), stringStringHashMap -> {
            machineInfoViewModel.onChangeValueOfChangedData(getString(R.string.value_of_progress), valueOfProgress);
            machineInfoViewModel.onChangeValueOfChangedData(getString(R.string.blower_fan_switch), blowerFanSwitch);
            machineInfoViewModel.onChangeValueOfChangedData(getString(R.string.exhaust_fan_switch), exhaustFanSwitch);
            machineInfoViewModel.onChangeValueOfChangedData(getString(R.string.heating_machine_seek_bar), heatingMachineSeekBar);
        });

        machineInfoViewModel.getConstantlyChangingData().observe(getViewLifecycleOwner(), stringStringHashMap -> {
            machineInfoViewModel.onChangeValueOfConstantlyChangingData(getString(R.string.current_temperature_text_view), currentTemperatureTextView);
            machineInfoViewModel.onChangeValueOfConstantlyChangingData(getString(R.string.current_humidity_text_view), currentHumidityTextView);
        });

        if (langVi) {
            setViewVi();
        } else {
            setViewEn();
        }

        enableManual.setChecked(false);
        enableManual.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                runningButton.setEnabled(true);
                recipeSpinner.setEnabled(true);
                heatingMachineSeekBar.setEnabled(true);
                blowerFanSwitch.setEnabled(true);
                exhaustFanSwitch.setEnabled(true);
            } else {
                runningButton.setEnabled(false);
                recipeSpinner.setEnabled(false);
                heatingMachineSeekBar.setEnabled(false);
                blowerFanSwitch.setEnabled(false);
                exhaustFanSwitch.setEnabled(false);
            }
        });

        runningButton.setEnabled(false);
        recipeSpinner.setEnabled(false);
        heatingMachineSeekBar.setEnabled(false);
        blowerFanSwitch.setEnabled(false);
        exhaustFanSwitch.setEnabled(false);

        runningButton.setOnClickListener(v -> {
            enableManual.setChecked(false);

            RecipeItem item = mItems.get(pos);
            beginTimeTextView.setText(item.getDryingTimeTextView());
            completedTimeTextView.setText(item.getDryingTimeTextView());
            temperatureTextView.setText(item.getTemperatureTextView());
            humidityTextView.setText(item.getHumidityTextView());
            foodTypeTextView.setText(item.getFoodTypeTextView());
            weighTextView.setText(item.getWeighTextView());

            RelativeLayout layout = root.findViewById(R.id.layout_parent);
            Snackbar.make(layout, getString(R.string.running_vi), Snackbar.LENGTH_LONG).show();
        });

        createItemList();

        RecipeAdapter adapter = new RecipeAdapter(mainActivity, R.layout.selected_recipe_layout, mItems);

        recipeSpinner.setAdapter(adapter);
        recipeSpinner.setOnItemSelectedListener(this);

        /*
            Moi khi gia tri cua seekbar thay doi thi lable gia tri cua no se duoc cap nhat va di chuyen theo
            progress poniter.
         */
        heatingMachineSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                valueOfProgress.setText(progress + "W");

                int with = seekBar.getWidth() - seekBar.getPaddingLeft() - seekBar.getPaddingRight();
                int valuePos = seekBar.getPaddingLeft() + with * seekBar.getProgress() / seekBar.getMax();

                valueOfProgress.measure(0, 0);
                int valueWidth = valueOfProgress.getMeasuredWidth();
                int delta = valueWidth / 2;

                valueOfProgress.setX(seekBar.getX() + valuePos - delta);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setViewVi() {
        HashMap<String, String> label = new HashMap<>();
        label.put(getString(R.string.image_resource), String.valueOf(R.drawable.ic_menu_camera));
        label.put(getString(R.string.enable_manual_label), getString(R.string.enable_manual_vi));
        label.put(getString(R.string.running_button), getString(R.string.run_vi));
        label.put(getString(R.string.machine_info), getString(R.string.machine_info_vi));
        label.put(getString(R.string.begin_time_title), getString(R.string.begin_time_title_vi));
        label.put(getString(R.string.completed_time_title), getString(R.string.completed_time_title_vi));
        label.put(getString(R.string.temperature_title), getString(R.string.temperature_title_vi));
        label.put(getString(R.string.humidity_title), getString(R.string.humidity_title_vi));
        label.put(getString(R.string.food_type_title), getString(R.string.food_type_title_vi));
        label.put(getString(R.string.recipe_title_list), getString(R.string.recipe_title_list_vi));
        label.put(getString(R.string.weigh_title), getString(R.string.weigh_title_vi));
        label.put(getString(R.string.current_temperature_humidity_title), getString(R.string.current_temperature_humidity_title_vi));
        label.put(getString(R.string.current_temperature_title), getString(R.string.temperature_title_vi));
        label.put(getString(R.string.current_humidity_title), getString(R.string.humidity_title_vi));
        label.put(getString(R.string.heating_machine_title), getString(R.string.heating_machine_title_vi));
        label.put(getString(R.string.min), getString(R.string.seek_bar_min));
        label.put(getString(R.string.max), getString(R.string.seek_bar_max));
        label.put(getString(R.string.status_fan_title), getString(R.string.status_fan_title_vi));
        label.put(getString(R.string.blower_fan_title), getString(R.string.blower_fan_title_vi));
        label.put(getString(R.string.closed_blower_fan_title), getString(R.string.off_vi));
        label.put(getString(R.string.opened_blower_fan_title), getString(R.string.on_vi));
        label.put(getString(R.string.exhaust_fan_title), getString(R.string.exhaust_fan_title_vi));
        label.put(getString(R.string.closed_exhaust_fan_title), getString(R.string.off_vi));
        label.put(getString(R.string.opened_exhaust_fan_title), getString(R.string.on_vi));

        if (getArguments() != null) {
            MachineInfoFragmentArgs args = MachineInfoFragmentArgs.fromBundle(getArguments());

            label.put(getString(R.string.machine_title), args.getMachineTitle());

            HashMap<String, String>  establishedData = new HashMap<>();

            establishedData.put(getString(R.string.begin_time_text_view), args.getBeginTimeTextView());
            establishedData.put(getString(R.string.completed_time_text_view), args.getCompletedTimeTextView());
            establishedData.put(getString(R.string.temperature_text_view), args.getTemperatureTextView());
            establishedData.put(getString(R.string.humidity_text_view), args.getHumidityTextView());
            establishedData.put(getString(R.string.food_type_text_view), args.getFoodTypeTextView());
            establishedData.put(getString(R.string.weigh_text_view), args.getWeighTextView());

            HashMap<String, String> changedData = new HashMap<>();
            changedData.put(getString(R.string.blower_fan_switch), String.valueOf(args.getBlowerFanSwitch()));
            changedData.put(getString(R.string.exhaust_fan_switch), String.valueOf(args.getExhaustFanSwitch()));
            changedData.put(getString(R.string.heating_machine_seek_bar), String.valueOf(args.getHeatingMachineSeekBar()));
            changedData.put(getString(R.string.value_of_progress), args.getHeatingMachineSeekBar() + "W");

            HashMap<String, String> constantlyChangingData = new HashMap<>();
            constantlyChangingData.put(getString(R.string.current_temperature_text_view), args.getCurrentTemperatureTextView());
            constantlyChangingData.put(getString(R.string.current_humidity_text_view), args.getCurrentHumidityTextView());

            ///////////////////////////////////////////////////////////////////////
            // NOTE: Luon dung Handker de thay doi element View UI de tranh gap loi
            ///////////////////////////////////////////////////////////////////////
            new Handler(Looper.getMainLooper()).post(() -> {
                machineInfoViewModel.setEstablishedData(establishedData);
                machineInfoViewModel.setChangedData(changedData);
                machineInfoViewModel.setConstantlyChangingData(constantlyChangingData);
            });
        }

        new Handler(Looper.getMainLooper()).post(() -> {
            machineInfoViewModel.setLabel(label);
        });
    }

    private void setViewEn() {
        //TODO
    }

    private void createItemList() {
        mItems = new ArrayList<>();
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title_vi, "Khoai lan", R.string.weigh_title_vi, "500", R.string.temperature_title_vi, "70", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "03:40"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title_vi, "Mít tố nữ", R.string.weigh_title_vi, "120", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "60", R.string.drying_time_title_vi, "01:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title_vi, "lúa", R.string.weigh_title_vi, "1000", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "07:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title_vi, "Chuối lá xiêm", R.string.weigh_title_vi, "200", R.string.temperature_title_vi, "60", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title_vi, "Khoai lan", R.string.weigh_title_vi, "500", R.string.temperature_title_vi, "70", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "03:40"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title_vi, "Mít tố nữ", R.string.weigh_title_vi, "120", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "60", R.string.drying_time_title_vi, "01:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title_vi, "lúa", R.string.weigh_title_vi, "1000", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "07:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title_vi, "Chuối lá xiêm", R.string.weigh_title_vi, "200", R.string.temperature_title_vi, "60", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title_vi, "Khoai lan", R.string.weigh_title_vi, "500", R.string.temperature_title_vi, "70", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "03:40"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title_vi, "Mít tố nữ", R.string.weigh_title_vi, "120", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "60", R.string.drying_time_title_vi, "01:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title_vi, "lúa", R.string.weigh_title_vi, "1000", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "07:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title_vi, "Chuối lá xiêm", R.string.weigh_title_vi, "200", R.string.temperature_title_vi, "60", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Củ cải", R.string.food_type_title_vi, "Củ cải trắng", R.string.weigh_title_vi, "300", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
        mItems.add(new RecipeItem("Khoai lan", R.string.food_type_title_vi, "Khoai lan", R.string.weigh_title_vi, "500", R.string.temperature_title_vi, "70", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "03:40"));
        mItems.add(new RecipeItem("Mít", R.string.food_type_title_vi, "Mít tố nữ", R.string.weigh_title_vi, "120", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "60", R.string.drying_time_title_vi, "01:00"));
        mItems.add(new RecipeItem("lúa", R.string.food_type_title_vi, "lúa", R.string.weigh_title_vi, "1000", R.string.temperature_title_vi, "65", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "07:00"));
        mItems.add(new RecipeItem("Chuối", R.string.food_type_title_vi, "Chuối lá xiêm", R.string.weigh_title_vi, "200", R.string.temperature_title_vi, "60", R.string.humidity_title_vi, "80", R.string.drying_time_title_vi, "02:00"));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        //TODO
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*
        Ham xu li moi khi chon cong thuc say o phan tu spinner (dropdown)
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
        RecipeItem item = mItems.get(position);
        //////////////////////////////////////////////////
        RelativeLayout layout = root.findViewById(R.id.layout_parent);
        Snackbar.make(layout, item.getRecipeTitle() + ": " + item.getWeighTextView() + getString(R.string.kg), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}