package com.androidakbar.dz_7_1_1_and;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

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

                DateFormat timeFormat = new SimpleDateFormat("HH", Locale.getDefault());
                int time = Integer.parseInt(timeFormat.format(Calendar.getInstance().getTime()));


                if (time >= 6 && time < 14) {
                    intent.setData(Uri.parse("http://morning"));
                } else if (time >= 14 && time < 15) {
                    intent.setData(Uri.parse("http://afternoon"));
                } else {
                    intent.setData(Uri.parse("http://evening"));
                }

                startActivity(intent);

            }
        });

    }


}