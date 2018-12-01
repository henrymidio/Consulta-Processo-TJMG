package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import org.cptjmg.consultaprocesso.data.ProcessoRepository;
import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;
import org.cptjmg.consultaprocesso.util.SingleLiveEvent;
import org.cptjmg.consultaprocesso.util.Validator;

import java.util.List;

public class ProcessoViewModel extends ViewModel {

    public ObservableField<String> numProcesso = new ObservableField<>();

    private SingleLiveEvent<String> _displayAlert = new SingleLiveEvent();
    public LiveData<String> displayAlert = _displayAlert;

    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    private ProcessoRepository processoRepository;

    private MediatorLiveData<ApiResponse<Processo>> _processo = new MediatorLiveData<>();
    public LiveData<ApiResponse<Processo>> processo = _processo;

    public void init(Context context) {
        processoRepository = new ProcessoRepository(context);
    }

    public void onBuscarClicked() {

        _isLoading.setValue(true);
/*
        if(!Validator.isNumProcessoValid(numProcesso.get())) {
            _isLoading.setValue(false);
            _displayAlert.setValue("Para numeração interna do tribunal o número do processo deve ter 17 dígitos");
            return;
        }
*/
        fetchProcesso();

    }

    private void fetchProcesso() {
        // TODO: Ajustar instância
        final MutableLiveData<ApiResponse<Processo>> tempProcesso = processoRepository.getProcesso("01772277020148130479", "1");

        _processo.addSource(tempProcesso, new Observer<ApiResponse<Processo>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<Processo> processoApiResponse) {
                _processo.removeSource(tempProcesso);
                _processo.postValue(processoApiResponse);
            }
        });
    }

}
