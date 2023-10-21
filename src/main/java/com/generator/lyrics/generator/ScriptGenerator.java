package com.generator.lyrics.generator;

public class ScriptGenerator {

    private final String DDL_CREATE_TABLE = "CREATE TABLE ";
    private final String DDL_PRIMARY_KEY = "PRIMARY KEY";
    private final String DDL_FOREIGN_KEY = "FOREIGN KEY";
    private final String DDL_REFERENCES = "REFERENCES";
    private final String DDL_NOT_NULL = "NOT NULL";
    private final String DDL_DEFAULT = "DEFAULT";
    private final String DDL_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
    private final String DDL_TYPE_BIGINT = "BIGINT";
    private final String DDL_TYPE_VARCHAR = "VARCHAR";
    private final String DDL_TYPE_TEXT = "TEXT";
    private final String DDL_TYPE_DATETIME = "DATETIME";

    private final String SIZE_50_BRACKET = "(50)";
    private final String SIZE_100_BRACKET = "(100)";
    private final String SIZE_3000_BRACKET = "(3000)";
    private final String COMMA_SPACE = ", ";
    private final String STARTING_BRACKET = "(";
    private final String CLOSING_BRACKET = ")";
    private final String SEMICOLON = ";";
    private final String SPACE = " ";
    private final String STARTING_BRACKET_SPACE = " (";
    private final String CLOSING_BRACKET_SPACE = ") ";
    private final String CLOSING_BRACKET_SEMICOLON = ");";


    private final String DML_CREATED_BY_SYSTEM = "'SYSTEM'";
    private final String DML_CREATED_TIMESTAMP = "CURRENT_TIMESTAMP";
    private final String DML_INSERT_INTO = "INSERT INTO ";
    private final String DML_VALUES_BRACKET = " VALUES (";

    public static void main(String[] args) {
        DDLGenerator ddlGenerator = new DDLGenerator();
        String createTableDDLScripts = ddlGenerator.generateDDLScripts();
        System.out.println(createTableDDLScripts);
    }

    public void generateScripts() {
        DDLGenerator ddlGenerator = new DDLGenerator();
        String createTableDDLScripts = ddlGenerator.generateDDLScripts();
        System.out.println(createTableDDLScripts);
        /*DMLGenerator dmlGenerator = new DMLGenerator();
        String insertIntoDDL = dmlGenerator.generateDMLScripts();
        ddlGenerator.generateDDLScripts();*/
    }

    public void generateDMLScripts() {

    }

}
