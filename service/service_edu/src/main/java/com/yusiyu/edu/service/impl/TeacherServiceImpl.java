package com.yusiyu.edu.service.impl;

import com.yusiyu.edu.entity.Teacher;
import com.yusiyu.edu.mapper.TeacherMapper;
import com.yusiyu.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author yusiyu
 * @since 2021-02-13
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    //使用该代码生成器，不需要在这写mapper注入
//    @Autowired
//    TeacherMapper teacherMapper;
}
