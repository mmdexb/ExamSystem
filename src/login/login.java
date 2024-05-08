package login;

import java.awt.FlowLayout;
import javax.swing.*;

//登陆界面
public class login {
    public static final String COMMAND_LOGIN ="LoginBut" ;
    public static final String COMMAND_REGISTER ="RegisterBut" ;
    public static final String COMMAND_LOGIN2 ="LoginBut2" ;
    public static JFrame frame=new JFrame("登陆页面");
    public static JLabel label1=new JLabel("用户名");
    public static JTextField username=new JTextField(20);
    public static JLabel label2=new JLabel("密 码");
    public static JPasswordField password=new JPasswordField(20);
    public static JButton LoginBut=new JButton("学生登陆");
    public static JButton LoginBut2=new JButton("教师登陆");
    public static JButton RegisterBut=new JButton("注册");

    public login(){
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initFrame();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initFrame() {
        JPanel panel01=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(label1);
        username.setDocument(new TxDocument(10));
        panel01.add(username);

        JPanel panel02=new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel02.add(label2);
        password.setDocument(new TxDocument(20));
        panel02.add(password);

        JPanel panel03=new JPanel(new FlowLayout(FlowLayout.CENTER));

        LoginBut.setActionCommand(COMMAND_LOGIN);
        LoginBut.addActionListener(new Actions());
        panel03.add(LoginBut);

        LoginBut2.setActionCommand(COMMAND_LOGIN2);
        LoginBut2.addActionListener(new Actions());
        panel03.add(RegisterBut);

        RegisterBut.setActionCommand(COMMAND_REGISTER);
        RegisterBut.addActionListener(new Actions());
        panel03.add(LoginBut2);

        Box vbox=Box.createVerticalBox();
        vbox.add(panel01);
        vbox.add(panel02);
        vbox.add(panel03);

        frame.setContentPane(vbox);
    }

    public static void main(String[] args) {
        new login();
    }


}
