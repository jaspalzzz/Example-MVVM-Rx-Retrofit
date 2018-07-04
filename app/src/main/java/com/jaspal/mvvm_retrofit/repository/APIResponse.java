package com.jaspal.mvvm_retrofit.repository;

import com.jaspal.mvvm_retrofit.models.Project;

import java.util.List;

public class APIResponse<T> {

    StatusEnum statusEnum;
    T response;
    Throwable error;

    public APIResponse(){}

    public APIResponse(StatusEnum statusEnum,T response, Throwable error) {
        this.response = response;
        this.error = error;
        this.statusEnum=statusEnum;
    }

    public APIResponse setLoading()
    {
        return new APIResponse(StatusEnum.LOADING,null,null);
    }

    public APIResponse setResponse(List<Project> response)
    {
        return new APIResponse(StatusEnum.SUCCESS,response,null);
    }

    public APIResponse setError(Throwable error)
    {
        return new APIResponse(StatusEnum.ERROR,null,error);
    }

    public T getResponse() {
        return response;
    }

    public Throwable getError() {
        return error;
    }

    public StatusEnum getStatus() {
        return statusEnum;
    }
}
