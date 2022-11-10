package com.simile.plan.swing.app.ide;

/**
 * @Author yitao
 * @Created 2022/11/09
 */
public interface KeywordHolder {

    boolean isKeyword(String word);

    class DefaultKeywordHolder implements KeywordHolder {
        @Override
        public boolean isKeyword(String word) {
            return false;
        }
    }

    class JavaKeywordHolder implements KeywordHolder {
        @Override
        public boolean isKeyword(String word) {
            return "private".equals(word) || "public".equals(word) || "protected".equals(word)
                    || "class".equals(word) || "enum".equals(word) || "interface".equals(word)
                    || "abstract".equals(word) || "extends".equals(word) || "implements".equals(word)
                    || "long".equals(word) || "int".equals(word) || "double".equals(word)
                    || "boolean".equals(word) || "char".equals(word) || "float".equals(word)
                    || "byte".equals(word) || "void".equals(word)
                    || "Long".equals(word) || "String".equals(word) || "Double".equals(word)
                    || "Float".equals(word) || "Integer".equals(word) || "Boolean".equals(word)
                    || "Byte".equals(word)
                    || "false".equals(word) || "true".equals(word)
                    || "volatile".equals(word) || "synchronized".equals(word)
                    || "transient".equals(word) || "return".equals(word)
                    || "import".equals(word) || "package".equals(word)
                    ;
        }
    }

    class MysqlKeywordHolder implements KeywordHolder {
        @Override
        public boolean isKeyword(String word) {
            return "CREATE".equalsIgnoreCase(word) || "TABLE".equalsIgnoreCase(word) ||
                    "IF".equalsIgnoreCase(word) || "NOT".equalsIgnoreCase(word) ||
                    "EXISTS".equalsIgnoreCase(word) || "NULL".equalsIgnoreCase(word)
                    || "AUTO_INCREMENT".equalsIgnoreCase(word) || "COMMENT".equalsIgnoreCase(word)
                    || "INT".equalsIgnoreCase(word) || "DATETIME".equalsIgnoreCase(word)
                    || "VARCHAR".equalsIgnoreCase(word) || "CHAR".equalsIgnoreCase(word)
                    || "DEFAULT".equalsIgnoreCase(word) || "CURRENT_TIMESTAMP".equalsIgnoreCase(word)
                    || "ON".equalsIgnoreCase(word) || "UPDATE".equalsIgnoreCase(word)
                    || "ENGINE".equalsIgnoreCase(word) || "CHARSET".equalsIgnoreCase(word)
                    ;
        }
    }


}
