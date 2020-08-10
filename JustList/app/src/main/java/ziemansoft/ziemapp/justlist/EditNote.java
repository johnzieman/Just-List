package ziemansoft.ziemapp.justlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditNote extends AppCompatActivity {
    private EditText day;
    private EditText title;
    private EditText description;
    private EditText priority;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        day = findViewById(R.id.editTextDay);
        title = findViewById(R.id.editTextTextTitle);
        description = findViewById(R.id.editTextTextDescription);
        priority = findViewById(R.id.editTextPriority);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    public void saveNote(View view) {
        String dayField = day.getText().toString();
        String titleField = title.getText().toString();
        String descriptionField = description.getText().toString();
        String priorityField = priority.getText().toString();
        if (dayField.length() > 0 && titleField.length() > 0) {
           viewModel.insertNote(new Notes(dayField, titleField, descriptionField, priorityField));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Fields Day and Title cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }
}