package ziemansoft.ziemapp.justlist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class NotesDataBase extends RoomDatabase {
    private static NotesDataBase notesDataBase;
    private static final String DB_NAME = "notes1";
    private static Object lock = new Object();

    public static NotesDataBase getInstance(Context context) {
        synchronized (lock) {
            if (notesDataBase == null) {
                notesDataBase = Room.databaseBuilder(context, NotesDataBase.class, DB_NAME).build();
            }
            return notesDataBase;
        }
    }

    public abstract NotesDao notesDao();
}
