package com.lingDream.autoJavaFile.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import static com.lingDream.root.utils.strUtil.StringUtils.underscoreToCamelCase;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 11:49
 */
@Data
@Accessors(chain = true)
public class ColumnAndProperty {
    private String columnName;//列名称
    private String columnType;//列类型
    private String columnComment;//列备注
    private String columnKey;//是否是主键
    private String isAutoIncrement;//是自动递增
    private String propertyType;//属性类型
    private String comment;//属性注释

    //属性名
    public String getPropertyName() {
        return underscoreToCamelCase(columnName);
    }

    //属性类型
    public String getPropertyType() {
        switch (columnType) {
            case "INT":
            case "INT UNSIGNED":
                return "Integer";
            case "VARCHAR":
                return "String";
            case "DOUBLE":
                return "Double";
            case "DATETIME":
            case "DATE":
                return "Date";
        }
        return columnType;
    }
}
