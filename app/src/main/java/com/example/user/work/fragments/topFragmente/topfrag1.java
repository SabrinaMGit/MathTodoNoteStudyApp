package com.example.user.work.fragments.topFragmente;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.work.R;
import com.example.user.work.adapter.ThemenAdapter;
import com.example.user.work.models.Themen;

import java.util.ArrayList;
import java.util.List;

import com.example.user.work.recyclerview.ZoomInItemAnimator;
import com.example.user.work.recyclerview.itemdecorators.ShadowVerticalSpaceItemDecorator;
import com.example.user.work.recyclerview.itemdecorators.VerticalSpaceItemDecorator;


public class topfrag1 extends Fragment {
    //public static final String PAGE_TITLE = "Informatik";

        public topfrag1() {
            // Required empty public constructor
        }

    public static topfrag1 newInstance() {
        topfrag1 fragment = new topfrag1();
        return fragment;
    }
    private RecyclerView listingsView;
    private List<Themen> themer;

    CollapsingToolbarLayout ctl;
    Toolbar toolbar;



        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.frag1, container, false);




            loadThemen();
            ThemenAdapter adapter = new ThemenAdapter(getActivity(),R.layout.list_item_themen, themer); // new Adapter Name Use

            // 4. Initialize ItemAnimator, LayoutManager and ItemDecorators
            ZoomInItemAnimator itemAnimator = new ZoomInItemAnimator();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            VerticalSpaceItemDecorator itemDecorator = new VerticalSpaceItemDecorator((int) getResources().getDimension(R.dimen.spacer_20)); // set the size between the layouts
            ShadowVerticalSpaceItemDecorator shadowItemDecorator = new ShadowVerticalSpaceItemDecorator(getActivity(), R.drawable.drop_shadow);

            // 5. Inflate our RecyclerView
            listingsView = (RecyclerView)rootView.findViewById(R.id.listings_view); //new ListView Name

            // 6. For performance, tell OS RecyclerView won't change size
            listingsView.setHasFixedSize(true);

            //RelativeLayout.LayoutParams lp =
                    //new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 800);
            //listingsView.setLayoutParams(lp);

            // 7. Set the LayoutManager
            listingsView.setLayoutManager(layoutManager);

            // 8. Set the ItemDecorators
            listingsView.addItemDecoration(shadowItemDecorator);
            listingsView.addItemDecoration(itemDecorator);

            // 9. Set the ItemAnimator
            listingsView.setItemAnimator(itemAnimator);

            // 10. Attach the adapter to RecyclerView
            listingsView.setAdapter(adapter);






            return rootView;
        }



    private void loadThemen() {

        themer = new ArrayList<>();

        Bitmap logikIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logikgatter);
        Bitmap historyIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_zuse);
        Bitmap algebraIcon =BitmapFactory.decodeResource(getResources(), R.mipmap.ic_agebra);
        Bitmap hardwareIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_hardware);
        Bitmap compilerIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_compiler);

        Themen history = new Themen("Die History und die Teilgebiete der Informatik", "\n Informatik ist die „Wissenschaft von der systematischen Darstellung, Speicherung, Verarbeitung und Übertragung von Informationen, besonders der automatischen Verarbeitung mithilfe von Digitalrechnern“. Historisch hat sich die Informatik einerseits aus der Mathematik entwickelt, andererseits als Ingenieursdisziplin aus dem praktischen Bedarf nach der schnellen und insbesondere automatischen Ausführung von Berechnungen.\n\n" +
                "•\tDer Begriff Informatik\n" +
                "•\tHistorische Entwicklung der Informatik\n" +
                "   -\tAbakus\n" +
                "   -\tDer Begriff Algorithmus und Ibn Musa AI- \n      Chwarismi\n" +
                "   -\tWichtige Stationen von 1500 bis 1930\n" +
                "   -\tKonrad Zuse und der erste funktionstüchtige \n       Computer\n" +
                "   -\tHoward H. Aiken und die Mark I\n" +
                "   -\tJohn von Neumann\n" +
                "   -\tGenerationen der elektronischen \n       Datenverarbeitung\n" +
                "•\tEinordnung und Einteilung der Informatik\n" +
                "   -\tVerschiedene Einsatzgebiete von Computern \n     (Informatik)\n" +
                "   -\tDie Informatik und unsere Abhängigkeit von ihr\n\n"
                , "Theoretische Informatik","Praktische Informatik","Technische Informatik");
        history.logo = historyIcon;
        themer.add(history);

        Themen logikgatter = new Themen("Speicherung und Interpretation von Informationen", "•\tUnterschiedliche Zahlensysteme\n" +
                "   -\tDas römische Zahlensystem\n" +
                "   -\tPositionssysteme\n" +
                "   -\tPositionssysteme bei natürlichen Zahlen\n" +
                "   -\tPositionssysteme bei gebrochenen Zahlen\n" +
                "•\tDual- Oktal- und Hexalsystem\n" +
                "   -\tDas Dualsystem und das Bit im Rechner\n" +
                "   -\tKonvertieren zwischen Dual- und Oktalsystem\n" +
                "   -\tKonvertieren zwischen Dual- und Hexadezimalsystem\n" +
                "•\tKonvertierungsalgorithmen\n" +
                "   -\tKonvertieren von anderen Systemen in das Dezimalsystem\n" +
                "   -\tKonvertieren vom Dezimalsystem in andere Positionssysteme\n" +
                "   -\tKonvertieren echt gebrochener Zahlen\n" +
                "   -\tKonvertieren unecht gebrochener Zahlen\n" +
                "•\tRechenoperationen im Dualsystem\n" +
                "   -\tAddition\n" +
                "   -\tSubtraktion und Darstellung negativer Zahlen\n" +
                "   -\tMultiplikation und Division\n" +
                "   -\tKonvertieren durch sukzessive Multiplikation und Addition\n" +
                "•\tReelle Zahlen\n" +
                "•\tCodes zur Darstellung von Zeichen\n" ,"Unterschiedliche Zahlensysteme","Dual-,Oktal- und Hexadezimalzahlen","ASCI-Code, Unicode, Barcode");
        logikgatter.logo = logikIcon;
        themer.add(logikgatter);

        Themen algebra = new Themen("Boolesche Algebra", "Boolesche Schaltungen =  \n" +
                "•\tAxiome = Gesetze = Kommunikativ-, Assoziativ-, Distributiv-, Identitäts-,     Null- /Eins-, Komplementär-, Idempotenz-, Verschmelzungsgesetz\n", "Operatoren","Boolische Schaltungen","Axiome");
        algebra.logo = algebraIcon;
        themer.add(algebra);

        Themen hardware = new Themen("Hardware- Komponenten eines Computers", "", "Zentraleinheit","Assembler Befehle","Die Peripherie");
        hardware.logo = hardwareIcon;
        themer.add(hardware);

        Themen compiler = new Themen("Vom Programm zum Maschinenprogramm", "A Piece of Cake & Desserts, a local Tampa bakery specializing in Wedding Cakes, Celebration Cakes, Dessert Bars and Cookies.  We offer homemade and handmade desserts and bars.", "Entwicklung eines Programms","Compiler, Linker","Lader, Debugger");
        compiler.logo = compilerIcon;
        themer.add(compiler);

        themer.add(history);
        themer.add(logikgatter);
        themer.add(hardware);
        themer.add(algebra);
        themer.add(compiler);
    }

    // Sets the app title to use a custom font
    private void setupActionBarTheme() {

    }

}