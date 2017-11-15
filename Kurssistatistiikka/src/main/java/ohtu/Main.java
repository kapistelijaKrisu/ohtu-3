package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        studentStats(args);
        courseStats();

    }

    public static void studentStats(String[] args) throws IOException {
        String studentNr = "014687309";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String studentUrl = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";

        String studentText = Request.Get(studentUrl).execute().returnContent().asString();
        String courseText = Request.Get(courseUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(studentText, Submission[].class);
        Course course = mapper.fromJson(courseText, Course.class);

        System.out.println("Kurssin: " + course.getName() + " (" + course.getTerm() + ")");

        System.out.println("Opiskelian infot:");
        int hours = 0;
        int exercisesCount = 0;
        for (Submission submission : subs) {
            hours += submission.getHours();
            exercisesCount += submission.getExercises().size();
            System.out.println(submission + " (max:" + course.getExercises()[submission.getWeek() - 1] + ")");
        }

        System.out.println("  Hours spent in total: " + hours
                + "\n  Exercises done in total : " + exercisesCount);
    }

    public static void courseStats() throws IOException {
        String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String courseStats = Request.Get(statsUrl).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject parsittuData = parser.parse(courseStats).getAsJsonObject();
        int i = 1;
        int studentCount = 0;
        int exeCount = 0;
        while (true) {
            try {
                JsonObject week = parsittuData.getAsJsonObject("" + i);
                JsonPrimitive studentJson = (JsonPrimitive) week.get("students");
                studentCount += studentJson.getAsInt();

                JsonPrimitive exeJson = (JsonPrimitive) week.get("exercise_total");
                exeCount += exeJson.getAsInt();
                i++;
            } catch (Exception e) {
                break;
            }
        }
        
        System.out.println("course has in total of " + studentCount + " weekly submission.");
        System.out.println("course has in total of " + exeCount + " submitted exercises");
    }
}
