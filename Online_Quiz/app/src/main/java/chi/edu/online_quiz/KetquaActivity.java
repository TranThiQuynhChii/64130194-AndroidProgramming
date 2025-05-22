package chi.edu.online_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KetquaActivity extends AppCompatActivity {

    //Khai báo biến

    private TextView tvCorrectAnswers, tvScore;
    private Button btnRetry, btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        //Ánh xa giao diện
        tvCorrectAnswers = findViewById(R.id.tvCorrectAnswers);
        tvScore = findViewById(R.id.tvScore);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        int soCauDung = getIntent().getIntExtra("soCauDung", 0); //Số câu người dùng làm đúng trong bài đc truyền từ QuizActivity sang 
        int tongSoCau = getIntent().getIntExtra("tongSoCau", 0); //Số câu hỏi trong bài
        //Chuyển socaudung va tongsocau về dang float
        int diem = (int) ((soCauDung / (float) tongSoCau) * 10); // Tính điểm trên thang 10

        tvCorrectAnswers.setText("Số câu đúng: " + soCauDung + "/" + tongSoCau); //chỉ là nội dung gán
        tvScore.setText("Điểm: " + diem);

        // Nút "Làm lại" – chạy lại đề vừa làm
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getIntent() lấy cái Intent đã được gửi đến KetquaActivity.
                String currentMode = getIntent().getStringExtra("mode"); //truyền giá trị mode từ QuizActivity sang KetquaActivity
                String currentExam = getIntent().getStringExtra("exam"); //Giá trị exam cũngđược truyền như mode đều sử dụng Intent
                Intent intent = new Intent(KetquaActivity.this, QuizActivity.class);
                intent.putExtra("mode", currentMode);      // gửi lại mode cũ
                if (currentExam != null) {
                    intent.putExtra("exam", currentExam);  // gửi lại exam nếu có
                }
                startActivity(intent);
                finish(); // Đóng màn hình hiện tại
            }
        });

        // Khi người dùng ấn nút trang chính sẽ quay về màn hình chính
        btnHome.setOnClickListener(new View.OnClickListener() { //Gắn sự kiện lắng nghe
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KetquaActivity.this, MainActivity.class);
                //FLAG_ACTIVITY_CLEAR_TOP xóa các màn hình đã mở trước nó chỉ còn lại mình nó để không quay lại được
                //FLAG_ACTIVITY_NEW_TASK chuyển tới trang chính bằng một tab mới
                //Hai cờ này giúp khi bấm nút 'Trang chính', ứng dụng quay về màn hình chính và
                //xóa hết các màn hình đã mở trước đó, không cho quay lại bằng nút Back
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

    }
}
