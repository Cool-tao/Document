include ':app'  
include ':libraries:someProject'  
assemble  组装项目的输出的任务  
check  运行所有检查的任务  
build  这个任务将执行assemble和check  
clean  这个任务将清理项目的输出  
connectedCheck  运行需要一个已连接的设备或模拟器的检查。它们将在所有已连接的设备上并行运行  
deviceCheck  使用 API 连接到远程设备运行检查。这一个是在 CI 服务器上使用的  
compile是默认的那个，其含义是包含所有的依赖包，即在APK里，compile的依赖会存在。  
provided的意思是提供编译支持，但是不会写入apk。  
compile： main application
androidTestCompile： test application
debugCompile： debug Build Type  
releaseCompile： release Build Type  
runtime：运行时所需要的依赖。默认情况下，包含了编译时期的依赖  
testCompile：编译测试代码时所需要的依赖。默认情况下，包含了编译时产生的类文件，以及编译时期所需要的依赖  
testRuntime：测试运行时期的依赖。默认情况下，包含了上面三个时期的依赖  
branchOneCompile 'com.android.support:appcompat-v7:22.2.0'//只为branchOne添加这个依赖  
