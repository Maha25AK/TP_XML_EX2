package com.example.tp_xml_ex2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private final double EUR_EN_MAD = 10.69;
    private final double USD_EN_MAD = 9.85;

    private EditText etMad, etUsd, etEur;
    private EditText champActif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMad = findViewById(R.id.etMad);
        etUsd = findViewById(R.id.etUsd);
        etEur = findViewById(R.id.etEur);
        Button btnConvertir = findViewById(R.id.btnConvertir);
        Button btnReset = findViewById(R.id.btnReset);

        champActif = etMad;


        for (EditText champ : new EditText[]{etMad, etUsd, etEur}) {

            champ.setOnFocusChangeListener((v, aLeFocus) -> {

                if (aLeFocus) champActif = (EditText) v;

            });
        }

        btnConvertir.setOnClickListener(v -> convertir());

        btnReset.setOnClickListener(v -> reinitialiser());
    }

    private void convertir() {
        double valeur = lire(champActif);


        double enMad;
        if (champActif == etMad) {
            enMad = valeur;
        } else if (champActif == etUsd) {
            enMad = valeur * USD_EN_MAD;
        } else {
            enMad = valeur * EUR_EN_MAD;
        }


        etMad.setText(formater(enMad));
        etUsd.setText(formater(enMad / USD_EN_MAD));
        etEur.setText(formater(enMad / EUR_EN_MAD));
    }

    private void reinitialiser() {
        etMad.setText("0");
        etUsd.setText("0");
        etEur.setText("0");
    }

    private double lire(EditText champ) {
        String s = champ.getText().toString().replace(',', '.');

        if (s.isEmpty()) return 0;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String formater(double x) {
        return String.format(Locale.US, "%.2f", x);

    }
}