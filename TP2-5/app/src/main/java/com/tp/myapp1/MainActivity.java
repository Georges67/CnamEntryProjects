package com.tp.myapp1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

    private ToggleButton m_startButton;
    private TextView m_counterView;
    private SeekBar m_mySeekBar;
    private ProgressBar m_myProgressBar;
    private TextView m_myProgressTextView;


    private Thread m_counterThread;
    private int m_counter;
    private Boolean m_counting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_counterView = this.findViewById(R.id.counter);
        m_startButton = this.findViewById(R.id.toggleStart);

        m_myProgressTextView = this.findViewById(R.id.progressValueTextView);
        m_mySeekBar = this.findViewById(R.id.mySeekBar);
        m_mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                m_counter = i;
                m_myProgressBar.setProgress(i);
                m_myProgressTextView.setText(String.format("Progress ; %d", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        m_myProgressBar = (ProgressBar) this.findViewById(R.id.myProgressBar);
    }

    public void onStartClick(View view) {
        if( m_startButton.isChecked() ) {
            m_counting = true;
            // TO complete ...
        }
        else {
            m_counting = false;
        }
    }

    private void showCounter() {
        m_myProgressTextView.setText("Progress: " + m_counter);
        m_counterView.setText(String.valueOf(m_counter));
        if(m_counter <= 100)
            m_myProgressBar.setProgress(m_counter);
    }
}
