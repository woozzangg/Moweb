# 모여봐요 웹캠으로 by 순간뽀짝

---
![Untitled](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/eeea2b46-959d-4770-bf3c-b12a9df56b27/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220729%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220729T025414Z&X-Amz-Expires=86400&X-Amz-Signature=7794679e60e8aca4ebc20a1949dd2d94aadfeb59ca9430b8d8da226da4d08bf4&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

### 직접 만나지 않고도 서로 다른 장소에서 같은 공간에 함께 있는 것처럼 사진을 촬영할 수 있는 서비스

---

## **순간뽀짝 팀원 소개**

🐣**이나경**: 팀장, BE

🐬**김소현**: FE

👶**김승준**: BE

🎅**김우창**: FE

😎**정태윤**: BE, infra

🐲**하미르**: BE

---

## **기획 배경**

코로나19 상황으로 인해 모여서 단체사진을 찍을 수 있는 기회가 많이 사라졌습니다. 누군가와 만나더라도 마스크를 착용한 채로 사진을 찍어야 합니다.

또한 화상 회의 프로그램을 이용한 비대면 만남이 증가했지만, 단순 스크린샷으로 단체사진을 남기기엔 직접 촬영한 사진 같지 않아 아쉬움이 있었습니다. 

‘모여봐요 웹캠으로’ 프로젝트는 사용자들에게 웹캠을 이용하여 비대면으로도 실제로 함께 사진을 찍는 것과 같은 경험을 제공하고자 시작되었습니다.

---

## **핵심 기능**

- 개인 카메라 배경 지우기
- 가상 배경 선택 가능
- 실시간으로 함께 사진 찍기
- 링크로 방 입장
- 사진 저장
- 사진 SNS로 공유하기

## 핵심 **기술**

- WebRTC
- WebSocket
- REST API
- AWS

## 기획 산출물

- 와이어프레임
- 기능명세서
- ERD
- API 명세서

---

## 커밋 규칙

```yaml
Init : 파일/폴더 생성
Delete : 파일/폴더 삭제
Rename : 파일/폴더 이름 변경
Feature : 기능 구현
Add : 추가
Update : 코드 수정
Remove : 코드 삭제
Test : 테스트
Fix : 버그 수정
Docs : 문서 작업
Design: 디자인 작업
Help: 도움 요청
```

---

## 개발 환경 및 기술 스택

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">


<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">


<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">


<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">


<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">


<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white">


<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">


<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">


<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">


<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

### 백엔드

- **IDE** : IntelliJ
- **Framework** : Spring boot
- **JAVA** : 17
- **Build** : Gradle
- **WAS** : Tomcat
- **DBMS** : MySql
- **DB API** : JPA

### 프론트엔드

- **IDE** : Vscode
- **Framework/Library** : Vue2

### 서버

- AWS
- Docker
