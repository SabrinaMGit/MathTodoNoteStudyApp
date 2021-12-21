package com.example.user.work.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.work.models.Themen;
import com.example.user.work.R;
import com.example.user.work.recyclerview.itemholders.ThemenHolder;

import java.util.List;

public class ThemenAdapter extends RecyclerView.Adapter<ThemenHolder> {

    private final List<Themen> themer;
    private Context context;
    private int itemResource;

    public ThemenAdapter(Context context, int itemResource, List<Themen> themen) {

        // 1. Initialize our adapter
        this.themer = themen;
        this.context = context;
        this.itemResource = itemResource;
    }

    // 2. Override the onCreateViewHolder method
    @Override
    public ThemenHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 3. Inflate the view and return the new ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(this.itemResource, parent, false);
        return new ThemenHolder(this.context, view);
    }

    // 4. Override the onBindViewHolder method
    @Override
    public void onBindViewHolder(ThemenHolder holder, int position) {

        // 5. Use position to access the correct Bakery object
        Themen themer = this.themer.get(position);

        // 6. Bind the bakery object to the holder
        holder.bindThemen(themer);
    }

    @Override
    public int getItemCount() {

        return this.themer.size();
    }
}