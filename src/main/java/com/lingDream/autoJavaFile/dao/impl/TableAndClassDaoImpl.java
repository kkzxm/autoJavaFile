package com.lingDream.autoJavaFile.dao.impl;

import com.lingDream.autoJavaFile.dao.TableAndClassDao;
import com.lingDream.autoJavaFile.dao.root.BaseDao;
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
 * @CreateTime: 2021-03-18 10:48
 */
@Component
public class TableAndClassDaoImpl extends BaseDao implements TableAndClassDao {

    public TableAndClassDaoImpl(SqlSessionFactory sqlSessionFactory, RootSetting rootSetting) throws SQLException {
        super(sqlSessionFactory, rootSetting);
    }

    @Override
    public List<TableAndClass> getAllTables() throws SQLException {
        List<TableAndClass> tableAndClasses = new ArrayList<>();
        ResultSet tables = databaseMetaData.getTables(catalog, "", null, new String[]{"TABLE"});
        while (tables.next()) {
            TableAndClass tableAndClass = new TableAndClass(rootSetting);
            tableAndClass.setTableName(tables.getString("TABLE_NAME"));
            tableAndClasses.add(tableAndClass);
        }
        return tableAndClasses;
    }
}
