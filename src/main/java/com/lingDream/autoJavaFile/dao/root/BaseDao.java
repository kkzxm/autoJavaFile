package com.lingDream.autoJavaFile.dao.root;


import com.lingDream.autoJavaFile.entity.RootSetting;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 09:11
 */
public class BaseDao {
    protected final SqlSessionFactory sqlSessionFactory;
    protected final Connection connection;
    protected DatabaseMetaData databaseMetaData;
    protected String catalog;
    protected RootSetting rootSetting;

    public BaseDao(SqlSessionFactory sqlSessionFactory, RootSetting rootSetting) throws SQLException {
        this.rootSetting = rootSetting;
        this.sqlSessionFactory = sqlSessionFactory;
        this.connection = sqlSessionFactory.openSession().getConnection();
        databaseMetaData = connection.getMetaData();
        catalog = connection.getCatalog();
    }
}
