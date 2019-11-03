package edu.calvin.cs262.beg23.homework3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data accessor for the Game Table
 */
@Dao
public interface GameDao {

    // Insert a Game obj
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Game game);

    // Delete all Games
    @Query("DELETE FROM game_table")
    void deleteAll();

    // Get all Games ordered by ID
    @Query("SELECT * from game_table ORDER BY id ASC")
    LiveData<List<Game>> getAllGames();

    // Get one Game
    @Query("SELECT * from game_table LIMIT 1")
    Game[] getAnyGame();

    // Delete a Game
    @Delete
    void deleteGame(Game game);
}