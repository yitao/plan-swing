package com.simile.plan.swing.example.custom.layout;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class BoxLayoutDemo1 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        jf.setPreferredSize(new Dimension(350,200));
        jf.setSize(350, 200);


        // 创建一个垂直箱容器，放置上面两个水平箱（Box组合嵌套）
        Box vBox = Box.createVerticalBox();

        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel("lable" + i);
            label.setPreferredSize(new Dimension(350, 50));
//            label.setMinimumSize(new Dimension(350, 50));
//            label.setMaximumSize(new Dimension(350, 50));
            label.setBorder(new LineBorder(Color.GRAY));
            vBox.add(label);
        }

        JScrollPane container = new JScrollPane(vBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        container.setPreferredSize(new Dimension(350, 200));

        // 把垂直箱容器作为内容面板设置到窗口
        jf.setContentPane(container);

//        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);
    }
}
