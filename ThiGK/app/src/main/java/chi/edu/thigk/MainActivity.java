package chi.edu.thigk;

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
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    void Timdieukien(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //tim dieu kien
        Timdieukien();
        //bo lang nghe
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly lenh o day
                Intent chucnang1 = new Intent(MainActivity.this, ActivityCN1.class);
                startActivity(chucnang1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly lenh o day
                Intent chucnang2 = new Intent(MainActivity.this, ActivityCN2.class);
                startActivity(chucnang2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly lenh o day
                Intent chucnang3 = new Intent(MainActivity.this, ActivityCN3.class);
                startActivity(chucnang3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly lenh o day
                Intent chucnang4 = new Intent(MainActivity.this, ActivityAboutMe.class);
                startActivity(chucnang4);
            }
        });
    }
}