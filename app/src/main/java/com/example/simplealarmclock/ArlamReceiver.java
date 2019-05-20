package com.example.simplealarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ArlamReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String str_get=intent.getExtras().getString("extra");
    Intent myIntert= new Intent(context ,Music.class);
    myIntert.putExtra("extra",str_get);
    context.startService(myIntert);
    }
}
