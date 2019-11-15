package edu.calvin.cs262.beg23.homework3;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Repository to access the database through DAO
 */
public class PlayerRepository {

    private PlayerDao mPlayerDao;
    private LiveData<List<Player>> mAllPlayers;

    /**
     * Create this Repository by getting a database and all Players
     *
     * @param application Current Application
     */
    PlayerRepository(Application application) {
        MonopolyRoomDatabase db = MonopolyRoomDatabase.getDatabase(application);
        mPlayerDao = db.playerDao();
        GameDao mGameDao = db.gameDao();
        PlayerGameDao mPlayerGameDao = db.playerGameDao();
        mAllPlayers = mPlayerDao.getAllPlayers();
    }

    /**
     * Return all Players in DB
     *
     * @return LiveData List of all Players
     */
    LiveData<List<Player>> getAllPlayers() {
        return mAllPlayers;
    }

    /**
     * Spawn AsyncTask to delete all Players
     */
    public void deleteAll()  {
        new deleteAllPlayersAsyncTask(mPlayerDao).execute();
    }

    /**
     * Spawn AsyncTask to insert one Player
     *
     * @param player Player to be inserted
     */
    public void insert (Player player) {
        new insertAsyncTask(mPlayerDao).execute(player);
    }

    /**
     * Spawn AsyncTask to delete one Player
     *
     * @param player Player to be deleted
     */
    public void deletePlayer(Player player)  {
        new deletePlayerAsyncTask(mPlayerDao).execute(player);
    }

    /**
     * AsyncTask to insert new Players
     */
    private static class insertAsyncTask extends AsyncTask<Player, Void, Void> {

        private PlayerDao mAsyncTaskDao;

        insertAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all Players
     */
    private static class deleteAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlayerDao mAsyncTaskDao;

        deleteAllPlayersAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * AsyncTask to delete a single Player
     */
    private static class deletePlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDao mAsyncTaskDao;

        deletePlayerAsyncTask(PlayerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Player... params) {
            mAsyncTaskDao.deletePlayer(params[0]);
            return null;
        }
    }
}