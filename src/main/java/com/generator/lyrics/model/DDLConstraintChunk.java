package com.generator.lyrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DDLConstraintChunk {
    private String columnName;
    private String foreignTable;
    private String foreignColumn;

    @Override
    public String toString() {
        return "\tFOREIGN KEY ".concat("(").concat(columnName).concat(") REFERENCES ")
                .concat(foreignTable).concat("(").concat(foreignColumn).concat(") , \n");
    }
}
