package TeacherPanel;

import pojo.Question;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AddShiJuan {
    JFrame frame = new JFrame("制作试卷");
    JTextField title = new JTextField(20);
    JTextField score=new JTextField(20);
    JTextField content = new JTextField(20);
    JTextField tid=new JTextField(5);
    JButton add = new JButton("添加到试卷");
    JButton del=new JButton("从试卷中删除");
    Panel ShiJuanInfo = new Panel();
    Panel ShiJuanContent = new Panel();
    JTextArea shijuan = new JTextArea(10, 20);
    Panel Tiku=new Panel();
    JTextArea tikus=new JTextArea(10,20);
    JScrollPane scrollpane=new JScrollPane();
    JScrollPane scrollpane2=new JScrollPane();
    JButton submit = new JButton("提交试卷");
    static ArrayList<Question> List=new ArrayList<>();

    public AddShiJuan() {
        frame.setSize(1000, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ShiJuanInfo.setLayout(new BoxLayout(ShiJuanInfo, BoxLayout.Y_AXIS));
        ShiJuanInfo.add(new JLabel("试卷标题"));
        ShiJuanInfo.add(title);
        ShiJuanInfo.add(new JLabel("单题分数"));
        ShiJuanInfo.add(score);
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(new JLabel(" "));
        ShiJuanInfo.add(submit);


        ShiJuanContent.setLayout(new BoxLayout(ShiJuanContent, BoxLayout.Y_AXIS));
        ShiJuanContent.add(new JLabel("输入题目ID"));
        ShiJuanContent.add(tid);
        ShiJuanContent.add(add);
        ShiJuanContent.add(del);
        ShiJuanContent.add(new JLabel("试卷内容"));
        shijuan.setEditable(false);
        scrollpane.setViewportView(shijuan);
        ShiJuanContent.add(scrollpane);

        add.addActionListener(e -> addQusetion());
        del.addActionListener(e-> delQusetion());

        Tiku.setLayout(new BoxLayout(Tiku, BoxLayout.Y_AXIS));
        Tiku.add(new JLabel("题库"));
        tikus.setEditable(false);
        scrollpane2.setViewportView(tikus);
        Tiku.add(scrollpane2);

        frame.setLayout(new BorderLayout());
        frame.add(ShiJuanInfo, BorderLayout.WEST);
        frame.add(ShiJuanContent, BorderLayout.EAST);
        frame.add(Tiku,BorderLayout.CENTER);

        frame.setVisible(true);
        RefrshTiku();
    }

    private void delQusetion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {}
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";

        try{
            Connection conn = DriverManager.getConnection(url,u,p);
            Statement stmt = conn.createStatement();
            System.out.println(tid.getText());
            String sql = "select * from tiku where tid=" + tid.getText();
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(EditTiku.frame,"没有此题目","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (rs.next()){
                shijuan.setText("");
                List.removeIf(q -> q.getTid() == Integer.parseInt(tid.getText()));
                for(Question q:List){
                    shijuan.append(q.getTid() + "\t" + q.getTitle() + "\t" + q.getAnswer() + "\t" + q.getA() + "\t" + q.getB() + "\t" + q.getC() + "\t" + q.getD() + "\n");
                }
                tid.setText("");
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(EditTiku.frame,"删除失败","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addQusetion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {}
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";

        try{
            Connection conn = DriverManager.getConnection(url,u,p);
            Statement stmt = conn.createStatement();
            String sql = "select * from tiku where tid=" + tid.getText();
            ResultSet rs = stmt.executeQuery(sql);
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(EditTiku.frame,"没有此题目","错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (rs.next()){
                shijuan.append(rs.getString("tid") + "\t" + rs.getString("title") + "\t" + rs.getString("answer") + "\t" + rs.getString("A") + "\t" + rs.getString("B") + "\t" + rs.getString("C") + "\t" + rs.getString("D") + "\n");
                List.add(new Question(rs.getString("title"),rs.getString("answer"),rs.getString("D"),rs.getString("C"),rs.getString("A"),rs.getString("B"),rs.getInt("tid")));
            }
            rs.close();
            stmt.close();
            conn.close();
            tid.setText("");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void RefrshTiku() {
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
            tikus.setText("");
            tikus.append("题目ID\t题目\t答案\t选项A\t选项B\t选项C\t选项D\n");
            while (rs.next()){
                tikus.append(rs.getString("tid") + "\t" + rs.getString("title") + "\t" + rs.getString("answer") + "\t" + rs.getString("A") + "\t" + rs.getString("B") + "\t" + rs.getString("C") + "\t" + rs.getString("D") + "\n");
            }
            rs.close();
            stmt.close();
            conn.close();

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new AddShiJuan();
    }


}
