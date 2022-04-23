package com.example.rocnikovaprace.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocnikovaprace.ImageSaver;
import com.example.rocnikovaprace.MainActivity;
import com.example.rocnikovaprace.MalyAdapter;
import com.example.rocnikovaprace.NastaveniFragment;
import com.example.rocnikovaprace.R;
import com.example.rocnikovaprace.Slovicka;
import com.example.rocnikovaprace.databinding.FragmentHomeBinding;
import com.example.rocnikovaprace.ui.gallery.StredniAdapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment implements MalyAdapter.onNoteListener {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    ArrayList<Slovicka> source;
    ArrayList<Slovicka> source2;
    RecyclerView.LayoutManager RecyclerViewLayoutManager;
    RecyclerView.LayoutManager RecyclerViewLayoutManager2;
    MalyAdapter adapter;
    StredniAdapter adapter2;
    LinearLayoutManager HorizontalLayout;
    LinearLayoutManager HorizontalLayout2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        String c = "";
        File file = new File(getContext().getFilesDir(), "cislo.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
             c =br.readLine();
        } catch (Exception e) {
            System.out.println("Chyba při čtení ze souboru.");
        }






        if(c.equals("ano")){

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
                bw.write("ne");
                bw.newLine();
                bw.flush();

            } catch (Exception e) {
                System.out.println("Do souboru se nepovedlo zapsat.");
            }

            Intent i = new Intent(getContext(), MainActivity.class);
            startActivity(i);



        }


        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        recyclerView
                = (RecyclerView) root.findViewById(
                R.id.recyclerview3);
        RecyclerViewLayoutManager
                = new

                LinearLayoutManager(
                getContext());


        recyclerView2
                = (RecyclerView) root.findViewById(
                R.id.recyclerview2);
        RecyclerViewLayoutManager
                = new

                LinearLayoutManager(
                getContext());


        // Přiřadí LayoutManager k Recycler View
        recyclerView.setLayoutManager(
                RecyclerViewLayoutManager);

        recyclerView2.setLayoutManager(
                RecyclerViewLayoutManager2);

        // Přidá položka do ArrayListu
        AddItemsToRecyclerViewArrayList();
        AddItemsToRecyclerViewArrayList2();

        // Zavolá konstruktor
        adapter = new MalyAdapter(source);
        adapter2 = new StredniAdapter(source2);

        // Nastaví Horizontal Layout Manager pro Recycler view
        HorizontalLayout
                = new

                LinearLayoutManager(
                getActivity().

                        getApplicationContext(),

                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(HorizontalLayout);


        HorizontalLayout2
                = new

                LinearLayoutManager(
                getActivity().

                        getApplicationContext(),

                LinearLayoutManager.HORIZONTAL,
                false);
        recyclerView2.setLayoutManager(HorizontalLayout2);


        // Set adapter on recycler view
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(simpleCallback2);
        itemTouchHelper2.attachToRecyclerView(recyclerView2);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //if (notifyFragmentExit()) return false;
        switch(item.getItemId()){
            case R.id.nav_nastaveni2:
                //FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //transaction.show(new NastaveniFragment()).commit();
                Bundle bundle = new Bundle();
                Navigation.findNavController(getView()).navigate(R.id.nav_home, bundle);
                break;
        }
        return super.onOptionsItemSelected(item);
    }






    //Přidá položky do seznamu
    public void AddItemsToRecyclerViewArrayList() {
        source = new ArrayList<>();
        File file = new File(getContext().getFilesDir(), "slovicka.txt");
        //Načte slovíčka ze souboru
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            int p = 0;
            while ((s = br.readLine()) != null) {
                Bitmap bitmap = new ImageSaver(getContext()).setFileName(s + ".png").setDirectoryName(file.getName()).load();
                Slovicka slovo = new Slovicka(s, bitmap);
                //Přidá je do ArrayListu
                source.add(slovo);
                p++;
            }
        } catch (Exception e) {
            System.out.println("Chyba při čtení ze souboru.");
        }


    }


    public void AddItemsToRecyclerViewArrayList2() {
        source2 = new ArrayList<>();


    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.UP) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            return false;
        }

        //Po přetáhnutí nahoru, nebo dolu smaže položku ze seznamu
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            source2.add(source.get(position));
            source.remove(position);
            recyclerView.getAdapter().notifyItemRemoved(position);
            recyclerView2.getAdapter().notifyItemInserted(source2.size());

        }
    };


    ItemTouchHelper.SimpleCallback simpleCallback2 = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.DOWN) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(source2, fromPosition, toPosition);
            recyclerView2.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        //Po přetáhnutí nahoru, nebo dolu smaže položku ze seznamu
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            Slovicka slovicka = source2.get(position);
            source.add(slovicka);
            source2.remove(position);
            recyclerView2.getAdapter().notifyItemRemoved(position);
            recyclerView.getAdapter().notifyItemInserted(source.size());


        }
    };


}

