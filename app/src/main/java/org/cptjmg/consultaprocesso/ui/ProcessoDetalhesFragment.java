package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.databinding.FragmentProcessoDetalhesBinding;
import org.cptjmg.consultaprocesso.model.Processo;

import java.util.List;


public class ProcessoDetalhesFragment extends Fragment {

    ProcessoViewModel processoViewModel;
    RecyclerView recyclerView;
    Processo processo;

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

        recyclerView = binding.timelineRV;
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        TimelineAdapter tlAdapter = new TimelineAdapter(processo.getMovimentacao());
        recyclerView.setAdapter(tlAdapter);

        binding.setProcesso(processo);

        createLayoutPartes(binding.containerPartes);

        return view;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    private void createLayoutPartes(LinearLayout ll) {
        boolean titulo = true;

        List<String> partes = processo.getPartes();

        for (String parte : partes) {

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            if(titulo) {
                TextView textView = new TextView(getContext());
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
                textView.setText(parte);
                linearLayout.addView(textView);
                titulo = false;
            } else {
                TextView textView2 = new TextView(getContext());
                textView2.setText(parte);
                linearLayout.addView(textView2);
                titulo = true;
            }

            ll.addView(linearLayout);

        }


    }
}
