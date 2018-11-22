package com.eaziche.zoo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.eaziche.zoo.R;
import com.eaziche.zoo.activities.GalleryDetailActivity;
import com.eaziche.zoo.adapters.GalleryImageAdapter;
import com.eaziche.zoo.models.GalleryImage;
import com.eaziche.zoo.utils.GalleryApiInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String MY_FRAGMENT_TAG = "GALLERY";
    GridView gridView;
    GalleryImageAdapter imageAdapter;
    private static GalleryFragment myFragment;
    private View _rootView;

    public GalleryFragment() {
        // Required empty public constructor
    }

    public static GalleryFragment newInstance() {

        if (myFragment == null){
            myFragment = new GalleryFragment();
        }
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (_rootView == null) {
            _rootView = inflater.inflate(R.layout.fragment_gallery_grid, container, false);
            return _rootView;
        }
        else{
            Log.e("hello","Are ye toh yaaha pe aagaya");
        }
        return _rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageAdapter = new GalleryImageAdapter(getContext(), 0);
        gridView.setAdapter(imageAdapter);

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(getString(R.string.gallery_feed)).build();
        GalleryApiInterface apiInterface = restAdapter.create(GalleryApiInterface.class);
        apiInterface.getStream(new Callback<List<GalleryImage>>() {
            @Override
            public void success(List<GalleryImage> galleryImages, Response response) {
                if (galleryImages.isEmpty()) return;

                for (GalleryImage image : galleryImages) {
                    Log.e("ZOO", image.getCaption() + " " + image.getThumbnail());
                    imageAdapter.add(image);
                }
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setOnItemClickListener(this);
        gridView.setDrawSelectorOnTop(true);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GalleryImage image = (GalleryImage) parent.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), GalleryDetailActivity.class);
        intent.putExtra(GalleryDetailActivity.EXTRA_IMAGE, image.getImage());
        intent.putExtra(GalleryDetailActivity.EXTRA_CAPTION, image.getCaption());
        startActivity(intent);
    }
}
