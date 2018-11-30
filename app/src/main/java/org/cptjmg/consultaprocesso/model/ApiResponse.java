package org.cptjmg.consultaprocesso.model;

import java.util.List;

public class ApiResponse<T> {
    private String status_code;
    private List<T> result;

    private String error;
    private String message;

    public ApiResponse() {
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
