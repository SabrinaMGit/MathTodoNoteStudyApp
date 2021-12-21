package com.example.user.work.fragments.bottomFragmente;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.work.R;
import com.example.user.work.adapter.QuizViewAdapter;
import com.example.user.work.models.Quiz;

import java.util.ArrayList;
import java.util.List;

public class bottomfrag2 extends Fragment {

    public bottomfrag2() {

    }

    public static bottomfrag2 newInstance() {
        bottomfrag2 fragment = new bottomfrag2();
        return fragment;
    }

    List<Quiz> lstBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag2, container, false);


        lstBook = new ArrayList<>();
        lstBook.add(new Quiz("Informatik", "Categorie Book", "Description book", R.drawable.informatik));
        lstBook.add(new Quiz("Elektrotechnik", "Categorie Book", "Description book", R.drawable.arduino));
        lstBook.add(new Quiz("Englisch", "Categorie Book", "Description book", R.drawable.englisch));
        lstBook.add(new Quiz("Spanisch", "Categorie Book", "Description book", R.drawable.spain));
        lstBook.add(new Quiz("Mathe", "Categorie Book", "Description book", R.drawable.mathe));
        lstBook.add(new Quiz("Politik", "Categorie Book", "Description book", R.drawable.politik));
        lstBook.add(new Quiz("Wirtschaft", "Categorie Book", "Description book", R.drawable.wirtschaft));
        lstBook.add(new Quiz("Beruehmte Personen", "Categorie Book", "Description book", R.drawable.napoleon));
        lstBook.add(new Quiz("Sport", "Categorie Book", "Description book", R.drawable.sport));
        lstBook.add(new Quiz("Chemie", "Categorie Book", "Description book", R.drawable.chemie));
        lstBook.add(new Quiz("The Vegitarian", "Categorie Book", "Description book", R.drawable.spain));
        lstBook.add(new Quiz("The Wild Robot", "Categorie Book", "Description book", R.drawable.spain));
        lstBook.add(new Quiz("Maria Semples", "Categorie Book", "Description book", R.drawable.spain));
        lstBook.add(new Quiz("The Martian", "Categorie Book", "Description book", R.drawable.spain));
        lstBook.add(new Quiz("He Died with...", "Categorie Book", "Description book", R.drawable.spain));

        RecyclerView myrv = (RecyclerView) rootView.findViewById(R.id.recyclerview_id);
        QuizViewAdapter myAdapter = new QuizViewAdapter(getActivity(), lstBook);
        myrv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        myrv.setAdapter(myAdapter);
        return rootView;
    }

}