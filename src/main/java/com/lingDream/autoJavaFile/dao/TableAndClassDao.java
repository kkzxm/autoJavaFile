package com.lingDream.autoJavaFile.dao;

import com.lingDream.autoJavaFile.entity.TableAndClass;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 08:53
 */
public interface TableAndClassDao {
    /**
     * 从数据库中查到所有表的信息
     * (只能得到名字)
     */
    List<TableAndClass> getAllTables() throws SQLException;
}
