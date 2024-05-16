package pojo;

public class Question {
    private String title;
    private Integer tid;
    private String A;
    private String B;
    private String C;
    private String D;
    private String answer;

    public Question(String title, String answer, String a, String b, String c, String d, Integer tid) {
        this.title = title;
        this.answer = answer;
        D = d;
        C = c;
        A = a;
        B = b;
        this.tid = tid;
    }

    public Question(String a, String title, String b, String c, String d, String answer) {
        A = a;
        this.title = title;
        B = b;
        C = c;
        D = d;
        this.answer = answer;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
