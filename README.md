# Haibara’s Measurement / 海扒拉的测绘

0.1.0-alpha.1 工程起步包。不是完整可玩版本，不是已验证可安装的模组发布版。

## 当前代码
- 官方 NeoForge 1.21.1 ModDevGradle 工程与 Gradle Wrapper。
- 六种占位物品注册及中英文名称、原版临时图标（用 /give haibaras_measurement:survey_pen 获取）。
- 不依赖 Minecraft 的区域、地形瓦片、记录与上传快照数据契约。
- 多边形验证、区域所有者判断、不可变快照的基础检查。
- 测绘笔选点状态：按序选桩、重复点/跨维度检查、撤销、取消、闭合预览（尚未接入游戏）。
- AI 需求解析、路线规划、约束校验、蓝图计数、蓝图导出、存档仓储接口。
- 可选 Create 6.0.10 开发依赖开关。尚无 Create 功能实现。

## 构建
安装 JDK 21，在此目录运行：
Windows: `gradlew.bat build`；开发客户端：`gradlew.bat runClient`。
Linux/macOS: `bash gradlew build`；`bash gradlew runClient`。
可选 Create 开发环境：`bash gradlew build -Pwith_create=true`。
首次下载需要访问 Gradle、NeoForge、Mojang 与 Maven。完整检查：`bash gradlew coreCheck`。

## 明确尚未实现
物品放置/同格陈列、工程台双格方块、地图采集与界面、数据组件和世界存档、联网与权限执行、测绘桩消耗、版本同步、AI 服务、路径算法、Create 蓝图生成/大炮施工均待开发。
当前物品只是注册占位，不能测绘、摆放或打开界面。

## 版本
Minecraft 1.21.1 / Java 21 / NeoForge 21.1.250（模板固定版本，Create 共存尚未实测）。
Create 目标 6.0.10，开发 Maven 6.0.10-280；暂不设强制运行依赖。
许可证：项目新增代码 All Rights Reserved；模板许可证保留在 TEMPLATE_LICENSE.txt。

## 来源
- https://github.com/NeoForgeMDKs/MDK-1.21.1-ModDevGradle
- https://wiki.createmod.net/developers/depend-on-create/neoforge-1.21.1
