package com.jaspal.mvvm_retrofit.views.ui;

import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.jaspal.mvvm_retrofit.JApplication;

public abstract class BaseActivity<T extends ViewDataBinding, V extends ViewModel> extends AppCompatActivity {
    public T binding;
    public V viewModel;
    public abstract ActivityBinding getBindingActivity();
    public abstract void onCreateActivity(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBinding activityBinding = getBindingActivity();
        binding = DataBindingUtil.setContentView(this, activityBinding.getLayoutResId());
        viewModel = JApplication.getInstance().getViewModelProvider().create(activityBinding.getClazz());
        onCreateActivity(savedInstanceState);
    }
    public class ActivityBinding {
        @LayoutRes
        private int layoutId;
        private Class<V> clazz;
        public ActivityBinding(@LayoutRes int layoutId, Class<V> clazz) {
            this.layoutId = layoutId;
            this.clazz = clazz;
        }
        public int getLayoutResId() {
            return layoutId;
        }
        public Class<V> getClazz() {
            return clazz;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
