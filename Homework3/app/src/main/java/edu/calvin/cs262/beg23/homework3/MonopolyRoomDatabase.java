package edu.calvin.cs262.beg23.homework3;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Class to store the monopoly database
 */
@Database(entities = {Player.class, Game.class, PlayerGame.class}, version = 1, exportSchema = false)
public abstract class MonopolyRoomDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
    public abstract GameDao gameDao();
    public abstract PlayerGameDao playerGameDao();
    private static MonopolyRoomDatabase INSTANCE;

    // Initialize the database
    static MonopolyRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MonopolyRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MonopolyRoomDatabase.class, "monopoly_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final PlayerDao mPlayerDao;
        private final GameDao mGameDao;
        private final PlayerGameDao mPlayerGameDao;
        String[] playerEmails = {"tahu@gmail.com", "kopaka@calvin.edu", "gali@yahoo.net"};
        String[] playerNames = {"Tahu", "Kopaka", "Gali"};
        String[] gameTimes = {"2006-06-27 08:00:00", "2013-10-01 08:34:22", "2019-11-02 6:50:19"};
        int[] gameIds = {1, 1, 2, 2, 3, 3};
        int[] playerIds = {1, 2, 1, 3, 2, 3};
        int[] scores = {0, 2350, 1000, 0, 500, 5500};

        PopulateDbAsync(MonopolyRoomDatabase db) {
            mPlayerDao = db.playerDao();
            mGameDao = db.gameDao();
            mPlayerGameDao = db.playerGameDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // If we have no players, then create the initial list of players
            if (mPlayerDao.getAnyPlayer().length < 1) {
                for (int i = 0; i <= playerNames.length - 1; i++) {
                    Player player = new Player(0, playerEmails[i], playerNames[i]);
                    mPlayerDao.insert(player);
                }
            }

            // If we have no games, then create the initial list of games
            if (mGameDao.getAnyGame().length < 1) {
                for (int i = 0; i <= gameTimes.length - 1; i++) {
                    Game game = new Game(0, gameTimes[i]);
                    mGameDao.insert(game);
                }
            }

            // If we have no playerGames, then create the initial list of playerGames
            if (mPlayerGameDao.getAnyPlayerGame().length < 1) {
                for (int i = 0; i <= gameIds.length - 1; i++) {
                    PlayerGame playerGame = new PlayerGame(gameIds[i], playerIds[i], scores[i]);
                    mPlayerGameDao.insert(playerGame);
                }
            }

            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
}
