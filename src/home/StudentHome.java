package home;

import StudentPanel.HistoryScore;
import StudentPanel.LianXi;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentHome {
    public static JFrame frame = new JFrame("学生主页");


    public StudentHome(String username, String id) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        JPanel personalInfoPanel = new JPanel();
        personalInfoPanel.setLayout(new BoxLayout(personalInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("用户名: " + username);
        personalInfoPanel.add(usernameLabel);

        JLabel lastExamScoreLabel = new JLabel("学号: " + id);
        personalInfoPanel.add(lastExamScoreLabel);


        JPanel buttonsPanel = new JPanel();

        JButton practiceButton = new JButton("进行考试");
        buttonsPanel.add(practiceButton);

        JButton examButton = new JButton("查询历史成绩");
        buttonsPanel.add(examButton);
        practiceButton.addActionListener(e -> {
            JFrame Test = new JFrame("选择考试");
            JTextArea tests = new JTextArea(10, 20);
            JTextField choice = new JTextField(20);
            JButton sure = new JButton("确定");
            Test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            Test.setSize(300, 300);
            ArrayList<String> sids = new ArrayList<>();

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
                String sql="Select * from shijuan";
                System.out.println(sql);
                stmt.executeQuery(sql);
                ResultSet rs = stmt.executeQuery(sql);
                tests.append("编号    标题\n");
                while (rs.next()) {
                    sids.add(rs.getString("sid"));
                    tests.append(rs.getString("sid")+"    "+rs.getString("title")+"\n");
                }
                conn.close();
            }catch (Exception e2) {
                System.out.println(e2);
            }
            Test.setLocationRelativeTo(null);
            Test.setVisible(true);
            sure.addActionListener(e1 -> {
                //如果输入的不是正确编号
                if(!choice.getText().matches("[0-9]+")||!sids.contains(choice.getText())){
                    JOptionPane.showMessageDialog(null,"请输入正确编号");
                    return;
                }
                int sid=Integer.parseInt(choice.getText());
                new LianXi(sid,Integer.parseInt(id));
                Test.dispose();
            });
            JPanel panel = new JPanel( new FlowLayout());
            panel.add(choice);
            panel.add(sure,BorderLayout.SOUTH);
            tests.setEditable(false);
            Test.add(tests);
            Test.add(panel,BorderLayout.SOUTH);

        });
        examButton.addActionListener(e -> {
            new HistoryScore(Integer.parseInt(id));
        });


        frame.getContentPane().add(personalInfoPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonsPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
