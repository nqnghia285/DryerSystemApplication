package com.nqnghia.dryersystemapplication.ui.statistics;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.nqnghia.dryersystemapplication.MainActivity;
import com.nqnghia.dryersystemapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel statisticsViewModel;
    private GraphView graph;
    private BarGraphSeries<DataPoint> series1;
    private LineGraphSeries<DataPoint> series2;

    private Random random = new Random();
    private MainActivity mainActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call setHasOptionsMenu() to Fragment can setup menu
        setHasOptionsMenu(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statisticsViewModel =
                ViewModelProviders.of(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

        graph = root.findViewById(R.id.graph);
        //graph.getViewport().setYAxisBoundsManual(true);
        //graph.getViewport().setScalable(true);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setTextColor(Color.rgb(0, 255, 255));
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setMargin(40);
        graph.getLegendRenderer().setPadding(10);
        graph.getViewport().setMaxY(80);
        graph.getViewport().setMinY(0);
        graph.setTitle("Biểu đồ nhiệt độ(°C) & độ ẩm(%)");
        graph.setTitleColor(Color.BLUE);
        graph.setBackgroundColor(Color.rgb(255, 165, 0));
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Thời gian(phút)");
        graph.getGridLabelRenderer().setVerticalAxisTitle("°C & %");
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(Color.BLUE);
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(Color.GREEN);

        series1 = new BarGraphSeries<>();
        series1.setTitle("Độ ẩm");
        series1.setDataWidth(0.5);
        series1.setColor(Color.BLUE);
        graph.addSeries(series1);

        series2 = new LineGraphSeries<>();
        series2.setTitle("Nhiệt độ");
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(10);
        series2.setThickness(1);
        series2.setColor(Color.GREEN);
        graph.addSeries(series2);

        setupChart();

        /////////////////////////////////////////////////////
        AnyChartView anyChartView = root.findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(root.findViewById(R.id.progress_bar));

        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Rouge", 80540));
        data.add(new ValueDataEntry("Foundation", 94190));
        data.add(new ValueDataEntry("Mascara", 102610));
        data.add(new ValueDataEntry("Lip gloss", 110430));
        data.add(new ValueDataEntry("Lipstick", 128000));
        data.add(new ValueDataEntry("Nail polish", 143760));
        data.add(new ValueDataEntry("Eyebrow pencil", 170670));
        data.add(new ValueDataEntry("Eyeliner", 213210));
        data.add(new ValueDataEntry("Eyeshadows", 249980));

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("${%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Top 10 Cosmetic Products by Revenue");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("${%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Product");
        cartesian.yAxis(0).title("Revenue");

        anyChartView.setChart(cartesian);

        return root;
    }

    private void setupChart() {
        for (int i = 0; i < 50; i++) {
            series1.appendData(new DataPoint(i, random.nextInt(50)), true, 80);
            series2.appendData(new DataPoint(i, random.nextInt(50)), true, 80);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivity = (MainActivity) context;
//            mainActivity.getFab().hide();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        //TODO
        super.onCreateOptionsMenu(menu, inflater);
    }
}