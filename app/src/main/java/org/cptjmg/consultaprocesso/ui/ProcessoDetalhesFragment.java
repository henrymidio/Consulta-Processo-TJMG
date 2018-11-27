package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.databinding.FragmentProcessoDetalhesBinding;


public class ProcessoDetalhesFragment extends Fragment {

    ProcessoViewModel processoViewModel;

    public ProcessoDetalhesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processoViewModel = ViewModelProviders.of(getActivity()).get(ProcessoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProcessoDetalhesBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_processo_detalhes, container, false);
        View view = binding.getRoot();

        binding.setProcesso(processoViewModel.processo.getValue().getResult());

        return view;
    }
}
