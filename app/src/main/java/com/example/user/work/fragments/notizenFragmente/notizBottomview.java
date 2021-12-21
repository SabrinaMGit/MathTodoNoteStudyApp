package com.example.user.work.fragments.notizenFragmente;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.work.R;
import com.example.user.work.activities.NavigationDrawer.NotizenMain;
import com.example.user.work.activities.NavigationDrawer.NotizenView;

public class notizBottomview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notizen_bottomview_main);

        BottomNavigationView mBottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.write:
                    selectedFragment = NotizenMain.newInstance();
                    Toast.makeText(notizBottomview.this, "Notizen :)",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.output:
                    selectedFragment = NotizenView.newInstance();
                    break;
                case R.id.allView:
                    selectedFragment = NotizenMain.newInstance();
                    break;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };
}