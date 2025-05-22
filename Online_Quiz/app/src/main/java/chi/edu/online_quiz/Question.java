package chi.edu.online_quiz;

//Là một lớp class cực kỳ quan trọng
public class Question {
    //được khai báo là private nên không thể truy cập trực tiếp từ bên ngoài class.
    private String question; //Câu hỏi
    private String[] options; //Đáp án
    private int correctIndex; //Ví trí đáp án đúng

    public Question(String question, String[] options, int correctIndex) {
        //Dùng từ khóa this để phân biệt giữa biến của class và biến tham số.
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    // getter methods hàm dùng để lấy (đọc) dữ liệu từ các biến private trong class Question
    public String getQuestion() { return question; } //trả về nội dung câu hỏi.Dùng để lấy ra chuỗi nội dung câu hỏi
    public String[] getOptions() { return options; } //trả về mảng các đáp án.Dùng để lấy ra danh sách 4 đáp án của câu hỏi.
    public int getCorrectIndex() { return correctIndex; } //trả về chỉ số (index) của đáp án đúng.Chỉ số này tương ứng với vị trí trong mảng options, bắt đầu từ 0.
}
