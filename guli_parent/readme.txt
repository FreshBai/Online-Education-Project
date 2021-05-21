dayOne:
  1、colud依赖和文件上传依赖有问题，先注释掉
  2、开发讲师管理模块，在service_edu中，service、controller、mapper通过代码生成器生成
  3、mybatis-plus的版本是3.0.5，版本太低，使用3.4.1试试

dayTwo:
  1、整合swagger，创建公共模块，使所有模块都能使用
  2、在service_base中加入启动类，不然会报错！（maven打包错误，找不到主类）
  3、启动测验，访问：http://localhost:8001/swagger-ui.html，地址是固定的
  4、统一返回结果也是公共使用的，放到common模块中,定义common_utils子模块  service_edu中需要使用，在父service中引入依赖
  5、遇到的问题，在service_edu中使用common_utils的R类时报错，可能是maven编译的方式不一样，解决方法:在parent的pom中使用
  <pluginManager>管理maven的插件
  6、讲师分页功能,以及带条件的分页，根据名称，级别，开始时间，结束时间多条件组合查询，怎么把这些条件传到后端接口？
  建议使用对象包裹传递，这就是 VO 对象，前后端传递的对象
  7、讲师新增，首先使用自动填充将创建时间和更新时间弄好,在service_edu中添加自动填充处理器，官网上复制的好像不行
  8、统一异常处理，也放在service_base中，定义一个全局异常处理类
  9、统一日志处理，logback日志工具，可以将日志传输到日志文件！但是需要将配置文件中所有关于日志的设置注释
  在resource下面创建logback-spring.xml配置文件

dayThree:
  1、前端开发 VScode
  2、开发前端，自定义路由，讲师列表和讲师添加，讲师列表用于展示list表单，同时，每一行有修改和删除按钮。点击修改按钮，进行隐藏路由跳转到讲师添加页面
  在讲师添加页面中的保存按钮进行条件判断来确认是新增或保存
  3、需要注意的细节：跨域问题（后端用@CrossOrigin注解解决）、接受参数（后端用@RequestBody注解，其他两种方式貌似不行)

dayFour:
  1、





