package com.simile.plan.swing.example.custom.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import com.simile.plan.swing.example.custom.component.Textarea;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class JScrollPaneDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(250, 250);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建文本区域组件
        Textarea textArea = new Textarea();
        textArea.setLineWrap(true);                         // 自动换行
        textArea.setFont(new Font(null, Font.PLAIN, 18));   // 设置字体

        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                textArea,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        jf.setContentPane(scrollPane);
        jf.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                textArea.setWrapStyleWord(true);
                textArea.getGraphics().setColor(Color.BLACK);
                textArea.setForeground(Color.BLACK);
                textArea.append("this is a ");
                textArea.setForeground(Color.RED);
                textArea.getGraphics().setColor(Color.RED);
                textArea.append("red");
//                textArea.getGraphics().setColor(Color.BLACK);
//                textArea.setForeground(Color.BLACK);
            }
        }).start();

    }
}
