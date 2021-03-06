### 回到我们拿到bean的方法getBean，我们再从头开始，ctrl+左击进入到BeanFactory类，我们可以看到他又四个实现这个方法的类。
![Image](./images/3.png)
### 我们要如何知道我们使用的AnnotationConfigApplicationContext类取bean的能力来自于那个类呢?
#### 首先我们打开AnnotationConfigApplicationContext类可以看到其继承了一个GenericApplicationContext，当前能力绝对来自于它。
![Image](./images/4.png)
#### 分析此类的结构，看到其继承了AbstractApplicationContext，其类拥有了getBean方法。
![Image](./images/5.png)
#### 我们到AbstractApplicationContext类的getBean(String name, Class<T> requiredType)方法处打一个断点。
![Image](./images/6.png)
##### assertBeanFactoryActive();方法解析
![Image](./images/7.png)
```text
    根据英文意思，大概可以了解到这个方法是用来检测BeanFactory是否处于活动状态的方法，假如spring容器已经关闭(不处于活跃状态)则会抛出异常
【throw new IllegalStateException(getDisplayName() + " has been closed already");】，假如spring容器正在创建或者刷新中就
会抛出【throw new IllegalStateException(getDisplayName() + " has not been refreshed yet");】
```
##### getBeanFactory()方法解析
```text
public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    可以看到AbstractApplicationContext类的getBeanFactory()方法是一个抽象接方法，AnnotationConfigApplicationContext继承的
GenericApplicationContext类实现了这个方法，我们需要从哪儿入手。
```
我们来到GenericApplicationContext类查看此方法，英文大概意思是：从这个context返回一个单例的BeanFactory对象。
```java
    /**
	 * Return the single internal BeanFactory held by this context
	 * (as ConfigurableListableBeanFactory).
	 */
	@Override
	public final ConfigurableListableBeanFactory getBeanFactory() {
		return this.beanFactory;
	}
```
###### 这个beanFactory是从哪里来的呢？
我们查看这个类的构造方法，我们可知beanFactory在构造器方法就进行了初始化赋值。
```java
	/**
	 * Create a new GenericApplicationContext.
	 * @see #registerBeanDefinition
	 * @see #refresh
	 */
	public GenericApplicationContext() {
		this.beanFactory = new DefaultListableBeanFactory();
	}
```
##### getBean()方法解析
###### beanFactory的getBean方法有四个实现类。
![Image](./images/9.png)
###### 综合上getBeanFactory()方法的分析，我们可以知道，beanFactory的实现指向DefaultListableBeanFactory类，我们到DefaultListableBeanFactory进行解析此类结构
![Image](./images/10.png)
###### 由解析DefaultListableBeanFactory的结构我们可以知道，getBean方法实现是在AbstractBeanFactory类中，我们通过BeanFactory找到对应的实现方法
```java
    @Override
	public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return doGetBean(name, requiredType, null, false);
	}
```
##### doGetBean()方法解析
###### 注释大概意思为，返回一个实例，这个实例bean可以是共享或者独立的，也就我们常说的单例(Singleton)或者多例(Prototype)的bean
![Image](./images/11.png)
```text
    name: bean的名字
    requiredType: 创建的bean的类型
    args: 创建bean实例需要的参数
    typeCheckOnly: 是否对实例进行类型检查 (我也不太清楚其作用，但是不影响)
```
###### 方法知识点一： final String beanNam                                                                                                                                                                                                                                                                                                                                                                                                                              e = transformedBeanName(name);
transformedBeanName方法：返回这个bean的名称，如果有必要可以去除FactoryBean的'&'前缀去掉。
```java
	protected String transformedBeanName(String name) {
		return canonicalName(BeanFactoryUtils.transformedBeanName(name));
	}
```
BeanFactoryUtils.transformedBeanName方法：返回这个bean的名称，如果有必要可以去除FactoryBean的'&'前缀去掉。
```java
    public static String transformedBeanName(String name) {
		Assert.notNull(name, "'name' must not be null");
		if (!name.startsWith(BeanFactory.FACTORY_BEAN_PREFIX)) {
			return name;
		}
		return transformedBeanNameCache.computeIfAbsent(name, beanName -> {
			do {
				beanName = beanName.substring(BeanFactory.FACTORY_BEAN_PREFIX.length());
			}
			while (beanName.startsWith(BeanFactory.FACTORY_BEAN_PREFIX));
			return beanName;
		});
	}
```
canonicalName方法：从aliasMap(别名map)中获取到真正的bean的ID (不指定bean的名称的时候，spring会默认以类名为bean的ID)
```java
	/** Map from alias to canonical name. */
	private final Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    public String canonicalName(String name) {
		String canonicalName = name;
		// Handle aliasing...
		String resolvedName;
		do {
			resolvedName = this.aliasMap.get(canonicalName);
			if (resolvedName != null) {
				canonicalName = resolvedName;
			}
		}
		while (resolvedName != null);
		return canonicalName;
	}
```
