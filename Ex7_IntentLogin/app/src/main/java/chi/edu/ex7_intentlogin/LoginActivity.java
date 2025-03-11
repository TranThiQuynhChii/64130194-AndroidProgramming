package chi.edu.ex7_intentlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnXacNhan = (Button) findViewById(R.id.btnOK);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xu ly dang nhap
                //lay du lieu
                //b1.tim tham chieu den dk
                EditText edTenDN = (EditText) findViewById(R.id.edtUsername);
                EditText edPass = (EditText) findViewById(R.id.edtpass);
                //b2.Lay du lieu
                String tenDangNhap = edTenDN.getText().toString();
                String mk = edPass.getText().toString();
                //kiem tra mat khau
                if(tenDangNhap.equals("TranThiQuynhChi") && mk.equals("1320")) //mk dung
                {
                    //chuyen sang man hinh home
                    Intent iQuiz = new Intent(LoginActivity.this, HomeActivity.class);
                    //goi du lieu vao iquiz,dang key-value,key duoc dung de ben kia loc r du lieu
                    iQuiz.putExtra("ten dang nhap",tenDangNhap);
                    iQuiz.putExtra("mk dang nhap", mk);
                    //gui di
                    startActivity(iQuiz);
                }else{
                    Toast.makeText(LoginActivity.this,"BẠN ĐÃ NHẬP SAI THÔNG TIN", Toast.LENGTH_LONG);
                }
            }
        });
    }
}