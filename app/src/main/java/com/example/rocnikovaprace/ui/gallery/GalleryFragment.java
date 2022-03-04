package com.example.rocnikovaprace.ui.gallery;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rocnikovaprace.Adapter;
import com.example.rocnikovaprace.ImageSaver;
import com.example.rocnikovaprace.MainActivity;
import com.example.rocnikovaprace.R;
import com.example.rocnikovaprace.Slovicka;
import com.example.rocnikovaprace.databinding.FragmentGalleryBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;
    String nazevslova = "";

    // Recycler View object
    RecyclerView recyclerView;

    // Array list for recycler view data source
    ArrayList<Slovicka> source;

    // Layout Manager
    RecyclerView.LayoutManager RecyclerViewLayoutManager;

    // adapter class object
    Adapter adapter;

    // Linear Layout Manager
    LinearLayoutManager HorizontalLayout;

    View ChildView;
    int RecyclerViewItemPosition;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // dialog
        AlertDialog.Builder builder
                = new AlertDialog.Builder(getContext());
        builder.setTitle("Zadejte heslo");

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.heslodialog,
                        null);
        builder.setView(customLayout);
        builder.setCancelable(false);
        builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which) {
                                EditText editText
                                        = customLayout
                                        .findViewById(
                                                R.id.dialogoveheslo);
                                String zeSouboru = "";
                                File heslosoubor = new File(getContext().getFilesDir(), "heslo.txt");
                                try (BufferedReader br = new BufferedReader(new FileReader(heslosoubor))) {
                                    zeSouboru = br.readLine();
                                } catch (Exception e) {
                                    System.out.println("Chyba při čtení ze souboru.");
                                }

                                if (zeSouboru.equals(editText.getText().toString())){
                                }
                                else {
                                Intent i = new Intent(getContext(), MainActivity.class);
                                startActivity(i);}


                            }
                        });

        // create and show
        // the alert dialog
        AlertDialog dialog
                = builder.create();
        dialog.show();



    // initialisation with id's
    recyclerView
                =(RecyclerView)root.findViewById(
    R.id.recyclerview);
    RecyclerViewLayoutManager
                =new

    LinearLayoutManager(
            getContext());

    // Set LayoutManager on Recycler View
        recyclerView.setLayoutManager(
    RecyclerViewLayoutManager);

    // Adding items to RecyclerView.
    AddItemsToRecyclerViewArrayList();

    // calling constructor of adapter
    // with source list as a parameter
    adapter =new

    Adapter(source);

    // Set Horizontal Layout Manager
    // for Recycler view
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


    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        return root;
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        int i = 0;
        File file = new File(getContext().getFilesDir(), "slovicka.txt");

        try {
            FileWriter fw = new FileWriter(file, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception exception) {

            System.out.println("Chyba v mazání");

        }

        while (i < source.size()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(source.get(i).toString());
                bw.newLine();
                bw.flush();


            } catch (Exception e) {
                System.out.println("Do souboru se nepovedlo zapsat.");
            }

            i++;

        }


        binding = null;
    }

    public void AddItemsToRecyclerViewArrayList() {
        // Adding items to ArrayList
        source = new ArrayList<>();
        int p;
        File file = new File(getContext().getFilesDir(), "slovicka.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            p = 0;
            while ((s = br.readLine()) != null) {
                Bitmap bitmap = new ImageSaver(getContext()).setFileName(s + ".png").setDirectoryName("images").load();
                Slovicka slovo = new Slovicka(s, bitmap);
                source.add(slovo);
                p++;
            }
        } catch (Exception e) {
            System.out.println("Chyba při čtení ze souboru.");
        }


    }


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, ItemTouchHelper.UP | ItemTouchHelper.DOWN) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(source, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            nazevslova = source.get(position).toString();
            source.remove(position);
            recyclerView.getAdapter().notifyItemRemoved(position);
            Snackbar.make(recyclerView, nazevslova, Snackbar.LENGTH_LONG)
                    .setAction("Zpět", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bitmap bitmap2 = new ImageSaver(getContext()).setFileName(nazevslova + ".png").setDirectoryName("images").load();
                            Slovicka slovo = new Slovicka(nazevslova, bitmap2);
                            source.add(position, slovo);
                            recyclerView.getAdapter().notifyItemInserted(position);


                        }
                    }).show();


        }
    };


}