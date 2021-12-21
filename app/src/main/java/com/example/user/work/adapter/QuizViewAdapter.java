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
import com.example.user.work.activities.Quiz_Activity;
import com.example.user.work.activities.Quiz_Menu_Activity;
import com.example.user.work.models.Quiz;

import java.util.List;

public class QuizViewAdapter extends RecyclerView.Adapter<QuizViewAdapter.MyViewHolder>{

    private Context mContext ;
    private List<Quiz> mData ;


    public QuizViewAdapter(Context mContext, List<Quiz> mData) {
        this.mContext = mContext; //get Activity of bottomfrag2
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //get the initialize class ids

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);  //get the frag3 layout
        view = mInflater.inflate(R.layout.cardveiw_item_quiz,parent,false); //set the CardView Layout in frag3 layout
        return new MyViewHolder(view); //return the cardView
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) { //set the Data in all positions of the CardView

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.img_book_thumbnail.setImageResource(mData.get(position).getThumbnail());
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



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder { //This class is to initialize views and say which place to set the cardView

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id) ;
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }

}
