package chi.edu.thigk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivityCN2 extends AppCompatActivity {
    ListView ListViewBH;
    ArrayList<String> dsBaiHat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cn2);
        ListViewBH = findViewById(R.id.dsBaiHat);
        dsBaiHat = new ArrayList<String>();
        dsBaiHat.add("Tiến về Sài Gòn");
        dsBaiHat.add("Giải Phóng Miền Nam");
        dsBaiHat.add("Đất nước trọn niềm vui");
        dsBaiHat.add("Bài ca thống nhất");
        dsBaiHat.add("Mùa Xuân trên Thành Phố Hồ Chí Minh");
        ArrayAdapter<String> AdapterBH = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,dsBaiHat
        );
        ListViewBH.setAdapter(AdapterBH);
        ListViewBH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //lay noi dung item duoc chon
                String selecteditem = dsBaiHat.get(position);
                //chuyen sang item va chuyen du lieu
                Intent iItem = new Intent(ActivityCN2.this, Item3Activity.class);
                iItem.putExtra("Bạn chọn", selecteditem);
                startActivity(iItem);
            }
        });
    }
}