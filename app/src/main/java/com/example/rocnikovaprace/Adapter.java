package com.example.rocnikovaprace;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocnikovaprace.ui.gallery.RecyclerViewClickInterface;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyView> {

    private List<Slovicka> list;
    RecyclerViewClickInterface recyclerViewClickInterface;
    Context context;

    public static class MyView
            extends RecyclerView.ViewHolder {


        public TextView textView;
        public ImageView obrazek;
        public CardView cardView;

        // Konstruktor s paramentrem View
        public MyView(View view) {
            super(view);


            textView = (TextView) view
                    .findViewById(R.id.textview);

            obrazek = (ImageView) view
                    .findViewById(R.id.obrazek);

            cardView = (CardView) view
                    .findViewById(R.id.cardview);


        }
    }

    //Další konstruktor
    public Adapter(List<Slovicka> horizontalList, Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.list = horizontalList;
        this.context = context;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    // Metoda, která se stará o rozložení a vzhled jednotlivých položek v seznamu
    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType) {

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


    @Override
    public void onBindViewHolder(final MyView holder, @SuppressLint("RecyclerView") final int position) {

        //Nastaví text a obrázek
        holder.textView.setText(list.get(position).slovo);
        holder.obrazek.setImageBitmap(list.get(position).bitmapa);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewClickInterface.setClick(position);
            }
        });


    }

    // Vrátí délku seznamu
    @Override
    public int getItemCount() {
        return list.size();
    }


}
