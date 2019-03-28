package com.example.json;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ListView mListView;
    private Button mViewMore;
    private String idy;

    // List HasMap key, value
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewMore = findViewById(R.id.viewMore);
        mListView =  findViewById(R.id.list);
        contactList = new ArrayList<>();
        // Execute the methods of the entire class
        new GetContacts().execute();




    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        /**
         * Barre de chargement
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            // L'utilisateur ne peut quitter cette fenetre
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * Traitement
         * @param arg0
         * @return
         */
        @Override
        protected Void doInBackground(Void... arg0) {
            // Call class
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String results = httpHandler.callHttp();

            if (results != null) {
                try {
                    JSONObject jsonObj = new JSONObject(results);
                    // Getting JSON Array node nom du tableau
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");

                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("name", name);
                        contact.put("email", email);
                        contact.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e("Error", "Json parsing error: " + e.getMessage());
                }

            } else {
                Log.e("Error", "Couldn't get json from server.");
            }
            return null;
        }

        /**
         * When doInBackground() is finish
         * @param result
         */
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * name, email, mobile : key list
             * namy, email, mobile : id in list_item.xml
             *
             * The elements of the list_item.xml are injected into the ListView
             *
             **/
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList, R.layout.list_item, new String[]{"name", "email", "mobile"}, new int[]{R.id.namy, R.id.email, R.id.mobile});
            mListView.setAdapter(adapter);
        }
    }

public void clickMe(View view){

//        Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
//        //  intent.putExtra("id", idy);
//        startActivity(intent);
    Toast.makeText(this, "Button "+ idy,Toast.LENGTH_LONG).show();

}

}
