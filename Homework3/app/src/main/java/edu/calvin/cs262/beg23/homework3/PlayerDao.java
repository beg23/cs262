package edu.calvin.cs262.beg23.homework3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Data accessor for the Player Table
 */
@Dao
public interface PlayerDao {

    // Insert a Player obj
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Player player);

    // Delete all Players
    @Query("DELETE FROM player_table")
    void deleteAll();

    // Get all Players ordered by ID
    @Query("SELECT * from player_table ORDER BY id ASC")
    LiveData<List<Player>> getAllPlayers();

    // Get one Player
    @Query("SELECT * from player_table LIMIT 1")
    Player[] getAnyPlayer();

    // Delete a Player
    @Delete
    void deletePlayer(Player player);
}