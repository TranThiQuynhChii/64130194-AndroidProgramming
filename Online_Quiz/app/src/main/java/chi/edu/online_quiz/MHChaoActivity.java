package chi.edu.online_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MHChaoActivity extends AppCompatActivity {

    @Override
    //Sử dụng pthuc oncreate
    protected void onCreate(Bundle savedInstanceState) {
        //gọi lại phương thức cha để đảm bảo các thao tác khởi tạo cơ bản được thực hiện.
        super.onCreate(savedInstanceState);
        //giao diện sẽ hiển thị trải dài đến tận các cạnh màn hình
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mhchao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent để chuyển từ MHChaoActivity sang MainActivity.
                Intent intent = new Intent(MHChaoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}