package com.example.final_project_tourmate.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public abstract class ActivityRegisterUserBinding extends ViewDataBinding {
  @NonNull
  public final EditText emailET;

  @NonNull
  public final EditText nameET;

  @NonNull
  public final EditText passwordET;

  @NonNull
  public final EditText phnET;

  @NonNull
  public final EditText rePasswordET;

  @NonNull
  public final Button regBtn;

  protected ActivityRegisterUserBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, EditText emailET, EditText nameET, EditText passwordET, EditText phnET,
      EditText rePasswordET, Button regBtn) {
    super(_bindingComponent, _root, _localFieldCount);
    this.emailET = emailET;
    this.nameET = nameET;
    this.passwordET = passwordET;
    this.phnET = phnET;
    this.rePasswordET = rePasswordET;
    this.regBtn = regBtn;
  }

  @NonNull
  public static ActivityRegisterUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityRegisterUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityRegisterUserBinding>inflate(inflater, com.example.final_project_tourmate.R.layout.activity_register_user, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityRegisterUserBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityRegisterUserBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityRegisterUserBinding>inflate(inflater, com.example.final_project_tourmate.R.layout.activity_register_user, null, false, component);
  }

  public static ActivityRegisterUserBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityRegisterUserBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityRegisterUserBinding)bind(component, view, com.example.final_project_tourmate.R.layout.activity_register_user);
  }
}
