package truong.edu.autocompletetv_and_listview;



import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private ListView listView;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> autoCompleteAdapter;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lấy đối tượng từ layout
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        listView = findViewById(R.id.listView);

        // Chuẩn bị dữ liệu mẫu
        dataList = new ArrayList<>();
        dataList.add("Apple");
        dataList.add("Banana");
        dataList.add("Cherry");
        dataList.add("Date");
        dataList.add("Elderberry");
        dataList.add("Fig");
        dataList.add("Grape");

        // Thiết lập ArrayAdapter cho AutoCompleteTextView
        autoCompleteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, dataList);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
        autoCompleteTextView.setThreshold(1); // Gợi ý từ ký tự đầu tiên

        // Thiết lập ArrayAdapter cho ListView
        listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(listAdapter);

        // Xử lý sự kiện click trên ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = dataList.get(position);
                Toast.makeText(MainActivity.this, "Bạn chọn: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
