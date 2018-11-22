package com.eaziche.zoo.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eaziche.zoo.R;
import com.eaziche.zoo.models.Animal;
import com.eaziche.zoo.utils.EventBus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExibitDetalFragment extends Fragment {

    TextView species,desc;
    ImageView image;
    Context context;

    public ExibitDetalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getInstance().unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_exibit_detal, container, false);
        species = (TextView) view.findViewById(R.id.species);
        desc = (TextView) view.findViewById(R.id.description);
        image = (ImageView) view.findViewById(R.id.image);
        return view;

    }


    @Subscribe
    public void getAnimalDetail(Animal animal){
        Log.e("Animal",animal.getName());
        //Toast.makeText(getContext(), animal.getThumbnail()+"", Toast.LENGTH_SHORT).show();
        Picasso.with(getContext()).load(animal.getThumbnail()) .placeholder(android.R.drawable.btn_star).error(android.R.drawable.btn_star).into(image);
        species.setText(animal.getSpecies());
        desc.setText(animal.getDesc());
    }

}
