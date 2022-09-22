package com.simile.plan.swing.example.custom.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.simile.plan.swing.example.custom.component.Textarea;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class JScrollPaneDemo2 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 700);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        Box box = Box.createVerticalBox();
        GridLayout layout = new GridLayout(0, 1);
        JPanel box = new JPanel(layout);
        //box 内部的元素会平分容器

        for (int i = 0; i < 20; i++) {
            // 创建文本区域组件
            Textarea textArea = new Textarea();
            textArea.setLineWrap(true);                         // 自动换行
            textArea.setFont(new Font(null, Font.PLAIN, 18));   // 设置字体

            textArea.setText(i + "");

            JPanel line = new JPanel();
            line.setPreferredSize(new Dimension(0,40));
            line.add(textArea);
            line.setBorder(new LineBorder(Color.GRAY, 1));

            box.add(line);
        }


        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                box,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        jf.setContentPane(scrollPane);
        jf.setVisible(true);
    }
}
