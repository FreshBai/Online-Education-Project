package com.cheng.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * Created by Administrator on 2021/5/2.
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        // 模块名
        String moduleName = "eduservice";

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取当前项目根路径
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("E:\\guli_parent\\service\\service_edu"+"/src/main/java");
        gc.setAuthor("Cheng");
        gc.setOpen(false); //不打开生产的文件
        gc.setFileOverride(false); //不覆盖之前生成的文件
        gc.setServiceName("%Service");
        gc.setIdType(IdType.ASSIGN_ID);// 主键策略 自增  注意要和数据库中表实际情况对应
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);//自动开启swagger2的支持
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:/guli?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
        dsc.setUsername("root");
        dsc.setPassword("wsacpc980809");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //也就是在moduleName下创建service、controller等包
        pc.setModuleName(moduleName);
        pc.setParent("com.cheng");
        pc.setController("controller");
        pc.setService("service");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setInclude("t_user");
        //可以用同配符号:表示生成t_开头的对应库下所有表
        //,"edu_comment","edu_course","edu_course_collect","edu_course_description","edu_subject","edu_teacher","edu_video"
        strategy.setInclude("edu_teacher");
        strategy.setNaming(NamingStrategy.underline_to_camel);// 下划线转驼峰
        strategy.setTablePrefix("t_");//去掉t_这个前缀后生成类名
        strategy.setEntityLombokModel(true);// 自动生成lombok注解  记住要有lombok依赖和对应的插件哈
        //strategy.setLogicDeleteFieldName("is_deleted");//设置逻辑删除字段 要和数据库中表对应哈

        // 设置创建时间和更新时间自动填充策略
        TableFill created_date = new TableFill("created_date", FieldFill.INSERT);
        TableFill updated_date = new TableFill("updated_date", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(created_date);
        tableFills.add(updated_date);
        strategy.setTableFillList(tableFills);

        // 乐观锁策略
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);//采用restful 风格的api
        strategy.setControllerMappingHyphenStyle(true); // controller 请求地址采用下划线代替驼峰
        mpg.setStrategy(strategy);

        // 执行
        mpg.execute();
    }
}
