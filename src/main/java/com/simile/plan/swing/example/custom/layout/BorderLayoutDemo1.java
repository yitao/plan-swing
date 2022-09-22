package com.simile.plan.swing.example.custom.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class BorderLayoutDemo1 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面包容器，指定使用 边界布局
        JPanel container = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setBorder(new LineBorder(Color.GRAY));
        leftPanel.setBorder(new LineBorder(Color.GRAY));
        centerPanel.setBorder(new LineBorder(Color.GRAY));
        rightPanel.setBorder(new LineBorder(Color.GRAY));
        bottomPanel.setBorder(new LineBorder(Color.GRAY));

        // 创建 5 个按钮
        topPanel.add(new JButton("topPanel"));
        leftPanel.add(new JButton("leftPanel"));
        centerPanel.add(new JButton("centerPanel"));
        rightPanel.add(new JButton("rightPanel"));
        bottomPanel.add(new JButton("bottomPanel"));



        // 把 5 个按钮添加到容器中的 5 个方位
        container.add(topPanel, BorderLayout.NORTH);
        container.add(bottomPanel, BorderLayout.SOUTH);
        container.add(leftPanel, BorderLayout.WEST);
        container.add(rightPanel, BorderLayout.EAST);
        container.add(centerPanel, BorderLayout.CENTER);

        jf.setContentPane(container);
//        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
