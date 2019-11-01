package edu.calvin.cs262.beg23.lab09;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data accessor for the database
 */
@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // Insert a Word obj
    void insert(Word word);

    @Query("DELETE FROM word_table")
    // Delete all Words
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    // Get all Words ordered alphabetically
    LiveData<List<Word>> getAllWords();

    @Query("SELECT * from word_table LIMIT 1")
    Word[] getAnyWord();

    @Delete
    void deleteWord(Word word);
}
