package edu.calvin.cs262.beg23.homework2;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PageLoader extends AsyncTaskLoader<String> {
    private String mUrl;

    /**
     * The constructor sets the url to load.
     *
     * @param context The current Context.
     * @param url     The String of the url to load.
     */
    PageLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    /**
     * Begin loading the requested page.
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        forceLoad();
    }

    /**
     * Get the page source of mUrl.
     *
     * @return The String of the page source.
     */
    @Nullable
    @Override
    public String loadInBackground() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader;
        String pageContent;

        try {
            // Connect to given url (timeout after 1 minute)
            URL requestURL = new URL(mUrl);
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(60000);
            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            // Gather the page source
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            pageContent = builder.toString();
        } catch (IOException e) {

            // Display any errors
            e.printStackTrace();
            pageContent = "Check your internet connection and provided url, then try again";
        } finally {

            // Disconnect
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return pageContent;
    }
}
