在app的module里的build.gradle文件中，在android { ...}里面加上这样一段代码，即可修改生成的apk的文件名。  

```
applicationVariants.all { variant ->
    variant.outputs.all { output ->
        def outputFile = output.outputFile
        if (outputFile != null && outputFile.name.endsWith('.apk')) {
            //  删除  之前打包的 apk
            def file = new File(outputFile.getParentFile().getParentFile().getParentFile().getAbsolutePath())
            file.deleteDir()
            //生成新的apk  AndFun_basic_debug_1.0_2018_0311_1203.apk
            def fileName = "AndFun_${variant.flavorName}_${variant.buildType.name}_${defaultConfig.versionName}_${buildTime()}.apk"
            outputFileName = fileName
        }
    }
}
//  依赖函数
static def buildTime() {
    def date = new Date()
    def formattedDate = date.format('yyyy_MMdd_HHmm')
    return formattedDate
}  
```
