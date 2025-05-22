package chi.edu.online_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

//main khi người dùng chọn chế độ nào thì Intent sẽ chuyển sang một màn hình khác
//ScrollView Là vùng hiển thị có thể cuộn theo chiều dọc và chỉ chứa 1 ptu con duy nhất

public class MainActivity extends AppCompatActivity {
    //cardView giao diện giống "nút thẻ", có bóng và góc bo tròn
    CardView btnLuyenTap, btnThiThu, btnNangCao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLuyenTap = findViewById(R.id.btnLuyenTap);
        btnThiThu = findViewById(R.id.btnThiThu);
        btnNangCao = findViewById(R.id.btnNangCao);

        btnLuyenTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("luyentap");
            }
        });

        // Phần này để mở màn chọn đề thi
        btnThiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent dùng để chuyển giữa các màn hình
                Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                startActivity(intent);
            }
        });

        btnNangCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("nangcao");
            }
        });
    }
    //Hàm dùng chung để mở màn hình làm bài quiz (QuizActivity)
    private void openQuiz(String mode) {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        //Dữ liệu mode giúp QuizActivity nhận biết đc rằng hiển thị câu hỏi của phần nào
        intent.putExtra("mode", mode);
        startActivity(intent);
    }
}
