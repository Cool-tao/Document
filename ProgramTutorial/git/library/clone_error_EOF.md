### 错误信息  
```
Cloning into '/Users/alex/WorkSpace/sourceTree/coolProject_e_android'...
fatal: The remote end hung up unexpectedly
fatal: early EOF
fatal: index-pack failed
```

◆ 解决办法  
在sourceTree下 生成一个文件夹，与仓库名同名，coolProject_e_android  
```
cd /Users/alex/WorkSpace/sourceTree/  
git clone -b 分支名 --depth 1 http://git地址.git
```

◆ 网上见到的解决办法，注入此类，并不适合我  
```
git config --global http.postBuffer 524288000  
git config --global core.compression 0
git config --global --add core.compression -1
git config --list  
```
最终在 Users/alex/.gitconfig 中，会形成  
记得name email  要填写的  
```
[core]
	excludesfile = /Users/alex/.gitignore_global
	compression = 0
	compression = -1
[difftool "sourcetree"]
	cmd = opendiff \"$LOCAL\" \"$REMOTE\"
	path = 
[mergetool "sourcetree"]
	cmd = /Applications/Sourcetree.app/Contents/Resources/opendiff-w.sh \"$LOCAL\" \"$REMOTE\" -ancestor \"$BASE\" -merge \"$MERGED\"
	trustExitCode = true
[user]
	name = alex
	email = alex@oop.com
[commit]
	template = /Users/alex/.stCommitMsg
[http]
	postBuffer = 1048576000
```

◆ compression  
compression 是压缩的意思，从 clone 的终端输出就知道，服务器会压缩目标文件，然后传输到客户端，客户端再解压。    
取值为 [-1, 9]，  
-1 以 zlib 为默认压缩库，  
0 表示不进行压缩，  
1..9 是压缩速度与最终获得文件大小的不同程度的权衡，  
数字越大，压缩越慢，  当然得到的文件会越小。  

