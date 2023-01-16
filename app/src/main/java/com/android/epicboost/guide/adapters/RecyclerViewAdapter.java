package com.android.epicboost.guide.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.epicboost.guide.R;
import com.android.epicboost.guide.model.Item;
import com.android.epicboost.guide.listeners.onClickItemListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    List<Item> itemList;
    onClickItemListener listener;

    public RecyclerViewAdapter(Activity context, List<Item> itemList, onClickItemListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        RecyclerViewViewHolder viewHolder = new RecyclerViewViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        Picasso.get().load(item.getPoster_path()).into(viewHolder.poster);

        viewHolder.title.setText(item.getName());
        String overview =item.getOverview();
        if (overview.length()<150) viewHolder.overview.setText(overview);
        else viewHolder.overview.setText(overview.substring(0,150)+"...");

        viewHolder.rating.setText(Double.toString(item.getVote_average()));
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

class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
    ImageView poster;
    TextView title;
    TextView overview;
    TextView rating;

    public RecyclerViewViewHolder(@NonNull View itemView) {
        super(itemView);
        poster = itemView.findViewById(R.id.poster);
        title = itemView.findViewById(R.id.title);
        overview = itemView.findViewById(R.id.overview);
        rating = itemView.findViewById(R.id.rating);
    }
}
