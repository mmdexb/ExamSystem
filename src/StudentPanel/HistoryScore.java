package StudentPanel;

import javax.swing.*;
import java.sql.*;

public class HistoryScore {
    JFrame frame = new JFrame("历史成绩");
    JTextArea textArea = new JTextArea();

    public HistoryScore(int id)
    {
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        textArea.setBounds(0, 0, 500, 500);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        String url="jdbc:mysql://demo.linuschen.ink:3306/exam";
        String u="exam";
        String p="1qaz2wsx";
        try {
            Connection conn= DriverManager.getConnection(url,u,p);
            Statement stmt=conn.createStatement();
            String sql="SELECT h.time AS UseTime, h.score AS UserScore, h.sid,\n" +
                    "       sj.title, sj.content, sj.score, sj.time\n" +
                    "FROM history h\n" +
                    "         JOIN shijuan sj ON sj.sid = h.sid\n" +
                    "WHERE h.id ="+id+";";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()){
                String content=rs.getString("content");
                //分隔
                String[] content1=content.split(",");
                double score=content1.length*rs.getDouble("score");

                textArea.append("试卷标题："+rs.getString("title")+"\n");
                textArea.append("考试成绩："+rs.getString("UserScore")+"(满分"+ score +")\n");
                textArea.append("考试用时："+rs.getString("UseTime")+"秒\n");
                textArea.append("***************************************\n");

            }
        }catch (Exception e){
            System.out.println(e);
        }
        frame.add(textArea);
        frame.setVisible(true);
        textArea.setEditable(false);



    }
}
