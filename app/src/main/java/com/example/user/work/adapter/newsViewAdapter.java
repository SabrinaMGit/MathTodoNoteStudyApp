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
import com.example.user.work.activities.Quiz_Menu_Activity;
import com.example.user.work.models.Quiz;

import java.util.List;

public class newsViewAdapter extends RecyclerView.Adapter<newsViewAdapter.MyViewHolder>{

    private Context mContext ;
    private List<Quiz> mData ;


    public newsViewAdapter(Context mContext, List<Quiz> mData) {
        this.mContext = mContext; //get Activity of bottomfrag2
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //get the initialize class ids

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);  //get the frag3 layout
        view = mInflater.inflate(R.layout.news_item_view,parent,false); //set the CardView Layout in frag3 layout
        return new MyViewHolder(view); //return the cardView
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) { //set the Data in all positions of the CardView

        holder.title.setText(mData.get(position).getTitle());
        holder.img_news_thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {  //Click on one CardView to do something
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,Quiz_Menu_Activity.class); //set a new Activity


                // passing data to the book activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());


                // start the activity
                mContext.startActivity(intent);

            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.img_news_thumbnail.getLayoutParams().height = 20;
                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder { //This class is to initialize views and say which place to set the cardView

        TextView title;
        ImageView img_news_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title) ;
            img_news_thumbnail = (ImageView) itemView.findViewById(R.id.news_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }

}
