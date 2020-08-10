package ziemansoft.ziemapp.justlist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getAllNotes();

    @Insert
    void insertNote(Notes notes);

    @Delete
    void deleteNote(Notes notes);

    @Query("DELETE FROM notes")
    void deleteAllNotes();
}
