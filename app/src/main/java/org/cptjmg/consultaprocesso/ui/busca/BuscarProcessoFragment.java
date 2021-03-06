package org.cptjmg.consultaprocesso.ui.busca;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.cptjmg.consultaprocesso.BaseFragment;
import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.databinding.FragmentBuscarProcessoBinding;
import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;
import org.cptjmg.consultaprocesso.ui.processodetalhes.ProcessoDetalhesFragment;
import org.cptjmg.consultaprocesso.ui.ProcessoViewModel;


public class BuscarProcessoFragment extends BaseFragment {

    private FragmentManager fragmentManager;
    private ProcessoViewModel processoViewModel;

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

        final FragmentBuscarProcessoBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_buscar_processo, container, false);

        binding.setViewModel(processoViewModel);

        processoViewModel.processo.observe(this, new Observer<ApiResponse<Processo>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Processo> processoApiResponse) {
                hideLoading();
                if(processoApiResponse != null && processoApiResponse.getResult().size() == 1) {
                    ProcessoDetalhesFragment processoDetalhes = ProcessoDetalhesFragment.newInstance(0);
                    android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.addToBackStack(null);
                    ft.add(R.id.fragment_container, processoDetalhes);
                    ft.commit();
                }
                else if(processoApiResponse != null && processoApiResponse.getResult().size() > 1) {
                    ProcessosListaFragment processosListaFragment = new ProcessosListaFragment();
                    android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.add(R.id.fragment_container, processosListaFragment);
                    ft.commit();
                }
                else {
                    Toast.makeText(getContext(), "Servidor não encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        processoViewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        processoViewModel.displayAlert.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                showAlert(s);
            }
        });

        processoViewModel.navigateToScanner.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                CameraFragment cameraFragment = new CameraFragment();
                android.support.v4.app.FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.addToBackStack(null);
                ft.add(R.id.fragment_container, cameraFragment);
                ft.commit();
            }
        });

        return binding.getRoot();

    }

    private void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(message)
                .setTitle("Atenção");
        builder.setPositiveButton("OK", null);

        AlertDialog dialog = builder.create();

        dialog.show();
    }

}
