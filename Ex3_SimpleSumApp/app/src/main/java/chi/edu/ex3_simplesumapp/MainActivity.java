package chi.edu.ex3_simplesumapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;3


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Gan layout tuong ung voi file nay
        setContentView(R.layout.activity_main);
    }
    //Day la bo lang nghe va xu ly su kien click tren nut Tinh Tong
    public void XuLyCong(View view){
        //Tim, tham chieu dieu khien tren tem XML
       EditText editTextsoA = findViewById(R.id.edtA);
       EditText editTextsoB = findViewById(R.id.edtB);
       EditText editTextsoKq = findViewById(R.id.edtKq);
       //Lay du lieu ve o dieu kien so a
        String strA = editTextsoA.getText().toString(); //strA = "2"
        //Lay du lieu o dieu kien so b
        String strB = editTextsoB.getText().toString(); //strB = "4"

        //chuyen du lieu sang dang so
        int so_A = Integer.parseInt(strA); //2
        int so_B = Integer.parseInt(strB); //4

        //Tinh toan theo yeu cau
        int tong =so_A + so_B;
        String strTong = String.valueOf(tong); //chuyen sang dang chuoi: "6"

        //Hien ra man hinh
        editTextsoKq.setText(strTong); //6

    }
}