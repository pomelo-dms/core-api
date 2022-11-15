package cn.ikangjia.pomelo.manager.impl;

import cn.ikangjia.pomelo.api.dto.table.ColumnCreateDTO;
import cn.ikangjia.pomelo.api.dto.table.TableCreateDTO;
import cn.ikangjia.pomelo.core.ExecuteHandler;
import cn.ikangjia.pomelo.core.entity.TableEntity;
import cn.ikangjia.pomelo.core.sqlbuilder.table.TableSQLBuilder;
import cn.ikangjia.pomelo.manager.TableManager;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 21:08
 */
@Service
public class TableManagerImpl implements TableManager {
    private final ExecuteHandler handler;

    public TableManagerImpl(ExecuteHandler handler) {
        this.handler = handler;
    }

    @Override
    public void createTable(long dataSourceId, TableCreateDTO tableCreateDTO) {
        String databaseName = tableCreateDTO.getDatabaseName();
        String tableName = tableCreateDTO.getTableName();
        String comment = tableCreateDTO.getComment();

        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(tableName);
        tableEntity.setComment(comment);
        tableEntity.setDbName(databaseName);

        List<ColumnCreateDTO> columnDTOList = tableCreateDTO.getColumnCreateDTOList();

        String sql = TableSQLBuilder.getCreateSQL(tableEntity, columnDTOList);
        handler.execute(sql);
    }
}
