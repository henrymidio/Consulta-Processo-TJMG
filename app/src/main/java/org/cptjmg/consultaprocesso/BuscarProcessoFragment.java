package org.cptjmg.consultaprocesso;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BuscarProcessoFragment extends Fragment {

    Button btnBuscar;
    FragmentManager fragmentManager;

    public BuscarProcessoFragment() {
        // Required empty public constructor
    }

    public static BuscarProcessoFragment newInstance() {
        BuscarProcessoFragment fragment = new BuscarProcessoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busca, container, false);

        btnBuscar = view.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProcessoDetalhes processoDetalhes = new ProcessoDetalhes();
                android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.add(R.id.fragment_container, processoDetalhes);
                ft.commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }



}
