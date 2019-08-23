package com.example.exercicio04_general_custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textQuantidade, resultado;
    SeekBar quantidade;
    Button lancar;
    EditText valor1, valor2, valor3, valor4, valor5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuantidade=findViewById(R.id.textQuantidade);
        lancar=findViewById(R.id.lancar);
        quantidade=findViewById(R.id.quantidade);
        valor1 = findViewById(R.id.valor1);
        valor2 = findViewById(R.id.valor2);
        valor3 = findViewById(R.id.valor3);
        valor4 = findViewById(R.id.valor4);
        valor5 = findViewById(R.id.valor5);
        resultado = findViewById(R.id.resultado);


        quantidade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i==1){
                    textQuantidade.setText(i + " Dado");
                }else{
                    textQuantidade.setText(i + " Dados");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                valor1.setVisibility(View.GONE);
                valor2.setVisibility(View.GONE);
                valor3.setVisibility(View.GONE);
                valor4.setVisibility(View.GONE);
                valor5.setVisibility(View.GONE);
                for(int i=1;i<=seekBar.getProgress();i++){
                    if(i==1){
                        valor1.setVisibility(View.VISIBLE);
                    }
                    if(i==2){
                        valor2.setVisibility(View.VISIBLE);
                    }
                    if(i==3){
                        valor3.setVisibility(View.VISIBLE);
                    }
                    if(i==4){
                        valor4.setVisibility(View.VISIBLE);
                    }
                    if(i==5){
                        valor5.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    public void lancar(View view) {

        Random random = new Random();
        ArrayList valores = new ArrayList();
        String jogadas = "", maiorPonto = "";
        int pontos = 0;

        //adicionar valores dos campos
        if(valor1.getVisibility()==View.VISIBLE && !valor1.getText().toString().isEmpty()){
            valores.add(Integer.parseInt(valor1.getText().toString()));
        }else{
            resultado.setText("Informe o valor do 1º Campo!");
            return;
        }
        if(valor2.getVisibility()==View.VISIBLE && !valor2.getText().toString().isEmpty()){
            valores.add(Integer.parseInt(valor2.getText().toString()));
        }else if(valor2.getVisibility()==View.VISIBLE && valor2.getText().toString().isEmpty()){
            resultado.setText("Informe o valor do 2º Campo!");
            return;
        }
        if(valor3.getVisibility()==View.VISIBLE && !valor3.getText().toString().isEmpty()){
            valores.add(Integer.parseInt(valor3.getText().toString()));
        }else if(valor3.getVisibility()==View.VISIBLE && valor3.getText().toString().isEmpty()){
            resultado.setText("Informe o valor do 3º Campo!");
            return;
        }
        if(valor4.getVisibility()==View.VISIBLE && !valor4.getText().toString().isEmpty()){
            valores.add(Integer.parseInt(valor4.getText().toString()));
        }else if(valor4.getVisibility()==View.VISIBLE && valor4.getText().toString().isEmpty()){
            resultado.setText("Informe o valor do 4º Campo!");
            return;
        }
        if(valor5.getVisibility()==View.VISIBLE && !valor5.getText().toString().isEmpty()){
            valores.add(Integer.parseInt(valor5.getText().toString()));
        }else if(valor5.getVisibility()==View.VISIBLE && valor5.getText().toString().isEmpty()){
            resultado.setText("Informe o valor do 5º Campo!");
            return;
        }

        //preenche o restante com numeros random
        for (int i = quantidade.getProgress()+1; i <= 5; i++) {
            valores.add(random.nextInt(6) + 1);
        }

        //mostrando ordem de jogada original e ordenada
        jogadas="Jogada por ordem de dado.\n\n";
        for (int i = 0; i < 5; i++) {
            jogadas += "(" + String.valueOf(i + 1) + ")Dado - Valor: " + String.valueOf(valores.get(i) + "\n");
        }
        jogadas+="\nOrdem de valores (menor - maior)\n\n";
        Collections.sort(valores);
        for (int i = 0; i < 5; i++) {
            jogadas += "Valor: " + String.valueOf(valores.get(i) + "\n");
        }

        //verificar pontos
        //jogada de 1
        if (Collections.frequency(valores, 1) > 0) {
            pontos = Collections.frequency(valores, 1);
            maiorPonto = "Jogada de 1 = " + String.valueOf(Collections.frequency(valores, 1) + " Pontos\n");
        }

        //jogada de 2
        if (Collections.frequency(valores, 2) > 0) {
            if(pontos==Collections.frequency(valores, 2) * 2) {
                maiorPonto = maiorPonto + "Jogada de 2 = " + String.valueOf(Collections.frequency(valores, 2) * 2 + " Pontos\n");
            }else if (pontos < Collections.frequency(valores, 2) * 2) {
                maiorPonto = "Jogada de 2 = " + String.valueOf(Collections.frequency(valores, 2) * 2 + " Pontos\n");
                pontos = Collections.frequency(valores, 2) * 2;
            }
        }

        //jogada de 3
        if (Collections.frequency(valores, 3) > 0) {
            if (pontos == Collections.frequency(valores, 3) * 3) {
                maiorPonto = maiorPonto + "\nJogada de 3 = " + String.valueOf(Collections.frequency(valores, 3) * 3 + " Pontos\n");
            }else if(pontos < Collections.frequency(valores, 3) * 3){
                maiorPonto = "Jogada de 3 = " + String.valueOf(Collections.frequency(valores, 3) * 3 + " Pontos\n");
                pontos = Collections.frequency(valores, 3) * 3;
            }
        }

        //jogada de 4
        if (Collections.frequency(valores, 4) > 0) {
            if(pontos == Collections.frequency(valores, 4) * 4){
                maiorPonto = maiorPonto + "Jogada de 4 = " + String.valueOf(Collections.frequency(valores, 4) * 4 + " Pontos\n");
            }else if (pontos < Collections.frequency(valores, 4) * 4) {
                pontos = Collections.frequency(valores, 4) * 4;
                maiorPonto = "Jogada de 4 = " + String.valueOf(Collections.frequency(valores, 4) * 4 + " Pontos\n");
            }
        }

        //jogada de 5
        if (Collections.frequency(valores, 5) > 0) {
            if(pontos == Collections.frequency(valores, 5) * 5){
                maiorPonto = maiorPonto + "Jogada de 5 = " + String.valueOf(Collections.frequency(valores, 5) * 5 + " Pontos\n");
            }else if (pontos < Collections.frequency(valores, 5) * 5) {
                pontos = Collections.frequency(valores, 5) * 5;
                maiorPonto = "Jogada de 5 = " + String.valueOf(Collections.frequency(valores, 5) * 5 + " Pontos\n");
            }
        }

        //jogada de 6
        if (Collections.frequency(valores, 6) > 0) {
            if(pontos == Collections.frequency(valores, 6) * 6){
                maiorPonto = maiorPonto + "\nJogada de 6 = " + String.valueOf(Collections.frequency(valores, 6) * 6 + " Pontos\n");
            }else if (pontos < Collections.frequency(valores, 6) * 6) {
                pontos = Collections.frequency(valores, 6) * 6;
                maiorPonto = "Jogada de 6 = " + String.valueOf(Collections.frequency(valores, 6) * 6 + " Pontos\n");
            }
        }

        //trinca
        if (Collections.frequency(valores, 1) == 3 || Collections.frequency(valores, 2) == 3 ||
                Collections.frequency(valores, 3) == 3 || Collections.frequency(valores, 4) == 3 ||
                Collections.frequency(valores, 5) == 3 || Collections.frequency(valores, 6) == 3) {

            int aux = 0;
            for (int i = 0; i < 5; i++) {
                aux += Integer.parseInt(String.valueOf(valores.get(i)));
            }
            if(pontos==aux) {
                maiorPonto = maiorPonto + "Trinca com " + pontos + " pontos\n";
            }else if (pontos < aux) {
                pontos = aux;
                maiorPonto = "Trinca com " + pontos + " pontos\n";
            }
        }

        //quadra
        if (Collections.frequency(valores, 1) == 4 || Collections.frequency(valores, 2) == 4 ||
                Collections.frequency(valores, 3) == 4 || Collections.frequency(valores, 4) == 4 ||
                Collections.frequency(valores, 5) == 4 || Collections.frequency(valores, 6) == 4) {
            int aux = 0;
            for (int i = 0; i < 5; i++) {
                aux += Integer.parseInt(String.valueOf(valores.get(i)));
            }
            if(pontos==aux) {
                maiorPonto = maiorPonto + "Quadra com " + pontos + " pontos\n";
            }else if (pontos < aux) {
                pontos = aux;
                maiorPonto = "Quadra com " + pontos + " pontos\n";
            }
        }

        //full-house
        for (int i = 1; i < 6; i++) {
            if (Collections.frequency(valores, i) == 3) {
                for (int j = i + 1; j <= 6; j++) {
                    if (Collections.frequency(valores, j) == 2) {
                        if(pontos==25){
                            maiorPonto = maiorPonto + "Full-House: " + pontos + " pontos\n";
                            break;
                        }else if (pontos < 25) {
                            pontos = 25;
                            maiorPonto = "Full-House: " + pontos + " pontos\n";
                            break;
                        }
                    }
                }
            }
        }
        for (int i = 1; i < 6; i++) {
            if (Collections.frequency(valores, i) == 2) {
                for (int j = i + 1; j <= 6; j++) {
                    if (Collections.frequency(valores, j) == 3) {
                        if(pontos==25){
                            maiorPonto = maiorPonto + "Full-House: " + pontos + " pontos\n";
                            break;
                        }else if (pontos < 25) {
                            pontos = 25;
                            maiorPonto = "Full-House: " + pontos + " pontos\n";
                            break;
                        }
                    }
                }
            }
        }

        //sequencia alta
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add(2);
        aux.add(3);
        aux.add(4);
        aux.add(5);
        aux.add(6);
        if(valores.equals(aux)){
            if(pontos==30){
                maiorPonto = maiorPonto + "Sequencia Alta: " + pontos + " pontos\n";
            }else if (pontos < 30) {
                pontos = 30;
                maiorPonto = "Sequencia Alta: " + pontos + " pontos\n";
            }
        }

        aux.clear();
        //sequencia baixa
        aux.add(1);
        aux.add(2);
        aux.add(3);
        aux.add(4);
        aux.add(5);
        if(valores.equals(aux)){
            if(pontos==40){
                maiorPonto = maiorPonto + "Sequencia Alta: " + pontos + " pontos\n";
            }else if (pontos < 40) {
                pontos = 40;
                maiorPonto = "Sequencia Baixa: " + pontos + " pontos\n";
            }
        }

        //yam(general)
        if(Collections.frequency(valores,1)==5 || Collections.frequency(valores,2)==5 ||
                Collections.frequency(valores,3)==5 || Collections.frequency(valores,4)==5 ||
                Collections.frequency(valores,5)==5 || Collections.frequency(valores,6)==5){

            if(pontos==50){
                maiorPonto = maiorPonto + "\nYam (General): " + pontos + " pontos\n";
            }else if (pontos < 50) {
                pontos = 50;
                maiorPonto = "Yam (General): " + pontos + " pontos\n";
            }
        }

        //jogada aleatoria
        int soma=0;
        for(int i=0;i<5;i++){
            soma += Integer.parseInt(String.valueOf(valores.get(i)));
        }
        if(pontos==soma){
            maiorPonto = maiorPonto + "Jogada Aleatoria: " + pontos + " pontos\n";
        }else if (pontos < soma) {
            pontos = soma;
            maiorPonto = "Jogada Aleatoria: " + pontos + " pontos\n";
        }
        resultado.setText(jogadas + "\n" + "Maior classificação(ções)\n\n" + maiorPonto);
    }
}
