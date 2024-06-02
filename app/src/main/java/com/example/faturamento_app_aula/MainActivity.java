package com.example.faturamento_app_aula;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private NumberPicker numberPicker;  //cria o objeto NumberPicker
    private Button mButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.button);
        numberPicker = findViewById(R.id.idNumberPicker); //associar o numberPicker ao elemento de layout
        numberPicker.setMinValue(2015); // valor minimo que vai apraecer
        numberPicker.setMaxValue(2023); // valor maximo que vai aparecer

        //criando o listener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
           @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) { //adiciona a ação - método especifico para o NumberPicker
                Toast.makeText(MainActivity.this, "Teste", Toast.LENGTH_SHORT).show(); // toast serve para apresentar uma mensagem (depois some), foi colocada por enquanto, ate fazer a parte logica mesmos
            }                                                                                          // Toast.LENGTH_SHORT é o tempo de duração
        });                                                                                          // sempre que eu mudar a data, ele executa a função

        //para botoes, usar View.OnClickListener() que é o padrão, para chamar direto no XML
       mButton.setOnClickListener(new View.OnClickListener() {  //esperar ele abrir as opções para selecionar, já traz as partes necessárias do código.
           @Override
           public void onClick(View v) {
               Toast.makeText(MainActivity.this, "apertei o botao", Toast.LENGTH_SHORT).show();
           }
       });




      /*  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */
    }
}