package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.cptjmg.consultaprocesso.R;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setSubtitle("TJMG");

        ViewModelProviders.of(this).get(ProcessoViewModel.class)
                .init(this);

        BuscarProcessoFragment buscarProcessoFragment = BuscarProcessoFragment.newInstance();
        android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, buscarProcessoFragment);
        ft.commit();
    }
}
