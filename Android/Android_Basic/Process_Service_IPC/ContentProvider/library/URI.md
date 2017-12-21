#### URI  

Uniform Resource Identifier，即统一资源标识符  
自定义URI = content://com.alex.andfun.provider.DownloadContentProvider/UserEntity/1  
自定义URI = 主题名（Schema）://授权信息（Authority）/表名（Path）/记录（ID）  
主题（Schema）：Android所规定的，Content Provider的URI前缀；  
授权信息（Authority）：Content Provider的唯一标识符；  
表名（Path）：Content Provider指向的数据库中的某个table；  
记录（ID）：表中的某条记录，如果没有指定，表示所有记录；  

◆ Uri结构  
基本形式：scheme:scheme-specific-part#fragment  
进一步划分：scheme://authority/path?query#fragment  
终极划分：scheme://host:port/path?query#fragment   
◆ 表名（Path）  
path可以有多个，每个用/连接，比如  
scheme://authority/path1/path2/path3?query#fragment  
◆ query参数  
◑ query参数可以带有对应的值，也可以不带，如果带对应的值用=表示，如:    
scheme://authority/path1/path2/path3?id = 1#fragment，这里有一个参数id，它的值是1  
◑ query参数可以有多个，每个用&连接  
scheme://authority/path1/path2/path3?id = 1&name = mingming&old#fragment  
这里有三个参数：  
参数1：id，其值是:1  
参数2：name，其值是:mingming  
参数3：old，没有对它赋值，所以它的值是null  
◆ 特别的  
在android中，除了scheme、authority是必须要有的，其它的几个path、query、fragment，它们每一个可以选择性的要或不要，但顺序不能变，比如：  
其中"path"可不要：scheme://authority?query#fragment  
其中"path"和"query"可都不要：scheme://authority#fragment  
其中"query"和"fragment"可都不要：scheme://authority/path  
"path","query","fragment"都不要：scheme://authority  
等等……
◆ 通配符：  
* 匹配任意长度的任何有效字符的字符串   
content://com.example.app.provider/*   表示 匹配provider的任何内容  

＃ 匹配任意长度的数字字符的字符串  
content://com.example.app.provider/table/#   表示 匹配provider中的table表的所有行  
