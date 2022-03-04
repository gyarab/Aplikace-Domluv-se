package com.example.rocnikovaprace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MalyAdapter extends RecyclerView.Adapter<Adapter.MyView>{
    // The adapter class which
// extends RecyclerView Adapter


    // List with String type
    private List<Slovicka> list;

    //declare interface

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView
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
    public MalyAdapter(List<Slovicka> horizontalList)
    {
        this.list = horizontalList;
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public Adapter.MyView onCreateViewHolder(ViewGroup parent,
                                             int viewType)
    {

        // Inflate item.xml using LayoutInflator
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.malyitem,
                        parent,
                        false);

        // return itemView
        return new Adapter.MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final Adapter.MyView holder,
                                 final int position)
    {

        // Set the text of each item of
        // Recycler view with the list item

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

    public class ViewHolder extends RecyclerView.ViewHolder {
TextView textView


    }

public interface onNoteListener{
        void onNoteClick(int position);

}

}
