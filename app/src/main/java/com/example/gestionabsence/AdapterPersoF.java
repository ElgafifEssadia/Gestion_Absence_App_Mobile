package com.example.gestionabsence;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterPersoF extends ArrayList<Etudiant> {
    int res;
    Context con;
    ArrayList<Etudiant> arlEtudiants;

   /* public AdapterPersoF(@NonNull Context context, int resource, @NonNull ArrayList<Etudiant> objects) {
        super(context, resource, objects);
        this.res = resource;
        this.con=context;
        this.arlEtudiants=objects;
    }



    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.item_module, null, true);

        //getting text views
        TextView nom = listViewItem.findViewById(R.id.itemEtudiant);
        TextView qnt = listViewItem.findViewById(R.id.itemSeance);
        TextView price = listViewItem.findViewById(R.id.itemJust);


        //Getting the hero for the specified position
        Absence absence = products.get(position);

        //setting hero values to textviews
        nom.setText(absence.getNom());
        qnt.setText(absence.getTypeSeance());
        price.setText(absence.getJustification());


        //returning the listitem
        return listViewItem;
    }*/
}

