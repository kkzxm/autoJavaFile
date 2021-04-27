package com.lingDream.autoJavaFile.tools;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.lingDream.root.utils.fileUtil.FileUtils.newFile;

@Component
public class TemplateTool {
    public final TemplateEngine engine;
    public TemplateTool(TemplateEngine engine) {
        this.engine = engine;
    }

    /**
     * 生成静态文件
     *
     * @param freeTempName 模板名称
     * @param context      数据内容
     * @param outFilePath  输出路径
     * @return 是否创建成功
     */
    public boolean process(String freeTempName, Context context, String outFilePath) {
        FileWriter fileWriter = null;
        File file = newFile(outFilePath);
        boolean flag = true;
        try {
            fileWriter = new FileWriter(file);
            engine.process(freeTempName, context, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
