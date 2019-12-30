package com.iremipek.AcPisir.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String durum = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if(durum.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {
            Toast.makeText(context,"Çalıyor",Toast.LENGTH_LONG).show();
        }

        if(durum.equals(TelephonyManager.EXTRA_STATE_IDLE))
        {
            Toast.makeText(context, "Kapandı", Toast.LENGTH_LONG).show();
        }

    }

}
