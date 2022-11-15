package cn.ikangjia.pomelo.core.sqlbuilder;

import cn.ikangjia.pomelo.api.dto.table.ColumnCreateDTO;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.chrono.IsoChronology;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 21:22
 */
public class TableSQLBuilder {


    public static String getCreateSQL(String databaseName, String tableName, String comment, List<ColumnCreateDTO> columnDTOList) {
        StringBuilder sb = new StringBuilder();

        // create table db_xx.t_person (
        sb.append("create table ").append(databaseName).append(".").append(tableName).append(" ( ");

        if (ObjectUtils.isEmpty(columnDTOList) || columnDTOList.size() < 1) {
            throw new RuntimeException("列参数有误");
        }
        StringBuilder columnSB = new StringBuilder();
        columnDTOList.forEach(columnInfo -> {
            System.out.println(columnInfo);
            columnSB.append(columnInfo.getColumnName()).append(" ")
                    .append(columnInfo.getTypeInfo());

            if (columnInfo.isNull()) {
                columnSB.append(" NULL ");
            } else {
                columnSB.append(" NOT NULL ");
            }

            if (columnInfo.isUnique()) {
                columnSB.append(" unique ");
            }

            if (StringUtils.hasText(columnInfo.getDefaultValue())) {
                columnSB.append(" DEFAULT ").append("'")
                        .append(columnInfo.getDefaultValue()).append("'");
            }

            if (columnInfo.isIncrement()){
                columnSB.append(" AUTO_INCREMENT ");
            }
            columnSB.append(",");
        });
        String columnStr = columnSB.toString();

        List<String> primaryColumn = columnDTOList.stream()
                .filter(ColumnCreateDTO::isPrimaryKey)
                .map(ColumnCreateDTO::getColumnName)
                .toList();
        if (primaryColumn.size() < 1) {
            columnStr = columnStr.substring(0, columnStr.length() - 1);
        }else {
            String collect = String.join(",", primaryColumn);
            String primaryStr = "PRIMARY KEY ( " + collect + ")";
            columnStr = columnStr + primaryStr;
        }
        sb.append(columnStr).append(" ) ");
        if (StringUtils.hasText(comment)) {
            sb.append(" comment='").append(comment).append("'");
        }
        sb.append(";");
        return sb.toString();
    }
}
