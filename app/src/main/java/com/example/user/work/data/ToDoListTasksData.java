package com.example.user.work.data;

import com.example.user.work.R;
import com.example.user.work.models.ToDoListTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToDoListTasksData {

    String note;
    String date;

    Map<ToDoListTask, List<ToDoListTask>> listHash = new HashMap<>();
    private List<ToDoListTask> tasks = new ArrayList<ToDoListTask>() {
        {
            add(new ToDoListTask(0, R.drawable.ic_subject, "Notizen", note, true));

            add(new ToDoListTask(1, R.drawable.ic_list, "Liste", "App", false));

            add(new ToDoListTask(2, R.drawable.ic_alarm, "Fähligkeitsdatum", "Keine Erinnerung eingerichtet", false));

            add(new ToDoListTask(3, R.drawable.ic_notifications_off, "Erinnerung", "Erinnere mich nicht daran", false));

            add(new ToDoListTask(4, R.drawable.ic_preoritat, "Priorität", "Keine", false));
            add(new ToDoListTask(5, R.drawable.ic_star_border_black_24dp, "Highlight", "Heben Sie diese Aufgabe in der Listenansicht hervor.", false));
            add(new ToDoListTask(6, R.drawable.ic_menu_share, "Abgeschlossen", date, false));
        }

    };
    private List<ToDoListTask> childListe = new ArrayList<ToDoListTask>() {
        {
            add(new ToDoListTask(0, R.drawable.ic_erinnerung_yellow, "Erinnerungstyp", "Benarichtigung", false));

            add(new ToDoListTask(1, R.drawable.ic_erinnerungstyp_yellow, "Wiederholung", "Wiederholt sich nicht", false));
        }

    };

    public Map<ToDoListTask, List<ToDoListTask>> getListHash() {
        return listHash;
    }

    public List<ToDoListTask> getToDoListTasks() {
        return tasks;
    }

    public List<ToDoListTask> getChild() {
        return childListe;
    }

    public String getNote() {
        return note;
    }

    public void setTasks(List<ToDoListTask> tasks) {
        this.tasks = tasks;
    }
}
