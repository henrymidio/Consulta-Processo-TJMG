package org.cptjmg.consultaprocesso.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import org.cptjmg.consultaprocesso.MainApp;
import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProcessoRepository {


    private Retrofit retrofit;

    private Context context;
    private ProcessoService processoService;

    public ProcessoRepository(Context context) {
        this.context = context;
        retrofit = NetworkServiceLocator.getRetrofit();
        processoService = retrofit.create(ProcessoService.class);
    }

    public MutableLiveData<ApiResponse<Processo>> getProcesso(String numProcesso, int instancia) {
        final MutableLiveData<ApiResponse<Processo>> result = new MutableLiveData<>();

        Call<ApiResponse<Processo>> repos = processoService.getProcesso(numProcesso, instancia);

        repos.enqueue(new Callback<ApiResponse<Processo>>() {
            @Override
            public void onResponse(Call<ApiResponse<Processo>> call, Response<ApiResponse<Processo>> response) {
                try {
                    if(response.isSuccessful()) {
                        //Log.e("classe", response.body().getStatus_code());
                        result.setValue(response.body());
                    } else {
                        result.setValue(null);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    result.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Processo>> call, Throwable t) {
                result.setValue(null);
                t.printStackTrace();
            }
        });

        return result;
    }

    public LiveData<Boolean> pingService() {
        final MutableLiveData<Boolean> ret = new MutableLiveData<>();

        processoService.pingServer().enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("resposta", response.body());
                ret.setValue(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("resposta", t.getMessage());
                ret.setValue(false);
            }
        });

        return ret;
    }

}
