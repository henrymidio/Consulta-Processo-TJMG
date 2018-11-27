package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;

import org.cptjmg.consultaprocesso.data.ProcessoRepository;
import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;

public class ProcessoViewModel extends ViewModel {

    private ProcessoRepository processoRepository;

    private MediatorLiveData<ApiResponse<Processo>> _processo = new MediatorLiveData<>();
    LiveData<ApiResponse<Processo>> processo = _processo;


    public void init(Context context) {
        processoRepository = new ProcessoRepository(context);
    }

    public void getProcesso(String numProcesso, String comarca) {
        final MutableLiveData<ApiResponse<Processo>> tempProcesso = processoRepository.getProcesso(numProcesso, comarca);

        _processo.addSource(tempProcesso, new Observer<ApiResponse<Processo>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Processo> processoApiResponse) {
                _processo.removeSource(tempProcesso);
                _processo.postValue(processoApiResponse);
            }
        });
    }

}
