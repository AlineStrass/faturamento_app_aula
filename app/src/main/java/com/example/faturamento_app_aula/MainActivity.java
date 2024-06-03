package com.example.faturamento_app_aula;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //criar um arquivo "meus dados"
    public static final String ARQUIVO_MEUS_DADOS = "MeusDados";

    //instanciando objetos das views que irão ter alguma interação.
    private TextView mTextViewSaldo;
    private EditText mEditTextValor;
    private NumberPicker numberPicker;  //cria o objeto NumberPicker
    private RadioGroup mRadioGroup;
    private Button mButton;
    private Button mButtonTitulo;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //realacionando com o id que foi cadastrado lá no arquivo xml
        mTextViewSaldo = findViewById(R.id.textView2);
        mEditTextValor = findViewById(R.id.editTextTextPersonName);
        mRadioGroup = findViewById(R.id.radioGroup);
        mButton = findViewById(R.id.button);
        mButtonTitulo = findViewById(R.id.button5);
        numberPicker = findViewById(R.id.idNumberPicker); //associar o numberPicker ao elemento de layout

        //define as datas de inicio e fim do number picker
        numberPicker.setMinValue(2013); // valor minimo que vai apraecer
        numberPicker.setMaxValue(2024); // valor maximo que vai aparecer

        //registra o listener pra alteração de valores no NumberPicker
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
           @Override
           //adiciona a ação - método especifico para o NumberPicker - onValueChange: sempre que eu mudar a data, ele executa a função
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
               // toast serve para apresentar uma mensagem (depois some), foi colocada por enquanto, ate fazer a parte logica mesmos
               // Toast.LENGTH_SHORT é o tempo de duração
               //Toast.makeText(MainActivity.this, "Teste", Toast.LENGTH_SHORT).show();

               exibirSaldo(newVal);
            }
        });

        //para botoes, usar View.OnClickListener() que é o padrão, para chamar direto no XML
       mButton.setOnClickListener(new View.OnClickListener() {  //esperar ele abrir as opções para selecionar, já traz as partes necessárias do código.
           @SuppressLint("NonConstantResourceId")
           @Override
           public void onClick(View view) {
               if (!mEditTextValor.getText().toString().isEmpty()){
                   //recupera o valor digitado e o converte para float
                   float valor = Float.parseFloat(mEditTextValor.getText().toString());

                   //recupera o ano selecionado
                   int ano = numberPicker.getValue();

                   //verifica qual radioButton está selecionado
                   //recuperamos o id do radioButton que esta selecinado e comparamos com o ID dos radioButtons
                   int checkedRadioButtonId = mRadioGroup.getCheckedRadioButtonId();

                   if (checkedRadioButtonId == R.id.radioButton3) {
                       adicionarValor(ano, valor);
                   } else if (checkedRadioButtonId == R.id.radioButton4) {
                       excluirValor(ano, valor);
                   }
                   exibirSaldo(ano);
               }
           }
       });

       /*
       ButtonTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PersonalizarActivity.class);
                startActivity(intent);
            }
      });
        */
    }

    //implementação das funções
    private void adicionarValor(int ano, float valor) {
        //instanciar um objeto SharedPreferences (para salvar informações)
        SharedPreferences sharedPreferences =
                //informa o arquivo onde será salvo e qual o tipo de acesso
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);
                //atualizar o valor - coloca a chave (ano) e o valorparao será zero
                float valorAtual = sharedPreferences.getFloat(String.valueOf(ano), 0);
                //soma o valor atual e soma com o valor que veio no parametro no inicio da função
                float novoValor = valorAtual + valor;
                //adicionar valor associado ao nao - chave (ano) e valor (valor)
                sharedPreferences.edit().putFloat(String.valueOf(ano), valor);
    }

    private void excluirValor(int ano, float valor) {
        //carrega o arquivo que salva as informações
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);
        //pegar o valor atual que está armazenado
        float valorAtul = sharedPreferences.getFloat(String.valueOf(ano),0);
        //faz o novo valor - se for menor que zero vai retornar zero pois não vai trazer faturamento negativo
        float novoValor = valorAtul - valor;
        if (novoValor < 0) {
            novoValor = 0;
        }

        //depois de termos o novo valor, chamamos o editor
        sharedPreferences.edit()
                //coloca o novo valor relacionado ao ano
                .putFloat(String.valueOf(ano), novoValor)
                //aplica as alterações
                .apply();
    }

    private void exibirSaldo(int ano) {
        //acessa o arquivo onde as infos estao salvas
        SharedPreferences sharedPreferences =
                getSharedPreferences(ARQUIVO_MEUS_DADOS, Context.MODE_PRIVATE);
        //cria um objeto ara trazer as infos, se não tiver nada o valor ele vai trazer zero
        float saldo = sharedPreferences.getFloat(String.valueOf(ano), 0);
        //exibe o valor na tela, na view do app
        mTextViewSaldo.setText(String.format("R$ %f", saldo));
    }


}

