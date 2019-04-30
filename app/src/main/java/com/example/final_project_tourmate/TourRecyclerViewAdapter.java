package com.example.final_project_tourmate;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.final_project_tourmate.Package.TourInfo;
import com.example.final_project_tourmate.databinding.ShowTourDetailInfoBinding;

import java.text.SimpleDateFormat;
import java.util.List;


public class TourRecyclerViewAdapter extends RecyclerView.Adapter<TourRecyclerViewAdapter.ViewHolder>
{
    private Context context;
    private List<TourInfo> dataList;
    private EventListener listener;
    private TourInfo currentData;

    private SimpleDateFormat dateSDF = new SimpleDateFormat("dd/MM/yyyy");



    public TourRecyclerViewAdapter(Context context,List<TourInfo> dataList)
    {

        this.context = context;
        this.dataList = dataList;
        this.listener= (EventListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ShowTourDetailInfoBinding showTourDetailInfoBinding = ShowTourDetailInfoBinding.inflate(layoutInflater, viewGroup, false);
        return new ViewHolder(showTourDetailInfoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i)
    {
         currentData = dataList.get(i);

        viewHolder.binding.tourNameTV.setText(currentData.getTourName());
        viewHolder.binding.tourDescriptionTV.setText(currentData.getTourDescription());
        viewHolder.binding.startDateTV.setText(dateSDF.format(currentData.getStartDate()));
        viewHolder.binding.endDateTV.setText(dateSDF.format(currentData.getEndDate()));
        viewHolder.binding.budgetTV.setText(String.valueOf(currentData.getBudget()));

        viewHolder.binding.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
                MenuInflater menuInflater=popupMenu.getMenuInflater();
                menuInflater.inflate(R.menu.row_menu, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem)
                    {
                        switch (menuItem.getItemId())
                        {
                            case R.id.editID:
                                listener.onEventUpdate(currentData.getTourUid());
                                Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.deleteID:
                                listener.onEventDelete(currentData.getTourUid());
                                Toast.makeText(context, "delte", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });

            }
        });


    }
    public int getItemCount() {
        return dataList.size();
    }

    interface EventListener
    {
        void onEventDelete(String id);
        void onEventUpdate(String id);
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //private TextView nameTV, descriptionTV, startDateTV, endDateTV, budgetTV;
        private ShowTourDetailInfoBinding binding;
        public ViewHolder(@NonNull ShowTourDetailInfoBinding showTourDetailInfoBinding)
        {
            super(showTourDetailInfoBinding.getRoot());
            binding = showTourDetailInfoBinding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id=currentData.getTourUid();
                    //Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context,TourDetailInfoActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("budget",currentData.getBudget());
                    intent.putExtra("tourName",currentData.getTourName());
                    context.startActivity(intent);
                }
            });
        }
    }


}
