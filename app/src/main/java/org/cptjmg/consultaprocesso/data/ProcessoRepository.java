package org.cptjmg.consultaprocesso.data;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProcessoRepository {

    private Retrofit retrofit;
    private Context context;

    public ProcessoRepository(Context context) {
        this.context = context;
        retrofit = NetworkServiceLocator.getRetrofit();
    }

    public MutableLiveData<ApiResponse<Processo>> getProcesso(String numProcesso, int instancia) {
        final MutableLiveData<ApiResponse<Processo>> result = new MutableLiveData<>();

        ProcessoService service = retrofit.create(ProcessoService.class);
        Call<ApiResponse<Processo>> repos = service.getProcesso(numProcesso, instancia);

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

}
