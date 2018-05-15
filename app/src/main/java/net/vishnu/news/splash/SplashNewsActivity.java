package net.vishnu.news.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import net.vishnu.news.R;
import net.vishnu.news.base.BaseNewsActivity;
import net.vishnu.news.main.MainNewsActivity;

public class SplashNewsActivity extends BaseNewsActivity {

    private static final int TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startMainActivity();
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashNewsActivity.this, MainNewsActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIME_OUT);
    }
}
