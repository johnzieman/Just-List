package ziemansoft.ziemapp.justlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private MainViewModel viewModel;
    public static ArrayList<Notes> notesArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        if (actionBar != null) {
            actionBar.hide();
        }
        recyclerView = findViewById(R.id.recycler);
        getData();
        adapter = new NotesAdapter(notesArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "Push note a bit longer to remove or just swipe", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongNoteClick(int position) {
                delete(position);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                delete(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    void delete(int position) {
        Notes notes = notesArray.get(position);
        viewModel.deleteNote(notes);
    }

    private void getData() {
        LiveData<List<Notes>> dataFromDB = viewModel.getNotes();
        dataFromDB.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notesFromLiveData) {
                notesArray.clear();
                notesArray.addAll(notesFromLiveData);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void openEditPage(View view) {
        Intent intent = new Intent(this, EditNote.class);
        startActivity(intent);
    }
}