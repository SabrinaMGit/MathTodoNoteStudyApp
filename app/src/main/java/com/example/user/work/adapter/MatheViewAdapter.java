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
import com.example.user.work.activities.NavigationDrawer.Blitzrechnen;
import com.example.user.work.activities.NavigationDrawer.HardMath;
import com.example.user.work.activities.Quiz_Menu_Activity;
import com.example.user.work.activities.TableSQLData;
import com.example.user.work.models.MatheGameView;
import com.example.user.work.models.Quiz;

import java.util.List;

public class MatheViewAdapter extends RecyclerView.Adapter<MatheViewAdapter.MyViewHolder>{


    private Context mContext ;
    private List<MatheGameView> mData ;


    public MatheViewAdapter(Context mContext, List<MatheGameView> mData) {
        this.mContext = mContext; //get Activity of bottomfrag2
        this.mData = mData;
    }

    @Override
    public MatheViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //get the initialize class id's

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);  //get the frag3 layout
        view = mInflater.inflate(R.layout.cardview_mathe_main,parent,false); //set the CardView Layout in frag3 layout
        return new MatheViewAdapter.MyViewHolder(view); //return the cardView
    }

    @Override
    public void onBindViewHolder(MatheViewAdapter.MyViewHolder holder, final int position) { //set the Data in all positions of the CardView

        holder.thema.setText(mData.get(position).getName());
        holder.imageMain.setImageResource(mData.get(position).getPicture());
        holder.cardView.setOnClickListener(new View.OnClickListener() {  //Click on one CardView to do something
            @Override
            public void onClick(View v) {

                if (position==0) {
                    Intent sql = new Intent(mContext, TableSQLData.class);
                    mContext.startActivity(sql);
                }
                if (position==1) {
                    Intent blitzrechnen = new Intent(mContext, Blitzrechnen.class);
                    mContext.startActivity(blitzrechnen);
                }
                if (position==2) {
                    Intent hardMath = new Intent(mContext, HardMath.class);
                    mContext.startActivity(hardMath);
                }

                /*Intent intent = new Intent(mContext, Quiz_Menu_Activity.class); //set a new Game Activity


                // passing data to the game activity
                intent.putExtra("Title",mData.get(position).getName());
                intent.putExtra("Description",mData.get(position).getRecord());
                intent.putExtra("Thumbnail",mData.get(position).getCrown());


                // start the game activity
                mContext.startActivity(intent);*/

            }
        });


        if(mData.get(position).getCrown() == true)
            holder.crown.setVisibility(View.VISIBLE);
        else
            holder.crown.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder { //This class is to initialize views and say which place to set the cardView

        TextView thema;
        ImageView imageMain;
        CardView cardView ;
        TextView rekord;
        ImageView crown;

        public MyViewHolder(View itemView) {
            super(itemView);

            thema = (TextView) itemView.findViewById(R.id.thema) ;
            imageMain = (ImageView) itemView.findViewById(R.id.mainPicture);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            rekord = itemView.findViewById(R.id.rekord);
            crown = itemView.findViewById(R.id.crown);

        }
    }
}
