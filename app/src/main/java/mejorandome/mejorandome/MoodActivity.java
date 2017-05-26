package mejorandome.mejorandome;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class MoodActivity extends AppCompatActivity {

    PieChartView pieChartView1;

    private PieChartData data;
    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasLabelForSelected = false;
    private Button saveMoodButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        pieChartView1 = (PieChartView) findViewById(R.id.pie_chart1);

        saveMoodButton = (Button) findViewById(R.id.save_mood_button);

        saveMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pieChartView1.setChartRotationEnabled(false);

        pieChartView1.setOnValueTouchListener(new ValueTouchListener());

        toggleLabels();
        generateData();
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(MoodActivity.this, value.getValue() + " %", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
        }
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;
        if (hasLabels) {
            hasLabelForSelected = false;
            pieChartView1.setValueSelectionEnabled(hasLabelForSelected);
            if (hasLabelsOutside) {
                pieChartView1.setCircleFillRatio(0.7f);
            } else {
                pieChartView1.setCircleFillRatio(1.0f);
            }
        }
    }

    private void generateData() {

        List<SliceValue> values = new ArrayList<>();

        SliceValue sliceValueBuenas = new SliceValue((float) 15, ContextCompat.getColor(getApplicationContext(),R.color.color_green));
        values.add(sliceValueBuenas);

        SliceValue sliceValueMalas = new SliceValue((float) 12, ContextCompat.getColor(getApplicationContext(),R.color.color_red));
        values.add(sliceValueMalas);


        data = new PieChartData(values);
        data.setHasLabels(hasLabels);
        pieChartView1.setPieChartData(data);
    }
}
