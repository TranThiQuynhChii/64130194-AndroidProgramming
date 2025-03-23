package chi.edu.ex6luyentap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button nutmh2;
    Button nutmh3;
    void TimDieuKhien(){
        nutmh2 = findViewById(R.id.btnmh2);
        nutmh3 = findViewById(R.id.btnmh3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Tim dk nut bam
        TimDieuKhien();
        //gan bo lang nghe su kien
        nutmh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainmh2 = new Intent(MainActivity.this, MH2Activity.class);
                startActivity(mainmh2);
            }
        });
        nutmh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainmh3 = new Intent(MainActivity.this, MH3Activity.class);
                startActivity(mainmh3);
            }
        });
    }
}