package view;

import controller.Closer;
import controller.LogonRegisterCtrl;
import style.StyleCtrl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOnFrm extends MyBootFrame {
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 550;
    private static final int WIDGET_X = 40;
    private static final int WIDGET_Y = 60;
    private static final int WIDGET_GAP = 60;
    private static final int FIELD_HEIGHT = 30;

    private JLabel[] errorLabels = new JLabel[] {
            new JLabel("请输入正确的姓名/学工号/邮箱"),
            new JLabel("密码错误"),
            new JLabel("请选择登陆身份"),
    };
    public LogOnFrm() {
        initComponents();
    }

    private void initComponents() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel jPanelSelectUserType = new JPanel();
        JComboBox<String> jComboBoxSelectUserType = new JComboBox<String>();
        // 登陆界面输入用户名字的label
        JLabel jLabelOfUserName = new JLabel();
        // 登陆界面输入用户密码的label
        JLabel jLabelOfPassword = new JLabel();

        JTextField jTextField = new JTextField();
        JPasswordField jPasswordField = new JPasswordField();
        JLabel jLabelOfUserType = new JLabel();
        JButton jButtonLogOn = new JButton();
        JButton jButtonRegister = new JButton();

    //todo：以下的路径都需要重新配图片
        jLabelOfUserName.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfUserName.setText("用户");

        jLabelOfPassword.setIcon(new ImageIcon("XXX")); // NOI18N
        jLabelOfPassword.setText("密码");

        jLabelOfUserType.setIcon(new ImageIcon("XXX"));
        jLabelOfUserType.setText("用户类型");
        jComboBoxSelectUserType.addItem("-请选择-");
        jComboBoxSelectUserType.addItem("访客");
        jComboBoxSelectUserType.addItem("管理员");

        jPanelSelectUserType.add(jLabelOfUserType);
        jPanelSelectUserType.add(jComboBoxSelectUserType);


        jButtonLogOn.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonLogOn.setText("登录");
        jButtonLogOn.addActionListener(evt -> {
            System.out.println("LogOnFrm : Click log on button");
            this.enWaitMode();
            LogonRegisterCtrl.timeoutWakeupTest(LogOnFrm.this);
        });

        jButtonRegister.setIcon(new ImageIcon("XXX")); // NOI18N
        jButtonRegister.setText("注册");
        jButtonRegister.addActionListener(evt -> {
            System.out.println("LogOnFrm : Click register button");
            LogonRegisterCtrl.changeLogToReg();
        });

        this.setTitle("用户登录");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);

        int y = WIDGET_Y;
        int fieldWidth = this.getWidth() - 2 * WIDGET_X - 3;

        Container container = this.getContentPane();
        addLabels(jLabelOfUserName,container,WIDGET_GAP);
        addLabels(jLabelOfPassword,container,2*WIDGET_GAP);
        addLabels(jLabelOfUserType,container,3*WIDGET_GAP);
        addContent(jTextField,container,fieldWidth,WIDGET_GAP+30);
        addContent(jPasswordField,container,fieldWidth,2*WIDGET_GAP+30);
        jComboBoxSelectUserType.setBounds(WIDGET_X-3,3*WIDGET_GAP+30,fieldWidth,FIELD_HEIGHT);
        this.add(jComboBoxSelectUserType);

        jButtonLogOn.setBounds(FRAME_WIDTH/2 + 5,4*WIDGET_GAP+30,fieldWidth-2*WIDGET_X-3, FIELD_HEIGHT);
        jButtonRegister.setBounds(WIDGET_X - 5,4*WIDGET_GAP+30,fieldWidth-2*WIDGET_X-3, FIELD_HEIGHT);
        this.add(jButtonRegister);
        this.add(jButtonLogOn);


        fieldWidth -= 5;
        for(JLabel label : errorLabels) {
            label.setBounds(WIDGET_X, y, fieldWidth, 30);
            label.setVerticalAlignment(JLabel.BOTTOM);
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setForeground(Color.RED);
            label.setVisible(false);
            container.add(label);
            y += WIDGET_GAP;
        }


    }

    private void addLabels(JLabel label, Container container, int y){
        label.setBounds(WIDGET_X, y, 100, 30);
        label.setVerticalAlignment(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.LEFT);
        container.add(label);
    }

    private void addContent(JTextField textField, Container container,int x,int y){
        textField.setBounds(WIDGET_X - 5, y, x, FIELD_HEIGHT);
        container.add(textField);
    }

    @Override
    public void enWaitMode() {
        this.setEnabled(false);
    }

    @Override
    public void disWaitMode() {
        this.setEnabled(true);
    }

    public static void main(String[] args) {
        StyleCtrl.setStyle(StyleCtrl.DARK);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogOnFrm().setVisible(true);
            }
        });
    }


}
