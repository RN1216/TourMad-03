package com.example.final_project_tourmate.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public abstract class ActivityEventListBinding extends ViewDataBinding {
  @NonNull
  public final Button addEvnt;

  @NonNull
  public final RecyclerView recyclerView;

  protected ActivityEventListBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, Button addEvnt, RecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.addEvnt = addEvnt;
    this.recyclerView = recyclerView;
  }

  @NonNull
  public static ActivityEventListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityEventListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityEventListBinding>inflate(inflater, com.example.final_project_tourmate.R.layout.activity_event_list, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityEventListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityEventListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityEventListBinding>inflate(inflater, com.example.final_project_tourmate.R.layout.activity_event_list, null, false, component);
  }

  public static ActivityEventListBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityEventListBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityEventListBinding)bind(component, view, com.example.final_project_tourmate.R.layout.activity_event_list);
  }
}
