package com.lingDream.autoJavaFile.view;

import com.lingDream.autoJavaFile.entity.TableAndClass;
import com.lingDream.autoJavaFile.service.TableAndClassService;
import com.lingDream.autoJavaFile.tools.TemplateTool;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-03-18 17:15
 */
@Component
public class AutoJavaFile {
    private final TableAndClassService tableAndClassService;
    private final TemplateTool templateTool;

    public AutoJavaFile(TableAndClassService tableAndClassService,
                        TemplateTool templateTool) {
        this.tableAndClassService = tableAndClassService;
        this.templateTool = templateTool;
    }

    public void createFile() {
        //得到classes的路径(编译后的classes)
        String filePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getFile();

        //装模板文件的容器
        File[] files = new File(filePath).listFiles();
        //装模板文件名的容器
        List<String> fileNameList = new ArrayList<>();

        //循环模板文件
        for (File file1 : Objects.requireNonNull(files)) {

            //提取出templates(模板文件夹,在该文件夹里操作)
            if ("templates".equals(file1.getName())) {
                for (File listFile : Objects.requireNonNull(file1.listFiles())) {
                    //跳过文件夹
                    if (listFile.isDirectory()) continue;

                    String name = listFile.getName();
                    String substring = name.substring(0, name.length() - 4);
                    fileNameList.add(substring);
                }
            }
        }

        //region 准备数据
        Context context = new Context();
        List<TableAndClass> tables = tableAndClassService.getTables();
        for (String thisPackage : fileNameList) {
            for (TableAndClass table : tables) {
                table.setThisPackage(thisPackage);

                context.setVariable("table", table);

                templateTool.process(thisPackage + ".txt", context, table.getClassPath());

                System.out.println(table.getClassPath());
            }
        }
        //endregion
    }
}
