package com.simile.plan.swing.app.ide;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @Author yitao
 * @Created 2022/11/10
 */
public class Main {

    public static void main(String[] args) {
        JFrame jf = new JFrame("代码编辑");
        Dimension screenSizeInfo = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setSize(screenSizeInfo.width, screenSizeInfo.height);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        CodeEditor codeEditor = new CodeEditor(jf);
//        jf.setContentPane();
        jf.setVisible(true);
    }
}
