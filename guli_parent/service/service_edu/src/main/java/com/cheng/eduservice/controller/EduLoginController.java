package com.cheng.eduservice.controller;

import com.cheng.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
@PostMapping("login")
    public R login(){
    return R.ok().data("token","jack");
}
 @GetMapping("info")
    public  R info(){
    return R.ok().data("roles","[admin]").data("name","jack").data("avatar","");
    }
}
