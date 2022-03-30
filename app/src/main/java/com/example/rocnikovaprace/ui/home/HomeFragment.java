package com.example.rocnikovaprace.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocnikovaprace.ImageSaver;
import com.example.rocnikovaprace.MalyAdapter;
import com.example.rocnikovaprace.R;
import com.example.rocnikovaprace.Slovicka;
import com.example.rocnikovaprace.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements MalyAdapter.onNoteListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    RecyclerView recyclerView;
    ArrayList<Slovicka> source;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    MalyAdapter adapter;
    LinearLayoutManager HorizontalLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView
                =(RecyclerView)root.findViewById(
                R.id.recyclerview3);
        RecyclerViewLayoutManager
                =new

                LinearLayoutManager(
                getContext());

        // Přiřadí LayoutManager k Recycler View
        recyclerView.setLayoutManager(
                RecyclerViewLayoutManager);

        // Přidá položka do ArrayListu
        AddItemsToRecyclerViewArrayList();

        // Zavolá konstruktor
        adapter =new

                MalyAdapter(source);

        // Nastaví Horizontal Layout Manager pro Recycler view
        HorizontalLayout
                =new

                LinearLayoutManager(
                getActivity().

                        getApplicationContext(),

                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);

        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//Přidá položky do seznamu
    public void AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = new ArrayList<>();
        File file = new File(getContext().getFilesDir(), "slovicka.txt");
        //Načte slovíčka ze souboru
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            int p = 0;
            while ((s = br.readLine()) != null) {
                Bitmap bitmap = new ImageSaver(getContext()).setFileName(s + ".png").setDirectoryName("images").load();
                Slovicka slovo = new Slovicka(s, bitmap);
                //Přidá je do ArrayListu
                source.add(slovo);
                p++;
            }
        } catch (Exception e) {
            System.out.println("Chyba při čtení ze souboru.");
        }


    }



}

