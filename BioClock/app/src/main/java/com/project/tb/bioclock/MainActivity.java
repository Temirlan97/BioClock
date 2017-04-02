package com.project.tb.bioclock;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView description_textView;
    private ProgressBar started_ProgressBar;
    private Button start_button;
    private Button stop_button;
    private TextView result_textView;
    private Button reset_button;
    private  TextView waiting_info;

    private long started = 0L, ended = 0L, result = 0L, low = 50000L,
            mid = 60000L, up = 70000L, low_mid = 56000L, up_mid = 64000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description_textView = (TextView) findViewById(R.id.description_textView);
        started_ProgressBar = (ProgressBar) findViewById(R.id.started_progress_bar);
        start_button = (Button) findViewById(R.id.start_button);
        stop_button = (Button) findViewById(R.id.stop_button);
        result_textView = (TextView) findViewById(R.id.result);
        reset_button = (Button) findViewById(R.id.reset_button);
        waiting_info = (TextView) findViewById(R.id.waiting_info);
    }

    void startCounting(View itemView){
        result_textView.setVisibility(View.INVISIBLE);
        reset_button.setVisibility(View.INVISIBLE);
        description_textView.setVisibility(View.INVISIBLE);
        started_ProgressBar.setVisibility(View.VISIBLE);
        waiting_info.setVisibility(View.VISIBLE);
        start_button.setVisibility(View.INVISIBLE);
        stop_button.setVisibility(View.VISIBLE);
        started = System.currentTimeMillis();
    }

    void stopCounting(View itemView){
        ended = System.currentTimeMillis();
        result = ended - started;
        waiting_info.setVisibility(View.INVISIBLE);
        started_ProgressBar.setVisibility(View.INVISIBLE);
        stop_button.setVisibility(View.INVISIBLE);
        if (result < low){
            String res = Long.toString(result/1000);
            res = "You pressed button in " + res + " seconds.\n" + getString(R.string.too_fast);
            result_textView.setText(res);
        } else if (result < low_mid){
            String res = Long.toString(result/1000);
            res = "You pressed button in " + res + " seconds.\n" + getString(R.string.fast);
            result_textView.setText(res);
        } else if (result < up_mid){
            String res = Long.toString(result/1000);
            res = "You pressed button in " + res + " seconds.\n" + getString(R.string.precise);
            result_textView.setText(res);
        } else if (result < up){
            String res = Long.toString(result/1000);
            res = "You pressed button in " + res + " seconds.\n" + getString(R.string.slow);
            result_textView.setText(res);
        } else{
            String res = Long.toString(result/1000);
            res = "You pressed button in " + res + " seconds.\n" + getString(R.string.tooSlow);
            result_textView.setText(res);
        }
        result_textView.setVisibility(View.VISIBLE);
        reset_button.setVisibility(View.VISIBLE);
    }

}
