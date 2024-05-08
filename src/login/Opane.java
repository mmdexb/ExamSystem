package login;

import javax.swing.*;

public class Opane {
    public static void registerOK(){
        JOptionPane.showMessageDialog(register.frame2,"注册成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }
    public static void registerPwdWrong(){
        JOptionPane.showMessageDialog(register.frame2,"两次密码不一致","提示",JOptionPane.INFORMATION_MESSAGE);
    }
    public static void registerUserExist(){
        JOptionPane.showMessageDialog(register.frame2,"用户名已存在","提示",JOptionPane.INFORMATION_MESSAGE);
    }
    public static void loginOK(){
        JOptionPane.showMessageDialog(login.frame,"登陆成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void loginOK2() {
        JOptionPane.showMessageDialog(login.frame,"教师登陆成功","提示",JOptionPane.INFORMATION_MESSAGE);
    }
}
