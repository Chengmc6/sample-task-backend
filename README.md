markdown
# 🧠 Sample Task Backend

这是一个使用 Spring Boot 构建的任务管理系统后端项目，采用分层架构设计，支持用户管理、任务处理、标签关联、附件上传等功能，遵循 RESTful API 规范，具备良好的可维护性与扩展性。

---

## 🚀 技术栈

- Java 17
- Spring Boot
- MyBatis-Plus
- MapStruct
- JWT
- Maven
- Git

---

## 📦 项目结构

src/main/java/com/example/task/ 
├── advice/ # 全局异常处理与响应增强 
├── common/ # 通用枚举、工具类、常量定义 
├── controller/ # 接口层，定义 RESTful API 
├── persistence/ # 数据访问与业务逻辑层 
│ ├── dto/ # 请求/响应数据传输对象 
│ ├── entity/ # 数据库实体类 
│ ├── mapper/ # MyBatis 映射接口 
│ ├── service/ # 业务接口定义 
│ │ └── impl/ # 业务实现类（I 前缀接口） 
└── TaskApplication.java # 项目启动入口

---

## ✅ 功能模块

- 用户模块：注册、登录、修改密码、获取用户信息
- 任务模块：创建任务、查询任务、批量删除任务
- 标签模块：任务标签关联、标签管理
- 附件模块：任务附件上传与管理
- 权限校验：基于 JWT 的用户鉴权
- 错误处理：统一异常捕获与语义化错误码响应

---

## 🧪 启动方式

```bash
# 克隆项目
git clone git@github.com:chengmc6/sample-task-backend.git

# 导入到 IDE（推荐 IntelliJ IDEA 或 VS Code）
# 配置数据库连接（application.yml）
# 启动 TaskApplication.java
📌 TODO
⏳ 分页查询与排序支持

⏳ Swagger 接口文档集成

⏳ 错误码模块化管理

⏳ 任务状态枚举与筛选逻辑

⏳ 文件上传模块优化（支持多文件）

🤝 贡献指南
欢迎提交 Issue 或 Pull Request 来优化项目结构、补充功能或修复 Bug。请遵循统一代码风格与模块命名规范。

📄 License
本项目采用 MIT License，详情请见 LICENSE。
