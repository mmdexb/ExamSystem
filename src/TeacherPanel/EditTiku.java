package TeacherPanel;

import login.Opane;
import login.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.PushbackInputStream;
import java.sql.*;


public class EditTiku {
    public static JFrame frame = new JFrame("编辑题库");
    public JTextArea tiku = new JTextArea();

    public JPanel EditPanel=new JPanel();
    public JTextField EditID=new JTextField(20);
    public JTextField EditQuestion=new JTextField(20);
    public JTextField EditAnswer=new JTextField(20);
    public JTextField EditOptionA=new JTextField(20);
    public JTextField EditOptionB=new JTextField(20);
    public JTextField EditOptionC=new JTextField(20);
    public JTextField EditOptionD=new JTextField(20);
    public JButton EditButton=new JButton("提交修改");


    public JPanel DelPanel=new JPanel();
    public JTextField DelID=new JTextField(20);
    public JButton DelButton=new JButton("删除题目");

    public JPanel AddPanel=new JPanel();
    public JTextField AddQuestion=new JTextField(20);
    public JTextField AddAnswer=new JTextField(20);
    public JTextField AddOptionA=new JTextField(20);
    public JTextField AddOptionB=new JTextField(20);
    public JTextField AddOptionC=new JTextField(20);
    public JTextField AddOptionD=new JTextField(20);
    public JButton AddButton=new JButton("添加题目");



    public EditTiku(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        EditPanel.setLayout(new GridLayout(4, 4));
        EditPanel.add(new JLabel("题目ID"));
        EditPanel.add(EditID);
        EditPanel.add(new JLabel("题目"));
        EditPanel.add(EditQuestion);
        EditPanel.add(new JLabel("答案选项"));
        EditPanel.add(EditAnswer);
        EditPanel.add(new JLabel("选项A"));
        EditPanel.add(EditOptionA);
        EditPanel.add(new JLabel("选项B"));
        EditPanel.add(EditOptionB);
        EditPanel.add(new JLabel("选项C"));
        EditPanel.add(EditOptionC);
        EditPanel.add(new JLabel("选项D"));
        EditPanel.add(EditOptionD);
        EditPanel.add(new JLabel(""));
        EditPanel.add(EditButton);

        DelPanel.setLayout(new GridLayout(13,1));
        DelPanel.add(new JLabel("题目ID"));
        DelPanel.add(DelID);
        DelPanel.add(DelButton);
        DelPanel.add(new JLabel(""));
        DelPanel.add(new JLabel(""));
        DelPanel.add(new JLabel(""));
        DelPanel.add(new JLabel(""));
        DelPanel.add(new JLabel(""));
        DelPanel.add(new JLabel(""));

        AddPanel.setLayout(new GridLayout(13,1));
        AddPanel.add(new JLabel("题目"));
        AddPanel.add(AddQuestion);
        AddPanel.add(new JLabel("答案选项"));
        AddPanel.add(AddAnswer);
        AddPanel.add(new JLabel("选项A"));
        AddPanel.add(AddOptionA);
        AddPanel.add(new JLabel("选项B"));
        AddPanel.add(AddOptionB);
        AddPanel.add(new JLabel("选项C"));
        AddPanel.add(AddOptionC);
        AddPanel.add(new JLabel("选项D"));
        AddPanel.add(AddOptionD);
        AddPanel.add(AddButton);

        frame.add(EditPanel, BorderLayout.SOUTH);
        frame.add(DelPanel, BorderLayout.WEST);
        frame.add(AddPanel, BorderLayout.EAST);
        frame.add(tiku, BorderLayout.CENTER);

        frame.setVisible(true);
        Refresh();

        EditButton.addActionListener(e -> PushEdit());
        DelButton.addActionListener(e -> PushDel());
        AddButton.addActionListener(e -> PushAdd());


    }

    private void PushAdd() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";

