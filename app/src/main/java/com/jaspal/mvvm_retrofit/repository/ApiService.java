package com.jaspal.mvvm_retrofit.repository;

import com.jaspal.mvvm_retrofit.models.Project;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Single<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String projectName);
}
