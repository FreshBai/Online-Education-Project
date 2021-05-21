import request from "@/utils/request"; //导入的整个包实现了axios,异步请求，这里我们调用这个包实现异步请求

//这是es6的语法，表示里面实现的方法都能够被调用，相当于public
export default {
  //JS比较灵活，方法参数可以不带类型！
  getTeacherListPage(current, limit, teacherQuery) {
    return request({
      url: `eduservice/teacher/pageTeacherCondition/${current}/${limit}`, //这里用到了``符号，可以自动解析路径
      method: "post",
      //params  data 表示用JSON传递数据
      data: teacherQuery
    });
  },
  deleteTeacherId(id) {
    return request({
      url: `eduservice/teacher/deleteTeacherById/${id}`,
      method: "delete"
    });
  },
  addTeacher(teacher) {
    return request({
      url: `eduservice/teacher/addTeacher`,
      method: "post",
      data: teacher //意思是将teacher转为JSON传入接口
    });
  },
  getTeacherInfo(id) {
    return request({
      url: `eduservice/teacher/getTeacher/${id}`,
      method: "get"
    });
  },
  updateTeacherInfo(teacher) {
    return request({
      url: `eduservice/teacher/updateTeacher`,
      method: "put",
      data: teacher //意思是将teacher转为JSON传入接口
    });
  }
};
