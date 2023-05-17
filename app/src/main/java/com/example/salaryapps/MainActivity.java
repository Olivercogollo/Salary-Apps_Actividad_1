package com.example.salaryapps;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView salarioBase;
    private TextView horasExtras;
    private RadioButton radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salarioBase = findViewById(R.id.salaryEditText);
        horasExtras = findViewById(R.id.overtimeEditText);
        radio = findViewById(R.id.yesRadioButton);

        Button calcularButton = findViewById(R.id.calculateButton);
        calcularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de entrada
                double salario_base = Double.parseDouble(salarioBase.getText().toString());
                int horas_extras = Integer.parseInt(horasExtras.getText().toString());
                boolean bonificaciones = radio.isChecked();

                // Calcular salario neto
                double salario_neto = calcula_salario(salario_base, horas_extras, bonificaciones);

                // Mostrar resultado en una vista de texto
                TextView resultadoTextView = findViewById(R.id.resultTextView);
                resultadoTextView.setText(String.format(Locale.getDefault(), "Salario neto: $%.2f", salario_neto));
            }
        });
    }

    public double calcula_salario(double salario_base, int horas_extras, boolean bonificaciones) {
        // Calcular cuanto se paga la hora
        double hora = salario_base / 192;

        // Calcular valor a pagar en horas extras
        double pagar_horas_extras = hora * horas_extras * 1.25;
        System.out.println(pagar_horas_extras);
        double bonificacion = 0 ;
        // Calcular valor de las bonificaciones
        if(radio.isChecked()){
            bonificacion = salario_base * 0.05 ;
        }
        System.out.println(bonificacion);


        // Calcular salario total antes de descuentos
        double salario_total = salario_base + pagar_horas_extras + bonificacion;
        System.out.println(salario_total);

        // Calcular descuentos
        double descuento_salud = salario_total * 0.035;
        System.out.println(descuento_salud);
        double descuento_pension = salario_total * 0.04;
        System.out.println(descuento_pension);
        double descuento_caja = salario_total * 0.01;
        System.out.println(descuento_caja);

        // Calcular salario neto
        double salario_neto = salario_total - descuento_salud - descuento_pension - descuento_caja;

        // Retornar salario neto
        return salario_neto;
    }

}