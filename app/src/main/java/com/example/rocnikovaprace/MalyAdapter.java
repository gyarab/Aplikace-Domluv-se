package com.example.rocnikovaprace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MalyAdapter extends RecyclerView.Adapter<Adapter.MyView> {

    private List<Slovicka> list;

    public interface onNoteListener {
    }


    public class MyView
            extends RecyclerView.ViewHolder {


        TextView textView;
        ImageView obrazek;
        CardView cardView;

        // Konstruktor s parametrem View
        public MyView(View view) {
            super(view);

            // initialise TextView with id
            textView = (TextView) view
                    .findViewById(R.id.textview);

            obrazek = (ImageView) view
                    .findViewById(R.id.obrazek);

            cardView = (CardView) view
                    .findViewById(R.id.cardview);


        }
    }

    // Další konstruktor
    public MalyAdapter(List<Slovicka> horizontalList) {
        this.list = horizontalList;
    }


    @Override
    public Adapter.MyView onCreateViewHolder(ViewGroup parent,
                                             int viewType) {

        // Přiřazení rozložení a vzhledu položky v recyclerView
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.malyitem,
                        parent,
                        false);
        return new Adapter.MyView(itemView);
    }


    @Override
    public void onBindViewHolder(final Adapter.MyView holder,
                                 final int position) {

        //Nastaví text a obrázek, každé položce v seznamu
        holder.textView.setText(list.get(position).slovo);
        holder.obrazek.setImageBitmap(list.get(position).bitmapa);

    }

    // Vrátí délku recyclerView
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
