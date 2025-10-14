package com.example.task;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator mpg=new AutoGenerator();

        //全局配置
        GlobalConfig gc=new GlobalConfig();
        String projectPath=System.getProperty("user.dir");
        gc.setOutputDir(projectPath+"/src/main/java");
        gc.setAuthor("陈高明");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dataSource=new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://localhost:3306/task_management?serverTimezone=UTC" +
                "&characterEncoding=utf8&useUnicode=true&useSSL=false&allowPublicKeyRetrieval=true");
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        mpg.setDataSource(dataSource);

        //包配置
        PackageConfig pc=new PackageConfig();

        pc.setParent("com.example.task");
        pc.setEntity("persistence.entity");
        pc.setMapper("persistence.mapper");
        pc.setService("persistence.service");
        pc.setServiceImpl("persistence.service.impl");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //自定义配置
        InjectionConfig ic=new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        String templatePath="/templates/mapper.xml.vm";
        List<FileOutConfig> focList=new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath+"/src/main/resources/mapper/"+tableInfo.getEntityName()+"Mapper"+ StringPool.DOT_XML;
            }
        });
        ic.setFileOutConfigList(focList);
        mpg.setCfg(ic);
        TemplateConfig config=new TemplateConfig();
        config.setXml(null);//.setController("")
        mpg.setTemplate(config);
        //策略配置
        StrategyConfig sc=new StrategyConfig();
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        //公共父类
        //写于父类中的公共字段
        sc.setControllerMappingHyphenStyle(true);
        sc.setTablePrefix(pc.getModuleName()+"_");
        mpg.setStrategy(sc);
        mpg.execute();

    }
}
