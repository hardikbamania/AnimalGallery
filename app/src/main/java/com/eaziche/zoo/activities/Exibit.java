package com.eaziche.zoo.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.eaziche.zoo.R;
import com.eaziche.zoo.models.Animal;
import com.squareup.picasso.Picasso;

public class Exibit extends AppCompatActivity {
    TextView species,desc;
    ImageView image;
    private Animal animal = null;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        species = (TextView) findViewById(R.id.species);
        desc = (TextView) findViewById(R.id.description);
        image = (ImageView)findViewById(R.id.image);

        animal = getIntent().getParcelableExtra("animal_detail");

        Toast.makeText(this, animal.getName()+"", Toast.LENGTH_SHORT).show();
        Picasso.with(this).load(animal.getThumbnail()) .placeholder(android.R.drawable.btn_star).error(android.R.drawable.btn_star).into(image);
        species.setText(animal.getSpecies());
        desc.setText(animal.getDesc());

    }

}
