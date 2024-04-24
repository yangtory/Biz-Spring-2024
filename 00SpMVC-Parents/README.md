# Spring MVC, Thymeleaf 를 이용한 화면 템플린 구현
- Spring MVC(5) 에서 Thymeleaf 사용하기
- 전통적으로 SpringMVC 에서는 JSP 를 사용하여 화면을 구현했다. 최근 JSP 이외에 여러가지 화면 구현 도구들이 많이 생겨났다.
- Spring 프로젝트에서 화면을 구현하는 도구를 `화면 템플릿` 이라고 한다.
- 그 중에서 `SpringMVC` 를 이후에 `Spring Boot` 가 나타나면서 많이 사용하는 화면 템플릿이 있다. 바로 `Thymeleaf` 이다.
- `HTML` 방식의 정적 화면을 구현하고, 거기에 여러 도구를 첨가하는 방식으로 화면이 구현된다.
- 똑같은 화면 구현 파일을 화면 디자이너와 개발자가 공유하는데 탁월한 역할을 수행한다.
- 개발자와 디자이너가 충돌하지 않고 협업할 수 있는 좋은 도구이다.

## 설정
- dependency 설정
- `Thymeleaf-springX` : 사용하는 `Spring Framework` 버전에 따라 적정한 도구를 설정
- `Thymeleaf-layout-xxx` : Thymeleaf 에서 layout 설정을 쉽게 구현할 수 있도록 도와주는 `3rd Lib` 이다.
- `layout` 도구는 `nz.net.ultraq.*` 에서 배포하는 도구를 주로 많이 사용한다.

```xml
		<!-- https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf-spring5 -->
		<dependency>
			<groupId>org.thymeleaf</groupId>
			<artifactId>thymeleaf-spring5</artifactId>
			<version>3.1.2.RELEASE</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/nz.net.ultraq.thymeleaf/thymeleaf-layout-dialect -->
		<dependency>
			<groupId>nz.net.ultraq.thymeleaf</groupId>
			<artifactId>thymeleaf-layout-dialect</artifactId>
			<version>3.3.0</version>
		</dependency>

```