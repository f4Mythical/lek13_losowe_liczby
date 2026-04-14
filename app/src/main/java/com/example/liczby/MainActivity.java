package com.example.liczby;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Integer> listaLiczb = new ArrayList<>();
    private MaterialButton[] buttons;
    private int[] buttonColors;
    private int obecnaLiczba;
    private Handler handler;
    private boolean czyGraAktywna = true;
    private int licznikKlikniec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvRandom = findViewById(R.id.tvRandom);
        MaterialButton btnRand1 = findViewById(R.id.btnRandom1);
        MaterialButton btnRand2 = findViewById(R.id.btnRandom2);
        MaterialButton btnRand3 = findViewById(R.id.btnRandom3);
        MaterialButton btnRand4 = findViewById(R.id.btnRandom4);
        MaterialButton btnRand5 = findViewById(R.id.btnRandom5);

        buttons = new MaterialButton[]{btnRand1, btnRand2, btnRand3, btnRand4, btnRand5};
        buttonColors = new int[buttons.length];

        Random random = new Random();
        handler = new Handler(Looper.getMainLooper());

        ArrayList<Integer> uzyteNumery = new ArrayList<>();
        for (int i = 0; i < buttons.length; i++) {
            int liczba;
            do {
                liczba = random.nextInt(10) + 1;
            } while (uzyteNumery.contains(liczba));

            uzyteNumery.add(liczba);
            listaLiczb.add(liczba);

            final int index = i;
            buttons[i].setText(String.valueOf(liczba));
            buttons[i].setBackgroundColor(Color.GRAY);
            buttonColors[i] = Color.GRAY;

            int finalLiczba = liczba;
            buttons[i].setOnClickListener(v -> {
                if (!czyGraAktywna) return;

                licznikKlikniec++;

                if (finalLiczba == obecnaLiczba) {
                    buttons[index].setBackgroundColor(Color.GREEN);
                    buttonColors[index] = Color.GREEN;
                } else {
                    buttons[index].setBackgroundColor(Color.RED);
                    buttonColors[index] = Color.RED;
                }

                sprawdzKoniecGry();
            });
        }

        losujLiczbe(tvRandom, random);
    }

    private void losujLiczbe(TextView tvRandom, Random random) {
        if (!czyGraAktywna) return;

        obecnaLiczba = random.nextInt(10) + 1;
        tvRandom.setText(String.valueOf(obecnaLiczba));

        handler.postDelayed(() -> {
            for (int i = 0; i < buttons.length; i++) {
                if (listaLiczb.get(i) == obecnaLiczba && buttonColors[i] != Color.GREEN && czyGraAktywna) {
                    buttons[i].setBackgroundColor(Color.RED);
                    buttonColors[i] = Color.RED;
                }
            }
            sprawdzKoniecGry();

            handler.postDelayed(() -> losujLiczbe(tvRandom, random), 1000);
        }, 1000);
    }

    private void sprawdzKoniecGry() {
        boolean koniec = true;
        for (int color : buttonColors) {
            if (color != Color.GREEN) {
                koniec = false;
                break;
            }
        }

        if (koniec) {
            czyGraAktywna = false;
            handler.removeCallbacksAndMessages(null);
            Toast.makeText(this, "Koniec gry! Kliknięcia: " + licznikKlikniec, Toast.LENGTH_SHORT).show();
        }
    }
}