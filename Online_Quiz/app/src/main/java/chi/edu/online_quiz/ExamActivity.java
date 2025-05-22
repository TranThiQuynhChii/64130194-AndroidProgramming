package chi.edu.online_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        Button btnDe1 = findViewById(R.id.btnDe1);
        Button btnDe2 = findViewById(R.id.btnDe2);
        Button btnDe3 = findViewById(R.id.btnDe3);
        Button btnDe4 = findViewById(R.id.btnDe4);
        Button btnDe5 = findViewById(R.id.btnDe5);

        btnDe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("De1");
            }
        });

        btnDe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("De2");
            }
        });

        btnDe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("De3");
            }
        });

        btnDe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("De4");
            }
        });

        btnDe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuiz("De5");
            }
        });
    }

    private void openQuiz(String mode) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }
}
