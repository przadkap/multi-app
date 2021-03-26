package com.pprzadka.multiapp;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactListActivity extends ListActivity {

    private ProgressDialog progressDialog;
    private static String url = "https://api.androidhive.info/contacts/";

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        contactsList = new ArrayList<HashMap<String, String>>();
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = ((TextView)view.findViewById(R.id.tv_firstname)).getText().toString();
                String phone = ((TextView)view.findViewById(R.id.tv_phone)).getText().toString();
                String home = contactsList.get(i).get(GV.TAG_HOME);
                String office = contactsList.get(i).get(GV.TAG_OFFICE);
                String address = contactsList.get(i).get(GV.TAG_ADDRESS);
                String gender = contactsList.get(i).get(GV.TAG_GENDER);
                String email = contactsList.get(i).get(GV.TAG_EMAIL);

                Intent in = new Intent(getApplicationContext(), ContactActivity.class);

                in.putExtra(GV.TAG_NAME, name);
                in.putExtra(GV.TAG_MOBILE, phone);
                in.putExtra(GV.TAG_HOME, home);
                in.putExtra(GV.TAG_OFFICE, office);
                in.putExtra(GV.TAG_ADDRESS, address);
                in.putExtra(GV.TAG_GENDER, gender);
                in.putExtra(GV.TAG_EMAIL, email);


                startActivity(in);
            }
        });
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ContactListActivity.this);
            progressDialog.setMessage("Please wait, data is being downloaded...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            ListAdapter listAdapter = new SimpleAdapter(
                    ContactListActivity.this,
                    contactsList,
                    R.layout.contact_list,
                    new String[]{GV.TAG_NAME, GV.TAG_MOBILE},
                    new int[]{
                            R.id.tv_firstname,
                            R.id.tv_phone,
                    }
            );
            setListAdapter(listAdapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(url);
            Log.d("RESPONSE", jsonStr.toString());
            if(jsonStr!=null) {
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    contacts = jsonObject.getJSONArray(GV.TAG_CONTACTS);
                    for(int i=0; i<contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String name = c.getString(GV.TAG_NAME);

                        JSONObject phone = c.getJSONObject(GV.TAG_PHONE);
                        String mobile = phone.getString(GV.TAG_MOBILE);
                        String home = phone.getString(GV.TAG_HOME);
                        String office = phone.getString(GV.TAG_OFFICE);

                        String address = c.getString(GV.TAG_ADDRESS);
                        String gender = c.getString(GV.TAG_GENDER);
                        String email  = c.getString(GV.TAG_EMAIL);

                        HashMap<String, String> oneContact = new HashMap<String, String>();
                        oneContact.put(GV.TAG_NAME, name);
                        oneContact.put(GV.TAG_MOBILE, mobile);
                        oneContact.put(GV.TAG_HOME, home);
                        oneContact.put(GV.TAG_OFFICE, office);
                        oneContact.put(GV.TAG_ADDRESS, address);
                        oneContact.put(GV.TAG_EMAIL, email);
                        oneContact.put(GV.TAG_GENDER, gender);
                        contactsList.add(oneContact);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("Service handler", "error, handler died, no connection, no internet");
            }
            return null;
        }
    }
}