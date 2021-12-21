package com.example.user.work.activities.NavigationDrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.work.R;
import com.example.user.work.activities.Work;

public class NotizenMain extends Fragment {

private DrawerLayout drawer;
private FloatingActionButton fab;
private EditText editText;
private String notiz;
private boolean newEntrySet;

    public NotizenMain()
    {

    }

    public static NotizenMain newInstance() {
        NotizenMain fragment = new NotizenMain();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notizen_drawer, container, false);



        editText = rootView.findViewById(R.id.edit_text);


        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notiz = editText.getText().toString();
                Bundle bundle=new Bundle();
                bundle.putString( "Notiz", notiz);
                //set Fragmentclass Arguments
                NotizenMain fragobj=new NotizenMain();

                Log.v("TAG", "SetNotiz= " +bundle);

                NotizenAllView notizenAllView = new NotizenAllView();
                notizenAllView.setNewEntry(true);
                Fragment selectedFragment = NotizenView.newInstance();
                fragobj.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
            }
        });

        drawer = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);

        NavigationView navigationView = rootView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawer.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        //Fragment selFragment = null;
                        switch (menuItem.getItemId()) {
                            case R.id.todolist:
                                Intent intent = new Intent(getContext(), com.example.user.work.activities.NavigationDrawer.ToDoList.class);
                                startActivity(intent);
                                break;
                            case R.id.mathe:
                                Intent mathe = new Intent(getContext(), MatheMain.class);
                                startActivity(mathe);
                                Toast.makeText(getContext(), "Mathe Game :O",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.notizen:
                                Intent note = new Intent(getContext(), NotizenAllView.class);
                                startActivity(note);
                                Toast.makeText(getContext(), "Notizen :)",Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.reatzel:
                                Intent books = new Intent(getContext(), Work.class);
                                startActivity(books);
                                Toast.makeText(getContext(), "Quiz and Books ^^",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.nav_share:
                                Intent share = new Intent();
                                share.setAction(share.ACTION_SEND);
                                share.putExtra(Intent.EXTRA_SUBJECT, "Do You Know It?");
                                share.putExtra(share.EXTRA_TEXT, "Wow it is so much interesting, I can't believe what here is written");
                                share.setType("text/plain");
                                startActivity(share);
                                break;
                            case R.id.nav_send:
                                //selFragment = bottomfrag4.newInstance();
                                break;
                        }
                        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selFragment);
                        transaction.commit();*/
                        return true;
                    }
                });

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return rootView;
    }

    public boolean isNewEntrySet() {
        return newEntrySet;
    }

    public void setNewEntrySet(boolean newEntrySet) {
        this.newEntrySet = newEntrySet;
    }
}
