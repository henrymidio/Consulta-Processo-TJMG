package org.cptjmg.consultaprocesso.data;

import org.cptjmg.consultaprocesso.model.ApiResponse;
import org.cptjmg.consultaprocesso.model.Processo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProcessoService {
    @GET("processos/{numProcesso}/")
    Call<ApiResponse<Processo>> getProcesso(@Path("numProcesso") String numProcesso);
}
