package edu.calvin.cs262.beg23.homework3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * PlayerGame table from database (joins Player and Game Tables)
 */
@Entity(tableName = "player_game_table", primaryKeys = { "gameId", "playerId" })
public class PlayerGame {

    @ForeignKey(entity = Game.class, parentColumns = "id", childColumns = "gameId")
    @ColumnInfo(name = "gameId")
    private int mGameId;

    @ForeignKey(entity = Player.class, parentColumns = "id", childColumns = "playerId")
    @ColumnInfo(name = "playerId")
    private int mPlayerId;

    @ColumnInfo(name = "score")
    private int mScore;

    /**
     * Constructor for PlayerGame Table
     *
     * @param gameId The gameId foreign key field
     * @param playerId The playerId foreign key field
     * @param score The money score field
     */
    public PlayerGame(int gameId, int playerId, int score) {
        this.mGameId = gameId;
        this.mPlayerId = playerId;
        this.mScore = score;
    }

    /**
     * Getters for Column values
     */
    public int getGameId(){return this.mGameId;}
    public int getPlayerId(){return this.mPlayerId;}
    public int getScore(){return this.mScore;}
}
