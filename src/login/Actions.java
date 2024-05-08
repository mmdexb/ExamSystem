package login;

import home.StudentHome;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Actions implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (login.COMMAND_LOGIN.equals(command)) {
            //学生登陆
            Opane.loginOK();
            StudentHome studentHome = new StudentHome("张三", "85");
            login.frame.dispose();


        } else if (register.COMMAND_RREGISTER.equals(command)) {
            //注册
            if (Arrays.equals(login.password.getPassword(), register.conpassword.getPassword())){
                Opane.registerOK();
            }else {
                Opane.registerPwdWrong();
            }

        } else if (login.COMMAND_LOGIN2.equals(command)) {
            //教师登陆
            Opane.loginOK2();

        }else if(login.COMMAND_REGISTER.equals(command)){
            register.reg();
        }
    }
}
