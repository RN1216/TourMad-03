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

public abstract class ActivityCreateEventTourBinding extends ViewDataBinding {
  @NonNull
  public final EditText endDateAddTourET;

  @NonNull
  public final Button saveTourInfoAddNewTourBtn;

  @NonNull
  public final EditText startDateAddTourET;

  @NonNull
  public final EditText tourBudgetET;

  @NonNull
  public final EditText tourDescriptionET;

  @NonNull
  public final EditText tourNameET;

  protected ActivityCreateEventTourBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, EditText endDateAddTourET, Button saveTourInfoAddNewTourBtn,
      EditText startDateAddTourET, EditText tourBudgetET, EditText tourDescriptionET,
      EditText tourNameET) {
    super(_bindingComponent, _root, _localFieldCount);
    this.endDateAddTourET = endDateAddTourET;
    this.saveTourInfoAddNewTourBtn = saveTourInfoAddNewTourBtn;
    this.startDateAddTourET = startDateAddTourET;
    this.tourBudgetET = tourBudgetET;
    this.tourDescriptionET = tourDescriptionET;
    this.tourNameET = tourNameET;
  }

  @NonNull
  public static ActivityCreateEventTourBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCreateEventTourBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCreateEventTourBinding>inflate(inflater, com.example.final_project_tourmate.R.layout.activity_create__event__tour_, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityCreateEventTourBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ActivityCreateEventTourBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ActivityCreateEventTourBinding>inflate(inflater, com.example.final_project_tourmate.R.layout.activity_create__event__tour_, null, false, component);
  }

  public static ActivityCreateEventTourBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ActivityCreateEventTourBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ActivityCreateEventTourBinding)bind(component, view, com.example.final_project_tourmate.R.layout.activity_create__event__tour_);
  }
}
