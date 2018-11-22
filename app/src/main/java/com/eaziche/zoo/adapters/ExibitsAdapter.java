package com.eaziche.zoo.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eaziche.zoo.R;
import com.eaziche.zoo.models.Animal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hardik on 23-11-2016.
 */

public class ExibitsAdapter extends ArrayAdapter<Animal> {

    private final Context context;

    public ExibitsAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView ==null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.view_exhibit_list_item, parent, false);
            convertView.setTag( holder);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.species = (TextView) convertView.findViewById(R.id.species);
            holder.thumbnail = (ImageView)convertView.findViewById(R.id.thumbnail);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText(getItem(position).getName());
        holder.species.setText(getItem(position).getSpecies());
        Picasso.with(context).load(getItem(position).getThumbnail()) .placeholder(android.R.drawable.btn_star).error(android.R.drawable.btn_star).into(holder.thumbnail);

        return convertView;

    }

    class ViewHolder{
        ImageView thumbnail ;
        TextView name,species;
        public ViewHolder(){
        }
    }
}
