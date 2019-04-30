package com.example.final_project_tourmate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.final_project_tourmate.Package.ExpenseInfo;
import com.example.final_project_tourmate.databinding.ShowExpenseListBinding;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>
{
    private Context context;
    private List<ExpenseInfo> expenseList;
    private OnItemClickListener listener;

    public ExpenseAdapter(Context context, List<ExpenseInfo> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ShowExpenseListBinding showExpenseListBinding = ShowExpenseListBinding.inflate(layoutInflater,viewGroup,false);
        return new ViewHolder(showExpenseListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ExpenseInfo expense = expenseList.get(i);
        viewHolder.binding.expenseTypeTV.setText(expense.getExpenseDescription());
        viewHolder.binding.amountTV.setText(String.valueOf(expense.getTourCost()));


    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        ShowExpenseListBinding binding;
        public ViewHolder(@NonNull ShowExpenseListBinding showExpenseListBinding) {
            super(showExpenseListBinding.getRoot());
            binding=showExpenseListBinding;
            showExpenseListBinding.getRoot().setOnClickListener(this);
            showExpenseListBinding.getRoot().setOnCreateContextMenuListener(this);


            showExpenseListBinding.getRoot().setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {


                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                    menu.setHeaderTitle("Please select below");
                    MenuItem edit = menu.add(Menu.NONE,1,1,"Edit");
                    MenuItem delete = menu.add(Menu.NONE,2,2,"Delete");


                    PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
                    MenuInflater menuInflater=popupMenu.getMenuInflater();
                    menuInflater.inflate(R.menu.row_menu, popupMenu.getMenu());
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem)
                        {
                            if (listener!=null){
                                int position = getAdapterPosition();
                                if (position!=RecyclerView.NO_POSITION){
                                    switch (menuItem.getItemId()){
                                        case 1:
                                            listener.onEdit(position);
                                            return true;
                                        case 2:
                                            listener.onDelete(position);
                                            return true;
                                    }
                                }
                            }
                            return false;
                        }
                    });




                }
            });

        }



     /* @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Please select below");
             MenuItem edit = menu.add(Menu.NONE,1,1,"Edit");
             MenuItem delete = menu.add(Menu.NONE,2,2,"Delete");
             edit.setOnMenuItemClickListener(this);
             delete.setOnMenuItemClickListener(this);

        }*/


        @Override
        public void onClick(View v) {
            if (listener!=null){
                int position = getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    listener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


            PopupMenu popupMenu=new PopupMenu(v.getContext(),v);
            MenuInflater menuInflater=popupMenu.getMenuInflater();
            menuInflater.inflate(R.menu.row_menu, popupMenu.getMenu());
            popupMenu.show();

        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (listener!=null){
                int position = getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION){
                    switch (item.getItemId()){
                        case 1:
                            listener.onEdit(position);
                            return true;
                        case 2:
                            listener.onDelete(position);
                            return true;
                    }
                }
            }
            return false;
        }


    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onEdit(int position);
        void onDelete(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

}
