package com.example.final_project_tourmate;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.example.final_project_tourmate.databinding.ActivityCreateEventTourBindingImpl;
import com.example.final_project_tourmate.databinding.ActivityEventListBindingImpl;
import com.example.final_project_tourmate.databinding.ActivityMainBindingImpl;
import com.example.final_project_tourmate.databinding.ActivityRegisterUserBindingImpl;
import com.example.final_project_tourmate.databinding.ActivityTourDetailInfoBindingImpl;
import com.example.final_project_tourmate.databinding.ShowTourDetailInfoBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCREATEEVENTTOUR = 1;

  private static final int LAYOUT_ACTIVITYEVENTLIST = 2;

  private static final int LAYOUT_ACTIVITYMAIN = 3;

  private static final int LAYOUT_ACTIVITYREGISTERUSER = 4;

  private static final int LAYOUT_ACTIVITYTOURDETAILINFO = 5;

  private static final int LAYOUT_SHOWTOURDETAILINFO = 6;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(6);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.final_project_tourmate.R.layout.activity_create__event__tour_, LAYOUT_ACTIVITYCREATEEVENTTOUR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.final_project_tourmate.R.layout.activity_event_list, LAYOUT_ACTIVITYEVENTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.final_project_tourmate.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.final_project_tourmate.R.layout.activity_register_user, LAYOUT_ACTIVITYREGISTERUSER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.final_project_tourmate.R.layout.activity_tour_detail_info, LAYOUT_ACTIVITYTOURDETAILINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.final_project_tourmate.R.layout.show_tour_detail_info, LAYOUT_SHOWTOURDETAILINFO);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYCREATEEVENTTOUR: {
          if ("layout/activity_create__event__tour__0".equals(tag)) {
            return new ActivityCreateEventTourBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_create__event__tour_ is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYEVENTLIST: {
          if ("layout/activity_event_list_0".equals(tag)) {
            return new ActivityEventListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_event_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREGISTERUSER: {
          if ("layout/activity_register_user_0".equals(tag)) {
            return new ActivityRegisterUserBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_register_user is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYTOURDETAILINFO: {
          if ("layout/activity_tour_detail_info_0".equals(tag)) {
            return new ActivityTourDetailInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_tour_detail_info is invalid. Received: " + tag);
        }
        case  LAYOUT_SHOWTOURDETAILINFO: {
          if ("layout/show_tour_detail_info_0".equals(tag)) {
            return new ShowTourDetailInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for show_tour_detail_info is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(6);

    static {
      sKeys.put("layout/activity_create__event__tour__0", com.example.final_project_tourmate.R.layout.activity_create__event__tour_);
      sKeys.put("layout/activity_event_list_0", com.example.final_project_tourmate.R.layout.activity_event_list);
      sKeys.put("layout/activity_main_0", com.example.final_project_tourmate.R.layout.activity_main);
      sKeys.put("layout/activity_register_user_0", com.example.final_project_tourmate.R.layout.activity_register_user);
      sKeys.put("layout/activity_tour_detail_info_0", com.example.final_project_tourmate.R.layout.activity_tour_detail_info);
      sKeys.put("layout/show_tour_detail_info_0", com.example.final_project_tourmate.R.layout.show_tour_detail_info);
    }
  }
}
