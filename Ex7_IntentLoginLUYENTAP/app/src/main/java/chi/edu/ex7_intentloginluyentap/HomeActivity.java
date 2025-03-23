package chi.edu.ex7_intentloginluyentap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        //lay intent ve
        Intent intentTuLogin = getIntent();
        //loc ra lay du lieu chuoi
        String tenDN_nhanduoc = intentTuLogin.getStringExtra("ten dang nhap");
        //gan vao dieu khien
        TextView tvTenDN = (TextView) findViewById(R.id.tvUsername);
        tvTenDN.setText(tenDN_nhanduoc);
    }
}