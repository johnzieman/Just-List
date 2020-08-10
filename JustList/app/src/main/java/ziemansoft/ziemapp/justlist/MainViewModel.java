package ziemansoft.ziemapp.justlist;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDataBase dataBase;
    private LiveData<List<Notes>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataBase = NotesDataBase.getInstance(application);
        notes = dataBase.notesDao().getAllNotes();
    }

    public LiveData<List<Notes>> getNotes() {
        return notes;
    }

    public void insertNote(Notes notes){
        new InsertNote().execute(notes);
    }

    public void deleteNote(Notes notes){
        new DeleteNote().execute(notes);
    }

    public void deleteAllNotes(){
        new DeleteAllNotes().execute();
    }

    private static class InsertNote extends AsyncTask<Notes, Void, Void>{
        @Override
        protected Void doInBackground(Notes... notes) {
            if(notes != null && notes.length > 0){
                dataBase.notesDao().insertNote(notes[0]);
            }
            return null;
        }
    }

    private static class DeleteNote extends AsyncTask<Notes, Void, Void>{
        @Override
        protected Void doInBackground(Notes... notes) {
            if(notes != null && notes.length>0){
                dataBase.notesDao().deleteNote(notes[0]);
            }
            return null;
        }
    }

    private static class  DeleteAllNotes extends AsyncTask<Notes, Void, Void>{
        @Override
        protected Void doInBackground(Notes... notes) {
            dataBase.notesDao().deleteAllNotes();
            return null;
        }
    }

}
