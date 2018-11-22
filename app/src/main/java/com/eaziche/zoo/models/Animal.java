package com.eaziche.zoo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hardik on 23-11-2016.
 */

public class Animal implements Parcelable{
    private String name,species,description,thumbnail,image;
    public Animal(){
    }
    public Animal(String name, String species, String desc, String image, String thumbnail) {
        this.name = name;
        this.species = species;
        this.description = desc;
        this.image = image;
        this.thumbnail = thumbnail;
    }

    protected Animal(Parcel in) {
        name = in.readString();
        species = in.readString();
        description = in.readString();
        thumbnail = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(species);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
