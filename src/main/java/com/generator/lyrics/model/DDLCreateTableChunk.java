package com.generator.lyrics.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DDLCreateTableChunk {
    private String createTable = "CREATE TABLE ";
    private String tableName;

    public DDLCreateTableChunk(String tableName) {
        this.tableName = tableName;
    }
    @Override
    public String toString() {
        return createTable.concat(tableName).concat(" ").concat("(\n");
    }
}
