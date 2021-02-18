package com.yusiyu.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yusiyu.commutils.R;
import com.yusiyu.edu.entity.Teacher;
import com.yusiyu.edu.entity.vo.TeacherQuery;
import com.yusiyu.edu.service.TeacherService;
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
 * @author yusiyu
 * @since 2021-02-13
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    //查询讲师表中的全部数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        //调用service中的方法
        List<Teacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    };

    //讲师的逻辑删除,在相关字段上加@TableLogic
    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")     //下面这个注解是得到url路径中的id值
    public R deleteById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean flag=teacherService.removeById(id);
        if(flag=true){
            return R.ok();
        }else
        return R.error();
    }

    //分页查询讲师的方法
    @ApiOperation(value = "分页查询讲师的方法")
    @GetMapping("pageTeacher/{current}/{size}")
    public R pageTeacher(@PathVariable int current,@PathVariable int size){
        //创建Page对象
        Page<Teacher> teacherPage=new Page<>(current,size);
        //调用方法实现分页
        teacherService.page(teacherPage,null);
        int total= (int) teacherPage.getTotal(); //总记录条数
        List<Teacher> records = teacherPage.getRecords();

        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //条件分页查询
    @ApiOperation(value = "条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public R pageTeacherCondition(@PathVariable int current, @PathVariable int size,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建一个page对象
        Page<Teacher> teacherPage=new Page<>(current,size);
        //构建查询条件
        QueryWrapper<Teacher> wrapper=new QueryWrapper<>();
        //多条件组合查询
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String begin=teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        //调用方法
        teacherService.page(teacherPage,wrapper);
        int total= (int) teacherPage.getTotal(); //总记录条数
        List<Teacher> records = teacherPage.getRecords();

        Map map=new HashMap();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //添加讲师的方法
    @ApiOperation(value = "添加讲师的方法")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if(save){
            return R.ok();
        }else
            return R.error();
    }

    //根据讲师ID查询
    @ApiOperation(value = "根据讲师ID查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("total",1).data("teacher",teacher);
    }

    //修改导师信息
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if(flag){
            return R.ok();
        }else return R.error();
    }

//    @ApiOperation(value = "根据ID修改讲师")
//    @PutMapping("{id}")
//    public R updateById(
//            @ApiParam(name = "id", value = "讲师ID", required = true)
//            @PathVariable String id,
//
//            @ApiParam(name = "teacher", value = "讲师对象", required = true)
//            @RequestBody Teacher teacher){
//
//        teacher.setId(id);
//        teacherService.updateById(teacher);
//        return R.ok();
//    }
}

