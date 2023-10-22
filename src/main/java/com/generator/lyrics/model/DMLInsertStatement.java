package com.generator.lyrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class DMLInsertStatement {
    private static final String INSERT_INTO = "INSERT INTO ";
    private String tableName;
    private Map<String, Object> columnValueMap;

    @Override
    public String toString() {
        String columnNamesCSV = "";
        String columnValuesCSV = "";
        int valueMapSize = columnValueMap.size();
        int loopCount = 0;
        for (Map.Entry<String, Object> entry : columnValueMap.entrySet()) {
            if (loopCount < valueMapSize - 1) {
                columnNamesCSV = columnNamesCSV.concat(entry.getKey()).concat(", ");
                columnValuesCSV =
                        columnValuesCSV.concat(getInsertValueByType(entry.getValue())).concat(", ");
            } else {
                columnNamesCSV = columnNamesCSV.concat(entry.getKey());
                columnValuesCSV = columnValuesCSV.concat(getInsertValueByType(entry.getValue()));
            }
            loopCount++;
        }
        return INSERT_INTO.concat(tableName).concat(" (").concat(columnNamesCSV).concat(") VALUES (").concat(columnValuesCSV).concat(");\n");
    }

    public String getInsertValueByType(Object value) {
        String insertValue;
        if (value instanceof String && !"CURRENT_TIMESTAMP".equalsIgnoreCase(value.toString())) {
            insertValue = "'".concat(value.toString()).concat("'");
        } else if (null != value && "CURRENT_TIMESTAMP".equalsIgnoreCase(value.toString())) {
            insertValue = value.toString();
        } else if (null != value) {
            insertValue = value.toString();
        } else {
            insertValue = "NULL";
        }
        return insertValue;
    }
}
