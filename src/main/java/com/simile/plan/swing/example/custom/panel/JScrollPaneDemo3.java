package com.simile.plan.swing.example.custom.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class JScrollPaneDemo3 {

    public static void main(String[] args) {
        FlatLightLaf.setup();
//        arc-theme-orange.theme.json
        String theme = "/arc_theme_dark.theme.json";
        IntelliJTheme.setup(JScrollPaneDemo3.class.getResourceAsStream(theme));

        JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 700);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout clayout = new GridLayout(1, 2);
        JPanel container = new JPanel(clayout);

        GridLayout layout = new GridLayout(0, 1);

        JPanel left = new JPanel(layout);
        JPanel right = new JPanel(layout);

        for (int i = 0; i < 10; i++) {
            // 创建文本区域组件
            JTextArea textArea = new JTextArea();
            textArea.setLineWrap(true);                         // 自动换行
            textArea.setFont(new Font(null, Font.PLAIN, 18));   // 设置字体

            textArea.setText(i + ":left");

            JPanel line = new JPanel();
            line.setPreferredSize(new Dimension(0, 40));
            line.add(textArea);
            line.setBorder(new LineBorder(Color.GRAY, 1));

            left.add(line);
        }

        //right 内部的元素会平分容器
        for (int i = 0; i < 20; i++) {
            // 创建文本区域组件
            JTextArea textArea = new JTextArea();
            textArea.setLineWrap(true);                         // 自动换行
            textArea.setFont(new Font(null, Font.PLAIN, 18));   // 设置字体

            textArea.setText(i + "");

            JPanel line = new JPanel();
            line.setPreferredSize(new Dimension(0, 40));
            line.add(textArea);
            line.setBorder(new LineBorder(Color.GRAY, 1));

            right.add(line);
        }


        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                left,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        JScrollPane scrollPane2 = new JScrollPane(
                right,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );
        container.add(scrollPane);
        container.add(scrollPane2);

        jf.setContentPane(container);
        jf.setVisible(true);
    }
}
