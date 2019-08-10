package com.hendyapp.listjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    private static String url = "https://jsonplaceholder.typicode.com/users";

    ArrayList<HashMap<String, String>> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Users");

        userList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetUsers().execute();
    }

    private class GetUsers extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ProtocolHandler sh = new ProtocolHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray users = new JSONArray(jsonStr);

                    // looping through All Users
                    for (int i = 0; i < users.length(); i++) {

                        JSONObject c = users.getJSONObject(i);
                        String id = Integer.toString(c.getInt("id"));
                        String name = c.getString("name");
                        String username = c.getString("username");
                        String email = c.getString("email");
                        String phone = c.getString("phone");
                        String website = c.getString("website");

                        JSONObject company = c.getJSONObject("company");
                        String cname = company.getString("name");
                        String ccp = company.getString("catchPhrase");
                        String cbs = company.getString("bs");

                        JSONObject address = c.getJSONObject("address");
                        String street = address.getString("street");
                        String suite = address.getString("suite");
                        String city = address.getString("city");
                        String zipcode = address.getString("zipcode");

                        JSONObject geo = address.getJSONObject("geo");
                        String lat = geo.getString("lat");
                        String lng = geo.getString("lng");

                        HashMap<String, String> user = new HashMap<>();

                        // adding each child node to HashMap key => value
                        user.put("id", id);
                        user.put("name", name);
                        user.put("username", username);
                        user.put("email", email);
                        user.put("phone", phone);
                        user.put("website", website);
                        user.put("street", street);
                        user.put("suite", suite);
                        user.put("city", city);
                        user.put("zipcode",zipcode);
                        user.put("lat", lat);
                        user.put("lng", lng);
                        user.put("cname",cname);
                        user.put("ccp",ccp);
                        user.put("cbs",cbs);

                        userList.add(user);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Parsing Json Error! " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Tidak dapat memperoleh json dari Server. Pastikan Anda terhubung ke Internet",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            /**
             * Updating parsed JSON data into ListView
             * */
            final ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, userList,
                    R.layout.lv_item, new String[]{"id","name"}, new int[]{R.id.id1,R.id.name});
            lv.setAdapter(adapter);
            lv.setSelected(true);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HashMap<String,String> object= userList.get(i);
                    Intent intent = new Intent(MainActivity.this,Detail.class);
                    intent.putExtra("id",object.get("id"));
                    intent.putExtra("name",object.get("name"));
                    intent.putExtra("username",object.get("username"));
                    intent.putExtra("email",object.get("email"));
                    intent.putExtra("phone",object.get("phone"));
                    intent.putExtra("website",object.get("website"));
                    intent.putExtra("street",object.get("street"));
                    intent.putExtra("suite",object.get("suite"));
                    intent.putExtra("city",object.get("city"));
                    intent.putExtra("zipcode",object.get("zipcode"));
                    intent.putExtra("lat",object.get("lat"));
                    intent.putExtra("lng",object.get("lng"));
                    intent.putExtra("cname",object.get("cname"));
                    intent.putExtra("ccp",object.get("ccp"));
                    intent.putExtra("cbs",object.get("cbs"));
                    startActivity(intent);
                }
            });
        }
    }

}
