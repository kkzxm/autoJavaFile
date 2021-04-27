package com.lingDream.autoJavaFile.dao;

import com.lingDream.autoJavaFile.entity.ColumnAndProperty;
import com.lingDream.autoJavaFile.entity.TableAndClass;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 15:26
 */
public interface ColumnAndPropertyDao {
    /**
     * 根据表名查询出所有的列信息
     * @return
     */
    List<ColumnAndProperty> getColumnInfoByTableName(TableAndClass tableAndClass) throws SQLException;
}
