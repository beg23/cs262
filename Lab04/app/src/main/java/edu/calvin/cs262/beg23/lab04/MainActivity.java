package edu.calvin.cs262.beg23.lab04;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String toastMessage = "You didn't order anything.";
    public static final String EXTRA_MESSAGE =
            "edu.calvin.cs262.beg23.lab04.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Removed by request of Lab instruction 4
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            /**
             * Launch the OrderActivity when shopping cart floating button pressed.
             * <p>
             * Sends the most recent toast message in the intent.
             * </p>
             *
             * @param view The calling View object (floating shopping cart button).
             *
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, toastMessage);
                startActivity(intent);
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handle selection from options menu and display corresponding toast.
     *
     * @param item The options MenuItem selected.
     * @return A true boolean if terminated as expected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_order:
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, toastMessage);
                startActivity(intent);
                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Displays the message in a temporary toast message.
     *
     * @param message The String to be toasted.
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a message that the banana image was clicked.
     *
     * @param view The calling View object (the banana button).
     */
    public void showBananaOrder(View view) {
        toastMessage = getString(R.string.banana_order_message);
        displayToast(toastMessage);
    }

    /**
     * Shows a message that the cherry image was clicked.
     *
     * @param view The calling View object (the cherry button).
     */
    public void showCherryOrder(View view) {
        toastMessage = getString(R.string.cherry_order_message);
        displayToast(toastMessage);
    }

    /**
     * Shows a message that the orange image was clicked.
     *
     * @param view The calling View object (the orange button).
     */
    public void showOrangeOrder(View view) {
        toastMessage = getString(R.string.orange_order_message);
        displayToast(toastMessage);
    }
}
