package com.example.user.work.data;

import com.example.user.work.models.Notizen;

import java.util.ArrayList;
import java.util.List;

public class NotizenData {

    public List<Notizen> notizens = new ArrayList<Notizen>() {
        {
            notizens.add(new Notizen(0, "2048", "hey", false));
            notizens.add(new Notizen(1, "Blitzrechnen", "h", true));
            notizens.add(new Notizen(2, "Hard Math", "h", true));
            notizens.add(new Notizen(3, "Richtig / Falsch", "h", true));
            notizens.add(new Notizen(4, "Trainieren", "h", false));
            notizens.add(new Notizen(5, "Multiplikationstabelle", "h", true));
            notizens.add(new Notizen(6, "Eingabe", "h", true));
            notizens.add(new Notizen(7, "Schulte-Tabelle", "h", false));
            notizens.add(new Notizen(8, "Balance", "h", true));
            notizens.add(new Notizen(9, "Balance", "h", true));
        }
    };

    public List<Notizen> getNotizens() {
        return notizens;
    }

    public void setNotizens(List<Notizen> notizens) {
        this.notizens = notizens;
    }
}
