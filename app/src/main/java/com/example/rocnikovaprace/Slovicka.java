package com.example.rocnikovaprace;

import android.graphics.Bitmap;

public class Slovicka {
    String slovo;
    Bitmap bitmapa;

    //Konstruktor, aby bylo možné vytvořit objekty
    public Slovicka(String slovo, Bitmap bitmapa) {
        this.slovo = slovo;
        this.bitmapa = bitmapa;
    }


    @Override
    public String toString() {
        return slovo;
    }
}
