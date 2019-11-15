package edu.calvin.cs262.beg23.homework3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Game table from database
 */
@Entity(tableName = "game_table")
public class Game {

    // Columns in Game Table
    // Help for auto generation from https://stackoverflow.com/questions/44109700/how-to-make-primary-key-as-autoincrement-for-room-persistence-lib
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    // Unsure how to do a timestamp better
    @ColumnInfo(name = "time")
    private String mTime;

    /**
     * Constructor for Game Table (id is auto generated)
     *
     * @param id The id field
     * @param time The time field (String)
     */
    public Game(int id, String time) {
        this.mId = id;
        this.mTime = time;
    }

    /**
     * Getters for Column values
     */
    public int getId(){return this.mId;}
    public String getTime(){return this.mTime;}
}

