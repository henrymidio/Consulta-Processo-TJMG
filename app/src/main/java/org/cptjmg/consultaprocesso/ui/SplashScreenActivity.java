package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.data.ProcessoRepository;
import org.cptjmg.consultaprocesso.util.NetworkUtils;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        SplashScreenViewModel splashScreenViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);
        splashScreenViewModel.init(this);

        final Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);

        if(NetworkUtils.isNetworkAvailable(this)) {
            splashScreenViewModel.pingServer().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean aBoolean) {
                    Log.d("network state", "available");
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Log.d("network state", "not available");
            startActivity(intent);
            finish();
        }
    }
}
