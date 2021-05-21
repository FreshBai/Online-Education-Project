package com.cheng;

import com.cheng.eduservice.controller.EduTeacherController;
import com.cheng.eduservice.service.EduTeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2021/5/3.
 **/
public class LogicDeleted {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Test
    public void deleted(){
        System.out.println(eduTeacherService);
        boolean b=eduTeacherService.removeById("1192249914833055746");
        System.out.println(b);
    }
}
