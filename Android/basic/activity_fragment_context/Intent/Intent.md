### Intent  

#### Intent 分为两种类型：  
##### 显式 Intent：  
按名称（完全限定类名）指定要启动的组件。 通常，您会在自己的应用中使用显式 Intent 来启动组件，这是因为您知道要启动的 Activity 或服务的类名。  
例如，启动新 Activity 以响应用户操作，或者启动服务以在后台下载文件。  
##### 隐式 Intent ：  
不会指定特定的组件，而是声明要执行的常规操作，从而允许其他应用中的组件来处理它。   
例如，如需在地图上向用户显示位置，则可以使用隐式 Intent，请求另一具有此功能的应用在地图上显示指定的位置。  
创建隐式 Intent 时，Android 系统通过将 Intent 的内容与在设备上其他应用的清单文件中声明的 Intent 过滤器进行比较，从而找到要启动的相应组件。  
 如果 Intent 与 Intent 过滤器匹配，则系统将启动该组件，并向其传递 Intent 对象。 如果多个 Intent 过滤器兼容，则系统会显示一个对话框，支持用户选取要使用的应用。
```
// Create the text message with a string
Intent sendIntent = new Intent();
sendIntent.setAction(Intent.ACTION_SEND);
sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
sendIntent.setType("text/plain");

// Verify that the intent will resolve to an activity
if (sendIntent.resolveActivity(getPackageManager()) != null) {
    startActivity(sendIntent);
}
```  
❊  接收隐式 Intent  
公布应用可以接收哪些隐式 Intent，请在清单文件中使用 <intent-filter> 元素为每个应用组件声明一个或多个 Intent 过滤器。  
每个 Intent 过滤器均根据 Intent 的操作、数据和类别指定自身接受的 Intent 类型。 仅当隐式 Intent 可以通过 Intent 过滤器之一传递时，系统才会将该 Intent 传递给应用组件。  
每个 Intent 过滤器均由应用清单文件中的 <intent-filter> 元素定义，在 <intent-filter> 内部，您可以使用以下三个元素中的一个或多个指定要接受的 Intent 类型：  
<action>  在 name 属性中，声明接受的 Intent 操作。该值必须是操作的文本字符串值，而不是类常量。  
<data>  使用一个或多个指定数据 URI 各个方面（scheme、host、port、path 等）和 MIME 类型的属性，声明接受的数据类型。  
<category>  在 name 属性中，声明接受的 Intent 类别。该值必须是操作的文本字符串值，而不是类常量。  
```
<activity android:name="ShareActivity">
    <intent-filter>
        <action android:name="android.intent.action.SEND"/>
        <category android:name="android.intent.category.DEFAULT"/>
        <data android:mimeType="text/plain"/>
    </intent-filter>
</activity>
```

[系统Intent介绍](library/introduce.md)      
