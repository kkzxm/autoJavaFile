package com.lingDream.autoJavaFile.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import static com.lingDream.root.utils.strUtil.StringUtils.underscoreToCamelCase;
import static com.lingDream.root.utils.strUtil.StringUtils.uppFirstChar;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-01-12 10:07
 */
@Data
@Accessors(chain = true)
public class TableAndClass {
    private final RootSetting rootSetting;//项目根设置
    private String tableName;//表名称
    private List<ColumnAndProperty> propertyList;//属性集合
    private String thisPackage;//包含当前使用的一些信息

    /**
     * 得到类名
     */
    public String getFileName() {
        String className = getClassName();
        if (!className.contains(".")) return className+".java";
        return className;
    }

    /**
     * 得到类的包路径
     */
    public String getClassPackage() {
        return rootSetting.getRootPackage()+"."+thisPackage;
    }

    /**
     * 得到类(文件)路径
     */
    public String getClassPath() {
        String classPath;

        String project_path = rootSetting.getProject_path();
        String project_name = rootSetting.getProject_name();
        String rootPackage = rootSetting.getRootPackage().replace('.','/');
        String className = getFileName();

        int i = thisPackage.indexOf("_");
        if (i!=-1)thisPackage = thisPackage.substring(0,i);

        classPath = project_path+"/"+project_name+"/src/main/java/"+rootPackage+'/'+thisPackage+'/'+className;

        return classPath;
    }

    public String getClassName() {
        String className = "";
        String oldClassName = getOldClassName();
        switch (thisPackage) {
            case "":
                break;
            case "entity":
            case "pojo":
                className = oldClassName;
                break;
            default:
                if (thisPackage.contains("_")){
                    className = oldClassName+uppFirstChar(thisPackage.replace('_','.'));
                }else{
                    className = oldClassName+uppFirstChar(thisPackage);
                }
                break;
        }
        return className;
    }

    /**
     * 得到旧的类名(没有加后缀前)
     */
    public String getOldClassName(){
        return  uppFirstChar(underscoreToCamelCase(tableName));
    }
}
