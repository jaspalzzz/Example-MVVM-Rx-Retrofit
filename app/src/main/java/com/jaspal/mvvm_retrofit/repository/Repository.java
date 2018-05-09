package com.jaspal.mvvm_retrofit.repository;

import android.arch.lifecycle.MutableLiveData;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jaspal.mvvm_retrofit.models.Project;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public String BASE_URL = "https://api.github.com/";
    private ApiService apiService;
    private static Repository instance;

    private Repository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public synchronized static Repository getInstance() {
        if (instance == null) {
            if (instance == null) {
                instance = new Repository();
            }
        }
        return instance;
    }

    public MutableLiveData<List<Project>> getProjectList(String user)
    {
        final MutableLiveData<List<Project>> list=new MutableLiveData<>();
        apiService.getProjectList(user).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                list.setValue(response.body());
            }
            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                list.setValue(null);
            }
        });
        return list;
    }
}
