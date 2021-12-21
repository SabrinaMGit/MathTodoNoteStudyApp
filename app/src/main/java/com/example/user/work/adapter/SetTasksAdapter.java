package com.example.user.work.adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.user.work.R;
import com.example.user.work.models.ToDoListModels;
import com.example.user.work.models.ToDoListTask;
import com.example.user.work.sql.DatabaseHelper;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class SetTasksAdapter extends RecyclerView.Adapter<SetTasksAdapter.MyViewHolder> {

    boolean starIsSet = false;
    Calendar calendar;
    int year;
    int month;
    int dayOfMonth;
    int currentHour;
    String days;
    String months;
    String years;
    boolean childrenIsSet = false;
    TimePickerDialog PickerDialog;
    int currentMinute;
    String amPm;
    DatePickerDialog datePicker;
    boolean timerIsSet;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    String notizen;
    String datum;
    String errinern;
    String prioritaet;
    int foundUserId;
    private Context mContext;
    private List<ToDoListTask> mData;
    private List<ToDoListTask> children;
    private int mExpandedPosition = -1;
    private Map<ToDoListTask, List<ToDoListTask>> listHashMap;
    private ToDoListModels toDoList;
    private DatabaseHelper databaseHelper;

    public SetTasksAdapter(Context mContext, List<ToDoListTask> mData, List<ToDoListTask> children, int foundUserId) {
        this.mContext = mContext; //get Activity of ToDoList_SetTasks
        this.mData = mData;
        this.children = children;
        this.foundUserId = foundUserId;
    }

    public String getYear() {
        return years;
    }

    public int getDayOfMonth() { return dayOfMonth; }

    public String getDay() { return days; }

    public boolean isStarIsSet() { return starIsSet; }

    public String getErrinern() { return errinern; }

    public String getPrioritaet() { return prioritaet; }

    public void setFoundUserId(int id) { this.foundUserId = id; }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //get the initialize class ids
        databaseHelper = new DatabaseHelper(mContext);
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);  //get the frag3 layout
        view = mInflater.inflate(R.layout.todolist_tasks, parent, false); //set the CardView Layout in frag3 layout
        return new MyViewHolder(view); //return the cardView
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) { //set the Data in all positions of the CardView
        holder.mainText.setText(mData.get(position).getName());
        holder.image.setImageResource(mData.get(position).getImage());
        holder.textnotizen.setText(mData.get(position).getNote());

        if(position == 6) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.INVISIBLE);
            holder.layout.setOrientation(LinearLayout.HORIZONTAL);
            holder.mainText.setGravity(Gravity.CENTER_VERTICAL);
            if (holder.checkBox.isChecked()) {

            }
            ViewGroup.LayoutParams params = holder.linearLayout.getLayoutParams();
