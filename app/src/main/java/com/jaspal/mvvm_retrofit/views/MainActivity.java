package com.jaspal.mvvm_retrofit.views;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jaspal.mvvm_retrofit.R;
import com.jaspal.mvvm_retrofit.databinding.ActivityMainBinding;
import com.jaspal.mvvm_retrofit.models.Project;
import com.jaspal.mvvm_retrofit.viewmodels.MainViewModel;
import com.jaspal.mvvm_retrofit.views.adapter.ProjectAdapter;
import com.jaspal.mvvm_retrofit.views.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    ProjectAdapter adapter;

    @Override
    public ActivityBinding getBindingActivity() {
        return new BaseActivity.ActivityBinding(R.layout.activity_main, MainViewModel.class);
    }

    @Override
    public void onCreateActivity(Bundle savedInstanceState) {
        adapter = new ProjectAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        binding.projectList.setLayoutManager(manager);
        binding.projectList.setAdapter(adapter);
        inflateProjectList();
    }

    public void inflateProjectList() {
        binding.setSetloading(true);
        viewModel.getProjectList("google").observe(MainActivity.this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                binding.setSetloading(false);
                adapter.setData(projects);
            }
        });
    }
}
