package chi.edu.online_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvTimer;
    private Button btnA, btnB, btnC, btnD, btnSubmit, btnNext;
    //Sử dụng list để lưu trữ toàn bộ nhiều câu hỏi rong một đề

    private List<Question> questions;
    private int currentQuestionIndex = 0; //chỉ số câu hỏi hiện tại
    private int selectedAnswerIndex = -1; //chỉ số đáp án được chọn. -1 là chưa chọn đáp án nào

    private CountDownTimer CountDownTimer; //đồng hồ đếm ngược khi thi thử
    private String mode = ""; //Chế độ làm bài
    private String selectedExam = "";
    private int soCauDung = 0; // Biến đếm số câu đúng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);

        //Nhận giá trị truyền từ màn hình Activity trước sang ,kết quả sẽ được lưu vào biến mode và truyền qua KetquaActivity
        mode = getIntent().getStringExtra("mode"); //lấy chế độ làm bài
        selectedExam = getIntent().getStringExtra("exam"); //Lấy tên đề thi

        questions = new ArrayList<>(); //question ở đây chính là Listquestion để lưu các câu hỏi cho bài làm
        switch (mode) {
            case "luyentap":
                loadPracticeQuestions();
                break;
            case "nangcao":
                loadAdvancedQuestions();
                break;
            case "De1":
                loadExam1();
                break;
            case "De2":
                loadExam2();
                break;
            case "De3":
                loadExam3();
                break;
            case "De4":
                loadExam4();
                break;
            case "De5":
                loadExam5();
                break;
            default:
                Toast.makeText(this, "Không tìm thấy đề thi", Toast.LENGTH_SHORT).show();
                finish();
        }

        loadQuestion();

        //Xử lý sự kiện cho các nút
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(0); //Khi người dùng chọn đáp án A
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(1);
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(2);
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(3);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
    }

    //Hàm hiển thị câu hỏi
    private void loadQuestion() {
        if (CountDownTimer != null) CountDownTimer.cancel();
        //Nếu người dùng đang chọn chế độ thi thử và làm đề thi thì bộ đếm thời gian bắt đầu
        if (mode.startsWith("De")) {
            startTimer();
        } else { //ngược lại khi người dùng chọn hai chế độ kia thì không cần sử dugnj bộ đếm thời gian
            tvTimer.setText("");
        }

        if (currentQuestionIndex < questions.size()) {
            //Lấy ra câu hỏi từ danh sách các câu hỏi đã cục bộ từ question
            Question q = questions.get(currentQuestionIndex);
            tvQuestion.setText(q.getQuestion()); //Hiển thị câu hỏi
            btnA.setText("A. " + q.getOptions()[0]);
            btnB.setText("B. " + q.getOptions()[1]);
            btnC.setText("C. " + q.getOptions()[2]);
            btnD.setText("D. " + q.getOptions()[3]);

            resetButtonColors(); //Hàm đặt lại màu sắc
            btnSubmit.setEnabled(true); //Kích hoạt nút gửi để người dùng có thể gửi đáp án
            btnNext.setVisibility(View.GONE); //Khi người dùng chưa ấn nút submit thì không đảm bảo không chuyển được sang câu tiếp theo
            selectedAnswerIndex = -1; //(-1) Đánh dấu người dùng chưa chọn đáp án nào cho câu hỏi htai
        } else { // ngược lại đã làm những điều trên thì sẽ sử dụng Intent đưua sang mh KetquaActivity
            Intent intent = new Intent(QuizActivity.this, KetquaActivity.class);
            intent.putExtra("soCauDung", soCauDung);
            intent.putExtra("tongSoCau", questions.size());
            intent.putExtra("mode", mode); // thêm dòng này
            intent.putExtra("exam", selectedExam); // thêm dòng này
            startActivity(intent);
        }
    }

    //Hàm ghi nhận đáp án người dùng đax chọn
    private void selectAnswer(int index) {
        //selectedAnswerIndex lưu lại chỉ số đáp án người dùng đã chọn
        selectedAnswerIndex = index; //Lưu lại vị trí đáp án người dùng đã chọn
        resetButtonColors(); //đặt lại tất cả các màu để chỉ có một nút đươc tô sáng
        Button[] buttons = {btnA, btnB, btnC, btnD};
        buttons[index].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));// màu xanh
    }

    //Hàm kiểm tra đáp án người dùng chọn có đúng hay không,đúng hiện xanh sai hiện đỏ
    private void checkAnswer() {
        if (selectedAnswerIndex == -1) { //Hiển thị xem người dùng chọn đáp án chưa -1 là chưa chọn
            Toast.makeText(this, "Hãy chọn một đáp án!", Toast.LENGTH_SHORT).show();
            return;
        }

        Question q = questions.get(currentQuestionIndex); //Lấy câu hỏi trong ds
        //Tạo 1 mảng buttons gồm 4 nút -> để xử lý trong vòng lặp for phía dưới
        Button[] buttons = {btnA, btnB, btnC, btnD}; //Khi  buttons[0] là btnA, buttons[1] là btnB, ..., buttons[3] là btnD.

        for (int i = 0; i < 4; i++) {
            if (i == q.getCorrectIndex()) { //Trả về chỉ số đáp án đúng. Nếu i trùng với đ/a đúng hiện xanh
                buttons[i].setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            } else if (i == selectedAnswerIndex) {
                buttons[i].setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }

        //So sánh đáp án người dùng chọn và đáp án đúng của câu hỏi
        if (selectedAnswerIndex == q.getCorrectIndex()) {
            soCauDung++; //Đúng thi tăng biến soCauDung lên 1 sau đó chuyển sang màn hình ketquaActivity
        }

        btnSubmit.setEnabled(false); //vô hiêu hóa để người dùng không ấn nhiều lần
        btnNext.setVisibility(View.VISIBLE);

        //Nếu đếm thời gian thì dừng lại khi người dùng đã làm xong bài hoặc đã chọn đáp án
        if (CountDownTimer != null) CountDownTimer.cancel();
    }

    //Hàm chuyển câu hỏi
    private void nextQuestion() {
        currentQuestionIndex++; //Khi đó số câu hỏi được tăng thêm 1
        loadQuestion();
    }

    private void resetButtonColors() { //Hàm đặt lại màu cũ ch 4 đáp án
        btnA.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnB.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnC.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnD.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    //Hàm đếm ngược thời gian khôgn trả về giá trị
    private void startTimer() {
        CountDownTimer = new CountDownTimer(10000, 1000) { //Khởi tạo 1 bộ đếm mới
            @Override
            public void onTick(long millisUntilFinished) { //millisUntilFinished là thời gian còn lại tính bằng milliseconds (ms)
                //Chia millisUntilFinished cho 1000 để chuyển từ milliseconds sang giây.
                int seconds = (int) (millisUntilFinished / 1000); //Ép kiểu (int) để làm sô nguyên
                tvTimer.setText("Thời gian: " + seconds + "s");
            }

            @Override
            public void onFinish() {
                //Sử dụng Toast để khi thời gian chạy hết sẽ thông báo Hết giờ, nó chỉ hiện lên một chút rồi tụ mất đi
                //makeText là một phương thức tĩnh
                Toast.makeText(QuizActivity.this, "Hết giờ!", Toast.LENGTH_SHORT).show();
                btnSubmit.setEnabled(false); //vô hiệu hóa người chơi không thể nộp câu tl khi hết giờ
                btnNext.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    //Hàm thêm các câu hỏi vào danh sách question bên class Question
private void loadPracticeQuestions() {
        questions.add(new Question("“汉语” nghĩa là gì?",
                new String[]{"Hàn Quốc", "Tiếng Hán", "Học sinh", "Người Việt"}, 1));
        questions.add(new Question("“谢谢” 是什么意思？",
                new String[]{"Xin lỗi", "Cảm ơn", "Tạm biệt", "Chào buổi sáng"}, 1));
        questions.add(new Question("“你好” có nghĩa là gì?",
                new String[]{"Tạm biệt", "Xin lỗi", "Chào bạn", "Tôi yêu bạn"}, 2));
        questions.add(new Question("“中国” có nghĩa là gì?",
                new String[]{"Trung Quốc", "Trung tâm", "Người Trung", "Ngôn ngữ"}, 0));
        questions.add(new Question("“再见” nghĩa là gì?",
                new String[]{"Xin chào", "Tạm biệt", "Cảm ơn", "Không có gì"}, 1));
    }

    private void loadExamQuestions() {
        questions.add(new Question("“我叫…” nghĩa là gì?",
                new String[]{"Tôi đói", "Tôi tên là", "Tôi khỏe", "Tôi đi chơi"}, 1));
        questions.add(new Question("Từ nào là động từ?",
                new String[]{"喜欢", "我", "你", "的"}, 0));
        questions.add(new Question("哪个是汉语的拼音？",
                new String[]{"pīn yīn", "hàn zì", "shū fǎ", "zhōng wén"}, 0));
        questions.add(new Question("“老师” nghĩa là gì?",
                new String[]{"Học sinh", "Thầy/Cô giáo", "Cha mẹ", "Bạn bè"}, 1));
        questions.add(new Question("“学生” nghĩa là gì?",
                new String[]{"Giáo viên", "Sinh viên", "Trường học", "Cô gái"}, 1));
    }

    private void loadAdvancedQuestions() {
        questions.add(new Question("“我喜欢学习汉语” có nghĩa là gì?",
                new String[]{"Tôi thích học tiếng Trung", "Tôi thích ăn cơm", "Tôi không hiểu", "Tôi đi học"}, 0));
        questions.add(new Question("Chọn từ mang nghĩa 'trường học':",
                new String[]{"老师", "学生", "学校", "作业"}, 2));
        questions.add(new Question("“作业” nghĩa là gì?",
                new String[]{"Giáo viên", "Bài tập", "Câu hỏi", "Bài kiểm tra"}, 1));
        questions.add(new Question("“你好吗？” có nghĩa là gì?",
                new String[]{"Tạm biệt", "Bạn khỏe không?", "Bạn là ai?", "Bạn đi đâu?"}, 1));
        questions.add(new Question("Từ nào có nghĩa là 'đọc sách'?",
                new String[]{"写字", "看书", "说话", "听歌"}, 1));
    }

    private void loadExam1() {
        questions.add(new Question("“你好” có nghĩa là gì?", new String[]{"Tạm biệt", "Xin lỗi", "Chào bạn", "Tôi yêu bạn"}, 2));
        questions.add(new Question("“谢谢” 是什么意思？", new String[]{"Xin lỗi", "Cảm ơn", "Tạm biệt", "Chào buổi sáng"}, 1));
        questions.add(new Question("“再见” nghĩa là gì?", new String[]{"Xin chào", "Tạm biệt", "Cảm ơn", "Không có gì"}, 1));
        questions.add(new Question("“中国” có nghĩa là gì?", new String[]{"Trung Quốc", "Trung tâm", "Người Trung", "Ngôn ngữ"}, 0));
        questions.add(new Question("“我叫…” nghĩa là gì?", new String[]{"Tôi đói", "Tôi tên là", "Tôi khỏe", "Tôi đi chơi"}, 1));
    }

    private void loadExam2() {
        questions.add(new Question("“学生” nghĩa là gì?", new String[]{"Giáo viên", "Sinh viên", "Trường học", "Cô gái"}, 1));
        questions.add(new Question("Từ nào là động từ?", new String[]{"喜欢", "我", "你", "的"}, 0));
        questions.add(new Question("“老师” nghĩa là gì?", new String[]{"Học sinh", "Thầy/Cô giáo", "Cha mẹ", "Bạn bè"}, 1));
        questions.add(new Question("“汉语” nghĩa là gì?", new String[]{"Hàn Quốc", "Tiếng Hán", "Học sinh", "Người Việt"}, 1));
        questions.add(new Question("“你好吗？” có nghĩa là gì?", new String[]{"Tạm biệt", "Bạn khỏe không?", "Bạn là ai?", "Bạn đi đâu?"}, 1));
    }

    private void loadExam3() {
        questions.add(new Question("“我喜欢学习汉语” có nghĩa là gì?", new String[]{"Tôi thích học tiếng Trung", "Tôi thích ăn cơm", "Tôi không hiểu", "Tôi đi học"}, 0));
        questions.add(new Question("Từ nào có nghĩa là 'đọc sách'?", new String[]{"写字", "看书", "说话", "听歌"}, 1));
        questions.add(new Question("Chọn từ mang nghĩa 'trường học':", new String[]{"老师", "学生", "学校", "作业"}, 2));
        questions.add(new Question("“作业” nghĩa là gì?", new String[]{"Giáo viên", "Bài tập", "Câu hỏi", "Bài kiểm tra"}, 1));
        questions.add(new Question("“早上好” có nghĩa là gì?", new String[]{"Chúc ngủ ngon", "Chào buổi sáng", "Buổi tối tốt lành", "Tạm biệt"}, 1));
    }

    private void loadExam4() {
        questions.add(new Question("“他是老师” nghĩa là gì?", new String[]{"Tôi là giáo viên", "Anh ấy là giáo viên", "Cô ấy là giáo viên", "Chúng tôi là học sinh"}, 1));
        questions.add(new Question("“我们” nghĩa là gì?", new String[]{"Tôi", "Bạn", "Chúng tôi", "Anh ấy"}, 2));
        questions.add(new Question("Từ nào mang nghĩa 'yêu thích'?", new String[]{"喜欢", "学生", "老师", "中国"}, 0));
        questions.add(new Question("“我爱你” nghĩa là gì?", new String[]{"Tôi ghét bạn", "Tôi yêu bạn", "Tôi chào bạn", "Tôi nhớ bạn"}, 1));
        questions.add(new Question("“是” là từ loại nào?", new String[]{"Danh từ", "Động từ", "Tính từ", "Đại từ"}, 1));
    }

    private void loadExam5() {
        questions.add(new Question("“中国人” nghĩa là gì?", new String[]{"Người Việt Nam", "Người Hàn Quốc", "Người Trung Quốc", "Người Nhật"}, 2));
        questions.add(new Question("“现在几点？” nghĩa là gì?", new String[]{"Mấy giờ rồi?", "Hôm nay là gì?", "Bạn đang làm gì?", "Chúng ta đi đâu?"}, 0));
        questions.add(new Question("“我饿了” có nghĩa là gì?", new String[]{"Tôi mệt", "Tôi khát", "Tôi buồn", "Tôi đói"}, 3));
        questions.add(new Question("“明天见” nghĩa là gì?", new String[]{"Hôm nay gặp lại", "Ngày mai gặp lại", "Tối nay gặp lại", "Không gặp nữa"}, 1));
        questions.add(new Question("“今天星期几？” có nghĩa là gì?", new String[]{"Hôm nay là ngày mấy?", "Hôm nay là thứ mấy?", "Hôm nay thời tiết thế nào?", "Hôm nay là tháng mấy?"}, 1));
    }

    //Sử dụng hàm này để khi thoát bài làm hoặc muốn chuyển qua màn hình khác
    @Override
    protected void onDestroy() {
        //Nếu khi đh vẫn chạy thì nó sẽ bị hủy, ngừng đếm thời gian khi người dùng đã out
        if (CountDownTimer != null) CountDownTimer.cancel();
        super.onDestroy();//Tiếp tục gọi hàm để hủy Activity đã mở
    }
}


