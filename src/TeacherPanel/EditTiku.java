package TeacherPanel;

import javax.swing.*;
import java.awt.*;
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        EditPanel.setLayout(new GridLayout(4, 4));
        EditPanel.add(new JLabel("题目ID"));
        EditPanel.add(EditID);
        EditPanel.add(new JLabel("题目"));
        EditPanel.add(EditQuestion);
        EditPanel.add(new JLabel("答案"));
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
        AddPanel.add(new JLabel("答案"));
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
            tiku.append("题目ID\t题目\t答案\t选项A\t选项B\t选项C\t选项D\n");
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

    public static void main(String[] args) {
        new EditTiku();
    }

}
