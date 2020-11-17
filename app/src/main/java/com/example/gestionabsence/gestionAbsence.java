package com.example.gestionabsence;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class gestionAbsence extends AppCompatActivity {
    Spinner spEtudiant, spSeance;
    EditText justfification;
    Button marquer,etd;

    AdapterPerso adapterPerso;

    ArrayList<String> etudiants;
    ArrayAdapter<String> adapter;

    ArrayList<String> seances;
    ArrayAdapter<String> adapter2;
    ArrayList<Absence> absences_r;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_absence);

        justfification = findViewById(R.id.justification);
        marquer = findViewById(R.id.marquerAbsence);
        spEtudiant = findViewById(R.id.spEtudiant);
        spSeance = findViewById(R.id.spSeance);

        etd = findViewById(R.id.etudiants);

        listView = findViewById(R.id.lv);


        etudiants = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, etudiants);
        spEtudiant.setAdapter(adapter);

        seances = new ArrayList<String>();
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seances);
        spSeance.setAdapter(adapter2);

        absences_r = new ArrayList<Absence>();

        etd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(gestionAbsence.this,GestionEtudiant.class );
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialog = new Dialog(gestionAbsence.this);
                dialog.setContentView(R.layout.dialoge_model);
                final EditText dialogJust = dialog.findViewById(R.id.dialogueJustification);

                Button modifier = dialog.findViewById(R.id.modifier);
                Button annuler = dialog.findViewById(R.id.annuler);

                dialogJust.setText(absences_r.get(position).getJustification());

                modifier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        absences_r.set(position,new Absence(dialogJust.getText().toString()));
                        adapterPerso.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                annuler.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                dialog.show();
            }
        });




        marquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*String string = spEtudiant.getSelectedItem().toString();
                String[] parts = string.split("|");

                String string2 = spSeance.getSelectedItem().toString();
                String[] parts2 = string.split("|");*/


                String url="https://upf-2019.000webhostapp.com/marquerAbsence.php?idE="+spEtudiant.getSelectedItem().toString()+"&idS="+spSeance.getSelectedItem().toString()+"&just="+justfification.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(gestionAbsence.this);
                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(gestionAbsence.this,response,Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(request);

            }
        });



        afficher();
    }

    protected void onStart() {
        super.onStart();

        String url = "https://upf-2019.000webhostapp.com/spEtudiant.php";
        BackTask t = new BackTask();
        t.execute(url);

        String url2 = "https://upf-2019.000webhostapp.com/spSeance.php";
        BackTask2 t2 = new BackTask2();
        t2.execute(url2);

    }

    public class BackTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            etudiants.clear();
        }

        @Override
        protected String doInBackground(String... params) {

            String res = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                res = reader.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray array = new JSONArray(s);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    //etudiants.add(object.getString("id") +" | "+ object.getString("nom"));
                    etudiants.add(object.getString("id"));
                    etudiants.add(object.getString("nom"));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public class BackTask2 extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            seances.clear();
        }

        @Override
        protected String doInBackground(String... params) {

            String res = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                res = reader.readLine();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray array = new JSONArray(s);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    //seances.add(object.getString("id") + " | " +object.getString("typeSeance"));
                    seances.add(object.getString("id"));
                    seances.add(object.getString("typeSeance"));
                }
                adapter2.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void afficher() {

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://upf-2019.000webhostapp.com/afficherAbsence.php",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {


                        JSONArray mJsonObject = null;
                        try {
                            mJsonObject = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < mJsonObject.length(); i++) {
                            try {

                                Absence absence = new Absence(mJsonObject.getJSONObject(i).getString("nom"),mJsonObject.getJSONObject(i).getString("typeSeance"),mJsonObject.getJSONObject(i).getString("justification"));
                                // db.inserer(mJsonObject.getJSONObject(i).getString("userId"),mJsonObject.getJSONObject(i).getString("id"),mJsonObject.getJSONObject(i).getString("title"));

                                absences_r.add(absence);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                        //creating custom adapter object
                        AdapterPerso adapterPerso  = new AdapterPerso(absences_r,getApplicationContext());

                        //adding the adapter to listview
                        listView.setAdapter(adapterPerso);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

}




