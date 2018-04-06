package com.example.pc_seven.contadorpersonalizado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Contador extends AppCompatActivity {

    private Button btnAdd, btnDos; //declarando variables se pueden ir en linea por que son el mismo objeto
    //private Button btnDos; //Decalarando Variables
    private TextView tvNumber; //Decalarando variables
    private int numero; //Declarando variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference FNnumero = database.getReference("contador");
        //FNnumero.setValue(7);
        // #se hizo para validar el dato en la nube

       FNnumero.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               //lee la variable en la nube pero la baja en string la pasalamos a Integer
               numero = Integer.valueOf(    dataSnapshot.getValue().toString()  );
               //Actualizamos el valor en la nube
               tvNumber.setText(    dataSnapshot.getValue().toString()  ); //numero leido de la nube
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        btnAdd = (Button) findViewById(R.id.btn_add);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        btnDos = (Button) findViewById(R.id.btn_dos);

        //creo los eventos para los botones este incrementara ek valor + 1;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero++;
                FNnumero.setValue(numero);
            }
        });
        //Este evento reseteara el valor a 0
        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numero = 0;
                FNnumero.setValue(numero);
            }
        });
    }
}
