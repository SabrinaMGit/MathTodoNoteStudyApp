package com.example.user.work.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.work.R;
import com.example.user.work.activities.NavigationDrawer.ToDoList;
import com.example.user.work.helpers.SwipeAndDragHelper;
import com.example.user.work.models.ToDoListModels;
import com.example.user.work.sql.DatabaseHelper;

import java.util.List;

/**
 * Created by Srijith on 08-10-2017.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        SwipeAndDragHelper.ActionCompletionContract {
    private static final int USER_TYPE = 1;
    private static final int HEADER_TYPE = 2;
    private List<ToDoListModels> toDoList;
    private ItemTouchHelper touchHelper;
    private boolean newEntry;
    DatabaseHelper databaseHelper;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        databaseHelper = new DatabaseHelper(parent.getContext());
        switch (viewType) {
            case USER_TYPE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.todolist_list, parent, false);
                return new ToDoListViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.todolist_list, parent, false);
                return new ToDoListViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == USER_TYPE) {
            ((ToDoListViewHolder) holder).task.setText(toDoList.get(position).getName());
            ((ToDoListViewHolder) holder).notizen.setText(toDoList.get(position).getNotizen());
            ((ToDoListViewHolder) holder).reorderView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                        touchHelper.startDrag(holder);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return toDoList == null ? 0 : toDoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(toDoList.get(position).getName())) {
            return HEADER_TYPE;
        } else {
            return USER_TYPE;
        }
    }

    public void setToDoList(List<ToDoListModels> toDoList) {
        this.toDoList = toDoList;
        notifyDataSetChanged();
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        ToDoListModels modelAtCurrentPosition = toDoList.get(oldPosition);
        ToDoListModels modelAtNewPosition = toDoList.get(newPosition);

        toDoList.remove(newPosition);
        toDoList.add(newPosition, modelAtCurrentPosition);

        toDoList.remove(oldPosition);
        toDoList.add(oldPosition, modelAtNewPosition);

        notifyItemMoved(oldPosition, newPosition);
    }

    @Override
    public void onViewSwiped(int position) {
        ToDoListModels models = toDoList.get(position);
        databaseHelper.deleteToDoTask(models);
        toDoList.remove(position);
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {

        this.touchHelper = touchHelper;
    }

    public class ToDoListViewHolder extends RecyclerView.ViewHolder {

        CheckBox box;
        TextView task;
        ImageView reorderView;
        TextView notizen;
        ImageView alarm;
        TextView time;

        public ToDoListViewHolder(View itemView) {
            super(itemView);

            box = itemView.findViewById(R.id.checkBox);
            task = itemView.findViewById(R.id.textview_name);
            reorderView = itemView.findViewById(R.id.imageview_reorder);
            notizen = itemView.findViewById(R.id.notizen);
            alarm = itemView.findViewById(R.id.bell);
            time = itemView.findViewById(R.id.time);
        }
    }
}
