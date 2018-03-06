## 自定义gradle plugin
* New Java Lib
* 删掉 man/java ,新建一个main/groovy,包名 ...
* 由于无法直接new groovy class, 所以我们new java class, 再改后缀 .groovy

> Notice that the properties filename matches the plugin id and is placed in the resources folder, and that the implementation-class property identifies the Plugin implementation class.

resources下的properties 文件名必须匹配 plugin id (即 groupid.artifactId). 否则找不到该plugin id


参考配置：
gradle 自定义plugin官方文档：
https://docs.gradle.org/current/userguide/custom_plugins.html
https://www.jianshu.com/p/af2b0a43133f