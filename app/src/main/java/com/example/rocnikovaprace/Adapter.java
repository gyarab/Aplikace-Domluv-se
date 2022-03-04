package com.example.rocnikovaprace;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView>{
    // The adapter class which
// extends RecyclerView Adapter


        // List with String type
        private List<Slovicka> list;

        // View Holder class which
        // extends RecyclerView.ViewHolder
        public static class MyView
                extends RecyclerView.ViewHolder {

            // Text View
            TextView textView;
            ImageButton obrazek;
            CardView cardView;

            // parameterised constructor for View Holder class
            // which takes the view as a parameter
            public MyView(View view)
            {
                super(view);

                // initialise TextView with id
                textView = (TextView)view
                        .findViewById(R.id.textview);

                obrazek = (ImageButton) view
                        .findViewById(R.id.obrazek);

                cardView = (CardView) view
                        .findViewById(R.id.cardview);




            }
        }

        // Constructor for adapter class
        // which takes a list of String type
        public Adapter(List<Slovicka> horizontalList)
        {
            this.list = horizontalList;
        }

        // Override onCreateViewHolder which deals
        // with the inflation of the card layout
        // as an item for the RecyclerView.
        @Override
        public MyView onCreateViewHolder(ViewGroup parent,
                                         int viewType)
        {

            // Inflate item.xml using LayoutInflator
            View itemView
                    = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item,
                            parent,
                            false);

            // return itemView
            return new MyView(itemView);
        }

        // Override onBindViewHolder which deals
        // with the setting of different data
        // and methods related to clicks on
        // particular items of the RecyclerView.
        @Override
        public void onBindViewHolder(final MyView holder,
                                     final int position)
        {

            // Set the text of each item of
            // Recycler view with the list items

            holder.textView.setText(list.get(position).slovo);
            holder.obrazek.setImageBitmap(list.get(position).bitmapa);

        }

        // Override getItemCount which Returns
        // the length of the RecyclerView.
        @Override
        public int getItemCount()
        {
            return list.size();
        }



}
