package com.simile.plan.swing.example.custom.other;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.PlainView;
import javax.swing.text.Segment;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.Utilities;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * @Author yitao
 * @Created 2022/11/03
 */
public class TextColorEditorDemo extends JFrame {
    private JEditorPane editorPane;
    private String[] keywords = {"private", "public", "class"};

    private StringBuffer sb = new StringBuffer();

    private boolean isKeyword(String kw) {
        return "private".equals(kw) || "public".equals(kw) || "class".equals(kw);
    }

    private boolean isSplit(char ch) {
        return '\r' == ch || '\n' == ch || '\t' == ch || ' ' == ch
                || ';' == ch
                || '[' == ch
                || ']' == ch
                || '{' == ch
                || '}' == ch
                || '(' == ch
                || ')' == ch
                || '<' == ch
                || '>' == ch;
    }

    public static void main(String[] args) {
        new TextColorEditorDemo().setVisible(true);
    }


//    @Override
//    public void insertUpdate(DocumentEvent e) {
//        Segment s = new Segment();
//        try {
//            e.getDocument().getText(e.getOffset(), e.getLength(), s);
//        } catch (BadLocationException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public void removeUpdate(DocumentEvent e) {
//
//    }
//
//    @Override
//    public void changedUpdate(DocumentEvent e) {
//
//    }

    public TextColorEditorDemo() {
        super("TextColorEditorDemo");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setUI();
    }

