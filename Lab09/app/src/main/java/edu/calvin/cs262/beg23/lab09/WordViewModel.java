package edu.calvin.cs262.beg23.lab09;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.calvin.cs262.beg23.lab09.Word;

/**
 * WordViewModel class to hold/process Word data for display by the UI
 */
public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;

    /**
     * Setup a WordRepository and get all Words from it.
     *
     * @param application
     */
    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() { return mAllWords; }

    /**
     * Inserts a Word into the DB.
     *
     * @param word Word to be inserted.
     */
    public void insert(Word word) { mRepository.insert(word); }

    public void deleteAll() {mRepository.deleteAll();}

    public void deleteWord(Word word) {mRepository.deleteWord(word);}
}
