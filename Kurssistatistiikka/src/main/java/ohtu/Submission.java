package ohtu;

import java.util.List;

public class Submission {

    private int week;
    private int hours;
    private List<Integer> exercises;

    public void setWeek(int week, int hours, List<Integer> exercises) {
        this.week = week;
        this.hours = hours;
        this.exercises = exercises;

    }

    public int getWeek() {
        return week;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public int getHours() {
        return hours;
    }
    
    

    @Override
    public String toString() {
        return "Week: " + week + " Hours spent: " + hours + "\n exercises done: " + exercises.toString();
    }

}
