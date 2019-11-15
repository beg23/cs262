package edu.calvin.cs262.beg23.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter to allow a RecyclerView to display all Players
 */
public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private LayoutInflater mInflater;
    private List<Player> mPlayers; // Cached copy of players

    PlayerListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        String player_str;
        if (mPlayers != null) {
            Player current = mPlayers.get(position);
            player_str = current.getName() + "\n" + current.getEmailAddress();
            holder.playerItemView.setText(player_str);
        } else {
            // Covers the case of data not being ready yet.
            holder.playerItemView.setText(R.string.no_player);
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
