package com.lingDream.autoJavaFile.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LI_Lingfei
 * @CreateTime: 2021-01-14 09:08
 */
@Data
@Accessors(chain = true)
public class RootSetting {
    private final String project_name;//项目名称
    private final String project_path;//项目根路径
    private final String rootPackage;//项目包结构
    private final String author;//作者
}
