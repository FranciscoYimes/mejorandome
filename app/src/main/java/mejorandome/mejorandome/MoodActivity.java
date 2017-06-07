package mejorandome.mejorandome;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MoodActivity extends AppCompatActivity {


    private Button saveMoodButton;
    private CircularSeekBar happySeekBar;
    private TextView happyTextProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);



        happySeekBar = (CircularSeekBar) findViewById(R.id.seekbar_happy);
        happyTextProgress = (TextView) findViewById(R.id.happy_progress_text);
        happyTextProgress.setText(String.valueOf(happySeekBar.getProgress()));


        happySeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {

                happySeekBar.setProgress(GetAproximateProgress(happySeekBar.getProgress()));
                happyTextProgress.setText(String.valueOf(happySeekBar.getProgress()/20));
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

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

    public int GetAproximateProgress(int customProgress)
    {
        if(customProgress<20)
        {
            return 0;
        }
        if(customProgress<40)
        {
            return 20;
        }
        if(customProgress<60)
        {
            return 40;
        }
        if(customProgress<80)
        {
            return 60;
        }
        if(customProgress<90)
        {
            return 80;
        }
        return 100;
    }
}
