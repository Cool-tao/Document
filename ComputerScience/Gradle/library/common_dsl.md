include ':app'  
include ':libraries:someProject'  
assemble  组装项目的输出的任务  
check  运行所有检查的任务  
build  这个任务将执行assemble和check  
clean  这个任务将清理项目的输出  
connectedCheck  运行需要一个已连接的设备或模拟器的检查。它们将在所有已连接的设备上并行运行  
deviceCheck  使用 API 连接到远程设备运行检查。这一个是在 CI 服务器上使用的  
