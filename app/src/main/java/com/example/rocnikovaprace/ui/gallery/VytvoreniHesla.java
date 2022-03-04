package com.example.rocnikovaprace.ui.gallery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.rocnikovaprace.MainActivity;
import com.example.rocnikovaprace.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class VytvoreniHesla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vytvoreni_hesla);

    }

    public void UlozHeslo (View view){
        EditText editText = findViewById(R.id.Heslo);
        if(editText.getText().toString().equals("")){
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage("Zadejte Heslo")
                    .setPositiveButton("ok" , null )
                    .show();
            return;
        }
       File heslosoubor = new File(getApplicationContext().getFilesDir(), "heslo.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(heslosoubor, true))) {
            bw.write(editText.getText().toString());
            bw.newLine();
            bw.flush();

        } catch (Exception e) {
            editText.setText("Do souboru se nepovedlo zapsat.");
        }

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);


    }





}