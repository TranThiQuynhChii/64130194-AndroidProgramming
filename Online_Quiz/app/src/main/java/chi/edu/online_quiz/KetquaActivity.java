package chi.edu.online_quiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class KetquaActivity extends AppCompatActivity {

    private TextView tvCorrectAnswers, tvScore, tvMessage;
    private Button btnRetry, btnHome;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        tvCorrectAnswers = findViewById(R.id.tvCorrectAnswers);
        tvScore = findViewById(R.id.tvScore);
        tvMessage = findViewById(R.id.tvMessage);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        int soCauDung = getIntent().getIntExtra("soCauDung", 0);
        int tongSoCau = getIntent().getIntExtra("tongSoCau", 0);
        int diem = (int) ((soCauDung / (float) tongSoCau) * 10);

        tvCorrectAnswers.setText("Số câu đúng: " + soCauDung + "/" + tongSoCau);
        tvScore.setText("Điểm: " + diem);

        if (diem == 10) {
            tvMessage.setText("🎉 Xuất sắc! Bạn đã trả lời đúng hết!");
        } else if (diem >= 8) {
            tvMessage.setText("👏 Rất tốt! Bạn gần như hoàn hảo rồi!");
        } else if (diem >= 5) {
            tvMessage.setText("👍 Cố lên! Bạn đang làm rất tốt!");
        } else {
            tvMessage.setText("😢 Không sao! Hãy thử lại và cải thiện nhé!");
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.chucmung);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentMode = getIntent().getStringExtra("mode");
                String currentExam = getIntent().getStringExtra("exam");
                Intent intent = new Intent(KetquaActivity.this, QuizActivity.class);
                intent.putExtra("mode", currentMode);
                if (currentExam != null) {
                    intent.putExtra("exam", currentExam);
                }
                startActivity(intent);
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KetquaActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();   // Dừng âm thanh
            } catch (IllegalStateException e) {
                e.printStackTrace();  // Nếu mediaPlayer chưa kịp phát xong
            }
            try {
                mediaPlayer.release(); // Giải phóng tài nguyên
            } catch (Exception e) {
                e.printStackTrace();
            }
            mediaPlayer = null;
        }
    }
}
