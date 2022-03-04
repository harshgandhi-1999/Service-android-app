package com.example.serviceapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyService extends Service {
    private static final String TAG = "MyService";

    private MediaPlayer mediaPlayer;
    private final int notification_id = 1;

    public MyService() {
        Log.i(TAG, "MyService: MY_SERVICE_CONSTRUCTOR_CALLED");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: SERVICE_CREATED");
        if(mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.soch_liya_song);
            mediaPlayer.setLooping(true);
        }
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: SERVICE_STARTED");
        if(mediaPlayer!=null && !mediaPlayer.isPlaying() &&  intent.getAction().equals(getString(R.string.play_song))){
            mediaPlayer.start();
            postNotification("Music is playing");
        }

        if(mediaPlayer!=null && mediaPlayer.isPlaying() && intent.getAction().equals(getString(R.string.pause_song))){
            mediaPlayer.pause();
            postNotification("Music is paused");
        }

        return START_STICKY;
    }

    private void createNotificationChannel(){
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(TAG,name,importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void postNotification(String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,TAG)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Service App")
                .setContentText(msg)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(TAG,notification_id,builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: SERVICE_DESTROYED");
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
            postNotification("Music has stopped");
        }
    }
}