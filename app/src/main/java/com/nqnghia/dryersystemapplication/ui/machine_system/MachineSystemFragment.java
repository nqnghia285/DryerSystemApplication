package com.nqnghia.dryersystemapplication.ui.machine_system;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        machineSystemViewModel =
                ViewModelProviders.of(this).get(MachineSystemViewModel.class);

        root = inflater.inflate(R.layout.fragment_machine_system, container, false);

        createItemList();
        buildRecyclerView();

        mainActivity.setItemSelectedListener(item -> {
            Log.e(TAG, "onOptionsItemSelected: ");
            switch (item.getItemId()) {
                case R.id.action_select_all:
                    for (MachineItem machineItem : mItems) {
                        machineItem.setSelected(true);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                case R.id.action_uncheck_all:
                    for (MachineItem machineItem : mItems) {
                        machineItem.setSelected(false);
                    }
                    mAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        });

        return root;
    }

    void createItemList() {
        mItems = new ArrayList<>();
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 01", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 02", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 03", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 04", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 05", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 06", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 07", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 08", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 09", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 10", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 11", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 12", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 13", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 14", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 15", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 16", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 17", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 18", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 19", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_share, "Máy số 20", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
        mItems.add(new MachineItem(R.drawable.ic_menu_camera, "Máy số 21", "Trạng thái hoạt động:", "Đang hoạt động", "Thời gian bắt đầu:", "12:30:25", "Thời gian kết thúc:", "13:30:25", "Loại nguyên liệu:", "Mit tuoi", "Khối lượng:", "100", "Nhiệt độ hiện tại:", "65", "Độ ẩm hiện tại:", "65"));
    }

    void buildRecyclerView() {
        mRecyclerView = root.findViewById(R.id.recycle_list_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new AdapterMachineItem(mItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            changeDestination(position);
        });
    }

    void changeDestination(int position) {
        NavController navController = Navigation.findNavController(mainActivity, R.id.nav_host_fragment);
        MachineSystemFragmentDirections.ActionNavMachineSystemToNavMachineInfo action = MachineSystemFragmentDirections.actionNavMachineSystemToNavMachineInfo();
        action.setImageResource(R.drawable.ic_menu_camera);
        int number = position + 1;
        action.setMachineTitle("Machine " + number);
        action.setMachineInfo("Machine Information");
        action.setBeginTimeTitle("Begin time:");
        action.setBeginTimeTextView("12:30:25");
        action.setCompletedTimeTitle("Completed time:");
        action.setCompletedTimeTextView("13:30:25");
        action.setTemperatureTitle("Temperature:");
        action.setTemperatureTextView("70");
        action.setHumidityTitle("Humidity:");
        action.setHumidityTextView("80");
        action.setFoodTypeTitle("Food type:");
        action.setFoodTypeTextView("Chile");
        action.setWeighTitle("Weigh:");
        action.setWeighTextView("300");
        action.setCurrentTemperatureHumidityTitle("Current temperature and humidity:");
        action.setCurrentTemperatureTitle("Current temperature:");
        action.setCurrentTemperatureTextView("65");
        action.setCurrentHumidityTitle("Current humidity:");
        action.setCurrentHumidityTextView("72");
        action.setStatusFanTitle("Status fan:");
        action.setBlowerFanTitle("Blower fan:");
        action.setClosedBlowerFanTextView("OFF");
        action.setBlowerFanSwitch(true);
        action.setOpenedBlowerFanTextView("ON");
        action.setExhaustFanTitle("Exhaust fan:");
        action.setClosedExhaustFanTextView("OFF");
        action.setExhaustFanSwitch(true);
        action.setOpenedExhaustFanTextView("ON");

        navController.navigate(action);
    }
}