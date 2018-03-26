## 自定义gradle plugin
* New Java Lib
* 删掉 man/java ,新建一个main/groovy,包名 ...
* 由于无法直接new groovy class, 所以我们new java class, 再改后缀 .groovy

> Notice that the properties filename matches the plugin id and is placed in the resources folder, and that the implementation-class property identifies the Plugin implementation class.

resources下的properties 文件名必须匹配 plugin id (即 groupid.artifactId). 否则找不到该plugin id


## 字节码修改
使用 Javassist 、ASM 修改，注入代码
ASM生成类来说，操作并没有Javassist简洁易懂，但对于class文件处理是最快的
Javassist最强只能做到方法内的表达式的替换

几种动态编程方法相比较，在性能上Javassist高于反射，但低于ASM，因为Javassist增加了一层抽象。
在实现成本上Javassist和反射都很低，而ASM由于直接操作字节码，相比Javassist**源码级别**的api实现成本高很多

## debug gradle plugin
1. new 远程连接，默认的即可
2. 执行编译命令：./gradlew assembleXXX -Dorg.gradle.debug=true
3. 选中新建的 romote, debug 按钮，然后再切回到 terminal 窗口，就可以了

* 有时候，studio 不稳定，可以将 2，3步骤换下


参考配置：
gradle 自定义plugin官方文档：
https://docs.gradle.org/current/userguide/custom_plugins.html
https://www.jianshu.com/p/af2b0a43133f