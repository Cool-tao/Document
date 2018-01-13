#### 简单工厂（Simple Factory Pattern）  

简单工厂模式(Simple Factory Pattern)：又称为静态工厂方法(Static Factory Method)模式，它属于类创建型模式。  
在简单工厂模式中，可以根据参数的不同返回不同类的实例。简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。  



#### 优点  

通过使用工厂类，外界不再需要关心如何创造各种具体的产品，只要提供一个产品的名称作为参数传给工厂，就可以直接得到一个想要的产品对象，  
并且可以按照接口规范来调用产品对象的所有功能（方法）。构造容易，逻辑简单。  

#### 缺点  

1. 简单工厂模式中的if else判断非常多，完全是硬编码，如果有一个新产品要加进来，就要同时添加一个新产品类，并且必须修改工厂类，    
再加入一个 else if 分支才可以， 这样就违背了 “开放-关闭原则”中的对修改关闭的准则了。  
当系统中的具体产品类不断增多时候，就要不断的修改工厂类，  对系统的维护和扩展不利。  

2. 一个工厂类中集合了所有的类的实例创建逻辑，违反了高内聚的责任分配原则，将全部的创建逻辑都集中到了一个工厂类当中，  
所有的业务逻辑都在这个工厂类中实现。什么时候它不能工作了，整个系统都会受到影响。  
因此一般只在很简单的情况下应用，  比如当工厂类负责创建的对象比较少时。  

3. 简单工厂模式由于使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。  

> 简单工厂模式在Java中的应用  

- DateFormat  
public final static DateFormat getDateInstance();  
public final static DateFormat getDateInstance(int style);  
public final static DateFormat getDateInstance(int style,Locale locale);  

- Cipher  
Cipher cp = Cipher.getInstance("AES");  

