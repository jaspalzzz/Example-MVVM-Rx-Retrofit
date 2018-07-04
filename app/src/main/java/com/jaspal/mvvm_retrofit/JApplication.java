package com.jaspal.mvvm_retrofit;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.jaspal.mvvm_retrofit.di.DaggerNetComponent;
import com.jaspal.mvvm_retrofit.di.NetComponent;
import com.jaspal.mvvm_retrofit.di.NetworkModule;


public class JApplication extends Application {
    public static JApplication instance;
    public ViewModelProvider.NewInstanceFactory provider;
    public String BASE_URL="http://api.github.com/";
    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        netComponent= DaggerNetComponent.builder()
                .networkModule(new NetworkModule(BASE_URL))
                .build();
        provider=new ViewModelProvider.AndroidViewModelFactory(this);
    }
    public NetComponent getNetComponent(){
        return netComponent;
    }
    public ViewModelProvider.NewInstanceFactory getViewModelProvider()
    {
        return provider;
    }
    public static JApplication getInstance() {
        return instance;
    }
}
