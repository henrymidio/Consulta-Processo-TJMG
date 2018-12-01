package org.cptjmg.consultaprocesso.ui.busca;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.cptjmg.consultaprocesso.R;
import org.cptjmg.consultaprocesso.model.Processo;
import org.cptjmg.consultaprocesso.ui.ProcessoViewModel;
import org.cptjmg.consultaprocesso.ui.processodetalhes.ProcessoDetalhesFragment;

import java.util.List;

public class ProcessosListaFragment extends Fragment {

    private ProcessoViewModel processoViewModel;
    private List<Processo> processos;

    public ProcessosListaFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        processoViewModel = ViewModelProviders.of(getActivity()).get(ProcessoViewModel.class);
        
        processos = processoViewModel.processo.getValue().getResult();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processos_lista, container, false);

        ListView listaDeProcessos = view.findViewById(R.id.processosLista);

        ArrayAdapter<Processo> adapter = new ArrayAdapter<Processo>(getContext(),
                android.R.layout.simple_list_item_1, processos);

        listaDeProcessos.setAdapter(adapter);

        listaDeProcessos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();

                ProcessoDetalhesFragment processoDetalhes = ProcessoDetalhesFragment.newInstance(position);
                android.support.v4.app.FragmentTransaction ft = supportFragmentManager.beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.add(R.id.fragment_container, processoDetalhes);
                ft.commit();
            }
        });

        return view;
    }
}
