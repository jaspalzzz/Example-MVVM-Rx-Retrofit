package com.jaspal.mvvm_retrofit.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.jaspal.mvvm_retrofit.models.Project;
import com.jaspal.mvvm_retrofit.repository.APIResponse;
import com.jaspal.mvvm_retrofit.repository.Repository;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends AndroidViewModel {

    CompositeDisposable compositeDisposable=new CompositeDisposable();
    Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository();
    }

    public MutableLiveData<APIResponse<List<Project>>> getProjectList(String user)
    {
        return repository.getProjectList(compositeDisposable,user);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
