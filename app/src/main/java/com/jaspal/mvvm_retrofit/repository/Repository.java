package com.jaspal.mvvm_retrofit.repository;

import android.arch.lifecycle.MutableLiveData;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jaspal.mvvm_retrofit.models.Project;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public String BASE_URL = "http://api.github.com/";
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

    public MutableLiveData<APIResponse> getProjectList(CompositeDisposable compositeDisposable, String user)
    {
        final MutableLiveData<APIResponse> projectListResponse=new MutableLiveData<>();
        apiService.getProjectList(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Project>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        projectListResponse.setValue(new APIResponse().setLoading());
                    }
                    @Override
                    public void onSuccess(List<Project> projects) {
                        projectListResponse.setValue(new APIResponse().setResponse(projects));
                    }
                    @Override
                    public void onError(Throwable e) {
                        projectListResponse.setValue(new APIResponse().setError(e));
                    }
                });
        return projectListResponse;
    }
}
