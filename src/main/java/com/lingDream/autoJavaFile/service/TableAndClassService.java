package com.lingDream.autoJavaFile.service;

import com.lingDream.autoJavaFile.dao.ColumnAndPropertyDao;
import com.lingDream.autoJavaFile.dao.TableAndClassDao;
import com.lingDream.autoJavaFile.entity.ColumnAndProperty;
import com.lingDream.autoJavaFile.entity.TableAndClass;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 15:24
 */
@Service
public class TableAndClassService {
    private final TableAndClassDao tableAndClassDao;
    private final ColumnAndPropertyDao columnAndPropertyDao;

    public TableAndClassService(TableAndClassDao tableAndClassDao,
                                ColumnAndPropertyDao columnAndProperty) {
        this.tableAndClassDao = tableAndClassDao;
        this.columnAndPropertyDao = columnAndProperty;
    }

    public List<TableAndClass> getTables() {
        try {
            List<TableAndClass> tableAndClassList = tableAndClassDao.getAllTables();
            for (TableAndClass tableAndClass : tableAndClassList) {
                List<ColumnAndProperty> columnInfoByTableName = columnAndPropertyDao.getColumnInfoByTableName(tableAndClass);
                tableAndClass.setPropertyList(columnInfoByTableName);
            }
            return tableAndClassList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
