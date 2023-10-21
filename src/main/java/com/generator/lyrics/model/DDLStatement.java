package com.generator.lyrics.model;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DDLStatement {
    private DDLCreateTableChunk ddlCreateTableChunk;
    private List<DDLColumnChunk> ddlColumnChunks;
    private List<DDLConstraintChunk> ddlConstraintChunks;

    @Override
    public String toString() {
        String columnChunks = ddlColumnChunks.stream().map(DDLColumnChunk::toString).collect(Collectors.joining());
        String constraintChunks = null;
        if (null != ddlConstraintChunks && !ddlConstraintChunks.isEmpty()) {
            constraintChunks = ddlConstraintChunks.stream().map(DDLConstraintChunk::toString).collect(Collectors.joining());
        }
        StringBuilder columnAndConstraintChunks = null;
        if (StringUtils.isNotBlank(columnChunks)) {
            columnAndConstraintChunks = new StringBuilder(columnChunks);
            if (null != constraintChunks) {
                columnAndConstraintChunks.append(constraintChunks);
            }
        }
        String finalColumnAndConstraintChunks = columnAndConstraintChunks == null ? columnChunks :
                columnAndConstraintChunks.toString();
        if (StringUtils.isNotBlank(finalColumnAndConstraintChunks) && finalColumnAndConstraintChunks.length() > 3) {
            finalColumnAndConstraintChunks = finalColumnAndConstraintChunks.substring(0, finalColumnAndConstraintChunks.length() - 3);
            finalColumnAndConstraintChunks = finalColumnAndConstraintChunks.concat("\n");
        }
        return ddlCreateTableChunk.toString().concat(finalColumnAndConstraintChunks).concat(");\n\n");
    }
}
