package login;

import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.*;

public class register {
    public static final String COMMAND_RREGISTER = "rregister";

    public static JFrame frame2 = new JFrame("注册界面");
    public static JLabel label1 = new JLabel("学 号");
    public static JTextField username = new JTextField(20);
    public static JLabel label2 = new JLabel("密 码");
    public static JPasswordField password = new JPasswordField(20);
    public static JLabel label3 = new JLabel("确认密码");
    public static JLabel label4 =new JLabel("姓名");
    public static JTextField name = new JTextField(20);
    public static JPasswordField conpassword = new JPasswordField(20);
    public static JButton rregisterbtn = new JButton("注册");

    public register() {

        //设置窗口大小
        frame2.setSize(350, 200);
        //设置按下右上角X号后当前页面
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //调用函数初始化窗体的组件
        initFrame2();
        //新窗口与旧窗口错开50像素。
        frame2.setBounds(
                new Rectangle(
                        (int) login.frame.getBounds().getX()+ 50,
                        (int) login.frame.getBounds().getY() + 50,
                        (int) login.frame.getBounds().getWidth(),
                        (int) login.frame.getBounds().getHeight()
                ));
        //窗口可见
        frame2.setVisible(true);
    }

    public void initFrame2() {

        JPanel panel11 = new JPanel();
        panel11.add(label1);
        panel11.add(username);

        JPanel panel15 = new JPanel();
        panel15.add(label4);
        panel15.add(name);

        JPanel panel12 = new JPanel();
        panel12.add(label2);
        panel12.add(password);

        JPanel panel13 = new JPanel();
        panel13.add(label3);
        conpassword.setDocument(new TxDocument(15));
        panel13.add(conpassword);

        JPanel panel14 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rregisterbtn.setActionCommand(COMMAND_RREGISTER);
        rregisterbtn.addActionListener(new Actions());
        panel14.add(rregisterbtn);

        Box vBox = Box.createVerticalBox();
        vBox.add(panel11);
        vBox.add(panel15);
        vBox.add(panel12);
        vBox.add(panel13);
        vBox.add(panel14);

        frame2.setContentPane(vBox);
    }
    public static void reg() {
        new register();
    }

}