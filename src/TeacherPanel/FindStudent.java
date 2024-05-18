package TeacherPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class FindStudent {
    static JFrame frame = new JFrame("查找学生");
    JTextField FindID=new JTextField(20);
    JButton FindButton=new JButton("查找");
    JTextArea FindResult = new JTextArea(20,40);
    JScrollPane scrollpane = new JScrollPane(FindResult);
    public FindStudent()
    {
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel(new GridLayout(0,3));
        panel.add(new JLabel("学号"));
        panel.add(FindID);
        panel.add(FindButton);

        JPanel panel2 = new JPanel();
        panel2.add(scrollpane);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);

        FindButton.addActionListener(e ->Submit());

    }

    private void Submit() {
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
            String sql = "select * from user where id='"+FindID.getText()+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                String name = rs.getString("name");
                String uid = rs.getString("id");
                FindResult.setText("学号："+rs.getString("id")+"\n姓名："+rs.getString("name")+"\n");
                FindResult.append("-----------成绩情况----------\n");
                String sid = "";
                String time="";
                String score="";
                String stitle = "";
                String sscore = "";
                String sstime="";
                String sql2 = "SELECT s.*, h.time AS Usertime, h.score AS UserScore\n" +
                        "FROM shijuan s\n" +
                        "         JOIN history h ON s.sid = h.sid\n" +
                        "WHERE h.id = " + uid+";";
                ResultSet rs2 = stmt.executeQuery(sql2);
                while (rs2.next()){
                    sid = rs2.getString("sid");
                    time = rs2.getString("time");
                    score = rs2.getString("score");
                    stitle = rs2.getString("title");
                    sscore = rs2.getString("UserScore");
                    sstime = rs2.getString("Usertime");
                    FindResult.append("试卷编号："+sid+"\n");
                    FindResult.append("试卷标题："+stitle+"\n");
                    FindResult.append("限制考试时间："+time+"\n");
                    FindResult.append("学生考试用时："+sstime+"\n");
                    FindResult.append("试卷总分："+score+"\n");
                    FindResult.append("考试成绩："+sscore+"\n");
                    FindResult.append("----------------------------\n");

                }

            }else{
                JOptionPane.showMessageDialog(null,"未找到该学生");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
