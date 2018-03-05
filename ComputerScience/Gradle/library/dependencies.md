compile fileTree(dir: 'libs', include: ['*.jar'])  
compile 'com.android.support:appcompat-v7:25.0.0'    
compile project(':YibaAnalytics')    
compile project(':library:YibaAnalytics')    
compile files('libs/YibaAnalytics5.jar')    

debugCompile project(path: ':library', configuration: 'debug')  
releaseCompile project(path: ':library', configuration: 'release')    

    
