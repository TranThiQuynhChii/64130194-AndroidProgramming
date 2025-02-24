package Chi.edu.ex4_addsubmuldiv_onclick;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextSo1;
    EditText editTextSo2;
    EditText editTextSoKetqua;
    Button nutCong,nutTru,nutNhan,nutChia;
    void TimDieuKhien(){
        EditText editTextSo1 = (EditText) findViewById(R.id.edtSo1);
        EditText editTextSo2 = (EditText) findViewById(R.id.edtSo2);
        EditText editTextKetqua = (EditText) findViewById(R.id.edtKetqua);
        nutCong = (Button) findViewById(R.id.btnCong);
        nutTru = (Button) findViewById(R.id.btnTru);
        nutNhan = (Button) findViewById(R.id.btnNhan);
        nutChia = (Button) findViewById(R.id.btnChia);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
    }
    //xu ly cong
    public void XuLyCong(View view){
        //code xu ly cong o day
        //b1.lay du lieu 2 so
        //b1.1 tim editext so 2 va so 2
        EditText editTextSo1 = (EditText) findViewById(R.id.edtSo1);
        EditText editTextSo2 = (EditText) findViewById(R.id.edtSo2);
        //lay du lieu tu 2 dkhien do
        String soThu1 = editTextSo1.getText().toString();
        String soThu2 = editTextSo2.getText().toString();
        //b1.3 chuyen du lieu tu chuoi sang so
        float soA = Float.parseFloat(soThu1);
        float soB = Float.parseFloat(soThu2);
        //b2 tinh toan
        float Tong = soA + soB;
        //b3 Hien ket qua
        //b3.1
        EditText editTextKetqua = (EditText) findViewById(R.id.edtKetqua);
        //b3.2 chuan bi du lieu xuat, bien thanh );dang chuoi
        String chuoiKetqua = String.valueOf(Tong);
        //b3.3 gan ket qua len dk
        editTextKetqua.setText(chuoiKetqua);
    }
    public void XuLyTru(View view){
        //code xu ly tru o day
        //b1.lay du lieu 2 so
        //b1.1 tim editext so 2 va so 2
        EditText editTextSo1 = (EditText) findViewById(R.id.edtSo1);
        EditText editTextSo2 = (EditText) findViewById(R.id.edtSo2);
        //lay du lieu tu 2 dkhien do
        String soThu1 = editTextSo1.getText().toString();
        String soThu2 = editTextSo2.getText().toString();
        //b1.3 chuyen du lieu tu chuoi sang so
        float soA = Float.parseFloat(soThu1);
        float soB = Float.parseFloat(soThu2);
        //b2 tinh toan
        float Tru = soA - soB;
        //b3 Hien ket qua
        //b3.1
        EditText editTextKetqua = (EditText) findViewById(R.id.edtKetqua);
        //b3.2 chuan bi du lieu xuat, bien thanh dang chuoi
        String chuoiKetqua = String.valueOf(Tru);
        //b3.3 gan ket qua len dk
        editTextKetqua.setText(chuoiKetqua);
    }
    public void XuLyNhan(View view){
        //code xu ly nhan o day
        //b1.lay du lieu 2 so
        //b1.1 tim edittext so 2 va so 2
        EditText editTextSo1 = (EditText) findViewById(R.id.edtSo1);
        EditText editTextSo2 = (EditText) findViewById(R.id.edtSo2);
        //lay du lieu tu 2 dkhien do
        String soThu1 = editTextSo1.getText().toString();
        String soThu2 = editTextSo2.getText().toString();
        //b1.3 chuyen du lieu tu chuoi sang so
        float soA = Float.parseFloat(soThu1);
        float soB = Float.parseFloat(soThu2);
        //b2 tinh toan
        float Nhan = soA * soB;
        //b3 Hien ket qua
        //b3.1
        EditText editTextKetqua = (EditText) findViewById(R.id.edtKetqua);
        //b3.2 chuan bi du lieu xuat, bien thanh dang chuoi
        String chuoiKetqua = String.valueOf(Nhan);
        //b3.3 gan ket qua len dk
        editTextKetqua.setText(chuoiKetqua);

    }
    public void XuLyChia(View view){
        //code xu ly chia o day
        //b1.lay du lieu 2 so
        //b1.1 tim editext so 2 va so 2
        EditText editTextSo1 = (EditText) findViewById(R.id.edtSo1);
        EditText editTextSo2 = (EditText) findViewById(R.id.edtSo2);
        //lay du lieu tu 2 dkhien do
        String soThu1 = editTextSo1.getText().toString();
        String soThu2 = editTextSo2.getText().toString();
        //b1.3 chuyen du lieu tu chuoi sang so
        float soA = Float.parseFloat(soThu1);
        float soB = Float.parseFloat(soThu2);
        //b2 tinh toan
        float Chia = soA / soB;
        //b3 Hien ket qua
        //b3.1
        EditText editTextKetqua = (EditText) findViewById(R.id.edtKetqua);
        //b3.2 chuan bi du lieu xuat, bien thanh dang chuoi
        String chuoiKetqua = String.valueOf(Chia);
        //b3.3 gan ket qua len dk
        editTextKetqua.setText(chuoiKetqua);
    }

}