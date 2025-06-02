package chi.edu.online_quiz;

import android.content.Intent;
import android.media.MediaPlayer;
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

    // Các thành phần giao diện
    private TextView tvQuestion, tvTimer;
    private Button btnA, btnB, btnC, btnD, btnSubmit, btnNext;

    // Danh sách câu hỏi
    private List<Question> questions;
    private int currentQuestionIndex = 0; // chỉ số câu hỏi hiện tại
    private int selectedAnswerIndex = -1; // chỉ số đáp án người dùng chọn, -1 là chưa chọn

    private CountDownTimer CountDownTimer; // bộ đếm thời gian làm bài
    private String mode = ""; // chế độ làm bài: luyện tập, nâng cao, đề thi 1-5
    private String selectedExam = ""; // tên đề thi được chọn
    private int soCauDung = 0; // số câu trả lời đúng

    // MediaPlayer để phát âm thanh đúng/sai
    private MediaPlayer mediaPlayerCorrect;
    private MediaPlayer mediaPlayerWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz); // gán layout cho activity

        // Gán các thành phần giao diện với biến trong code
        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);

        // Lấy dữ liệu mode và đề thi truyền từ activity trước (MainActivity hoặc màn hình chọn đề)
        mode = getIntent().getStringExtra("mode");
        selectedExam = getIntent().getStringExtra("exam");

        // Khởi tạo MediaPlayer phát âm thanh cho kết quả đúng/sai
        mediaPlayerCorrect = MediaPlayer.create(this, R.raw.dung);
        mediaPlayerWrong = MediaPlayer.create(this, R.raw.sai);

        questions = new ArrayList<>(); // tạo mới danh sách câu hỏi

        // Dựa vào mode, load bộ câu hỏi phù hợp
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
                finish(); // thoát activity nếu không tìm thấy đề
        }

        loadQuestion(); // tải câu hỏi đầu tiên lên giao diện

        // Thiết lập sự kiện cho các nút chọn đáp án, khi nhấn sẽ gọi hàm selectAnswer tương ứng
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(0); // chọn đáp án A (index 0)
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(1); // chọn đáp án B (index 1)
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(2); // chọn đáp án C (index 2)
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAnswer(3); // chọn đáp án D (index 3)
            }
        });

        // Nút gửi câu trả lời để kiểm tra kết quả
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(); // gọi hàm kiểm tra đáp án đã chọn
            }
        });

        // Nút chuyển sang câu hỏi tiếp theo
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(); // tải câu hỏi tiếp theo
            }
        });
    }

    // Giải phóng tài nguyên khi activity bị huỷ
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerCorrect != null) {
            mediaPlayerCorrect.release();
            mediaPlayerCorrect = null;
        }
        if (mediaPlayerWrong != null) {
            mediaPlayerWrong.release();
            mediaPlayerWrong = null;
        }
        if (CountDownTimer != null) {
            CountDownTimer.cancel();
        }
    }

    // Hàm tải câu hỏi hiện tại lên giao diện
    private void loadQuestion() {
        if (CountDownTimer != null) CountDownTimer.cancel(); // huỷ bộ đếm cũ nếu có

        if (mode.startsWith("De")) { // nếu là đề thi thì bắt đầu bộ đếm thời gian 10 giây
            startTimer();
        } else {
            tvTimer.setText(""); // nếu không phải đề thì không hiển thị thời gian
        }

        if (currentQuestionIndex < questions.size()) {
            // Lấy câu hỏi hiện tại
            Question q = questions.get(currentQuestionIndex);
            tvQuestion.setText(q.getQuestion()); // hiển thị câu hỏi
            btnA.setText("A. " + q.getOptions()[0]); // hiển thị đáp án A
            btnB.setText("B. " + q.getOptions()[1]);
            btnC.setText("C. " + q.getOptions()[2]);
            btnD.setText("D. " + q.getOptions()[3]);

            resetButtonColors(); // reset màu nền các nút về màu mặc định (xám)
            btnSubmit.setEnabled(true); // bật nút gửi
            btnNext.setVisibility(View.GONE); // ẩn nút tiếp theo
            selectedAnswerIndex = -1; // chưa chọn đáp án nào
        } else {
            // Nếu đã hết câu hỏi, chuyển sang màn hình kết quả
            Intent intent = new Intent(QuizActivity.this, KetquaActivity.class);
            intent.putExtra("soCauDung", soCauDung); // truyền số câu đúng
            intent.putExtra("tongSoCau", questions.size()); // tổng số câu hỏi
            intent.putExtra("mode", mode);
            intent.putExtra("exam", selectedExam);
            startActivity(intent);
        }
    }

    // Hàm chọn đáp án, đánh dấu nút được chọn với màu xanh dương
    private void selectAnswer(int index) {
        selectedAnswerIndex = index; // lưu lại đáp án đã chọn
        resetButtonColors(); // reset màu nền các nút trước đó
        Button[] buttons = {btnA, btnB, btnC, btnD};
        buttons[index].setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
    }

    // Hàm kiểm tra đáp án khi người dùng nhấn nút Submit
    private void checkAnswer() {
        if (selectedAnswerIndex == -1) {
            // Nếu chưa chọn đáp án nào, hiện thông báo yêu cầu chọn
            Toast.makeText(this, "Hãy chọn một đáp án!", Toast.LENGTH_SHORT).show();
            return;
        }

        Question q = questions.get(currentQuestionIndex);
        Button[] buttons = {btnA, btnB, btnC, btnD};

        // Tô màu các nút đáp án: xanh lá cho đáp án đúng, đỏ cho đáp án sai đã chọn
        for (int i = 0; i < 4; i++) {
            if (i == q.getCorrectIndex()) {
                buttons[i].setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            } else if (i == selectedAnswerIndex) {
                buttons[i].setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            }
        }

        // Nếu chọn đúng thì tăng số câu đúng và phát âm thanh đúng
        if (selectedAnswerIndex == q.getCorrectIndex()) {
            soCauDung++;
            if (mediaPlayerCorrect != null) {
                mediaPlayerCorrect.start();
            }
        } else {
            // Nếu sai phát âm thanh sai
            if (mediaPlayerWrong != null) {
                mediaPlayerWrong.start();
            }
        }

        btnSubmit.setEnabled(false); // tắt nút gửi để không gửi nhiều lần
        btnNext.setVisibility(View.VISIBLE); // hiện nút tiếp theo để chuyển câu hỏi

        if (CountDownTimer != null) CountDownTimer.cancel(); // huỷ bộ đếm thời gian nếu còn đang chạy
    }

    // Hàm chuyển sang câu hỏi tiếp theo
    private void nextQuestion() {
        currentQuestionIndex++;
        loadQuestion(); // load câu hỏi mới
    }

    // Hàm reset màu nền các nút trả lời về mặc định (màu xám)
    private void resetButtonColors() {
        btnA.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnB.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnC.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        btnD.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    // Hàm bắt đầu bộ đếm ngược thời gian 10 giây cho câu hỏi
    private void startTimer() {
        CountDownTimer = new CountDownTimer(10000, 1000) { // 10 giây, tick mỗi 1 giây
            @Override
            public void onTick(long millisUntilFinished) {
                // Cập nhật hiển thị thời gian còn lại
                tvTimer.setText("Thời gian: " + millisUntilFinished / 1000 + " giây");
            }

            @Override
            public void onFinish() {
                // Khi hết thời gian, tự động kiểm tra đáp án
                tvTimer.setText("Hết giờ!");
                checkAnswer();
            }
        };
        CountDownTimer.start(); // bắt đầu đếm ngược
    }

//Hàm thêm các câu hỏi vào danh sách question bên class Question
private void loadPracticeQuestions() {
        questions.add(new Question("“汉语” nghĩa là gì?",
                new String[]{"Hàn Quốc", "Tiếng Hán", "Học sinh", "Người Việt"}, 1));
        questions.add(new Question("“谢谢” 是什么意思？",
                new String[]{"Xin lỗi", "Cảm ơn", "Tạm biệt", "Chào buổi sáng"}, 1));
        questions.add(new Question("“作业” nghĩa là gì?",
            new String[]{"Giáo viên", "Bài tập", "Câu hỏi", "Bài kiểm tra"}, 1));
        questions.add(new Question("明天你打算去哪儿？",
            new String[]{"我去超市", "我昨天在家", "我是越南人", "我吃了饭"}, 0));
        questions.add(new Question("“你好” có nghĩa là gì?",
                new String[]{"Tạm biệt", "Xin lỗi", "Chào bạn", "Tôi yêu bạn"}, 2));
        questions.add(new Question("“你好吗？” có nghĩa là gì?",
            new String[]{"Tạm biệt", "Bạn khỏe không?", "Bạn là ai?", "Bạn đi đâu?"}, 1));
        questions.add(new Question("Bạn thường đi học bằng gì?",
            new String[]{"我坐公共汽车", "我吃米饭", "我看书", "我在家"}, 0));
        questions.add(new Question("你住在哪里？",
            new String[]{"我住在河内", "我吃米饭", "他是学生", "我们是同学"}, 0));
        questions.add(new Question("你最喜欢的水果是什么？",
            new String[]{"Tôi thích ăn táo", "Tôi học tiếng Trung", "Tôi đi siêu thị", "Tôi đọc sách"}, 0));
        questions.add(new Question("“再见” nghĩa là gì?",
                new String[]{"Xin chào", "Tạm biệt", "Cảm ơn", "Không có gì"}, 1));
    }

    private void loadExamQuestions() {
        questions.add(new Question("“我叫…” nghĩa là gì?",
                new String[]{"Tôi đói", "Tôi tên là", "Tôi khỏe", "Tôi đi chơi"}, 1));
        questions.add(new Question("Món ăn bạn thích là gì?",
                new String[]{"我喜欢面条", "我住在河内", "我是学生", "我去上学"}, 0));
        questions.add(new Question("Bạn thường đi học bằng gì?",
                new String[]{"我坐公共汽车", "我吃米饭", "我看书", "我在家"}, 0));
        questions.add(new Question("Từ nào là động từ?",
                new String[]{"喜欢", "我", "你", "的"}, 0));
        questions.add(new Question("“中国” có nghĩa là gì?",
                new String[]{"Trung Quốc", "Trung tâm", "Người Trung", "Ngôn ngữ"}, 0));
        questions.add(new Question("哪个是汉语的拼音？",
                new String[]{"pīn yīn", "hàn zì", "shū fǎ", "zhōng wén"}, 0));
        questions.add(new Question("“老师” nghĩa là gì?",
                new String[]{"Học sinh", "Thầy/Cô giáo", "Cha mẹ", "Bạn bè"}, 1));
        questions.add(new Question("你今天几点起床？",
                new String[]{"Tôi dậy lúc 7 giờ", "Tôi là học sinh", "Tôi ở Hà Nội", "Tôi ăn cơm"}, 0));
        questions.add(new Question("“学生” nghĩa là gì?",
                new String[]{"Giáo viên", "Sinh viên", "Trường học", "Cô gái"}, 1));
    }

    private void loadAdvancedQuestions() {
        questions.add(new Question("“我喜欢学习汉语” có nghĩa là gì?",
                new String[]{"Tôi thích học tiếng Trung", "Tôi thích ăn cơm", "Tôi không hiểu", "Tôi đi học"}, 0));
        questions.add(new Question("Chọn từ mang nghĩa 'trường học':",
                new String[]{"老师", "学生", "学校", "作业"}, 2));
        questions.add(new Question("你住在哪里？",
                new String[]{"我住在河内", "我吃米饭", "他是学生", "我们是同学"}, 0));
        questions.add(new Question("你喜欢喝什么？",
                new String[]{"我喜欢喝茶", "我喜欢看书", "我有两个哥哥", "我在上课"}, 0));
        questions.add(new Question("Từ nào có nghĩa là 'đọc sách'?",
                new String[]{"写字", "看书", "说话", "听歌"}, 1));
        questions.add(new Question(" 昨天的电影你看了吗？",
                new String[]{"是，我昨天看了。", "不，我吃饭了", "是，我去超市。", " 没有，我明天去。"},0));
        questions.add(new Question("你平时喜欢做什么运动？",
                new String[]{"Tôi thích ăn cơm", "Tôi thích chơi bóng rổ", "Tôi đi học", "Tôi là học sinh"}, 1));
        questions.add(new Question("她每天早上六点起床，然后做什么？",
                new String[]{"Ngủ", "Ăn sáng", "Xem tivi", "Đã học bài"}, 1));
        questions.add(new Question("如果明天下雨，你会做什么？",
                new String[]{"Tôi sẽ không đi công viên", "Tôi ăn táo", "Tôi có một anh trai", "Tôi đang đọc sách"}, 0));
        questions.add(new Question("这家饭馆的菜怎么样？",
                new String[]{"Rất ngon, phục vụ cũng rất tốt", "Tôi sống ở Bắc Kinh", "Tôi có tiết học vào thứ Tư", "Tôi không thích mùa đông"}, 0));
    }

    private void loadExam1() {
        questions.add(new Question("“你好” có nghĩa là gì?", new String[]{"Tạm biệt", "Xin lỗi", "Chào bạn", "Tôi yêu bạn"}, 2));
        questions.add(new Question("“我饿了” có nghĩa là gì?", new String[]{"Tôi mệt", "Tôi khát", "Tôi buồn", "Tôi đói"}, 3));
        questions.add(new Question("她在图书馆做什么？", new String[]{"听音乐", "说话", "看书", "买东西"}, 2));
        questions.add(new Question("如果明天下雨，你会做什么？", new String[]{"Tôi sẽ không đi công viên", "Tôi ăn táo", "Tôi có một anh trai", "Tôi đang đọc sách"}, 0));
        questions.add(new Question("你住在哪里？", new String[]{"我住在河内", "我吃米饭", "他是学生", "我们是同学"}, 0));
        questions.add(new Question("你喜欢喝什么？", new String[]{"我喜欢喝茶", "我喜欢看书", "我有两个哥哥", "我在上课"}, 0));
        questions.add(new Question("“你好吗？” có nghĩa là gì?", new String[]{"Tạm biệt", "Bạn khỏe không?", "Bạn là ai?", "Bạn đi đâu?"}, 1));
        questions.add(new Question("“早上好” có nghĩa là gì?", new String[]{"Chúc ngủ ngon", "Chào buổi sáng", "Buổi tối tốt lành", "Tạm biệt"}, 1));
        questions.add(new Question("“中国” có nghĩa là gì?", new String[]{"Trung Quốc", "Trung tâm", "Người Trung", "Ngôn ngữ"}, 0));
        questions.add(new Question("Chọn từ mang nghĩa 'trường học':", new String[]{"老师", "学生", "学校", "作业"}, 2));
    }

    private void loadExam2() {
        questions.add(new Question("Bạn tên là gì?", new String[]{"我叫明明", "我是学生", "你好吗", "我去学校"}, 0));
        questions.add(new Question("你喜欢什么颜色？", new String[]{"Màu đỏ", "Tôi đi học", "Hôm nay thứ hai", "Tôi sống ở Hà Nội"}, 0));
        questions.add(new Question("“学生” nghĩa là gì?", new String[]{"Giáo viên", "Sinh viên", "Trường học", "Cô gái"}, 1));
        questions.add(new Question("Từ nào là động từ?", new String[]{"喜欢", "我", "你", "的"}, 0));
        questions.add(new Question("你周末一般做什么？", new String[]{"我睡觉和学习", "我每天去上学", "我是老师", "我不学习"}, 0));
        questions.add(new Question("“老师” nghĩa là gì?", new String[]{"Học sinh", "Thầy/Cô giáo", "Cha mẹ", "Bạn bè"}, 1));
        questions.add(new Question("你晚上几点睡觉？", new String[]{"我晚上十点睡觉", "我喜欢跑步", "我在上课", "我去公园"}, 0));
        questions.add(new Question("你喜欢什么音乐？", new String[]{"我喜欢流行音乐", "我去买东西", "我写作业", "我不喝水"}, 0));
        questions.add(new Question("“汉语” nghĩa là gì?", new String[]{"Hàn Quốc", "Tiếng Hán", "Học sinh", "Người Việt"}, 1));
        questions.add(new Question("“你好吗？” có nghĩa là gì?", new String[]{"Tạm biệt", "Bạn khỏe không?", "Bạn là ai?", "Bạn đi đâu?"}, 1));
    }

    private void loadExam3() {
        questions.add(new Question("“我喜欢学习汉语” có nghĩa là gì?", new String[]{"Tôi thích học tiếng Trung", "Tôi thích ăn cơm", "Tôi không hiểu", "Tôi đi học"}, 0));
        questions.add(new Question("Từ nào có nghĩa là 'đọc sách'?", new String[]{"写字", "看书", "说话", "听歌"}, 1));
        questions.add(new Question("如果明天下雨，你会做什么？", new String[]{"Tôi sẽ không đi công viên", "Tôi ăn táo", "Tôi có một anh trai", "Tôi đang đọc sách"}, 0));
        questions.add(new Question("你住在哪里？", new String[]{"我住在河内", "我吃米饭", "他是学生", "我们是同学"}, 0));
        questions.add(new Question("现在几点了？", new String[]{"我是学生", "三点了", "我去上学", "我不知道"}, 1));
        questions.add(new Question("明天你打算去哪儿？", new String[]{"Tôi đi siêu thị", "Tôi ở nhà hôm qua", "Tôi là người Việt Nam", "Tôi đã ăn cơm"}, 0));
        questions.add(new Question("“作业” nghĩa là gì?", new String[]{"Giáo viên", "Bài tập", "Câu hỏi", "Bài kiểm tra"}, 1));
        questions.add(new Question("Hôm nay là thứ mấy?", new String[]{"今天是星期一", "我去学校", "我不吃饭", "我看电影"}, 0));
        questions.add(new Question("你今天穿什么衣服？", new String[]{"我穿白色的衬衫", "我吃米饭", "我去学校", "我有一本书"}, 0));
        questions.add(new Question("“早上好” có nghĩa là gì?", new String[]{"Chúc ngủ ngon", "Chào buổi sáng", "Buổi tối tốt lành", "Tạm biệt"}, 1));
    }

    private void loadExam4() {
        questions.add(new Question("Bạn thích làm gì vào cuối tuần?", new String[]{"我喜欢看电影", "我去超市", "我是老师", "我在中国"}, 0));
        questions.add(new Question("“他是老师” nghĩa là gì?", new String[]{"Tôi là giáo viên", "Anh ấy là giáo viên", "Cô ấy là giáo viên", "Chúng tôi là học sinh"}, 1));
        questions.add(new Question("你为什么学习汉语？", new String[]{"因为我喜欢中国", "因为我喜欢吃饭", "因为我是学生", "因为我有朋友"}, 0));
        questions.add(new Question("“我们” nghĩa là gì?", new String[]{"Tôi", "Bạn", "Chúng tôi", "Anh ấy"}, 2));
        questions.add(new Question("Từ nào mang nghĩa 'yêu thích'?", new String[]{"喜欢", "学生", "老师", "中国"}, 0));
        questions.add(new Question("你几岁了？", new String[]{"Tôi 20 tuổi", "Tôi ăn cơm", "Tôi là học sinh", "Tôi đọc sách"}, 0));
        questions.add(new Question("你几点吃午饭？", new String[]{"Tôi ăn trưa lúc 12 giờ", "Tôi ăn sáng lúc 7 giờ", "Tôi học lúc 3 giờ", "Tôi ngủ lúc 10 giờ"}, 0));
        questions.add(new Question("“明天见” nghĩa là gì?", new String[]{"Hôm nay gặp lại", "Ngày mai gặp lại", "Tối nay gặp lại", "Không gặp nữa"}, 1));
        questions.add(new Question("“我爱你” nghĩa là gì?", new String[]{"Tôi ghét bạn", "Tôi yêu bạn", "Tôi chào bạn", "Tôi nhớ bạn"}, 1));
        questions.add(new Question("“是” là từ loại nào?", new String[]{"Danh từ", "Động từ", "Tính từ", "Đại từ"}, 1));
    }

    private void loadExam5() {
        questions.add(new Question("你家有几口人？", new String[]{"Nhà tôi có 4 người", "Tôi là người Trung Quốc", "Tôi học lớp 10", "Tôi không biết"}, 0));
        questions.add(new Question("你几点吃午饭？", new String[]{"Tôi ăn trưa lúc 12 giờ", "Tôi ăn sáng lúc 7 giờ", "Tôi học lúc 3 giờ", "Tôi ngủ lúc 10 giờ"}, 0));
        questions.add(new Question("Bạn sống ở đâu?", new String[]{"我住在河内", "我去看书", "我是老师", "我吃米饭"}, 0));
        questions.add(new Question("你会说汉语吗？", new String[]{"Có, tôi biết nói", "Tôi học ở trường", "Không, tôi là người Việt", "Tôi thích ăn táo"}, 0));
        questions.add(new Question("你晚上几点睡觉？", new String[]{"我晚上十点睡觉", "我喜欢跑步", "我在上课", "我去公园"}, 0));questions.add(new Question("她在图书馆做什么？", new String[]{"Nghe nhạc", "Nói chuyện", "Đọc sách", "Mua đồ"}, 2));
        questions.add(new Question("“中国人” nghĩa là gì?", new String[]{"Người Việt Nam", "Người Hàn Quốc", "Người Trung Quốc", "Người Nhật"}, 2));
        questions.add(new Question("“现在几点？” nghĩa là gì?", new String[]{"Mấy giờ rồi?", "Hôm nay là gì?", "Bạn đang làm gì?", "Chúng ta đi đâu?"}, 0));
        questions.add(new Question("“我饿了” có nghĩa là gì?", new String[]{"Tôi mệt", "Tôi khát", "Tôi buồn", "Tôi đói"}, 3));
        questions.add(new Question("“明天见” nghĩa là gì?", new String[]{"Hôm nay gặp lại", "Ngày mai gặp lại", "Tối nay gặp lại", "Không gặp nữa"}, 1));
        questions.add(new Question("“今天星期几？” có nghĩa là gì?", new String[]{"Hôm nay là ngày mấy?", "Hôm nay là thứ mấy?", "Hôm nay thời tiết thế nào?", "Hôm nay là tháng mấy?"}, 1));
    }
}


