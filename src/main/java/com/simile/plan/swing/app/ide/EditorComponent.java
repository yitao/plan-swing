package com.simile.plan.swing.app.ide;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.Element;

/**
 * @Author yitao
 * @Created 2022/11/07
 */
public class EditorComponent implements DocumentListener {
    private JComponent parent;
    private JScrollPane scroll;
    private JEditorPaneCus textArea;
    private JTextArea lineArea;

    public EditorComponent(JComponent parent) {
        this.parent = parent;
        init();
    }

    public void init() {
        textArea = new JEditorPaneCus();

        lineArea = new JTextArea(0, 3);
        lineArea.setEditable(false);
        lineArea.setForeground(Color.GRAY);

        scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        textArea.getDocument().addDocumentListener(this);
        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                //替换制表符
                text = text.replace("\t", "    ");
                super.replace(fb, offset, length, text, attrs);
            }
        });

        scroll.setViewportView(textArea);
        scroll.setRowHeaderView(lineArea);
    }

    public void show() {
        parent.add(scroll);
    }

    public void hide() {
        parent.remove(scroll);
    }

    public JComponent getRoot() {
        return scroll;
    }

    public void setContent(String content) {
        textArea.setText(content);
    }

    public JEditorPaneCus getTextArea() {
        return textArea;
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
