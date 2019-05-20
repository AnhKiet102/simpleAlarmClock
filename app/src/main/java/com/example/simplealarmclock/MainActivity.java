package com.example.simplealarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button btnSettime,btnStop;
    TextView txtshow;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSettime=(Button)findViewById(R.id.btnSetTime);
        btnStop=(Button)findViewById(R.id.btnStop);
        timePicker=(TimePicker)findViewById(R.id.timepicker);
        txtshow=(TextView)findViewById(R.id.txtshow);
        calendar=Calendar.getInstance();
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        final Intent intent= new Intent(MainActivity.this,ArlamReceiver.class);
        btnSettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                int hour=timePicker.getCurrentHour();
                int min= timePicker.getCurrentMinute();

              //  String str_hour= String.valueOf(hour);
              //  String str_min= String.valueOf(min);
                String str_hour = null;
                String str_min= null;

                if(hour >12)
                {
                    str_hour= String.valueOf(hour-12);
                }

                if(min <10)
                {
                  str_min= "0"+ String.valueOf(min);
                }
                else {
                    str_min=String.valueOf(min);
                }


                intent.putExtra("extra","on");
                pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);

                txtshow.setText("Giờ bạn đặt là: "+ str_hour + ":" +str_min);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtshow.setText("Đã dừng !");

               alarmManager.cancel(pendingIntent);
               intent.putExtra("extra","off");
               sendBroadcast(intent);
            }
        });

    }
}
