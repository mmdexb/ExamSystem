package StudentPanel;

import pojo.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class LianXi {
    JFrame frame = new JFrame("练习");
    JButton A = new JButton("A");
    JButton B = new JButton("B");
    JButton C = new JButton("C");
    JButton D = new JButton("D");
    JTextField Answer = new JTextField(20);
    JButton Next = new JButton("下一题");
    JButton Prev = new JButton("上一题");
    JButton Submit = new JButton("提交");
    JTextArea QuestionArea = new JTextArea(10, 30);
    JTextArea ListArea = new JTextArea(20, 20);
    ArrayList<Question> arrayList = new ArrayList();
    ArrayList<String> AnswerList = new ArrayList();
    JTextArea TimerArea = new JTextArea(1, 10);
    int count = 0;
    int QusetionPointer=0;

    JPanel ButtonPanel = new JPanel();
    JPanel QuestionPanel = new JPanel();
    JPanel QuestionPanel2 = new JPanel();

    String content = "";
    String title = "";
    String score = "";
    String time = "";
    int secondsLeft ;
    String userId = "";
    int ssi;

    public LianXi(int sid,int Userid) {
        QuestionArea.setEditable(false);
        ListArea.setEditable(false);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ButtonPanel.setLayout(new GridLayout(4, 2));
        Answer.setEditable(false);
        Answer.setText("你选择了 答案");
        ButtonPanel.add(Answer);
        ButtonPanel.add(A);
        ButtonPanel.add(B);
        ButtonPanel.add(C);
        ButtonPanel.add(D);
        ButtonPanel.add(Prev);
        ButtonPanel.add(Next);
        ButtonPanel.add(Submit);
        A.addActionListener(e -> ButtonA());
        B.addActionListener(e -> ButtonB());
        C.addActionListener(e -> ButtonC());
        D.addActionListener(e -> ButtonD());
        Next.addActionListener(e -> ButtonNext());
        Prev.addActionListener(e -> ButtonPrev());
        Submit.addActionListener(e -> ButtonSubmit());

        QuestionPanel.add(QuestionArea);
        QuestionPanel2.setLayout(new GridLayout(8,1));
        TimerArea.setText("计时器");
        QuestionPanel2.add(TimerArea);

        ListArea.setText("题目列表");
        frame.add(QuestionPanel, BorderLayout.CENTER);
        frame.add(QuestionPanel2, BorderLayout.WEST);
        frame.add(ButtonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        QuestionArea.setText("题目");
        ssi=sid;
        userId=String.valueOf(Userid);



        StartLianxi(sid);


    }

    private void ButtonSubmit() {
        if(QusetionPointer!=count){
            int result = JOptionPane.showConfirmDialog(frame,"你还没完成，是否要提交？", "提示", JOptionPane.YES_NO_OPTION);
            if(result==0)
            {
                JOptionPane.showMessageDialog(frame,"提交成功");
                int rightConunt=0;

                for(int i=0;i<AnswerList.size();i++)
                {
                    System.out.println(AnswerList.get(i)+"<----->"+arrayList.get(i).getAnswer());
                    if(AnswerList.get(i).equals(arrayList.get(i).getAnswer()))
                    {
                        rightConunt++;
                    }
                }
                //string类型单题分数转化为double
                double Dscore=Double.parseDouble(score);
                JOptionPane.showMessageDialog(frame,"考试完成,本次得分"+rightConunt*Dscore+"(满分为"+count*Dscore+")");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
                String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
                String u="exam";
                String p="1qaz2wsx";

                try {
                    Connection conn = DriverManager.getConnection(url,u,p);
                    Statement stmt = conn.createStatement();
                    String sql="INSERT INTO history (id, sid, time, score) VALUES ("+userId+","+this.ssi+",'"+(Integer.parseInt(time)-secondsLeft)+"',"+rightConunt*Dscore+")";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    conn.close();
                    System.out.println("插入成功");

                }catch (Exception e) {
                    System.out.println(e);

                }

                frame.dispose();
            }
        }
    }
    private void ButtonSubmit(int wu) {
        if (wu==1){
            JOptionPane.showMessageDialog(frame,"提交成功");
            int rightConunt=0;

            for(int i=0;i<AnswerList.size();i++)
            {
                System.out.println(AnswerList.get(i)+"<----->"+arrayList.get(i).getAnswer());
                if(AnswerList.get(i).equals(arrayList.get(i).getAnswer()))
                {

                    rightConunt++;
                }
            }
            //string类型单题分数转化为double
            double Dscore=Double.parseDouble(score);
            JOptionPane.showMessageDialog(frame,"考试完成,本次得分"+rightConunt*Dscore+"(满分为"+count*Dscore+")");
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
            String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
            String u="exam";
            String p="1qaz2wsx";

            try {
                Connection conn = DriverManager.getConnection(url,u,p);
                Statement stmt = conn.createStatement();
                String sql="INSERT INTO history (id, sid, time, score) VALUES ("+userId+","+this.ssi+",'"+(Integer.parseInt(time)-secondsLeft)+"',"+rightConunt*Dscore+")";
                System.out.println(sql);
                stmt.executeUpdate(sql);
                conn.close();
                System.out.println("插入成功");

            }catch (Exception e) {
                System.out.println(e);

            }
            frame.dispose();

        }
    }

    private void ButtonPrev() {
        if(QusetionPointer>0)
        {
            QusetionPointer--;
            QuestionArea.setText("第"+(QusetionPointer+1)+"题" +arrayList.get(QusetionPointer).getTitle());
            QuestionArea.append("\nA."+arrayList.get(QusetionPointer).getA()+"\nB."+arrayList.get(QusetionPointer).getB()+"\nC."+arrayList.get(QusetionPointer).getC()+"\nD."+arrayList.get(QusetionPointer).getD());
        }else {
            JOptionPane.showMessageDialog(frame,"已经是第一题了");
        }

    }

    private void ButtonNext() {
        if(QusetionPointer<arrayList.size()-1)
        {
            QusetionPointer++;
            QuestionArea.setText("第"+(QusetionPointer+1)+"题" +arrayList.get(QusetionPointer).getTitle());
            QuestionArea.append("\nA."+arrayList.get(QusetionPointer).getA()+"\nB."+arrayList.get(QusetionPointer).getB()+"\nC."+arrayList.get(QusetionPointer).getC()+"\nD."+arrayList.get(QusetionPointer).getD());
        }else {
            JOptionPane.showMessageDialog(frame,"已经是最后一题了");
        }
    }

    private void ButtonD() {
        Answer.setText("你选择了答案 D "+arrayList.get(QusetionPointer).getD());
        AnswerList.add("D");
    }

    private void ButtonC() {
        Answer.setText("你选择了答案 C "+arrayList.get(QusetionPointer).getC());
        AnswerList.add("C");
    }

    private void ButtonB() {
        Answer.setText("你选择了答案 B "+arrayList.get(QusetionPointer).getB());
        AnswerList.add("B");
    }

    private void ButtonA() {
        Answer.setText("你选择了答案 A "+arrayList.get(QusetionPointer).getA());
        AnswerList.add("A");
    }

    private void StartLianxi(int sid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";

        try {
            Connection conn = DriverManager.getConnection(url,u,p);
            Statement stmt = conn.createStatement();
            String sql = "select * from shijuan where sid="+sid;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                content = rs.getString("content");
                title = rs.getString("title");
                score = rs.getString("score");
                time = rs.getString("time");
            }
            //分隔content
            String[] questions = content.split(",");
            for (String question : questions) {
                String sql2 = "select * from tiku where tid="+question;
                ResultSet rs2 = stmt.executeQuery(sql2);
                while (rs2.next()){
                    Question question1 = new Question();
                    question1.setTid(rs2.getInt("tid"));
                    question1.setTitle(rs2.getString("title"));
                    question1.setA(rs2.getString("A"));
                    question1.setB(rs2.getString("B"));
                    question1.setC(rs2.getString("C"));
                    question1.setD(rs2.getString("D"));
                    question1.setAnswer(rs2.getString("answer"));
                    arrayList.add(question1);
                    count++;
                }
            }
            QuestionPanel2.add(new JLabel("试卷题目: "+title));
            QuestionPanel2.add(new JLabel("限制用时: "+ time));
            QuestionPanel2.add(new JLabel("单题分数: "+ score));
            QuestionPanel2.add(new JLabel("题目数量"+ count));

        } catch (SQLException e) {
            System.out.println(e);
        }
        QuestionArea.setText("第"+(QusetionPointer+1)+"题: "+arrayList.get(QusetionPointer).getTitle());
        QuestionArea.append("\nA."+arrayList.get(QusetionPointer).getA()+"\nB."+arrayList.get(QusetionPointer).getB()+"\nC."+arrayList.get(QusetionPointer).getC()+"\nD."+arrayList.get(QusetionPointer).getD());
        secondsLeft = Integer.parseInt(time);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft > 0) {
                    TimerArea.setText("倒计时: " + secondsLeft + "秒");
                    System.out.println(secondsLeft);
                    secondsLeft--;
                } else {
                    JOptionPane.showMessageDialog(frame,"考试结束");
                    ButtonSubmit(1);
                    ((Timer) e.getSource()).stop();
                }
                if(!frame.isVisible()){
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();

    }



}
