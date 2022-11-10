package com.simile.plan.swing.example.custom.other;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

/**
 * @Author yitao
 * @Created 2022/11/03
 */
public class LineNumberEditorDemo extends JFrame implements DocumentListener {
    private JScrollPane scroll;
    private JTextArea textArea, lineArea;

    public static void main(String[] args) {

        new LineNumberEditorDemo().setVisible(true);

    }

    public LineNumberEditorDemo() {
        super("Line Numbers");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setUI();
    }

    private void setUI() {

        textArea = new JTextArea();

        lineArea = new JTextArea(0, 3);
        lineArea.setEditable(false);
        lineArea.setForeground(Color.GRAY);

        scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        textArea.getDocument().addDocumentListener(this);

        scroll.setViewportView(textArea);
        scroll.setRowHeaderView(lineArea);
        getContentPane().add(scroll, BorderLayout.CENTER);

    }

    public void changedUpdate(DocumentEvent event) {

        lineArea.setFont(textArea.getFont());
        lineArea.setText(getLine());

    }

    public void insertUpdate(DocumentEvent event) {

        lineArea.setFont(textArea.getFont());
        lineArea.setText(getLine());
    }

    public void removeUpdate(DocumentEvent event) {

        lineArea.setFont(textArea.getFont());
        lineArea.setText(getLine());
    }

    public String getLine() {

        int caretPos = 0;
        String lines;

        caretPos = textArea.getDocument().getLength();
        Element root = textArea.getDocument().getDefaultRootElement();
        lines = String.format("%s%s", 1, System.lineSeparator());

        for (int i = 2; i < root.getElementIndex(caretPos) + 2; i++) {
            lines += String.format("%s%s", i, System.lineSeparator());

        }

        return lines;

    }

}
