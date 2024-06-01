package com.example.faturamento_app_aula;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private NumberPicker numberPicker;  //cria o objeto NumberPicker

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        numberPicker = findViewById(R.id.idNumberPicker); //associar o numberPicker ao elemento de layout
        numberPicker.setMinValue(2015); // valor minimo que vai apraecer
        numberPicker.setMaxValue(2024); // valor maximo que vai aparecer


        //criando o listener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Toast.makeText(MainActivity.this, "Teste", Toast.LENGTH_SHORT).show(); // toast serve para apresentar uma mensagem, foi colocada por enquanto, ate fazer a parte logica mesmos
            }
        });


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */
    }
}