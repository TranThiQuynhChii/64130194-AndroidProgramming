package chi.edu.ex7_intentloginluyentap;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button btnXacNhan = (Button) findViewById(R.id.btnOk);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tim tham chieu
                EditText edTenDn =(EditText) findViewById(R.id.edtUsername);
                EditText edmk = (EditText) findViewById(R.id.edtPass);
                //lay du lieu
                String tenDN = edTenDn.getText().toString();
                String mk = edmk.getText().toString();
                //ktra mk
                if(tenDN.equals("quynhchi") && mk.equals("1320")){
                    //chuyen sang man hinh home
                    Intent iquiz = new Intent(LoginActivity.this, HomeActivity.class);
                    iquiz.putExtra("tendn",tenDN);
                    iquiz.putExtra("mk",mk);
                    startActivity(iquiz);
                }else{
                    Toast.makeText(LoginActivity.this, "BẠN NHẬP SAI VUI LÒNG NHẬP LẠI", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}