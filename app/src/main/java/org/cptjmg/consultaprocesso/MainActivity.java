package org.cptjmg.consultaprocesso;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setSubtitle("TJMG");
        //myToolbar.setLogo(R.mipmap.ic_launcher_round);

        BuscarProcessoFragment buscarProcessoFragment = BuscarProcessoFragment.newInstance();
        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, buscarProcessoFragment);
        ft.commit();
    }
}
