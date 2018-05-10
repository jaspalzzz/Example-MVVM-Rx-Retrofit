package com.jaspal.mvvm_retrofit.views;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jaspal.mvvm_retrofit.R;
import com.jaspal.mvvm_retrofit.databinding.ActivityMainBinding;
import com.jaspal.mvvm_retrofit.models.Project;
import com.jaspal.mvvm_retrofit.repository.APIResponse;
import com.jaspal.mvvm_retrofit.repository.StatusEnum;
import com.jaspal.mvvm_retrofit.viewmodels.MainViewModel;
import com.jaspal.mvvm_retrofit.views.adapter.ProjectAdapter;
import com.jaspal.mvvm_retrofit.views.ui.BaseActivity;

import java.util.List;

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
        viewModel.getProjectList("jaspalzzz").observe(MainActivity.this, new Observer<APIResponse>() {
            @Override
            public void onChanged(@Nullable APIResponse APIResponse) {
                binding.setSetloading(false);
                handleProjectList(APIResponse);
            }
        });
    }

    private void handleProjectList(APIResponse response) {
        switch (response.getStatus()) {
            case ERROR:
                binding.setSetloading(false);
                Toast.makeText(MainActivity.this, "Error : "+response.getError(), Toast.LENGTH_SHORT).show();
                break;
            case SUCCESS:
                binding.setSetloading(false);
                adapter.setData(response.getResponse());
                break;
            case LOADING:
                binding.setSetloading(true);
                break;
        }
    }

   /* private void handleProjectList(List<Project> response) {
            adapter.setData(response);
    }*/
}
