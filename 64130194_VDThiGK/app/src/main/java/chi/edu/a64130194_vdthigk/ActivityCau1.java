package chi.edu.a64130194_vdthigk;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityCau1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cau1);
    }
    //bo lang nghe va xu ly cong o day
    public void XuLyCong(View view){
        EditText edtSoA = (EditText) findViewById(R.id.edtSoA);
        EditText edtSoB = (EditText) findViewById(R.id.edtSoB);
        EditText edtKQ = (EditText) findViewById(R.id.edtKQ);
        //lay du lieu ve  dk so a
        String strA = edtSoA.getText().toString();
        String strB = edtSoB.getText().toString();
        //chuyen du lieu sang dang so
        int so_A = Integer.parseInt(strA);
        int so_B = Integer.parseInt(strB);
        //tinh tong
        int Tong = so_A + so_B;
        String strTong = String.valueOf(Tong);
        //hien thi ket qua ra man hinh
        edtKQ.setText(strTong);
    }
}