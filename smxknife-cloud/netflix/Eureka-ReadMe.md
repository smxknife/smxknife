# Eureka 使用中遇到的问题

### 1. 定义元数据

```properties
# 这种情况在元数据信息里会乱码
eureka.instance.metadata-map.hello=世界

# 这种情况整个服务会启动失败
eureka.instance.metadata-map.你好=世界

# 所以，直接采用英文的key和value就可以
eureka.instance.metadata-map.hello=world
```

### 2. eureka server 如何集群多台

配置文件如下：

```
spring:
  application:
    name: eureka
---
spring:
  config:
    activate:
      on-profile: peer1
server:
  port: 7901
eureka:
  client:
    service-url:
      defaultZone: http://peer2:7902/eureka
  instance:
    appname: peer1
---
spring:
  config:
    activate:
      on-profile: peer2
server:
  port: 7902
eureka:
  client:
    service-url:
      defaultZone: http://peer1:7901/eureka
  instance:
    appname: peer2
```

上面明明配置两台server相互注册，最终unavailable-replicas里面还是不可用

有两点注意：

> * spring.application.name要配置成一样的
> * eureka.instance.hostname 一定要配置，而且要不一样，而上面没有配置hostname配置的是appname

### 3. 配置spring-boot actuator

* pom引入依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

注意点：

* springboot 2.0 以前是开启了所有端点，而2.0 只开启了health和info两个端点
* 如果要开启所有端点，配置如下：

```properties

# 启动所有端点，springboot2.x默认只开启了health和info两个端点
management.endpoints.web.exposure.include=*
# 启动shutdown端点（仅支持post）
management.endpoint.shutdown.enabled=true
# 默认情况下，是never，只会显示status，如果改为always，那么会显示详细信息
management.endpoint.health.show-details=always

# 默认情况下，eureka server是通过心跳包来确定注册到server的服务是up还是down
# 但是这会存在弊端，有一种情况是，服务虽然在，但是已经无法继续提供服务了，这种情况下心疼包还是正常的，对server来说该服务还是正常的
# 所以，这种情况下是有问题的
# 开启该配置后，会改变eureka server对服务健康检查的方式，可以采用actuator的health端点进行检测
eureka.client.healthcheck.enabled=true
```

```java
// 上面eureka.client.healthcheck.enabled=true可以配合的java代码
@Service
public class HealthStatusChecker implements HealthIndicator {

	private volatile boolean up = true;

	@Override
	public Health health() {
		if (up) {
			return new Health.Builder().withDetail("quality", 192).up().build();
		}
		return new Health.Builder().withDetail("quality", 170).down().build();
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}
}

@RestController
@RequestMapping("/health/ctl")
public class HealthStatusController {

	@Autowired
	HealthStatusChecker healthStatusChecker;

	@RequestMapping("up")
	public String ctlUp() {
		healthStatusChecker.setUp(true);
		return "health status set to up: real status is " + healthStatusChecker.isUp();
	}

	@RequestMapping("down")
	public String ctlDown() {
		healthStatusChecker.setUp(false);
		return "health status set to down: real status is " + healthStatusChecker.isUp();
	}

	/**
	 * 这种情况下比较常用，比如catch到某种异常，表示该服务存在问题，想让服务下线，那么就可以按照下面的方式
	 * @return
	 */
	@RequestMapping("mock/exception")
	public String ctlException() {
		try {
			int a = 0;
			// 这里mock异常，在catch里面将服务状态设置为down
			int b = 3 / a;
		} catch (Exception e) {
			healthStatusChecker.setUp(false);
		}
		return String.valueOf(healthStatusChecker.isUp());
	}
}
```

### 4. 配置spring-boot security后报错

错误信息如下：

```shell
Request execution failure with status code 403; retrying on another server if available
com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
```

原因是因为：加入spring-boot security包后，里面默认的不允许跨域访问导致的，
禁止方法（允许跨域访问）如下：

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		super.configure(http);
	}
}
```

### 5. openfign调接口提示java.net.UnknownHostException: user-provider

```java
@FeignClient(name = "userApi", url = "http://user-provider/user") 
public interface UserApi {

	@GetMapping("alive")
	String isAlive();
}
```

有两种修改方式：

* 直连

`@FeignClient(name = "userApi", url = "http://localhost:9000/user") // 这种方式是对的，但是这里相当于直连，没有负载均衡`

* 通过name

`@FeignClient(name = "user-provider", path = "user")`

* 通过中间抽象API层，可以简化开发

> 定义一个api的项目，里面提供接口，如 UserApi
> provider里面的controller实现该接口
> consumer的接口继承该接口

### 6. openfeign通过@GetMapping带参数请求

```java
// provider 的controller代码
@Override
public User getById(Long id) {
    return new User(id, "hello" + id);
}

// 中间接口或者可以说feign接口
@GetMapping("getById")
User getById(Long id);
```

但是会提示出错

```shell
Fri Apr 30 22:29:11 CST 2021
There was an unexpected error (type=Internal Server Error, status=500).
[405] during [GET] to [http://user-provider/user/getById] [UserExtApi#getById(Long)]: [{"timestamp":"2021-04-30T14:29:11.417+00:00","status":405,"error":"Method Not Allowed","trace":"org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported\n\ta... (4906 bytes)]
feign.FeignException$MethodNotAllowed: [405] during [GET] to [http://user-provider/user/getById] [UserExtApi#getById(Long)]: [{"timestamp":"2021-04-30T14:29:11.417+00:00","status":405,"error":"Method Not Allowed","trace":"org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported\n\ta... (4906 bytes)]
	at feign.FeignException.clientErrorStatus(FeignException.java:203)
	at feign.FeignException.errorStatus(FeignException.java:177)
	at feign.FeignException.errorStatus(FeignException.java:169)
	at feign.codec.ErrorDecoder$Default.decode(ErrorDecoder.java:92)
	at feign.AsyncResponseHandler.handleResponse(AsyncResponseHandler.java:96)
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandle
	...
```

解决方案1：
接口处明明定义的是GetMapping，请求的时候会改为PostMapping，如果在不想改为PostMapping的情况下，可以在pom里面引入
(Feign默认所有带参数的请求都是Post)

```xml
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-httpclient</artifactId>
</dependency>
```
加入上面的依赖后，还会出现参数传递不过去的情况，所以需要在接口处添加@Requestparam

解决方案2：

* 从上面的错误来看，参数时需要使用PostMapping
* 如果改为PostMapping，那么请求不会报错，但是参数是传递不过去的
* 需要在provider的controller的参数加上@RequstBody