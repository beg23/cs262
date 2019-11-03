package edu.calvin.cs262.beg23.homework3;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.calvin.cs262.beg23.homework3.Game;
import edu.calvin.cs262.beg23.homework3.GameDao;

/**
 * Repository to access the database through DAO
 */
public class GameRepository {

    private GameDao mGameDao;
    private LiveData<List<Game>> mAllGames;

    /**
     * Create this Repository by getting a database and all Games
     *
     * @param application Current Application
     */
    GameRepository(Application application) {
        MonopolyRoomDatabase db = MonopolyRoomDatabase.getDatabase(application);
        mGameDao = db.gameDao();
        mAllGames = mGameDao.getAllGames();
    }

    /**
     * Return all Games in DB
     *
     * @return LiveData List of all Games
     */
    LiveData<List<Game>> getAllGames() {
        return mAllGames;
    }

    /**
     * Spawn AsyncTask to delete all Games
     */
    public void deleteAll()  {
        new deleteAllGamesAsyncTask(mGameDao).execute();
    }

    /**
     * Spawn AsyncTask to insert one Game
     *
     * @param game Game to be inserted
     */
    public void insert (Game game) {
        new insertAsyncTask(mGameDao).execute(game);
    }

    /**
     * Spawn AsyncTask to delete one Game
     *
     * @param game Game to be deleted
     */
    public void deleteGame(Game game)  {
        new deleteGameAsyncTask(mGameDao).execute(game);
    }

    /**
     * AsyncTask to insert new Games
     */
    private static class insertAsyncTask extends AsyncTask<Game, Void, Void> {

        private GameDao mAsyncTaskDao;

        insertAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * AsyncTask to delete all Games
     */
    private static class deleteAllGamesAsyncTask extends AsyncTask<Void, Void, Void> {
        private GameDao mAsyncTaskDao;

        deleteAllGamesAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    /**
     * AsyncTask to delete a single Game
     */
    private static class deleteGameAsyncTask extends AsyncTask<Game, Void, Void> {
        private GameDao mAsyncTaskDao;

        deleteGameAsyncTask(GameDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Game... params) {
            mAsyncTaskDao.deleteGame(params[0]);
            return null;
        }
    }
}