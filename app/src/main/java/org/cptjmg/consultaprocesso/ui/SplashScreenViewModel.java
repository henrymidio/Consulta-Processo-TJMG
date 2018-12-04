package org.cptjmg.consultaprocesso.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import org.cptjmg.consultaprocesso.data.ProcessoRepository;

public class SplashScreenViewModel extends ViewModel {

    private ProcessoRepository processoRepository;

    public void init(Context context) {
        processoRepository = new ProcessoRepository(context);
    }

    public LiveData<Boolean> pingServer() {
        return processoRepository.pingService();
    }

}
