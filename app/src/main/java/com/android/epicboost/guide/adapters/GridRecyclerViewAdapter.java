package com.android.epicboost.guide.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.epicboost.guide.R;
import com.android.epicboost.guide.model.ItemDetails;
import com.android.epicboost.guide.listeners.onClickItemListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    Activity context;
    List<ItemDetails> itemList;
    onClickItemListener listener;

    public GridRecyclerViewAdapter(Activity context, List<ItemDetails> itemList, onClickItemListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.grid_item, parent, false);

        GridRecyclerViewViewHolder viewHolder = new GridRecyclerViewViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemDetails item = itemList.get(position);
        GridRecyclerViewViewHolder viewHolder= (GridRecyclerViewViewHolder) holder;

        Picasso.get().load(item.getPoster_path()).into(viewHolder.poster);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view,item.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

class GridRecyclerViewViewHolder extends RecyclerView.ViewHolder {
    ImageView poster;

    public GridRecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
        poster = itemView.findViewById(R.id.movie_poster);
    }
}