    private void setUI() {
        editorPane = new JEditorPaneCus();
//        editorPane.setText("String name;");
//        editorPane.setText("private String name;");
        editorPane.setText("class private String name;");
        editorPane.setToolTipText("请输入");

        ((AbstractDocument) editorPane.getDocument()).setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                //替换制表符
                text = text.replace("\t", "    ");
                super.replace(fb, offset, length, text, attrs);
            }
        });

        this.add(editorPane);
    }

    class JEditorPaneCus extends JEditorPane {
        @Override
        protected EditorKit createDefaultEditorKit() {
            return new StyledEditorKitCus();
        }
    }

    class StyledEditorKitCus extends StyledEditorKit {

        @Override
        public Document createDefaultDocument() {
            return super.createDefaultDocument();
        }

        @Override
        public ViewFactory getViewFactory() {
            return new ViewFactoryCus();
        }
    }

    class ViewFactoryCus implements ViewFactory {
        @Override
        public View create(Element elem) {
            return new JavaEditorView(elem);
        }
    }

    class JavaEditorView extends PlainView {
        public JavaEditorView(Element elem) {
            super(elem);
        }

        @Override
        protected void drawLine(int lineIndex, Graphics g, int x, int y) {
            Element line = getElement().getElement(lineIndex);
            Element elem;


            try {
                if (line.isLeaf()) {
                    drawElement(lineIndex, line, g, x, y);
                } else {
                    // this line contains the composed text.
                    int count = line.getElementCount();
                    for (int i = 0; i < count; i++) {
                        elem = line.getElement(i);
                        x = drawElement(lineIndex, elem, g, x, y);
                    }
                }
            } catch (BadLocationException e) {
//                throw new StateInvariantError("Can't render line: " + lineIndex);
            }
//            super.drawLine(lineIndex, g, x, y);
        }

        private int drawElement(int lineIndex, Element elem, Graphics g, int x, int y) throws BadLocationException {
            int p0 = elem.getStartOffset();
            int p1 = elem.getEndOffset();
            p1 = Math.min(getDocument().getLength(), p1);
            return drawSelectedText(g, x, y, p0, p1);
        }

        @Override
        protected int drawSelectedText(Graphics g, int x, int y, int startOffset, int endOffset) throws BadLocationException {
//            return super.drawSelectedText(g, x, y, startOffset, endOffset);
            return full(g, x, y, startOffset, endOffset);
        }

        @Override
        protected int drawUnselectedText(Graphics g, int x, int y, int startOffset, int endOffset) throws BadLocationException {
            int docLength = getDocument().getLength();
            int length = (endOffset < docLength ? endOffset : docLength) - startOffset;
//            return scanParagraph(g, x, y, startOffset, length);
//            return super.drawUnselectedText(g, x, y, startOffset, endOffset);
            return full(g, x, y, startOffset, endOffset);
        }

        private int full(Graphics g, int x, int y, int startOffset, int endOffset) throws BadLocationException {
//            g.setColor(Color.RED);
//            Document doc = getDocument();
//            Segment s = new Segment();
//            doc.getText(startOffset, endOffset - startOffset, s);
//            int ret = Utilities.drawTabbedText(s, x, y, g, this, startOffset);
//            return ret;
//            int length = endOffset - startOffset;
//            return scanParagraph(g, x, y, startOffset, length);
            return fullCore(g, x, y, startOffset, endOffset);
        }

        private int fullCore(Graphics g, int x, int y, int startOffset, int endOffset) throws BadLocationException {
            Segment seg = new Segment();
//            x = 0;
//            startOffset = 0;
//            endOffset = getDocument().getLength();
//            System.out.println(x + ":" + startOffset);
            getDocument().getText(startOffset, endOffset - startOffset, seg);
//            System.out.println(seg.toString());
            int xCursor = x;
            //已经处理到的字符偏移位置
            int offset = startOffset;
            for (int i = 0; i < seg.length(); i++) {
                char ch = seg.charAt(i);
                if (ch == '\t') {

                }
                if (isSplit(ch)) {
                    //检查是否是关键词
                    String word = sb.toString();
                    Segment s = getLineBuffer();
                    getDocument().getText(offset, word.length(), s);
                    if (isKeyword(word)) {
                        g.setColor(Color.RED);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    xCursor = Utilities.drawTabbedText(s, xCursor, y, g, this, offset);
                    offset += word.length();
                    sb.setLength(0);
                    //处理分隔符
                    s = getLineBuffer();
                    getDocument().getText(offset, 1, s);
                    g.setColor(Color.BLACK);
                    xCursor = Utilities.drawTabbedText(s, xCursor, y, g, this, offset);
                    offset += 1;
                } else {
                    sb.append(ch);
                }

            }
            if (sb.length() != 0) {
                String word = sb.toString();
                Segment s = getLineBuffer();
                getDocument().getText(offset, word.length(), s);
                if (isKeyword(word)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                xCursor = Utilities.drawTabbedText(s, xCursor, y, g, this, offset);
                offset += word.length();
                sb.setLength(0);
            }
            return xCursor;
//            return Utilities.drawTabbedText(s, x, y, g, this, startOffset);
        }

        private int scanParagraph(Graphics g, int x, int y, int startOffset, int length) throws BadLocationException {
            Segment seg = new Segment();
            //得到编辑器组件
//            JavaCodeEditor editor = (JavaCodeEditor) getContainer();
            //得到startOffset,位置开始的length个长度的字符串，其实也就是我们要处理的字符串
            getDocument().getText(startOffset, length, seg);
            String txt = seg.toString();
//            System.out.println(txt);
            String identifier = "private";
            int len = identifier.length();
            if (txt.contains(identifier)) {
                int idx = txt.indexOf(identifier);
                if (idx != 0) {
                    int ret = 0;
                    g.setColor(Color.BLACK);
                    Segment text = getLineBuffer();
                    getDocument().getText(startOffset, idx, text);
                    ret = Utilities.drawTabbedText(text, x, y, g, this, startOffset);

                    g.setColor(Color.RED);
                    text = getLineBuffer();
                    getDocument().getText(idx + startOffset, len, text);
                    ret = Utilities.drawTabbedText(text, ret, y, g, this, startOffset + idx);

                    g.setColor(Color.BLACK);
                    text = getLineBuffer();
                    getDocument().getText(len + idx + startOffset, length - (len + idx), text);
                    ret = Utilities.drawTabbedText(text, ret, y, g, this, startOffset + len + idx);
                    return ret;
                } else {
                    int ret = 0;
                    g.setColor(Color.RED);
                    Segment text = getLineBuffer();
                    getDocument().getText(startOffset, len, text);
                    ret = Utilities.drawTabbedText(text, x, y, g, this, startOffset + idx);

                    g.setColor(Color.BLACK);
                    text = getLineBuffer();
                    getDocument().getText(len + startOffset, length - len, text);
                    ret = Utilities.drawTabbedText(text, ret, y, g, this, startOffset + len + idx);
                    return ret;
                }

            }
            g.setColor(Color.BLACK);
            Segment text = getLineBuffer();
            getDocument().getText(startOffset, length, text);
            return Utilities.drawTabbedText(text, x, y, g, this, startOffset);
//            int ret = 0;
//            for (int wordIndex = 0; wordIndex < seg.length(); ) {
//
//                char currentChar = seg.charAt(wordIndex);
//                if (Character.isJavaIdentifierStart(currentChar)) {
//                    //比如说以红色显示类名
//                    Segment text = getLineBuffer();
//                    getDocument().getText(startOffset + wordIndex, len, text);
//                    //还有其它样式的话只管给g加
//                    g.setColor(Color.RED);
//
//                    ret += Utilities.drawTabbedText(text, x, y, g, this, startOffset + wordIndex);
//                    wordIndex += len;
//                } else {
//                    wordIndex++;
//                }
//            }
//            return ret;
        }
    }
}
