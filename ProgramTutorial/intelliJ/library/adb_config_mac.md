### mac 配置 adb环境  
第一步 打开 finder  
shift + command + G 
输入  ~/Library/Android/sdk/platform-tools  

第二部 终端输入  
touch .bash_profile  
open -e .bash_profile  


追加  
export PATH=${PATH}:~/Library/Android/sdk/platform-tools  
