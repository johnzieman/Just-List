package ziemansoft.ziemapp.justlist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String day;
    private String title;
    private String description;
    private String priority;


    public void setId(int id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public Notes(int id, String day, String title, String description, String priority) {
        this.id = id;
        this.day = day;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Ignore
    public Notes(String day, String title, String description, String priority) {
        this.day = day;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
}



