package com.manuico.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.manuico.notesapp.R;
import com.manuico.notesapp.adapters.NoteAdapter;
import com.manuico.notesapp.models.Note;
import com.manuico.notesapp.models.User;
import com.manuico.notesapp.repositories.NoteRepository;
import com.manuico.notesapp.repositories.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int REGISTER_FORM_REQUEST = 100;

    private RecyclerView notesList;

    private TextView fullnameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullnameText = findViewById(R.id.welcome_text);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Long id = sp.getLong("id", 0);

        User user = UserRepository.load(id);

        if(user == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        fullnameText.setText("Bienvenido " + user.getFullname());

        // Configure ReciclerView
        notesList = (RecyclerView) findViewById(R.id.note_list);
        notesList.setLayoutManager(new LinearLayoutManager(this));

        // Set Data Adapter to ReciclerView
        List<Note> notes = NoteRepository.list();
        notesList.setAdapter(new NoteAdapter(notes));

    }

    public void callRegisterForm(View view){
        startActivityForResult(new Intent(this, NoteRegisterActivity.class), REGISTER_FORM_REQUEST);
    }

    // return from NoteRegisterActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // refresh data
        NoteAdapter adapter = (NoteAdapter)notesList.getAdapter();

        List<Note> notes = NoteRepository.list();
        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();

    }
}
