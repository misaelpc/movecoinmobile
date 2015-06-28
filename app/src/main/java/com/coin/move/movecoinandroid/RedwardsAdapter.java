package com.coin.move.movecoinandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by misaelperezchamorro on 6/28/15.
 */
public class RedwardsAdapter extends ArrayAdapter {

    public RedwardsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        listItemView = inflater.inflate(R.layout.redward_item,parent,false);

        return listItemView;
    }
}
