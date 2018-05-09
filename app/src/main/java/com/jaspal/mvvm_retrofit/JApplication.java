package com.jaspal.mvvm_retrofit;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

public class JApplication extends Application {
    public static JApplication instance;
    public ViewModelProvider.NewInstanceFactory provider;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        provider=new ViewModelProvider.AndroidViewModelFactory(this);
    }
    public ViewModelProvider.NewInstanceFactory getViewModelProvider()
    {
        return provider;
    }
    public static JApplication getInstance() {
        return instance;
    }
}
