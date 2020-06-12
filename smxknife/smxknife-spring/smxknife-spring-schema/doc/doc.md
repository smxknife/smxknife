# 自定义Spring XML扩展机制

## 步骤

> 1. 自定义创建XML Schema文件 .xsd
> 2. 自定义处理器（实现NamespaceHandler接口）
> 3. 自定义解析器（实现BeanDefinitionParser接口，可多个）
> 4. 注册到Spring容器中

## smxknife.xsd

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xsd:schema xmlns="http://www.smxknife.com/schema/smxknife.xsd"
			xmlns:xsd="http://www.w3.org/2001/XMLSchema"
			xmlns:beans="http://www.springframework.org/schema/beans"
			targetNamespace="http://www.smxknife.com/shcema/smxknife">

	<xsd:import namespace="http://www.springframework.org/schema/beans"/>

	<xsd:element name="application">
		<xsd:complexType>
			<xsd:complexContent>
				<xsd:extension base="beans:identifiedType">
					<xsd:attribute name="name" type="xsd:string" use="required"/>
				</xsd:extension>
			</xsd:complexContent>
		</xsd:complexType>
	</xsd:element>

	<xsd:element name="service">
		<xsd:complexType>
			<xsd:complexContent>
				<xsd:extension base="beans:identifiedType">
					<xsd:attribute name="name" type="xsd:string" use="required"/>
				</xsd:extension>
			</xsd:complexContent>
		</xsd:complexType>
	</xsd:element>


</xsd:schema>
```
xsd定义好之后，需要定义NamespaceHandler

```java
public class SmxknifeNamespaceHandler extends NamespaceHandlerSupport
public abstract class NamespaceHandlerSupport implements NamespaceHandler
public interface NamespaceHandler {
    void init();

    @Nullable
    BeanDefinition parse(Element var1, ParserContext var2);

    @Nullable
    BeanDefinitionHolder decorate(Node var1, BeanDefinitionHolder var2, ParserContext var3);
}
```

parse 和 decorate 方法可以完成元素节点的组装并通过 ParserContext 注册到 Ioc 容器中，但实际我们并没有调用这两个方法，而是通过 init() 方法注册 BeanDefinitionParser 来完成解析节点以及注册 Bean 的工作，所以对于 NamespaceHandler，我们主要关心 init 中注册的两个 BeanDefinitionParser 即可


