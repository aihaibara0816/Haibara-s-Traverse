# Codex 接手说明

> 构建状态更新：提交 `e05f0c7` 已通过 GitHub Actions 的 `bash gradlew build`。这不代表游戏内玩法或 Create 联动已通过验证。详见 [BUILD_STATUS.md](BUILD_STATUS.md)。

仓库：aihaibara0816/Haibara-s-Traverse。此任务已授权公开上传源码。
本包是工程骨架，尚不能在游戏中完成测绘。已有6个注册占位物品、纯Java区域/地形/备注/快照、扩展接口。
先看 README、docs/DESIGN、docs/VALIDATION。玩法无需重新讨论，按已确认规则推进。

下一项任务：
1. 安装/选择 JDK 21，运行 bash gradlew build（Windows gradlew.bat build），先修复编译问题。
2. 实现真正的界碑与测绘桩方块和占位模型、右键界碑编辑名称。
3. 测绘笔依次选桩，空气右键打开保存页，设置名称/类型/属性；保存成功才消耗桩。
4. 数据服务端存档，重启保留；首次区域所有者控制修改，后来者继承已有区域属性。
5. 验证单机/专用服务器、错误多边形、多人修改与存档恢复；清楚区分通过和未测项目。

AI/路径规划/Create蓝图目前仅保留接口，首版不加入完整AI推理。
平板/对讲机/工程台整合是下一里程碑，详见设计文档。
早期本地环境的 Gradle 下载失败已由后续 CI 构建成功补充；仍未完成游戏内验证，不要把源码包称为可玩发布版。

新增 SurveySelection 纯Java状态逻辑，可由服务端物品交互调用；已写入 CoreChecks，并纳入 Gradle check/build；后续修改仍应重新验证。
