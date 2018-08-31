正常情况下的， 你想要的数据格式  
```
{
  "code": "0",
  "message": "OK",
  "data": {
    "userName": "Alex",
    "age": 25,
    "devList": [
      "华为荣耀4",
      "华为荣耀5",
      "华为荣耀6"
    ],
    "tokenBean": {
      "lastTime": "2017-0421-1436-0330",
      "token": "TOKEN_000000"
    }
  }
}

```
不正常情况下，后端给的数据格式  
```
{
  "code": "0",
  "message": "OK",
  "data": "无此人"
}
```
建实体类JavaBean  
假设所有的数据都是有code、message、data 字段，所有的有用数据都是放在 data字段；当然这是个很美好的愿望，就像我写的程序没有bug一样。  

### Wrapper  
```
public interface Wrapper {     
    String getCode();      
    void setCode(String code);      
    String getMessage();      
    void setMessage(String message); 
}
```

```

```