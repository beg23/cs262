package edu.calvin.cs262.beg23.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.calvin.cs262.beg23.homework3.R;
import edu.calvin.cs262.beg23.homework3.Player;

/**
 * Adapter to allow a RecyclerView to display all Players
 */
public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private final LayoutInflater mInflater;
    private List<Player> mPlayers; // Cached copy of players

    PlayerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        String player_str;
        if (mPlayers != null) {
            Player current = mPlayers.get(position);
            player_str = current.getName() + "\n" + current.getEmailAddress();
            holder.playerItemView.setText(player_str);
        } else {
            // Covers the case of data not being ready yet.
            holder.playerItemView.setText("No Player");
        }
    }

    void setPlayers(List<Player> players){
        mPlayers = players;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mPlayers has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPlayers != null)
            return mPlayers.size();
        else return 0;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerItemView;

        private PlayerViewHolder(View itemView) {
            super(itemView);
            playerItemView = itemView.findViewById(R.id.textView);
        }
    }

    public Player getPlayerAtPosition (int position) {
        return mPlayers.get(position);
    }
}
