package com.eaziche.zoo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.eaziche.zoo.R;
import com.eaziche.zoo.adapters.ExibitsAdapter;
import com.eaziche.zoo.models.Animal;
import com.eaziche.zoo.models.AnimalDelegate;
import com.eaziche.zoo.utils.AnimalApiInterface;
import com.eaziche.zoo.utils.EventBus;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExibitFragment extends ListFragment {

    ExibitsAdapter exibitsAdapter;
    Animal[] ani;


    static ExibitFragment fragment;
    public ExibitFragment() {
        // Required empty public constructor
    }

    public static ExibitFragment newInstance() {
        fragment = new ExibitFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListShown(false);
        exibitsAdapter = new ExibitsAdapter(getActivity(),0);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.exibits_feeds)).build();
        AnimalApiInterface animalApiInterface = restAdapter.create(AnimalApiInterface.class);
        animalApiInterface.getStream(new Callback<List<Animal>>() {
            @Override
            public void success(List<Animal> animals, Response response) {
                if(animals ==null || animals.isEmpty())return;
                int i=0;
                ani = new Animal[animals.size()];
                for (Animal animal : animals){
                    ani[i] = animal;
                    exibitsAdapter.add(animal);
                    i++;
                }
                exibitsAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Retroit Error",error.getMessage());
            }
        });
     //   setListAdapter(exibitsAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //setListShown(true);
        setListAdapter(exibitsAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Animal animal = ani[position];
       try {
           EventBus.getInstance().post(new AnimalDelegate(animal));
           ExibitDetalFragment detalFragment = new ExibitDetalFragment();
            FragmentTransaction fragTransaction = getFragmentManager().beginTransaction();
            fragTransaction.addToBackStack("DetailFragment");
            fragTransaction.replace(R.id.content_main, detalFragment).commit();
        }catch (Exception e){
            Log.e("WHATHAPPEN",e.getLocalizedMessage()+e.getCause()+e.getMessage());
            e.printStackTrace();
        }

        /*Intent intent = new Intent(getActivity(),Exibit.class);
        intent.putExtra("animal_detail",animal);
        startActivity(intent);*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }


}
