package com.jaspal.mvvm_retrofit.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jaspal.mvvm_retrofit.models.Project;
import com.jaspal.mvvm_retrofit.repository.Repository;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    public LiveData<List<Project>> getProjectList(String user)
    {
        return Repository.getInstance().getProjectList(user);
    }
}