        try {
            Connection conn = DriverManager.getConnection(url, u, p);
            Statement stmt = conn.createStatement();
            String sql = "insert into tiku(title,answer,A,B,C,D) values('" + AddQuestion.getText() + "','" + AddAnswer.getText() + "','" + AddOptionA.getText() + "','" + AddOptionB.getText() + "','" + AddOptionC.getText() + "','" + AddOptionD.getText() + "')";
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(EditTiku.frame,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
            Refresh();
            //清空输入框
            AddQuestion.setText("");
            AddAnswer.setText("");
            AddOptionA.setText("");
            AddOptionB.setText("");
            AddOptionC.setText("");
            AddOptionD.setText("");

        }catch (Exception e){
            //提示添加失败
            JOptionPane.showMessageDialog(EditTiku.frame,e,"提示",JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private void PushDel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";
        try {
            Connection conn = DriverManager.getConnection(url, u, p);
            Statement stmt = conn.createStatement();
            String sql = "delete from tiku where tid=" + DelID.getText();
            int rows= stmt.executeUpdate(sql);
            if(DelID.getText().equals("")){
                JOptionPane.showMessageDialog(EditTiku.frame,"请输入题目ID","提示",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(rows==0){
                JOptionPane.showMessageDialog(EditTiku.frame,"没有此题目","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(EditTiku.frame,"删除成功","提示",JOptionPane.INFORMATION_MESSAGE);
            Refresh();
            DelID.setText("");

        }catch (Exception e){
            JOptionPane.showMessageDialog(EditTiku.frame,e,"提示",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void PushEdit() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";
        String existingTitle="";
        String existingAnswer="";
        String existingA="";
        String existingB="";
        String existingC="";
        String existingD="";

        try {
            Connection conn = DriverManager.getConnection(url, u, p);
            String sql="select title,A,B,C,D,answer from tiku where tid="+EditID.getText();
            Statement stmt = conn.createStatement();
            if(EditID.getText().equals("")){
                JOptionPane.showMessageDialog(EditTiku.frame,"请输入题目ID","提示",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(EditTiku.frame,"没有此题目","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            //如果没有查询到则报错
            while (rs.next()){
                existingTitle=rs.getString("title");
                existingAnswer=rs.getString("answer");
                existingA=rs.getString("A");
                existingB=rs.getString("B");
                existingC=rs.getString("C");
                existingD=rs.getString("D");
            }

            if(!EditQuestion.getText().equals("")){
                existingTitle=EditQuestion.getText();
            }
            if(!EditAnswer.getText().equals("")){
                existingAnswer=EditAnswer.getText();
            }
            if(!EditOptionA.getText().equals("")){
                existingA=EditOptionA.getText();
            }
            if(!EditOptionB.getText().equals("")){
                existingB=EditOptionB.getText();
            }
            if(!EditOptionC.getText().equals("")){
                existingC=EditOptionC.getText();
            }
            if(!EditOptionD.getText().equals("")){
                existingD=EditOptionD.getText();
            }
            sql="update tiku set title='"+existingTitle+"',answer='"+existingAnswer+"',A='"+existingA+"',B='"+existingB+"',C='"+existingC+"',D='"+existingD+"' where tid="+EditID.getText();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(EditTiku.frame,"修改成功","提示",JOptionPane.INFORMATION_MESSAGE);
            EditID.setText("");
            EditQuestion.setText("");
            EditAnswer.setText("");
            EditOptionA.setText("");
            EditOptionB.setText("");
            EditOptionC.setText("");
            EditOptionD.setText("");

            Refresh();
        }catch (Exception e){
            JOptionPane.showMessageDialog(EditTiku.frame,e,"提示",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void Refresh() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {}
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";

        try{
            Connection conn = DriverManager.getConnection(url,u,p);
            Statement stmt = conn.createStatement();
            String sql = "select * from tiku";
            ResultSet rs = stmt.executeQuery(sql);
            tiku.setText("");
            tiku.append("题目ID\t题目\t答案选项\t选项A\t选项B\t选项C\t选项D\n");
            while (rs.next()){
                tiku.append(rs.getString("tid") + "\t" + rs.getString("title") + "\t" + rs.getString("answer") + "\t" + rs.getString("A") + "\t" + rs.getString("B") + "\t" + rs.getString("C") + "\t" + rs.getString("D") + "\n");
            }
            rs.close();
            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e);
        }

    }


}
