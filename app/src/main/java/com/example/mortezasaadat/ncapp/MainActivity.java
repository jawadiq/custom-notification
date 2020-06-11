package com.example.mortezasaadat.ncapp;

import android.app.Notification;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWatchMovie;

    // Instance Variables
    private NotificationHandler notificationHandler;

    private static final int WATCH_MOVIE_NOTIFICATION_ID = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationHandler = new NotificationHandler(MainActivity.this);

        edtWatchMovie = findViewById(R.id.edtWatchMovie);

        findViewById(R.id.btnWatchMovie).setOnClickListener(MainActivity.this);
        findViewById(R.id.btnMovieSettings).setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnWatchMovie:

                postNotificationToUserDevice(WATCH_MOVIE_NOTIFICATION_ID,
                        edtWatchMovie.getText().toString());
                break;
            case R.id.btnMovieSettings:

                openNotificationSettingsForWatchMovieChannel(
                        NotificationHandler.WATCH_MOVIE_NOTIFICATION_CHANNEL_ID);
                break;

        }

    }

    private void postNotificationToUserDevice(int notificaionID, String titleText) {

        Notification.Builder notificationBuilder = null;

        switch (notificaionID) {

            case WATCH_MOVIE_NOTIFICATION_ID:

                notificationBuilder = notificationHandler.
                        createAndReturnWatchMovieNotification(titleText,
                                "This is a great movie");

                break;

        }

        if (notificationBuilder != null) {

            notificationHandler.notifyTheUser(WATCH_MOVIE_NOTIFICATION_ID,
                    notificationBuilder);
        }

    }

    private void openNotificationSettingsForWatchMovieChannel(String watchMovieChannelID) {


        Intent settingsIntent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        settingsIntent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        settingsIntent.putExtra(Settings.EXTRA_CHANNEL_ID, watchMovieChannelID);
        startActivity(settingsIntent);


    }
}
