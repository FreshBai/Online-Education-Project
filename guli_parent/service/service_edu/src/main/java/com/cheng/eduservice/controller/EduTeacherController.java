package com.cheng.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cheng.commonutils.R;
import com.cheng.eduservice.entity.EduTeacher;
import com.cheng.eduservice.entity.vo.TeacherQuery;
import com.cheng.eduservice.service.EduTeacherService;
import com.cheng.servicebase.exceptionhandle.CommonEnum;
import com.cheng.servicebase.exceptionhandle.TeacherException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Cheng
 * @since 2021-05-02
 */
@Api("讲师管理")
@RestController
@RequestMapping(("/eduservice/teacher"))
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表中的所有数据
    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public R selectAllTeacher() {
        List<EduTeacher> list = eduTeacherService.list();
        return R.ok().data("list", list);
    }

    //逻辑删除教师
    @ApiOperation("根据ID逻辑删除讲师")
    @DeleteMapping("deleteTeacherById/{id}")
    public R deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean b = eduTeacherService.removeById(id);
        if (b) {
            return R.ok();
        } else return R.error();
    }

    @ApiOperation("分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable(value = "current") long current,
                             @ApiParam(name = "limit", value = "每页条数", required = true) @PathVariable(value = "limit") long limit) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页，底层封装，把分页所有数据封装到pageTeacher里
        eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        Map map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
    }

    //条件查询带分页,required为true，表示参数不可省
    @ApiOperation("多条件组合查询分页列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current",value = "当前页",required = true) @PathVariable Long current,
                                  @ApiParam(name = "limit", value = "每页条数", required = true) @PathVariable Long limit,
                                  @ApiParam(value = "条件对象") @RequestBody(required = false) TeacherQuery teacherQuery) {


        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name))  wrapper.like("name",name);
        if(!StringUtils.isEmpty(level)) wrapper.eq("level",level);
        if(!StringUtils.isEmpty(begin)) wrapper.ge("gmt_create",begin);
        if(!StringUtils.isEmpty(begin)) wrapper.le("gmt_create",end);
        //排序
        wrapper.orderByDesc("gmt_create");

        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //执行分页方法
        eduTeacherService.page(pageTeacher, wrapper);
        //得到当前页的结果
        List<EduTeacher> records = pageTeacher.getRecords();
        //得到总条数
        long total = pageTeacher.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save=eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else return R.error();
    }

    @ApiOperation("通过ID查询讲师信息")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        if(eduTeacher==null){
            throw new TeacherException(CommonEnum.SERVER_BUSY);
        }
        return R.ok().data("teacher",eduTeacher);
    }

    @ApiOperation("通过ID修改讲师信息")
    @PutMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher Eduteacher){ //这里用@RequestParam貌似不行，直接用对象接受好像也不行
        System.out.println("前端传来的对象："+Eduteacher);
        boolean flag = eduTeacherService.updateById(Eduteacher);
        if(flag){
            return R.ok();
        }else return R.error();
    }
}

























