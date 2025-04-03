package chi.edu.thigk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Item3Activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item3);
        tv = findViewById(R.id.tv);
        //nhan du lieu tu intent
        String receivedData = getIntent().getStringExtra("Bạn chọn");
        //hien thi noi dung
        tv.setText(receivedData);
    }
}