代码上传 JCenter / Maven
* Maven
1. 本地maven repo test
在要publish的module的build.gradle配置：
```groovy
apply plugin: 'maven'

uploadArchives {
    repositories {
        mavenDeployer {
            pom.groupId = 'com.geng.insert'
            pom.artifactId = 'insert-plugin'
            pom.version = '1.0.0'

            //本地的Maven地址设置: project/repo
            repository(url: uri('../repo'))
        }
    }
}
```
2. 远程maven仓库
```groovy
apply plugin: 'com.github.dcendents.android-maven'

uploadArchives {
    repositories.mavenDeployer {
        repository(url: repositoryUrl) {
            authentication(userName: getPropertyFromLocalProperties("USER"), password: getPropertyFromLocalProperties("PASSWORD"))
        }
    }
}
// 可以参考开源库 robust 的相关配置
```

* JCenter
```groovy
apply plugin: 'com.jfrog.bintray'

bintray {
    user = getPropertyFromLocalProperties("bintray.user")
    key = getPropertyFromLocalProperties("bintray.apikey")
    configurations = ['archives']
    pkg {
        repo = 'maven'
        name = "${project.group}:${project.name}"
        userOrg = 'meituan'
        licenses = ['Apache-2.0']
        websiteUrl = 'http://tech.meituan.com/android_autopatch.html'
        vcsUrl = 'https://github.com/Meituan-Dianping/Robust'
        publish = true
    }
}
```

> 注意：使用 
apply plugin: 'com.jfrog.bintray'
apply plugin: 'com.github.dcendents.android-maven' 
需要在 project/build.gradle 配置 classpath


可以用的plugin:
https://github.com/bintray/gradle-bintray-plugin
https://github.com/novoda/bintray-release

https://github.com/dcendents/android-maven-gradle-plugin

参考：
https://www.jianshu.com/p/31410d71eaba
https://github.com/Meituan-Dianping/Robust/blob/master/gradle_mvn_push.gradle