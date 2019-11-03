package edu.calvin.cs262.beg23.homework3;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Player table from database
 */
@Entity(tableName = "player_table")
public class Player {

    // Columns in Player Table
    // Help for autogen from https://stackoverflow.com/questions/44109700/how-to-make-primary-key-as-autoincrement-for-room-persistence-lib
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "emailAddress")
    private String mEmailAddress;

    @ColumnInfo(name = "name")
    private String mName;

    /**
     * Constructor for Player Table (id is auto generated)
     *
     * @param id The id field
     * @param emailAddress The emailAddress field
     * @param name The name field
     */
    public Player(int id, @NonNull String emailAddress, String name) {
        this.mId = id;
        this.mEmailAddress = emailAddress;
        this.mName = name;
    }

    /**
     * Getters for Column values
     */
    public int getId(){return this.mId;}
    public String getEmailAddress(){return this.mEmailAddress;}
    public String getName(){return this.mName;}
}

