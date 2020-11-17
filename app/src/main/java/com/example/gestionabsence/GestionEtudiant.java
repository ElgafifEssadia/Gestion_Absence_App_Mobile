package com.example.gestionabsence;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GestionEtudiant extends AppCompatActivity {
    EditText nom,prenom,email,adresse;
    Spinner spFiliere;
    ListView lv;
    Button ajouter,modifier,supprimer,afficher,retour;
    ArrayList<Etudiant> etudiants_r;

    ArrayList<String> filieres;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_etudiant);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        adresse = findViewById(R.id.adresse);
        email = findViewById(R.id.email);
        ajouter = findViewById(R.id.ajouterEtu);
        supprimer = findViewById(R.id.supprimerEtu);
        modifier = findViewById(R.id.modifierEtu);
        afficher = findViewById(R.id.afficherEtu);
        retour = findViewById(R.id.retour);
        lv = findViewById(R.id.lv);
        spFiliere = findViewById(R.id.spFiliere);

        filieres = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, filieres);
        spFiliere.setAdapter(adapter);

        etudiants_r = new ArrayList<Etudiant>();

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GestionEtudiant.this,gestionAbsence.class );
                startActivity(i);
            }
        });

        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficher();
            }
        });



    }

    protected void onStart() {
        super.onStart();

        String url = "https://upf-2019.000webhostapp.com/spFiliere.php";
        BackTask t = new BackTask();
        t.execute(url);



    }


    public class BackTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();
            filieres.clear();
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
                    filieres.add(object.getString("id") +" | "+ object.getString("description"));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private void afficher() {

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://upf-2019.000webhostapp.com/afficherEtudiant.php",
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
                                Filiere f = new Filiere(mJsonObject.getJSONObject(i).getString("filiere"));

                                Etudiant etudiant = new Etudiant(mJsonObject.getJSONObject(i).getString("nom"),mJsonObject.getJSONObject(i).getString("prenom"),mJsonObject.getJSONObject(i).getString("adresse"),mJsonObject.getJSONObject(i).getString("email"),f);
                                // db.inserer(mJsonObject.getJSONObject(i).getString("userId"),mJsonObject.getJSONObject(i).getString("id"),mJsonObject.getJSONObject(i).getString("title"));

                                etudiants_r.add(etudiant);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                        //creating custom adapter object
                        //AdapterPerso adapterPerso  = new AdapterPerso(etudiants_r,getApplicationContext());

                        //adding the adapter to listview
                       // lv.setAdapter(adapterPerso);


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
