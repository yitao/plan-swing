package com.simile.plan.swing.example.custom.layout;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class CardLayoutDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(300, 200);

        // 创建卡片布局，卡片间水平和竖直间隔为 10
        final CardLayout layout = new CardLayout(10, 10);

        // 创建内容面板容器，指定布局管理器
        final JPanel panel = new JPanel(layout);

        JButton btn01 = new JButton("Button01");
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");

        panel.add(btn01, "btn01");
        panel.add(btn02, "btn02");
        panel.add(btn03, "btn03");

        // 先显示第二个
        layout.show(panel, "btn02");

        jf.setContentPane(panel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

        // 每间隔2秒切换显示下一个
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.next(panel);
            }
        }).start();
    }
}
