package com.example.final_project_tourmate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.final_project_tourmate.Package.TourInfo;
import com.example.final_project_tourmate.databinding.ShowTourDetailInfoBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class TourRecyclerViewAdapter extends RecyclerView.Adapter<TourRecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<TourInfo> dataList;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String user;

    public TourRecyclerViewAdapter(List<TourInfo> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ShowTourDetailInfoBinding showTourDetailInfoBinding = ShowTourDetailInfoBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(showTourDetailInfoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final TourInfo currentData = dataList.get(i);
        viewHolder.binding.tourNameTV.setText(currentData.getTourName());
        viewHolder.binding.tourDescriptionTV.setText(currentData.getTourDescription());
        viewHolder.binding.budgetTV.setText(String.valueOf(currentData.getBudget()));

    }
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //private TextView nameTV, descriptionTV, startDateTV, endDateTV, budgetTV;
        private ShowTourDetailInfoBinding binding;
        public ViewHolder(@NonNull ShowTourDetailInfoBinding showTourDetailInfoBinding) {
            super(showTourDetailInfoBinding.getRoot());
            binding = showTourDetailInfoBinding;
        }
    }
}
