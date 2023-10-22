package com.generator.lyrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DDLColumnChunk {
    public static final String SPACE = " ";
    public static String DDL_TYPE_BIGINT = "BIGINT";
    public static String DDL_TYPE_VARCHAR = "VARCHAR";
    public static String DDL_TYPE_TEXT = "TEXT";
    public static String DDL_TYPE_DATETIME = "DATETIME";
    public static String SIZE_4 = "4";
    public static String SIZE_10 = "10";
    public static String SIZE_50 = "50";
    public static String SIZE_100 = "100";
    public static String SIZE_200 = "200";
    public static String SIZE_300 = "300";
    public static String SIZE_3000 = "3000";
    public static String DDL_PRIMARY_KEY = "PRIMARY KEY";
    public static String DDL_FOREIGN_KEY = "FOREIGN KEY";
    public static String DDL_REFERENCES = "REFERENCES";
    public static String DDL_NOT_NULL = "NOT NULL";
    public static String DDL_DEFAULT = "DEFAULT";
    public static String DDL_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";

    private String columnName;
    private String columnType;
    private String columnSize;
    private String nullable;
    private String defaultKeyword = "DEFAULT";
    private String defaultValue;
    private String constraint;

    @Override
    public String toString() {
        StringBuilder columnBuilder = new StringBuilder("\t");
        columnBuilder.append(columnName).append("\t\t").append(columnType);
        if (null != columnSize) {
            columnBuilder.append("(").append(columnSize).append(")");
        }
        columnBuilder.append(SPACE);
        if (null != nullable) {
            columnBuilder.append(nullable).append(SPACE);
        }
        if (null != defaultKeyword && null != defaultValue) {
            columnBuilder.append(defaultKeyword).append(SPACE)
                    .append(defaultValue).append(SPACE);
        }
        columnBuilder.append(constraint).append(" , \n");
        return columnBuilder.toString().replaceAll("null","");
    }
}
