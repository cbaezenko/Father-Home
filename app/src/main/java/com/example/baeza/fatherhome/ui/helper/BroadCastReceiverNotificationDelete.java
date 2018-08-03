package com.example.baeza.fatherhome.ui.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

public class BroadCastReceiverNotificationDelete  extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Timber.d("Listening broadcast");
            EventBus.getDefault().post(new AudioEvent());
            //releasePlayer();
        }
}
