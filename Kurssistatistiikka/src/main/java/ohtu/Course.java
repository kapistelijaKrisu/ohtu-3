
package ohtu;

public class Course {
    private int[] exercises;
    private String name;
    private String term;

    public Course(int[] exercises, String name, String term) {
        this.exercises = exercises;
        this.name = name;
        this.term = term;
    }

    public int[] getExercises() {
        return exercises;
    }



    

    public String getName() {
        return name;
    }


    public String getTerm() {
        return term;
    }
    
    
    
}
