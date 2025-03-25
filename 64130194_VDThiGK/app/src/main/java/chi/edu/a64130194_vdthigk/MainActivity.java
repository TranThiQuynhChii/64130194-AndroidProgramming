package chi.edu.a64130194_vdthigk;

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
        //su kien
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lenh xu ly o day
                Intent icau1 = new Intent(MainActivity.this,ActivityCau1.class);
                startActivity(icau1);
            }
        });
        //tim dk
        Button btnS2 = (Button) findViewById(R.id.btnS2);
        //su kien
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lenh xu ly o day
                Intent icau2 = new Intent(MainActivity.this,ActivityCau2.class);
                startActivity(icau2);
            }
        });
    }
}