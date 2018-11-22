package com.eaziche.zoo.models;

/**
 * Created by hardik on 27-11-2016.
 */

public class AnimalDelegate {

    private  Animal animal = new Animal();

    public AnimalDelegate(Animal animal){
        this.animal = animal;
    }

    public Animal getAnimal(){
        return animal;
    }
}
