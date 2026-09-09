# 本次验证状态

- Gradle build：未完成。Wrapper 下载 Gradle 9.2.1 时 Network is unreachable，尚未进入源码编译。
- 纯 Java 检查：未运行。环境仅有 Java 17 JRE，无 javac；已提供 CoreChecks，不能宣称通过。
- 静态资源检查：JSON 可解析，替换后的模组元数据 TOML 可解析；Wrapper 文件齐备。
- 客户端、专用服务器、Create 共存：均未测试。
- 本包仅 M0 工程骨架，未输出可安装 JAR。

下一步先在 JDK 21 和可下载依赖的环境执行 gradlew.bat build，然后 runClient 与 runServer。之后实现 M1，不应先开发 AI。

补充：新增 SurveySelection（按序选点、XZ重复拒绝、维度隔离、256点上限、撤销/取消、非破坏性闭合预览）及核心检查。游戏中的测绘笔尚未连接此逻辑。等待 GitHub CI 或本地 JDK 21 执行检查。
