package mejorandome.mejorandome.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import mejorandome.mejorandome.CheckinActivity;
import mejorandome.mejorandome.MoodActivity;
import mejorandome.mejorandome.R;

public class DashboardFragment extends Fragment {

    private PieChartView pieChart;
    private PieChartData data;

    private View rootView;
    private Button moodButton;
    private Button checkinButton;
    private int dni;
    private int positiveEmotions;
    private int negativeEmotions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dni = getActivity().getIntent().getIntExtra("dni",0);

        pieChart = (PieChartView) rootView.findViewById(R.id.pie_chart);
        pieChart.setInteractive(true);

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

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        GetEmotionsData();

        return rootView;
    }

    private void generateData() {

        List<SliceValue> values = new ArrayList<>();

        SliceValue sliceValueBuenas = new SliceValue((float) positiveEmotions, ContextCompat.getColor(rootView.getContext(),R.color.color_green));
        values.add(sliceValueBuenas);

        SliceValue sliceValueMalas = new SliceValue((float) negativeEmotions, ContextCompat.getColor(rootView.getContext(),R.color.color_red));
        values.add(sliceValueMalas);

        data = new PieChartData(values);

        pieChart.setPieChartData(data);
    }

    private void GetPositiveNegativeData()
    {
        positiveEmotions = 78;
        negativeEmotions = 22;
    }

    private void GetEmotionsData()
    {
        GetPositiveNegativeData();
        generateData();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
