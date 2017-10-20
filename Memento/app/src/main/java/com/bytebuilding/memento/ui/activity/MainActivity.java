package com.bytebuilding.memento.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bytebuilding.memento.R;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Initialize SharedPreferences
                /*SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());*/

                //  Create a new boolean and preference and set it to true
                //boolean isFirstStart = getPrefs.getBoolean("firstStart", true);

                //  If the activity has never started before...
                //if (isFirstStart) {

                //  Launch app intro
                final Intent i = new Intent(MainActivity.this, IntroActivity.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(i);
                        finish();
                    }
                });

                //  Make a new preferences editor
                //SharedPreferences.Editor e = getPrefs.edit();

                //  Edit preference to make it false because we don't want this to run again
                //e.putBoolean("firstStart", false);

                //  Apply changes
                //e.apply();
            }
        }
        );

        // Start the thread
        t.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mDisposable.dispose();
    }
}
