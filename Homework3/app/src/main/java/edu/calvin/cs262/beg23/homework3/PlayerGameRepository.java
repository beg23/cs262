package edu.calvin.cs262.beg23.homework3;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.calvin.cs262.beg23.homework3.PlayerGame;
import edu.calvin.cs262.beg23.homework3.PlayerGameDao;

/**
 * Repository to access the database through DAO
 */
public class PlayerGameRepository {

    private PlayerGameDao mPlayerGameDao;
    private LiveData<List<PlayerGame>> mAllPlayerGames;

    /**
     * Create this Repository by getting a database and all PlayerGames
     *
     * @param application Current Application
     */
    PlayerGameRepository(Application application) {
        MonopolyRoomDatabase db = MonopolyRoomDatabase.getDatabase(application);
        mPlayerGameDao = db.playerGameDao();
        mAllPlayerGames = mPlayerGameDao.getAllPlayerGames();
    }

    /**
     * Return all PlayerGames in DB
     *
     * @return LiveData List of all PlayerGames
     */
    LiveData<List<PlayerGame>> getAllPlayerGames() {
        return mAllPlayerGames;
    }

    /**
     * Spawn AsyncTask to delete all PlayerGames
     */
    public void deleteAll()  {
        new deleteAllPlayerGamesAsyncTask(mPlayerGameDao).execute();
    }

    /**
     * Spawn AsyncTask to insert one PlayerGame
     *
     * @param playerGame PlayerGame to be inserted
     */
    public void insert (PlayerGame playerGame) {
        new insertAsyncTask(mPlayerGameDao).execute(playerGame);
    }

    /**
     * Spawn AsyncTask to delete one PlayerGame
     *
     * @param playerGame PlayerGame to be deleted
     */
    public void deletePlayerGame(PlayerGame playerGame)  {
        new deletePlayerGameAsyncTask(mPlayerGameDao).execute(playerGame);
    }

    /**
     * AsyncTask to insert new PlayerGames
     */
    private static class insertAsyncTask extends AsyncTask<PlayerGame, Void, Void> {

        private PlayerGameDao mAsyncTaskDao;

        insertAsyncTask(PlayerGameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlayerGame... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all PlayerGames
     */
    private static class deleteAllPlayerGamesAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlayerGameDao mAsyncTaskDao;

        deleteAllPlayerGamesAsyncTask(PlayerGameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * AsyncTask to delete a single PlayerGame
     */
    private static class deletePlayerGameAsyncTask extends AsyncTask<PlayerGame, Void, Void> {
        private PlayerGameDao mAsyncTaskDao;

        deletePlayerGameAsyncTask(PlayerGameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PlayerGame... params) {
            mAsyncTaskDao.deletePlayerGame(params[0]);
            return null;
        }
    }
}