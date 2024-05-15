package login;

import home.StudentHome;
import home.TeacherHome;

import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Actions implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {}
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";

        String command = e.getActionCommand();

        if (login.COMMAND_LOGIN.equals(command)) {

            String username= String.valueOf(login.username.getText());
            String pwd= String.valueOf(login.password.getPassword());
            //学生登陆
            try {
                Connection conn = DriverManager.getConnection(url,u,p);
                Statement stmt = conn.createStatement();
                String sql = "select * from user where id = '"+username+"' and password = '"+pwd+"'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    Opane.loginOK();
                    StudentHome studentHome = new StudentHome(rs.getString("name"), rs.getString("id"));
                    login.frame.dispose();
                }else {
                    Opane.loginFail();
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                Opane.serverError();
            }

        } else if (register.COMMAND_RREGISTER.equals(command)) {
            //注册
            if (Arrays.equals(register.password.getPassword(), register.conpassword.getPassword())){
                try {
                    Connection conn = DriverManager.getConnection(url,u,p);
                    Statement stmt = conn.createStatement();
                    String sql = "select * from user where id = '"+register.username.getText()+"'";
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()){
                        Opane.registerUserExist();
                    }else {
                        sql = "insert into user values('"+register.username.getText()+"','"+register.name.getText()+"','"+String.valueOf(register.password.getPassword())+"','0')";
                        stmt.executeUpdate(sql);
                        Opane.registerOK();
                        register.frame2.dispose();
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                    Opane.serverError();
                }
            }else {
                Opane.registerPwdWrong();
            }

        } else if (login.COMMAND_LOGIN2.equals(command)) {
            //教师登陆
            String username= String.valueOf(login.username.getText());
            String pwd= String.valueOf(login.password.getPassword());

            try {
                Connection conn = DriverManager.getConnection(url,u,p);
                Statement stmt = conn.createStatement();
                String sql = "select * from user where id = '"+username+"' and password = '"+pwd+"'and teacher = '1'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()){
                    Opane.loginOK2();
                    TeacherHome teacherHome = new TeacherHome(rs.getString("name"), rs.getString("id"));
                    login.frame.dispose();
                }else {
                    Opane.loginFail();
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
                Opane.serverError();
            }


        }else if(login.COMMAND_REGISTER.equals(command)){
            register.reg();
        }
    }
}
