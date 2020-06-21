package com.example.architectureexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAyncTask(noteDao).execute(note);

    }

    public void update(Note note) {
        new UpdateNoteAyncTask(noteDao).execute(note);

    }

    public void delete(Note note) {
        new DeleteNoteAyncTask(noteDao).execute(note);

    }

    public void deleteAllNotes() {
        new DeleteAllNotesAyncTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public InsertNoteAyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public UpdateNoteAyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        public DeleteNoteAyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public DeleteAllNotesAyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
