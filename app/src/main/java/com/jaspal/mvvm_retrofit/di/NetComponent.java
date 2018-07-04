package com.jaspal.mvvm_retrofit.di;

import com.jaspal.mvvm_retrofit.repository.Repository;
import com.jaspal.mvvm_retrofit.views.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetComponent {
     void inject(Repository repository);
}
