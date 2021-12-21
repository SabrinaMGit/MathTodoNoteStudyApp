package com.example.user.work.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.work.R;
import com.example.user.work.activities.NavigationDrawer.NotizenView;
import com.example.user.work.activities.Quiz_Menu_Activity;
import com.example.user.work.models.MatheGameView;
import com.example.user.work.models.Notizen;

import java.util.ArrayList;
import java.util.List;

public class NotizenViewAdapter extends RecyclerView.Adapter<NotizenViewAdapter.MyViewHolder>{


        private Context mContext ;
        private List<Notizen> mData ;


        public NotizenViewAdapter(Context mContext, List<Notizen> mData) {
            this.mContext = mContext; //get Activity of bottomfrag2
            this.mData = mData;
        }

        @Override
        public NotizenViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //get the initialize class id's

            View view ;
            LayoutInflater mInflater = LayoutInflater.from(mContext);  //get the frag3 layout
            view = mInflater.inflate(R.layout.notizen_cardview,parent,false); //set the CardView Layout in frag3 layout
            return new NotizenViewAdapter.MyViewHolder(view); //return the cardView
        }

        @Override
        public void onBindViewHolder(NotizenViewAdapter.MyViewHolder holder, final int position) { //set the Data in all positions of the CardView

            holder.preview.setText(mData.get(position).getNote());
            holder.cardView.setOnClickListener(new View.OnClickListener() {  //Click on one CardView to do something
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, NotizenView.class); //set a new Game Activity


                    // passing data to the game activity
                    intent.putExtra("Notiz",mData.get(position).getNote());


                    // start the game activity
                    mContext.startActivity(intent);

                }
            });

/*
            if(mData.get(position).isFavorite() == true)
                holder.favoriteIcon.setVisibility(View.VISIBLE);
            else
                holder.favoriteIcon.setVisibility(View.INVISIBLE);
*/
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder { //This class is to initialize views and say which place to set the cardView

            TextView preview;
            CardView cardView ;
            TextView notizText;
            ImageView favoriteIcon;

            public MyViewHolder(View itemView) {
                super(itemView);

                preview = (TextView) itemView.findViewById(R.id.thema) ;
                cardView = (CardView) itemView.findViewById(R.id.cardview_id);
                notizText = itemView.findViewById(R.id.rekord);
                favoriteIcon = itemView.findViewById(R.id.crown);

            }
        }
    }

