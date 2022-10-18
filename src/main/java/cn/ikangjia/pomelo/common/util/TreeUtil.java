package cn.ikangjia.pomelo.common.util;

import cn.ikangjia.pomelo.api.vo.TreeVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/18 21:25
 */
public class TreeUtil {
    private static final String node_key_level_1_db = "1-%s-db-%s";

    private static final String node_key_level_2_t = "2-%s-t-%s";
    private static final String node_key_level_2_v = "2-%s-v-%s";
    private static final String node_key_level_2_p = "2-%s-p-%s";

    private static final String node_key_level_3 = "1-db-%s";

    public static List<TreeVO> buildLevel0(Long dataSourceId, List<String> itemList) {
        List<TreeVO> result = new ArrayList<>();
        itemList.forEach(item -> {
            TreeVO level1 = new TreeVO();
            level1.setLevel(1);
            level1.setLeaf(false);
            level1.setLabelName(item);
            level1.setNodeKey(String.format(node_key_level_1_db, dataSourceId, item));
//            level1.setChildren(buildLevel1(dataSourceId, item));
            result.add(level1);
        });

        return result;
    }

    public static List<TreeVO> buildLevel1(Long dataSourceId, String fatherNodeKey) {
        TreeVO table = new TreeVO();
        table.setLevel(2);
        table.setLeaf(false);
        table.setLabelName("表");
        table.setNodeKey(String.format(node_key_level_2_t, dataSourceId, fatherNodeKey));

        TreeVO view = new TreeVO();
        view.setLevel(2);
        view.setLeaf(false);
        view.setLabelName("视图");
        view.setNodeKey(String.format(node_key_level_2_v, dataSourceId, fatherNodeKey));

        TreeVO procedure = new TreeVO();
        procedure.setLevel(2);
        procedure.setLeaf(false);
        procedure.setLabelName("存储过程");
        procedure.setNodeKey(String.format(node_key_level_2_p, dataSourceId, fatherNodeKey));

        List<TreeVO> result = new ArrayList<>();
        result.add(table);
        result.add(view);
        result.add(procedure);
        return result;
    }
}
