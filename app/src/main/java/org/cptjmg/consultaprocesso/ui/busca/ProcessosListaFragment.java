package org.cptjmg.consultaprocesso.ui.busca;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.cptjmg.consultaprocesso.R;

public class ProcessosListaFragment extends Fragment {
    public ProcessosListaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processos_lista, container, false);
        
        return view;
    }
}
