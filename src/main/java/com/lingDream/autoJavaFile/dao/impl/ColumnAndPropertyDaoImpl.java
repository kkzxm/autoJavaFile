package com.lingDream.autoJavaFile.dao.impl;

import com.lingDream.autoJavaFile.dao.ColumnAndPropertyDao;
import com.lingDream.autoJavaFile.dao.root.BaseDao;
import com.lingDream.autoJavaFile.entity.ColumnAndProperty;
import com.lingDream.autoJavaFile.entity.RootSetting;
import com.lingDream.autoJavaFile.entity.TableAndClass;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 15:28
 */
@Component
public class ColumnAndPropertyDaoImpl extends BaseDao implements ColumnAndPropertyDao {
    public ColumnAndPropertyDaoImpl(SqlSessionFactory sqlSessionFactory,
                                    RootSetting rootSetting) throws SQLException {
        super(sqlSessionFactory, rootSetting);
    }

    @Override
    public List<ColumnAndProperty> getColumnInfoByTableName(TableAndClass tableAndClass) throws SQLException {
        List<ColumnAndProperty> columnAndProperties = new ArrayList<>();
        ResultSet columns = databaseMetaData.getColumns(catalog, null, tableAndClass.getTableName(), null);
        while (columns.next()) {
            ColumnAndProperty columnAndProperty = new ColumnAndProperty();
            columnAndProperty.setColumnName(columns.getString("COLUMN_NAME"));
            columnAndProperty.setColumnType(columns.getString("TYPE_NAME"));
            columnAndProperty.setColumnComment(columns.getString("REMARKS"));
            columnAndProperty.setIsAutoIncrement(columns.getString("IS_AUTOINCREMENT"));
            columnAndProperties.add(columnAndProperty);
        }
        return columnAndProperties;
    }
}
