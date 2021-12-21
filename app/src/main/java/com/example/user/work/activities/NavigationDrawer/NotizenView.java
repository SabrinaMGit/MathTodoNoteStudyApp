package com.example.user.work.activities.NavigationDrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.work.R;

public class NotizenView extends Fragment {

    public NotizenView()
    {

    }

    public static NotizenView newInstance() {
        NotizenView fragment = new NotizenView();
        return fragment;
    }
    TextView notizen;
    String notiz = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.notizen_output_view, container, false);

        // null object reference is not show the message, fix this problem and look in the Activity NotizenMain for failure configuration

        notizen = rootView.findViewById(R.id.text);
        //notiz=getArguments().getString("Notiz");
        //notizen.setText(notiz);

        //NotizenData notizenData = new NotizenData();
        //notizenData.getNotizens().get(1).setNote(notiz);





        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        return rootView;
    }

}
