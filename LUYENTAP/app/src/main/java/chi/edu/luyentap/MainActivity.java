package chi.edu.luyentap;

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
        Button btnDN = (Button) findViewById(R.id.btn);
        //xu ly d
        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly lenh o day
                Intent imain2 = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(imain2);
            }
        });
    }
}