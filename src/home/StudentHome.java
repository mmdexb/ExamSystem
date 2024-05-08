package home;

import javax.swing.*;
import java.awt.*;

public class StudentHome {
    public static JFrame frame = new JFrame("学生主页");


    public StudentHome(String username, String lastExamScore) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        JPanel personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new BoxLayout(personalInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("用户名: " + username);
        personalInfoPanel.add(usernameLabel);

        JLabel lastExamScoreLabel = new JLabel("上次考试成绩: " + lastExamScore);
        personalInfoPanel.add(lastExamScoreLabel);


        JPanel buttonsPanel = new JPanel();

        JButton practiceButton = new JButton("进行练习");
        buttonsPanel.add(practiceButton);

        JButton examButton = new JButton("进行考试");
        buttonsPanel.add(examButton);


        frame.getContentPane().add(personalInfoPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonsPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
