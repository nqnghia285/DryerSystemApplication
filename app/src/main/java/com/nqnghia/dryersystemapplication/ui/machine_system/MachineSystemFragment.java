package com.nqnghia.dryersystemapplication.ui.machine_system;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.nqnghia.dryersystemapplication.AdapterMachineItem;
import com.nqnghia.dryersystemapplication.MachineItem;
import com.nqnghia.dryersystemapplication.MainActivity;
import com.nqnghia.dryersystemapplication.R;
import com.nqnghia.dryersystemapplication.RecipeItem;
import com.nqnghia.dryersystemapplication.ui.machine_info.MachineInfoFragment;

import java.util.ArrayList;

public class MachineSystemFragment extends Fragment {
    private static final String TAG = "MachineSystemFragment";
    private MachineSystemViewModel machineSystemViewModel;

    private MainActivity mainActivity;
    private View root;

    private RecyclerView mRecyclerView;
    private AdapterMachineItem mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MachineItem> mItems;
    private volatile int mPosition = 0;

    @Override
    public void onAttach(Context context) {
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        machineSystemViewModel =
                ViewModelProviders.of(this).get(MachineSystemViewModel.class);

        root = inflater.inflate(R.layout.fragment_machine_system, container, false);

        createItemList();
        buildRecyclerView();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mainActivity != null) {
            mainActivity.getFab().setOnClickListener(v -> {
                showMachineItemDialog();
            });
        }
    }

    // setup action menu from fragment
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_select_all:
                selectAll();
                break;
            case R.id.action_uncheck_all:
                uncheckAll();
                break;
            case R.id.action_remove:
                remove();
                break;
            case R.id.action_add_item:
                showMachineItemDialog();
                break;
            case R.id.action_edit_item:
                editMachineItemDialog();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMachineItemDialog() {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        @SuppressLint("InflateParams") View vi = inflater.inflate(R.layout.machine_item_dialog, null);

        EditText machine = vi.findViewById(R.id.machine_text_dialog);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.machine_title_alert_dialog_vi);
        builder.setView(vi);
        builder.setIcon(R.drawable.ic_add_box_black_24dp);
        builder.setPositiveButton(R.string.ok_vi, (dialog, which) -> {
            if (!machine.getText().toString().equals("")) {

                mItems.add(new MachineItem(
                        R.drawable.ic_menu_camera,
                        machine.getText().toString(),
                        R.string.status_title_vi,
                        R.string.running_vi,
                        R.string.begin_time_title_vi,
                        "12:00:00",
                        R.string.completed_time_title_vi,
                        "13:30:00",
                        R.string.food_type_title_vi,
                        R.string.orange_vi,
                        R.string.weigh_title_vi,
                        "300",
                        R.string.current_temperature_title_vi,
                        "65",
                        R.string.current_humidity_title_vi,
                        "72"));

                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(R.string.cancel_vi, (dialog, which) -> {

        });
        builder.create().show();
    }

    private void editMachineItemDialog() {
        int counter = 0;
        for (MachineItem machineItem : mItems) {
            if (machineItem.getSelected()) {
                counter++;
            }
        }

        if (counter != 0) {
            if (counter > 1) {
                uncheckAll();
            } else {
                LayoutInflater inflater = LayoutInflater.from(mainActivity);
                @SuppressLint("InflateParams") View vi = inflater.inflate(R.layout.machine_item_dialog, null);

                EditText machine = vi.findViewById(R.id.machine_text_dialog);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(R.string.machine_title_alert_dialog_2_vi);
                builder.setView(vi);
                builder.setIcon(R.drawable.ic_add_box_black_24dp);
                builder.setPositiveButton(R.string.ok_vi, (dialog, which) -> {
                    if (!machine.getText().toString().equals("")) {
                        mItems.get(mPosition).setTitle(machine.getText().toString());
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton(R.string.cancel_vi, (dialog, which) -> {

                });
                builder.create().show();
            }
        }
    }

    private void selectAll() {
        for (MachineItem machineItem : mItems) {
            machineItem.setSelected(true);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void uncheckAll() {
        for (MachineItem machineItem : mItems) {
            machineItem.setSelected(false);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void remove() {
        int position = 0;
        while (position < mItems.size()) {
            if (mItems.get(position).getSelected()) {
                mItems.remove(position);
            } else {
                position++;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void createItemList() {
        mItems = new ArrayList<>();
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 01", R.string.status_title_vi, R.string.running_vi, R.string.begin_time_title_vi, "12:00:00", R.string.completed_time_title_vi, "13:30:00", R.string.food_type_title_vi, R.string.orange_vi, R.string.weigh_title_vi, "300", R.string.current_temperature_title_vi, "65", R.string.current_humidity_title_vi, "72"));
        mItems.add(new MachineItem(R.drawable.ic_add_box_black_24dp, "Máy số 02", R.string.status_title_vi, R.string.running_vi, R.string.begin_time_title_vi, "12:00:00", R.string.completed_time_title_vi, "13:30:00", R.string.food_type_title_vi, R.string.orange_vi, R.string.weigh_title_vi, "300", R.string.current_temperature_title_vi, "65", R.string.current_humidity_title_vi, "72"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 03", R.string.status_title_vi, R.string.running_vi, R.string.begin_time_title_vi, "12:00:00", R.string.completed_time_title_vi, "13:30:00", R.string.food_type_title_vi, R.string.orange_vi, R.string.weigh_title_vi, "300", R.string.current_temperature_title_vi, "65", R.string.current_humidity_title_vi, "72"));
        mItems.add(new MachineItem(R.drawable.ic_add_box_black_24dp, "Máy số 04", R.string.status_title_vi, R.string.running_vi, R.string.begin_time_title_vi, "12:00:00", R.string.completed_time_title_vi, "13:30:00", R.string.food_type_title_vi, R.string.orange_vi, R.string.weigh_title_vi, "300", R.string.current_temperature_title_vi, "65", R.string.current_humidity_title_vi, "72"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 05", R.string.status_title_vi, R.string.running_vi, R.string.begin_time_title_vi, "12:00:00", R.string.completed_time_title_vi, "13:30:00", R.string.food_type_title_vi, R.string.orange_vi, R.string.weigh_title_vi, "300", R.string.current_temperature_title_vi, "65", R.string.current_humidity_title_vi, "72"));
    }

    private void buildRecyclerView() {
        mRecyclerView = root.findViewById(R.id.recycle_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new AdapterMachineItem(mItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((view, position) -> {
            if (view.getId() == R.id.image_card_view) {
                Log.e(TAG, "changeDestination(" + position + ")");
                changeDestination(position);
            } else if (view.getId() == R.id.machine_card_view) {
                Log.e(TAG, "set position: " + position);
                mPosition = position;
            }
        });
    }

    private void changeDestination(int position) {
        NavController navController = Navigation.findNavController(mainActivity, R.id.nav_host_fragment);
        MachineSystemFragmentDirections.ActionNavMachineSystemToNavMachineInfo action =
                MachineSystemFragmentDirections.actionNavMachineSystemToNavMachineInfo();
        action.setPosition(position);
        action.setImageResource(mItems.get(position).getImageResource());
        action.setMachineTitle(mItems.get(position).getTitle());
        action.setBeginTimeTextView("00:00");
        action.setCompletedTimeTextView("00:00");
        action.setTemperatureTextView("70");
        action.setHumidityTextView("80");
        action.setFoodTypeTextView("Chile");
        action.setWeighTextView("300");
        action.setCurrentTemperatureTextView("65");
        action.setCurrentHumidityTextView("72");
        action.setHeatingMachineSeekBar(100);
        action.setBlowerFanSwitch(true);
        action.setExhaustFanSwitch(true);

        navController.navigate(action);
    }
}