package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.cptjmg.consultaprocesso.BaseFragment;
import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;
import org.cptjmg.consultaprocesso.util.CommonUtils;


public class BuscarProcessoFragment extends BaseFragment {

    Button btnBuscar;
    FragmentManager fragmentManager;
    ProcessoViewModel processoViewModel;

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

        processoViewModel = ViewModelProviders.of(getActivity()).get(ProcessoViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_busca_processo, container, false);

        processoViewModel.processo.observe(this, new Observer<ApiResponse<Processo>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Processo> processoApiResponse) {
                hideLoading();
                if(processoApiResponse.getResult() != null) {
                    ProcessoDetalhesFragment processoDetalhes = new ProcessoDetalhesFragment();
                    android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.addToBackStack(null);
                    ft.add(R.id.fragment_container, processoDetalhes);
                    ft.commit();
                } else {
                    Toast.makeText(getContext(), "Erro", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBuscar = view.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                processoViewModel.getProcesso("01772277020148130479", "479");
            }
        });

        return view;
    }



}
