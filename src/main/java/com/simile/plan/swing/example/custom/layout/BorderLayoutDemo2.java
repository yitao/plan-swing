package com.simile.plan.swing.example.custom.layout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class BorderLayoutDemo2 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面包容器，指定使用 边界布局
        JPanel container = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JSplitPane centerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
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
//        centerPanel.add(new JButton("centerPanel"));
        rightPanel.add(new JButton("rightPanel"));
        bottomPanel.add(new JButton("bottomPanel"));

        JSplitPane ctop = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        // 设置左右两边显示的组件
        ctop.setLeftComponent(new JTextArea("左边按钮"));
        ctop.setRightComponent(new JTextArea("右边按钮"));

        JPanel cbot = new JPanel();
        cbot.add(new JTextArea("----bottom------"));

        // 分隔条上显示快速 折叠/展开 两边组件的小按钮
        centerPanel.setOneTouchExpandable(true);

        // 拖动分隔条时连续重绘组件
        centerPanel.setContinuousLayout(true);

        // 设置分隔条的初始位置
        centerPanel.setDividerLocation(150);

        centerPanel.setTopComponent(ctop);
        centerPanel.setBottomComponent(cbot);


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
