# Spring 파일 업로드 프로젝트
- Gallery-01 에서는 이미지를 base64 로 변환하여 DB 테이블에 저장했다.
- 이런 방식은 오래전에 파일의 크기가 크지 않을때는 괜찮은 방법이었다.
- 요즘은 카메라 성능 등이 좋아져 이미지 크기가 큰 경우가 많다.
- 일반적으로 2MB 이하의 이미지는 테이블에 저장해도 무리가 없지만 그 이상의 이미지는 전체 DBMS 시스템 자체의 성능이 불안정해지고 느려지게 된다.
- 이미지 파일은 파일 자체를 서버에 업로드하여 서버의 폴더에 저장하고, DB 테이블 에는 파일에 대한 정보(파일이름)만 저장하는 형태로 구현한다.
- 이미지 파일(또는 기타 파일)을 업로드하는 여러가지 도구를 활용해야 한다.

## 도구 설치
- fileUpload 를 위한 dependency 설정
```xml
<!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.5</version>
</dependency>

```

- `commons-fileUpload` 를 dependency 에 설정 한 후 `Maven dependencies` 를 확인하여 `commons-io` 항목이 없으면 아래 항목을 추가로 설치해 주어야 한다.
```xml
<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.16.1</version>
</dependency>

```