package com.manuico.notesapp.repositories;

import com.manuico.notesapp.models.Note;
import com.manuico.notesapp.models.User;
import com.orm.SugarRecord;

import java.util.Date;
import java.util.List;

public class NoteRepository {

    public static List<Note> list(){
        List<Note> notes = SugarRecord.listAll(Note.class);
        return notes;
    }

    public static Note read(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        return note;
    }

    public static void create(String title, String content){
        Note note = new Note();
        Date fecha = new Date();

        note.setTitle(title);
        note.setDate(fecha.getTime());
        note.setContent(content);

        SugarRecord.save(note);
    }

    /*public static User login(String username, String password) {
        List<User> users = SugarRecord.find(User.class, "username=? and password=?", username, password);

        if(!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }*/

    public static Note load(Long id) {
        Note note = SugarRecord.findById(Note.class, id);
        return note;
    }

    public static void update(String title, String content, Long id){
        Note note = SugarRecord.findById(Note.class, id);
        note.setTitle(title);
        note.setContent(content);
        SugarRecord.save(note);
    }

    public static void delete(Long id){
        Note note = SugarRecord.findById(Note.class, id);
        SugarRecord.delete(note);
    }

}
