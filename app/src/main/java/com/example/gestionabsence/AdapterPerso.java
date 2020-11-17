package com.example.gestionabsence;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class AdapterPerso extends ArrayAdapter<Absence> {

    //the hero list that will be displayed
    private List<Absence> products;

    //the context object
    private Context mCtx;

    //so while creating the object of this adapter class we need to give product and context
    public AdapterPerso(List<Absence> heroList, Context mCtx) {
        super(mCtx, R.layout.item_module, heroList);
        this.products = heroList;
        this.mCtx = mCtx;
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
    }
}
