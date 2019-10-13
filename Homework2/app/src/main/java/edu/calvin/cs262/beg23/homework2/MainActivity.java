package edu.calvin.cs262.beg23.homework2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private CharSequence mUrlType = "https://";
    private EditText mUrlText;
    private TextView mPageText;

    /**
     * Creates the activity and sets up the Spinner and page loader
     *     along with getting relevant text fields.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner operation = findViewById(R.id.url_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.url_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operation.setAdapter(adapter);
        operation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Stores the operator type (https:// or http://) when selected on the Spinner.
             *
             * @param parent The AdapterView which stores the array of url type.
             * @param view The View which was clicked.
             * @param pos The int index of the item which the Spinner currently has selected.
             * @param id The long row id of the selected item.
             */
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mUrlType = (CharSequence) parent.getItemAtPosition(pos);
            }


            /**
             * If the selection is lost from the Spinner, default to https.
             *
             * @param parent The AdapterView which lost its selection.
             */
            public void onNothingSelected(AdapterView<?> parent) {
                mUrlType = "https://";
            }
        });

        mUrlText = (EditText) findViewById(R.id.enter_url);
        mPageText = (TextView) findViewById(R.id.page_content);

        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
    }

    /**
     * Load the page at the entered url.
     *
     * @param view The calling View (the get page source button)
     */
    public void displayPage(View view) {
        // Produce the full url (http(s):// + rest.of.url.com)
        String fullUrl = mUrlType + mUrlText.getText().toString();

        // Validate the connection
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null ) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle urlBundle = new Bundle();

            // Start loading the page at the url
            urlBundle.putString("fullUrl", fullUrl);
            getSupportLoaderManager().restartLoader(0, urlBundle, this);
            mPageText.setText(R.string.loading);
        } else {

            // Prompt user to check connection
            mPageText.setText(R.string.check_connection);
        }
    }


    /**
     * Create PageLoader for the given url.
     *
     * @param id The id of the Loader.
     * @param args Should contain the url to load.
     * @return The PageLoader.
     */
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String fullUrl = "";

        if (args != null) {
            fullUrl = args.getString("fullUrl");
        }

        return new PageLoader(this, fullUrl);
    }

    /**
     * When the load is finished, display the resulting page source.
     *
     * @param loader The Loader which finished.
     * @param data The String containing the page source.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        mPageText.setText(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
