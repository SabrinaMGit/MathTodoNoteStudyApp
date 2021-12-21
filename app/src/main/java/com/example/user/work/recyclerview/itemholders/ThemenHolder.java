package com.example.user.work.recyclerview.itemholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.work.models.Themen;
import com.example.user.work.R;

public class ThemenHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ImageView themaLogo;
    private final TextView themaName;
    private final TextView thema1;
    private final TextView description;
    private final TextView thema2;
    private final TextView thema3;

    private Themen themen;
    private Context context;

    public ThemenHolder(Context context, View itemView) {

        super(itemView);

        // 1. Set the context
        this.context = context;

        // 2. Inflate the UI widgets of the holder
        this.themaLogo = (ImageView) itemView.findViewById(R.id.themen_logo);
        this.themaName = (TextView) itemView.findViewById(R.id.themen_name);
        this.thema1 = (TextView) itemView.findViewById(R.id.thema1);
        this.description = (TextView) itemView.findViewById(R.id.themen_description);
        this.thema2 = (TextView) itemView.findViewById(R.id.thema2);
        this.thema3 = (TextView) itemView.findViewById(R.id.thema3);

        // 3. Set the "onClick" listener of the holder
        itemView.setOnClickListener(this);
    }

    public void bindThemen(Themen thema) {

        // 4. Bind the data to the ViewHolder
        this.themen = thema;
        this.themaName.setText(thema.themenName);
        this.thema1.setText(thema.thema1);
        this.thema2.setText(thema.thema2);
        this.thema3.setText(thema.thema3);
        this.description.setText(thema.description);
        this.themaLogo.setImageBitmap(thema.logo);
    }

    @Override
    public void onClick(View v) {

        // 5. Handle the onClick event for the ViewHolder
        if (this.themen != null) {

            Toast.makeText(this.context, "Clicked on " + this.themen.themenName, Toast.LENGTH_SHORT ).show();
        }
    }
}
