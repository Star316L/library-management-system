# Library Management System (图书馆管理系统)

一个基于Spring MVC、MySQL和jQuery构建的，具备完整增删改查功能的图书馆管理系统。

## 🚀 技术栈
- **后端**: Java 8+, Spring MVC, MyBatis
- **数据库**: MySQL
- **前端**: JSP, jQuery, Bootstrap
- **构建工具**: Maven
- **服务器**: Apache Tomcat

## ✨ 核心功能
- **用户管理**: 管理员与读者两种角色，登录/注销。
- **图书管理**: 图书信息的增删改查、分类检索。
- **借阅管理**: 实现图书的借阅、归还、逾期提醒。
- **数据统计**: 简单的借阅排行榜、图书库存统计。

## 📁 项目结构

## 🛠️ 快速开始
### 环境准备
1.  JDK 1.8+
2.  MySQL 5.7+
3.  Maven 3.6+
4.  Tomcat 8+

### 部署步骤
1.  **克隆项目**:
    ```bash
    git clone https://github.com/你的用户名/library-management-system.git
    ```
2.  **初始化数据库**:
    - 在MySQL中创建名为 `library_db` 的数据库。
    - 执行项目 `sql/` 目录下的 `init.sql` 文件（你需要先创建这个文件）。
3.  **配置数据源**:
    - 修改 `src/main/resources/jdbc.properties` 中的数据库用户名和密码。
4.  **构建项目**:
    ```bash
    mvn clean package
    ```
5.  **部署运行**:
    - 将生成的 `target/library-management-system.war` 文件部署到Tomcat，或直接在IDE中运行。

## 📸 界面截图
（此处稍后上传，例如：）
- **登录界面**：![登录页](screenshots/login.png)
- **图书列表页**：![列表页](screenshots/book_list.png)

## 🤝 贡献
欢迎提交Issue和Pull Request！

## 📄 许可证
[MIT](LICENSE) (可选，但建议添加)
