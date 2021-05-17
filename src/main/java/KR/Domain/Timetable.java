package KR.Domain;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private List<Path> pathes = new ArrayList<>();

    public Timetable() {
        pathes = new ArrayList<>();
    }

    public List<Path> getPathes() {
        return pathes;
    }

    public void setPathes(ArrayList<Path> pathes) {
        this.pathes = pathes;
    }

    public Timetable(ArrayList<Path> pathes) {
        this.pathes = pathes;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "pathes=" + pathes +
                '}';
    }
}
