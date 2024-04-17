# Spring 이미지 갤러리 프로젝
- MySQL, Mybatis, Tiles 를 이용한 갤러리 프로젝트

## Tiles 를 이용한 Layout 설정
- dependency 설정
```xml
	<properties>
		<org.apache.tiles-version>3.0.8</org.apache.tiles-version>
	</properties>

		<!-- https://mvnrepository.com/artifact/org.apache.tiles/tiles-core -->
		<dependency>
			<groupId>org.apache.tiles</groupId>
			<artifactId>tiles-core</artifactId>
			<version>${org.apache.tiles-version}</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.apache.tiles/tiles-extras -->
		<dependency>
			<groupId>org.apache.tiles</groupId>
			<artifactId>tiles-extras</artifactId>
			<version>${org.apache.tiles-version}</version>
		</dependency>
```

## `tiles-context.xml` 에 `View Resolver` 설정
```xml
	<bean class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
		<property name="definitions">
			<list>
				<value>/WEB-INF/spring/appServlet/tiles-layout/*-layout.xml</value>
			</list>		
		</property>
	</bean>
	
	<bean class="org.springframework.web.servlet.view.UrlBasedViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"/>
		<property name="order" value="1"/>
	</bean>
```
- `layout.xml` 위치 설정
- `order` 값을 반드시 1로 설정

## `*-layout.xml` 에 `View Resolver` 를 받아서 `Rendering` 하는 방법 설정
- `default` definition 설정
- 전체 layout 을 감싸는 box-layout
- 여기에는 모든 페이지에 공통으로 include 할 파일을 설정해 준다

```xml
<tiles-definitions>
	<definition name="default"	template="/WEB-INF/views/home.jsp">
		<put-attribute name="head"	value="/WEB-INF/views/includes/include-head.jsp" />
		<put-attribute name="header" value="/WEB-INF/views/includes/include-header.jsp" />
		<put-attribute name="nav" value="/WEB-INF/views/includes/include-main-nav.jsp" />
	</definition>
</tiles-definitions>
```

- `Controller` 에서 retrun 된 문자열을 받아서 처리하는 부분
- `retrun home` 이 실행되면 다음의 definition 이 응답을 받아서 
Rendering 을 할 준비
- `default` 로 설정된 definition 을 layout 으로 삼아 화면을 구성한다

```xml
<tiles-definitions>
	<!-- default 를 상속 -->
	<!-- Controller 에서 retrun"home" 이 요청 되면 -->
	<definition name="home" extends="default">
		<put-attribute name="content" value=""/>
	</definition>

</tiles-definitions>
```