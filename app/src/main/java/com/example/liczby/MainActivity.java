package com.example.liczby;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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
        Button btnRand1 = findViewById(R.id.btnRandom1);
        Button btnRand2 = findViewById(R.id.btnRandom2);
        Button btnRand3 = findViewById(R.id.btnRandom3);
        Button btnRand4 = findViewById(R.id.btnRandom4);
        Button btnRand5 = findViewById(R.id.btnRandom5);
        Random random = new Random();
    }
}