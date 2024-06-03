package com.example.faturamento_app_aula;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText mEditTextNomeFantasia;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        //padrao do código, vem no arquivo
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mEditTextNomeFantasia = findViewById(R.id.editTextNomeEmpresa);
        mButton = findViewById(R.id.buttonCadastrarNomeEmpresa);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeFantasia = mEditTextNomeFantasia.getText().toString();
                //se for diferente de vazio
                if (!nomeFantasia.isEmpty()) {
                    getSharedPreferences(MainActivity.ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE)
                            .edit().putString("nomeFantasia", nomeFantasia).apply();
                }
            }
        });

    }
}