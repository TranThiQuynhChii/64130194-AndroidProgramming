package chi.edu.ex6_intentdongian;

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
    Button nutMH2;
    Button nutMH3;
    void TimDieuKhien(){
        nutMH2 = findViewById(R.id.btnMH2);
        nutMH3 = findViewById(R.id.btnMH3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tìm điều khiển nút bấm
        TimDieuKhien();
        //Găn bộ lắng nghe sự kiện
        nutMH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xử lý chuyển màn hình
                //b1.tạo một intent 2 tham so 1.màn hình hiện tại 2.màn hình chuyển tới class
                Intent intentMH2 = new Intent(MainActivity.this, MH2Activity.class);
                //b2.gửi
                startActivity(intentMH2);
            }
        });
        nutMH3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xử lý chuyển man hình
                //b1.Tạo 1 intent 2 tham số 1.màn hình hiện tại .this 2.màn hình chuyển tới class
                Intent intentMH3 = new Intent(MainActivity.this, MH3Activity.class);
                //b2.gui
                startActivity(intentMH3);
            }
        });
    }


}