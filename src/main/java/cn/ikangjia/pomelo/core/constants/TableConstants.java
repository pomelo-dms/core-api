package cn.ikangjia.pomelo.core.constants;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 14:06
 */
public interface TableConstants {
    enum TableType{
        BASE_TABLE("TABLE_NAME"),
        VIEW("VIEW"),
        SYSTEM_VIEW("SYSTEM_VIEW"),
        ;

        private final String tableType;

        TableType(String tableType) {
            this.tableType = tableType;
        }

        public String getTableType() {
            return tableType;
        }
    }
}
