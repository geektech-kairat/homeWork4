package com.example.lesson22.ui.home.HomeAdapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson22.App;
import com.example.lesson22.databinding.ItemLayoutBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ItemLayoutBinding binding;
    private Listen listen;
    private List<HomeModel> list = new ArrayList<>();


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBind(list.get(position), listen);
        if (position % 2 == 0) {
            binding.holderItem.setBackgroundColor(Color.CYAN);
        } else {
            binding.holderItem.setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public HomeAdapter(Listen listen) {
        this.listen = listen;
    }

    public void addModel(HomeModel homeModel) {
        list.add(homeModel);
        notifyDataSetChanged();
    }

    public void addList(List<HomeModel> homeModelList) {
        list = homeModelList;
        notifyDataSetChanged();
    }

    public void sortList() {
        Collections.sort(list, new Comparator<HomeModel>() {
            @Override
            public int compare(HomeModel o1, HomeModel o2) {
                return o1.getNumber().compareTo(o2.getName());
            }
        });

    }


    public HomeModel getHomeByPosition(int position) {
        return list.get(position);
    }

    public HomeModel getModelToId(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {


        public HomeViewHolder(@NonNull ItemLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void onBind(HomeModel homeModel, Listen listen) {


            binding.nameItem.setText(homeModel.getName());
            binding.numberItem.setText(homeModel.getNumber());
//            binding.date.setText(homeModel.getDate());

            binding.getRoot().setOnClickListener(v -> {

                listen.setDataForForm(homeModel, getAdapterPosition());
            });

            binding.getRoot().setOnLongClickListener(v -> {


                listen.del(getAdapterPosition());
                return true;
            });
        }
    }
}
