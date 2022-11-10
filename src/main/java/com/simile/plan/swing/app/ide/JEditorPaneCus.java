package com.simile.plan.swing.app.ide;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
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
 * @Created 2022/11/08
 */
public class JEditorPaneCus extends JEditorPane {
    private StringBuffer sb = new StringBuffer();
    private KeywordHolder keywordHolder = new KeywordHolder.DefaultKeywordHolder();
    private Color keywordColor = Color.ORANGE;
    private Color unkeywordColor = Color.BLACK;

    public void setKeywordHolder(KeywordHolder keywordHolder) {
        this.keywordHolder = keywordHolder;
    }

    private boolean isKeyword(String kw) {
        return keywordHolder.isKeyword(kw);
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

    @Override
    protected EditorKit createDefaultEditorKit() {
        return new StyledEditorKitCus();
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
                throw new RuntimeException(e);
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
            return full(g, x, y, startOffset, endOffset);
        }

        private int full(Graphics g, int x, int y, int startOffset, int endOffset) throws BadLocationException {
            Segment seg = new Segment();
            getDocument().getText(startOffset, endOffset - startOffset, seg);
            int xCursor = x;
            //已经处理到的字符偏移位置
            int offset = startOffset;
            for (int i = 0; i < seg.length(); i++) {
                char ch = seg.charAt(i);
                if (isSplit(ch)) {
                    //检查是否是关键词
                    String word = sb.toString();
                    Segment s = getLineBuffer();
                    getDocument().getText(offset, word.length(), s);
                    if (isKeyword(word)) {
                        g.setColor(keywordColor);
                    } else {
                        g.setColor(unkeywordColor);
                    }
                    xCursor = Utilities.drawTabbedText(s, xCursor, y, g, this, offset);
                    offset += word.length();
                    sb.setLength(0);
                    //处理分隔符
                    s = getLineBuffer();
                    getDocument().getText(offset, 1, s);
                    g.setColor(unkeywordColor);
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
                    g.setColor(keywordColor);
                } else {
                    g.setColor(unkeywordColor);
                }
                xCursor = Utilities.drawTabbedText(s, xCursor, y, g, this, offset);
                offset += word.length();
                sb.setLength(0);
            }
            return xCursor;
        }

    }
}
