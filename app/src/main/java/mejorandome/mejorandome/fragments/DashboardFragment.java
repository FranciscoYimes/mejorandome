package mejorandome.mejorandome.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.PieChartView;
import mejorandome.mejorandome.CheckinActivity;
import mejorandome.mejorandome.MoodActivity;
import mejorandome.mejorandome.R;

public class DashboardFragment extends Fragment {

    private PieChartView pieChart;
    private PieChartData data;
    private ColumnChartView columnChartView;
    private ColumnChartData columnChartData;
    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasLabelForSelected = false;
    private View rootView;
    private Button moodButton;
    private Button checkinButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        pieChart = (PieChartView) rootView.findViewById(R.id.pie_chart);
        pieChart.setInteractive(true);

        columnChartView = (ColumnChartView) rootView.findViewById(R.id.column_chart);

        moodButton = (Button) rootView.findViewById(R.id.mood_button);
        checkinButton = (Button) rootView.findViewById(R.id.checkin_button);
        moodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardFragment.this.getActivity(), MoodActivity.class);
                startActivity(i);
            }
        });

        checkinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardFragment.this.getActivity(), CheckinActivity.class);
                startActivity(i);
            }
        });

        pieChart.setChartRotationEnabled(false);

        pieChart.setOnValueTouchListener(new ValueTouchListener());
        //toggleLabels();
        generateData();

        return rootView;
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(DashboardFragment.this.getActivity(), value.getValue() + " %", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
        }
    }

    private void generateData() {

        List<SliceValue> values = new ArrayList<>();

        SliceValue sliceValueBuenas = new SliceValue((float) 15, ContextCompat.getColor(rootView.getContext(),R.color.color_green));
        values.add(sliceValueBuenas);

        SliceValue sliceValueMalas = new SliceValue((float) 12, ContextCompat.getColor(rootView.getContext(),R.color.color_red));
        values.add(sliceValueMalas);

        data = new PieChartData(values);

        pieChart.setPieChartData(data);
    }
}
