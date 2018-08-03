package com.example.baeza.fatherhome.network;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import timber.log.Timber;

public class JsonRequestModel implements JsonRequestManager.Model{

    private static JsonRequestManager.Presenter mPresenter;

    public JsonRequestModel(JsonRequestManager.Presenter presenter){
        this.mPresenter = presenter;
    }

    @Override
    public void requestJsonModel(String version, String book, int chapter, String apiKey) {
        String textStyle = ApiUtils.getOrationOneVersePerLine();
        Uri uri = Uri.parse("http://api.biblia.com/v1/bible/content/"
                + version+".txt.txt?passage="
                + book
                + chapter
                + "&style="+textStyle
                + "&redLetter=true"
                + "&callback=myCallbackFunction&key="
                +apiKey).buildUpon().build();

        new JsonRequestAsyncTask().execute(uri.toString());
    }


    private static class JsonRequestAsyncTask extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                try {
                    URI uri = new URI(url.getProtocol(),url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                    url = uri.toURL();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n\n");
                    Timber.d("Response: >>>"+ line);   //here u ll get whole response...... :-)
                }

                Timber.d(buffer.toString());
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mPresenter.refreshTextView(result);
        }
    }
}