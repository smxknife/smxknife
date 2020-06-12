# IoC系列

在IoC中存在两大容器接口系列，分别是BeanFactory和ApplicationContext

* BeanFactory是简单容器接口系列，实现容器的最基本功能
* ApplicationContext应用上下文，容器的高级形态，增加了面向框架的特性

Spring提供基本的IoC容器的接口和实现的基础上，通过定义BeanDefinition来管理基于Spring的应用中各种对象以及他们之间的相互依赖关系

## IoC容器设计

* BeanFactory -> HierarchicalBeanFactory -> ConfigurableBeanFactory

这是一条主要的设计路线，BeanFactory定义了基本IoC容器的规范

```java
public interface BeanFactory {
    String FACTORY_BEAN_PREFIX = "&";

    Object getBean(String var1) throws BeansException;

    <T> T getBean(String var1, Class<T> var2) throws BeansException;

    Object getBean(String var1, Object... var2) throws BeansException;

    <T> T getBean(Class<T> var1) throws BeansException;

    <T> T getBean(Class<T> var1, Object... var2) throws BeansException;

    boolean containsBean(String var1);

    boolean isSingleton(String var1) throws NoSuchBeanDefinitionException;

    boolean isPrototype(String var1) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, ResolvableType var2) throws NoSuchBeanDefinitionException;

    boolean isTypeMatch(String var1, Class<?> var2) throws NoSuchBeanDefinitionException;

    Class<?> getType(String var1) throws NoSuchBeanDefinitionException;

    String[] getAliases(String var1);
}
```

HierarchicalBeanFactory继承BeanFactory后，增加了getParentBeanFactory()的接口功能，这样使BeanFactory具备了双亲IoC容器的管理功能

```java

public interface HierarchicalBeanFactory extends BeanFactory {
    BeanFactory getParentBeanFactory();

    boolean containsLocalBean(String var1);
}
```

ConfigurableBeanFactory接口，定义了一些BeanFactory的一些配置功能，
比如通过setParentBeanFactory设置双亲IoC容器、addBeanPostProcessor配置Bean的后置处理器

```java

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void setParentBeanFactory(BeanFactory var1) throws IllegalStateException;

    void setBeanClassLoader(ClassLoader var1);

    ClassLoader getBeanClassLoader();

    void setTempClassLoader(ClassLoader var1);

    ClassLoader getTempClassLoader();

    void setCacheBeanMetadata(boolean var1);

    boolean isCacheBeanMetadata();

    void setBeanExpressionResolver(BeanExpressionResolver var1);

    BeanExpressionResolver getBeanExpressionResolver();

    void setConversionService(ConversionService var1);

    ConversionService getConversionService();

    void addPropertyEditorRegistrar(PropertyEditorRegistrar var1);

    void registerCustomEditor(Class<?> var1, Class<? extends PropertyEditor> var2);

    void copyRegisteredEditorsTo(PropertyEditorRegistry var1);

    void setTypeConverter(TypeConverter var1);

    TypeConverter getTypeConverter();

    void addEmbeddedValueResolver(StringValueResolver var1);

    boolean hasEmbeddedValueResolver();

    String resolveEmbeddedValue(String var1);

    void addBeanPostProcessor(BeanPostProcessor var1);

    int getBeanPostProcessorCount();

    void registerScope(String var1, Scope var2);

    String[] getRegisteredScopeNames();

    Scope getRegisteredScope(String var1);

    AccessControlContext getAccessControlContext();

    void copyConfigurationFrom(ConfigurableBeanFactory var1);

    void registerAlias(String var1, String var2) throws BeanDefinitionStoreException;

    void resolveAliases(StringValueResolver var1);

    BeanDefinition getMergedBeanDefinition(String var1) throws NoSuchBeanDefinitionException;

    boolean isFactoryBean(String var1) throws NoSuchBeanDefinitionException;

    void setCurrentlyInCreation(String var1, boolean var2);

    boolean isCurrentlyInCreation(String var1);

    void registerDependentBean(String var1, String var2);

    String[] getDependentBeans(String var1);

    String[] getDependenciesForBean(String var1);

    void destroyBean(String var1, Object var2);

    void destroyScopedBean(String var1);

    void destroySingletons();
}
```

* 第二条设计主线是以ApplicationContext应用上下文接口为核心的接口设计

BeanFactory -> (ListableBeanFactory, HierarchicalBeanFactory) -> ApplicationContext -> (WebApplicationContext, ConfigurableApplicationContext)

> ListableBeanFactory它可以枚举所有的bean实例，而不是客户端通过名称一个一个的查询得出所有的实例。要预加载所有的bean定义的beanfactory可以实现这个接口来

...省略n多字，详细看SpringIoC.