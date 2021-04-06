package com.example.lesson22.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson22.R;
import com.example.lesson22.databinding.BoardItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private BoardItemBinding binding;
    private Click click;
    private String[] text = new String[]{"Лучшие идеи приходят в голову неожиданно. " +
            "Даже если вы не планируете писать музыку или книги, блокнот позволит не терять " +
            "интересные мысли и творческое вдохновение.", "Если вы боретесь со стрессом или депрессией," +
            " попробуйте завести личный дневник", "Необязательно соблюдать правила орфографии и пунктуации. " +
            "Суть ведения дневника в том, чтобы писать не думая. "};
    private Integer[] gif = new Integer[]{R.drawable.gif1, R.drawable.gif2, R.drawable.gif3};


    public BoardAdapter(Click click) {
        this.click = click;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(BoardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.bind(position, click);
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    class BoardViewHolder extends RecyclerView.ViewHolder {


        public BoardViewHolder(@NonNull BoardItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(int position, Click click) {
            binding.title.setText(text[position]);
            binding.gifSet.setImageResource(gif[position]);
        }
    }
}
