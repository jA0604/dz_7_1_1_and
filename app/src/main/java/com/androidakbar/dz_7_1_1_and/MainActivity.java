package com.androidakbar.dz_7_1_1_and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appToolbar = findViewById(R.id.app_toolbar);
        appToolbar.setTitle(R.string.name_dz);
        appToolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryText));

        ((Button)findViewById(R.id.btn_sync)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SYNC);

                if (CurrentTimeBetwen(6, 14)) {
                    intent.setData(Uri.parse("http://morning"));
                } else if(CurrentTimeBetwen(14, 15)) {
                    intent.setData(Uri.parse("http://afternoon"));
                } else if (CurrentTimeBetwen(15, 6)) {
                    intent.setData(Uri.parse("http://evening"));
                }

                startActivity(intent);

            }
        });

    }

    private boolean CurrentTimeBetwen(int startTime, int endTime) {
        GregorianCalendar gcCurrent = new GregorianCalendar();
        GregorianCalendar gcGiven = new GregorianCalendar();

        gcGiven.set(Calendar.HOUR_OF_DAY, startTime);
        gcGiven.set(Calendar.MINUTE, 0);
        gcGiven.set(Calendar.SECOND, 0);

        boolean result;
        if (startTime > endTime) {
            result = (gcCurrent.getTimeInMillis() - (gcGiven.getTimeInMillis() - 86400000) > 0);
        } else {
            result = (gcCurrent.getTimeInMillis() - gcGiven.getTimeInMillis() > 0);
        }

        gcGiven.set(Calendar.HOUR_OF_DAY, endTime);
        gcGiven.set(Calendar.MINUTE, 0);
        gcGiven.set(Calendar.SECOND, 0);

        if (startTime > endTime) {
            return result && (gcCurrent.getTimeInMillis() - (gcGiven.getTimeInMillis() + 86400000) < 0);
        } else {

            return result && (gcCurrent.getTimeInMillis() - gcGiven.getTimeInMillis() < 0);
        }
    }
}