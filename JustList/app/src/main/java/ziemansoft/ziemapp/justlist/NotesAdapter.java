package ziemansoft.ziemapp.justlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    ArrayList<Notes> notesArray;

    public NotesAdapter(ArrayList<Notes> notes) {
        this.notesArray = notes;
    }

    interface OnNoteClickListener {
        void onNoteClick(int position);

        void onLongNoteClick(int position);
    }

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public NotesAdapter(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    private OnNoteClickListener onNoteClickListener;

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes notes = notesArray.get(position);
        holder.day.setText(notes.getDay());
        holder.title.setText(notes.getTitle());
        holder.description.setText(notes.getDescription());
        holder.priority.setText("Priority: " + notes.getPriority());

    }

    @Override
    public int getItemCount() {
        return notesArray.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        private TextView title;
        private TextView description;
        private TextView priority;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.textViewDay);
            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            priority = itemView.findViewById(R.id.textViewPriority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onNoteClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null) {
                        onNoteClickListener.onLongNoteClick(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}