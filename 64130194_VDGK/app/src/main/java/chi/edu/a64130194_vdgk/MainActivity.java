package chi.edu.a64130194_vdgk;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //tim dk
        Button btnS1 = (Button) findViewById(R.id.btnS1);
        //lenh
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lenh xu ly o day
                Intent icau1 = new Intent(MainActivity.this,Activity_cau1.class);
                startActivity(icau1);
            }
        });
        //tim dk
        Button btnS2 = (Button) findViewById(R.id.btnS2);
        //lenh
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lenh xu ly o day
                Intent icau2 = new Intent(MainActivity.this, Activity_Cau2.class);
                startActivity(icau2);
            }
        });
        //tim dk
        Button btnS3 = (Button) findViewById(R.id.btnS3);
        //lenh
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lenh xu ly o day
                Intent icau3 = new Intent(MainActivity.this,Activity_Cau3.class);
                startActivity(icau3);
            }
        });
        //tim dk
        Button btnS4 = (Button) findViewById(R.id.btnS4);
        //lenh
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lenh xu ly o day
                Intent icau4 = new Intent(MainActivity.this,Activity_Cau4.class);
                startActivity(icau4);
            }
        });
    }
}