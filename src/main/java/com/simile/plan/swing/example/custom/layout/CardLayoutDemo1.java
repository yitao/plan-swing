package com.simile.plan.swing.example.custom.layout;

import java.awt.CardLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class CardLayoutDemo1 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(300, 200);

        // 创建卡片布局，卡片间水平和竖直间隔为 10
        final CardLayout layout = new CardLayout(10, 10);

        // 创建内容面板容器，指定布局管理器
        final JPanel panel = new JPanel(layout);

        for (int i = 0; i < 3; i++) {
            JPanel panel1 = new JPanel();
            panel1.add(new TextArea("xxxx" + i, 30, 40));
            JScrollPane scrollPane = new JScrollPane(panel1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panel.add(scrollPane, "" + i);
        }

        // 先显示第二个
        layout.show(panel, "2");

        jf.setContentPane(panel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

//         每间隔2秒切换显示下一个
        new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.next(panel);
            }
        }).start();
    }
}
