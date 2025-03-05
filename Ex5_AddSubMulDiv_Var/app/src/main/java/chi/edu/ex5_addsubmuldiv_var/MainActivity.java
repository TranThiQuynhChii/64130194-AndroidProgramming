package chi.edu.ex5_addsubmuldiv_var;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimView();
        }
        //gan cac bo lang nghe
        btnCong.setOnClickListener(boLa)

        //-----------------------------------
        //Tao cac bo nghe va xu ly su kien
        View.OnClickListener boLangNghe_XuLyCong = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code xu ly cong o day
                //b1.Lay so 1
                String strSo1 = edtSoA.getText().toString();
                String strSo2 = edtSoB.getText().toString();
                //b2.chuyen thanh so de tinh toan
                double soA = Double.parseDouble(strSo1);
                double soB = Double.parseDouble(strSo2);
                //b3.Tinh toan
                double tong = soA + soB;
                //b4.Xuat
                String strKQ = String.valueOf(tong);
                tvKQ.setText(strKQ);
            }
        };

        //-----------------------------------
        void TimView(){
            //Tim view
            edtSoA = (EditText) findViewById(R.id.editTextA);
            edtSoB = (EditText) findViewById(R.id.editTextB);
            btnCong = (EditText) findViewById(R.id.buttonCong);
            btnTru = (EditText) findViewById(R.id.buttonTru);
            btnNhan = (EditText) findViewById(R.id.buttonNhan);
            btnChia = (EditText) findViewById(R.id.buttonChia);
        }
    //khai bao ca doi tuong tuong ung voi cac dieu khien (view) can thao tac
    EditText edtSoA;
    EditText edtSoB;
    Button btnCong,btnTru,btnNhan,btnChia;
    TextView tvKQ;
}