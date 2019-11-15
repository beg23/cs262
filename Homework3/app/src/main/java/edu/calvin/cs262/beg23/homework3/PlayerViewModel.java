package edu.calvin.cs262.beg23.homework3;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * PlayerViewModel class to hold/process Player data for display by the UI
 */
public class PlayerViewModel extends AndroidViewModel {

    private PlayerRepository mRepository;
    private LiveData<List<Player>> mAllPlayers;

    /**
     * Setup a PlayerRepository and get all Players from it
     *
     * @param application Current Application
     */
    public PlayerViewModel(Application application) {
        super(application);
        mRepository = new PlayerRepository(application);
        mAllPlayers = mRepository.getAllPlayers();
    }

    LiveData<List<Player>> getAllPlayers() {
        return mAllPlayers;
    }

    /**
     * Inserts a Player into the DB
     *
     * @param player Player to be inserted
     */
    public void insert(Player player) {
        mRepository.insert(player);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deletePlayer(Player player) {
        mRepository.deletePlayer(player);
    }
}

