package com.jaspal.mvvm_retrofit.repository;

import android.arch.lifecycle.MutableLiveData;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jaspal.mvvm_retrofit.JApplication;
import com.jaspal.mvvm_retrofit.models.Project;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    @Inject ApiService apiService;
    public Repository() {
        JApplication.getInstance().getNetComponent().inject(Repository.this);
    }
    public MutableLiveData<APIResponse<List<Project>>> getProjectList(CompositeDisposable compositeDisposable, String user)
    {
        final MutableLiveData<APIResponse<List<Project>>> projectListResponse=new MutableLiveData<>();
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
