package com.jaspal.mvvm_retrofit.views.adapter;

import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaspal.mvvm_retrofit.R;
import com.jaspal.mvvm_retrofit.databinding.ProjectItemBinding;
import com.jaspal.mvvm_retrofit.models.Project;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.Holder> {

    List<Project> projects;
    public ProjectAdapter() {
    }
    public void setData(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProjectAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ProjectItemBinding binding=DataBindingUtil.inflate(inflater, R.layout.project_item,parent,false);
        return new Holder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.Holder holder, int position) {
        holder.binding.setProject(projects.get(position));
        holder.binding.executePendingBindings();
    }
    @Override
    public int getItemCount() {
        return projects == null ? 0 : projects.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        ProjectItemBinding binding;
        public Holder(ProjectItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
