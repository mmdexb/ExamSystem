package home;

import javax.swing.*;

public class TeacherHome {
    public static JFrame frame=new JFrame("教师主页");

    public TeacherHome(String username, String id) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        JPanel personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new BoxLayout(personalInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("用户名: " + username);
        personalInfoPanel.add(usernameLabel);

        JLabel lastExamScoreLabel = new JLabel("工号: " + id);
        personalInfoPanel.add(lastExamScoreLabel);


        JPanel buttonsPanel = new JPanel();

        JButton StudentInfo = new JButton("查看学生信息");
        buttonsPanel.add(StudentInfo);

        JButton AddShiJuan = new JButton("制作试卷");
        buttonsPanel.add(AddShiJuan);

        JButton EditQuestions = new JButton("编辑题库");
        buttonsPanel.add(EditQuestions);

        JButton AddStudent = new JButton("添加学生");
        buttonsPanel.add(AddStudent);



        frame.getContentPane().add(personalInfoPanel);
        frame.getContentPane().add(buttonsPanel);

        frame.setVisible(true);
    }
}
