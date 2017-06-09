package mejorandome.mejorandome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import mejorandome.mejorandome.SeekBarClasses.RangeSliderView;

public class MoodActivity extends AppCompatActivity {


    private Button saveMoodButton;
    private RangeSliderView rangeSliderView1;
    private RangeSliderView rangeSliderView2;
    private RangeSliderView rangeSliderView3;
    private RangeSliderView rangeSliderView4;
    private RangeSliderView rangeSliderView5;
    private RangeSliderView rangeSliderView6;
    private int[] range;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        range = new int[6];

        rangeSliderView1 = (RangeSliderView) findViewById(R.id.seekbar_1);
        rangeSliderView2 = (RangeSliderView) findViewById(R.id.seekbar_2);
        rangeSliderView3 = (RangeSliderView) findViewById(R.id.seekbar_3);
        rangeSliderView4 = (RangeSliderView) findViewById(R.id.seekbar_4);
        rangeSliderView5 = (RangeSliderView) findViewById(R.id.seekbar_5);
        rangeSliderView6 = (RangeSliderView) findViewById(R.id.seekbar_6);

        rangeSliderView1.setInitialIndex(3);
        rangeSliderView2.setInitialIndex(1);
        rangeSliderView3.setInitialIndex(2);
        rangeSliderView4.setInitialIndex(4);
        rangeSliderView5.setInitialIndex(2);
        rangeSliderView6.setInitialIndex(3);

        rangeSliderView1.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                range[0] = index;
            }
        });

        rangeSliderView2.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                range[1] = index;
            }
        });

        rangeSliderView3.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                range[2] = index;
            }
        });

        rangeSliderView4.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                range[3] = index;
            }
        });

        rangeSliderView5.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                range[4] = index;
            }
        });

        rangeSliderView6.setOnSlideListener(new RangeSliderView.OnSlideListener() {
            @Override
            public void onSlide(int index) {
                range[5] = index;
            }
        });

        saveMoodButton = (Button) findViewById(R.id.save_mood_button);
        saveMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