// Changes the height and width to the specified *pixels*
            params.height = 100;
            holder.linearLayout.setLayoutParams(params);
        }

        if (mData.get(position).getEdit()) {
            holder.textnotizen.setVisibility(View.INVISIBLE);
            holder.editText.setVisibility(View.VISIBLE);
        } else {
            holder.textnotizen.setVisibility(View.VISIBLE);
            holder.editText.setVisibility(View.INVISIBLE);
        }
        final boolean isExpanded = position == mExpandedPosition;
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {  //Click on one CardView to do something
            @Override
            public void onClick(View v) {
                Log.d(SetTasksAdapter.class.getSimpleName(), String.valueOf(position));
                Log.d(SetTasksAdapter.class.getSimpleName(), String.valueOf(mData));

                if (position == 0) {
                   notizen = holder.editText.getText().toString();
                }

                if (position == 1) {
                    AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                    alertDialog.setTitle("Liste");
                    alertDialog.setMessage("App");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                if (position == 2) {
                    calendar = Calendar.getInstance();
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH);
                    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    datePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {

                            days = Integer.toString(day);
                            months = Integer.toString(month);
                            years = Integer.toString(year);

                            holder.image.setImageResource(R.drawable.ic_alarm_yellow);

                            PickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                                    timerIsSet = true;
                                    currentHour = hourOfDay;
                                    currentMinute = minutes;
                                    datum = ""+days+"."+months+"."+years+" "+currentHour+":"+currentMinute;
                                    holder.textnotizen.setText(days + "/" + months + "/" + years + " @ " + String.format("%02d:%02d", currentHour, currentMinute));
                                }
                            }, currentHour, currentMinute, false);
                            PickerDialog.show();
                        }
                    }, dayOfMonth, month, year);
                    datePicker.show();

                }


                if (position == 3) {
                    if (timerIsSet == true) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("Erinnerung");

                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view = inflater.inflate(R.layout.erinnerung_alert, null);
                        builder.setView(view);
                        radio1 = view.findViewById(R.id.radio1);
                        radio2 = view.findViewById(R.id.radio2);
                        radio3 = view.findViewById(R.id.radio3);
                        builder.setNegativeButton("ABBRECHEN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        holder.image.setImageResource(R.drawable.ic_notifications_yellow);
                        final AlertDialog dialog = builder.show();

                        radio1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                if (childrenIsSet == true) {
                                    mData.removeAll(children);
                                    notifyItemRangeRemoved(position + 1, children.size());
                                    childrenIsSet = false;
                                    errinern = "Erriner mich nicht";
                                }
                            }
                        });

                        radio2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                if (childrenIsSet == false) {
                                    mData.addAll(position + 1, children);
                                    notifyItemRangeInserted(position + 1, children.size());
                                    childrenIsSet = true;
                                    errinern = "Erriner mich, wenn es faellig ist";
                                }
                            }
                        });
                        radio3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                if (childrenIsSet == false) {
                                    mData.addAll(position + 1, children);
                                    notifyItemRangeInserted(position + 1, children.size());
                                    childrenIsSet = true;

                                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                    builder.setTitle("Erinnerungsvorschuss");

                                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View view = inflater.inflate(R.layout.erinnerungsvorschuss, null);
                                    builder.setView(view);
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    builder.setNegativeButton("ABBRECHEN", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                                    DatePicker datePicker = view.findViewById(R.id.datePickerExample);
                                    datePicker.init(currentMinute, month, dayOfMonth, new DatePicker.OnDateChangedListener() {
                                        @Override
                                        public void onDateChanged(DatePicker datePicker, int current, int months, int day) {
                                            SetTasksAdapter.this.currentMinute = currentMinute;
                                            SetTasksAdapter.this.month = months;
                                            SetTasksAdapter.this.days = days;
                                        }
                                    });
                                    builder.show();
                                }
                                errinern = "Erinnere mich im Voraus";
                            }
                        });


                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
                        alertDialog.setTitle("ACHTUNG");
                        alertDialog.setMessage("Sag mir wann. Stellen Sie zuerst ein Datum und eine Uhrzeit ein.");
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "VERSTANDEN",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
                if (position == 4)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Aufgabenpriorität");

                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.erinnerung_alert, null);
                    builder.setView(view);
                    TextView text1 = view.findViewById(R.id.text1);
                    TextView text2 = view.findViewById(R.id.text2);
                    TextView text3 = view.findViewById(R.id.text3);
                    text1.setText("Hoch");
                    text2.setText("Mittel");
                    text3.setText("Niedrig");
                    radio1 = view.findViewById(R.id.radio1);
                    radio2 = view.findViewById(R.id.radio2);
                    radio3 = view.findViewById(R.id.radio3);
                    builder.setNegativeButton("ABBRECHEN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    final AlertDialog dialog = builder.show();

                    radio1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.image.setImageResource(R.drawable.ic_flag_red);
                            holder.textnotizen.setText("Hoch");
                            prioritaet = "Hoch";
                            dialog.dismiss();
                            //holder.image.setColorFilter(mContext.getResources().getColor(R.color.);
                        }
                    });
                    radio2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.image.setImageResource(R.drawable.ic_flag_orange);
                            holder.textnotizen.setText("Mittel");
                            prioritaet = "Mittel";
                            dialog.dismiss();
                            //holder.image.setColorFilter(mContext.getResources().getColor(R.color.);
                        }
                    });
                    radio3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.image.setImageResource(R.drawable.ic_flag_yellow);
                            holder.textnotizen.setText("Niedrig");
                            prioritaet = "Niedrig";
                            dialog.dismiss();
                            //holder.image.setColorFilter(mContext.getResources().getColor(R.color.);
                        }
                    });
                }
                if(position == 5)
                {
                    if(starIsSet == false) {
                        holder.image.setImageResource(R.drawable.ic_star_yellow);
                        starIsSet = true;
                    }
                    else {
                        holder.image.setImageResource(R.drawable.ic_star_border_black_24dp);
                        starIsSet = false;
                    }
                }
            }
        });
    }

    public void addToDatabase(String aufgabenName){
        toDoList = new ToDoListModels();
        toDoList.setId(foundUserId);
        toDoList.setName("Zweiter Versuch");
        toDoList.setNotizen("Note");
        if (aufgabenName!=null) {
            toDoList.setName(aufgabenName);
        }
        if (notizen!=null) {
            toDoList.setNotizen(notizen);
        }
        if (datum!=null) {
            toDoList.setDatum(datum);
        }
        if (errinern!=null) {
            toDoList.setErrinern(errinern);
        }
        if (prioritaet!=null) {
            toDoList.setPrioritaet(prioritaet);
        }
        databaseHelper.addToDoTask(toDoList);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder { //This class is to initialize views and say which place to set the cardView

        TextView mainText;
        ImageView image;
        TextView textnotizen;
        EditText editText;
        CheckBox checkBox;
        LinearLayout layout;
        LinearLayout linearLayout;


        public MyViewHolder(View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);
            textnotizen = itemView.findViewById(R.id.text_notizen);
            editText = itemView.findViewById(R.id.edit_text);
            checkBox = itemView.findViewById(R.id.checkBox);
            layout = itemView.findViewById(R.id.layout);
            linearLayout = itemView.findViewById(R.id.layoutLinear);


        }
    }
}
