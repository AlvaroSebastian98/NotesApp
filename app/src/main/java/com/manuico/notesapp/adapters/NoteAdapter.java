package com.manuico.notesapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.manuico.notesapp.R;
import com.manuico.notesapp.models.Note;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    private List<Note> notes;

    public NoteAdapter(List<Note> notes){
        this.notes = notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView date;
        public TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
            content = (TextView) itemView.findViewById(R.id.content_text);
        }
    }

    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteAdapter.ViewHolder viewHolder, int position) {
        Note note = this.notes.get(position);
        Date d = new Date();
        d.setTime(note.getDate());
        String date = new SimpleDateFormat("dd/MM/yyyy").format(d);

        viewHolder.title.setText(note.getTitle());
        viewHolder.date.setText(date);
        viewHolder.content.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }
}
