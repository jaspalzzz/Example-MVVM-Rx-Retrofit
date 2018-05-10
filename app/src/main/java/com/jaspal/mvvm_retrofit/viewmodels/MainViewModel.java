package com.jaspal.mvvm_retrofit.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jaspal.mvvm_retrofit.repository.APIResponse;
import com.jaspal.mvvm_retrofit.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends ViewModel {

    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<APIResponse> getProjectList(String user)
    {
        return Repository.getInstance().getProjectList(compositeDisposable,user);
    }


    @Override
    protected void onCleared() {
        super.onCleared();

    }

}
