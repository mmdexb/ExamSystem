package TeacherPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddStudent {
    public static JFrame frame = new JFrame("添加学生");
    JTextField StuID = new JTextField(20);
    JTextField StuName = new JTextField(20);
    JTextField StuPwd = new JTextField(20);
    JButton AddButton = new JButton("添加学生");
    JTextArea stus = new JTextArea(20,10);
    public AddStudent()
    {
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("学号"));
        panel1.add(StuID);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("姓名"));
        panel2.add(StuName);

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("密码"));
        panel3.add(StuPwd);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel4.add(AddButton);

        Box VBOX=Box.createVerticalBox();
        VBOX.add(panel1);
        VBOX.add(panel2);
        VBOX.add(panel3);
        VBOX.add(panel4);


        frame.add(VBOX);
        frame.add(stus,BorderLayout.SOUTH);


        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        createTable();

        AddButton.addActionListener(e -> {
            if(StuID.getText().equals("")||StuName.getText().equals("")||StuPwd.getText().equals("")){
                JOptionPane.showMessageDialog(null,"请输入完整信息");
            }else{
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
                String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
                String u="exam";
                String p="1qaz2wsx";
                try {Connection conn = DriverManager.getConnection(url,u,p);
                    Statement stmt = conn.createStatement();
                    String sql = "insert into user(id,name,password,teacher) values('"+StuID.getText()+"','"+StuName.getText()+"','"+StuPwd.getText()+"','0')";
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"添加失败");
                }
                stus.setText("");
                createTable();
            }
        });



    }

    public void createTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";
        try {Connection conn = DriverManager.getConnection(url,u,p);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM user where teacher = 0";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                stus.append("学号："+rs.getString("id")+"   姓名："+rs.getString("name")+"   密码："+rs.getString("password")+"\n\n");

            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static void main(String[] args){
        new AddStudent();
    }

}
