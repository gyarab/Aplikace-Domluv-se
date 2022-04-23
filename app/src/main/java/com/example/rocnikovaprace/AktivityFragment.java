package com.example.rocnikovaprace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocnikovaprace.ui.gallery.RecyclerViewClickInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class AktivityFragment extends Fragment {

    private AktivityViewModel mViewModel;
    String nazevslova = "";


    RecyclerView recyclerView;
    ArrayList<Slovicka> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    Adapter adapter;
    LinearLayoutManager HorizontalLayout;

    public static AktivityFragment newInstance() {
        return new AktivityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {




        View view = inflater.inflate(R.layout.aktivity_fragment, container, false);


        recyclerView
                = (RecyclerView) view.findViewById(
                R.id.recyclerview100);
        RecyclerViewLayoutManager
                = new

                LinearLayoutManager(
                getContext());

        // Přiřadí LayoutManager k Recycler View
        recyclerView.setLayoutManager(
                RecyclerViewLayoutManager);

        // Přidá položky do seznamu
        AddItemsToRecyclerViewArrayList();

        // Zavolá konstruktor
        RecyclerViewClickInterface inter = new RecyclerViewClickInterface() {
            @Override
            public void setClick(int abc) {
                source.remove(abc);
                Toast.makeText(getContext(),"Position is"+abc,Toast.LENGTH_LONG).show();
                adapter.notifyDataSetChanged();
            }
        };
        adapter = new Adapter(source, getContext(), inter);

        // Nastaví Horizontal Layout Manager pro Recycler view
        HorizontalLayout
                = new

                LinearLayoutManager(
                getActivity().

                        getApplicationContext(),

                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);

        // Nastaví adapter pro recycler view
        recyclerView.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AktivityViewModel.class);
        // TODO: Use the ViewModel
    }


    //Přidá položky do seznamu
    public void AddItemsToRecyclerViewArrayList() {
        source = new ArrayList<>();
        File file = new File(getContext().getFilesDir(), "rozvrh.txt");
        File file2 = new File(getContext().getFilesDir(), "aktivity.txt");
        //Nejdřív je načte ze souboru
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            int p = 0;
            while ((s = br.readLine()) != null) {
                Bitmap bitmap = new ImageSaver(getContext()).setFileName(s + ".png").setDirectoryName(file2.getName()).load();
                Slovicka slovo = new Slovicka(s, bitmap);
                //Potom je přidá do ArrayListu
                source.add(slovo);
                p++;
            }
        } catch (Exception e) {
            System.out.println("Chyba při čtení ze souboru.");
        }


    }




}