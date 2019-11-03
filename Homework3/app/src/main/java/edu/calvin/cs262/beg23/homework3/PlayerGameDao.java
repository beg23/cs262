package edu.calvin.cs262.beg23.homework3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import edu.calvin.cs262.beg23.homework3.Game;
import edu.calvin.cs262.beg23.homework3.Player;

import java.util.List;

/**
 * Data accessor for the PlayerGame Table
 */
@Dao
public interface PlayerGameDao {

    // Insert a PlayerGame obj
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PlayerGame playerGame);

    // Delete all PlayerGames
    @Query("DELETE FROM player_game_table")
    void deleteAll();

    // Get all PlayerGames ordered by ID
    @Query("SELECT * from player_game_table ORDER BY gameId ASC")
    LiveData<List<PlayerGame>> getAllPlayerGames();

    // Get one PlayerGame
    @Query("SELECT * from player_game_table LIMIT 1")
    PlayerGame[] getAnyPlayerGame();

    // Delete a PlayerGame
    @Delete
    void deletePlayerGame(PlayerGame playerGame);

    // Get all Games which a given Player is in
    @Query("SELECT * FROM game_table INNER JOIN player_game_table ON " +
            "game_table.id = player_game_table.gameId WHERE " +
            "player_game_table.playerId = :playerId")
    List<Game> getGamesForPlayers(final int playerId);

    // Get all Players which are in a given Game
    @Query("SELECT * FROM player_table INNER JOIN player_game_table ON " +
            "player_table.id = player_game_table.playerId WHERE " +
            "player_game_table.gameId = :gameId")
    List<Player> getPlayersForGames(final int gameId);
}