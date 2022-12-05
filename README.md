# 모여봐요 웹캠으로

## 직접 만나지 않고도 서로 다른 장소에서 같은 공간에 함께 있는 것처럼 사진을 촬영할 수 있는 서비스

## 기획 배경

코로나19 상황으로 인해 모여서 단체사진을 찍을 수 있는 기회가 많이 사라졌습니다. 누군가와 만나더라도 마스크를 착용한 채로 사진을 찍어야 합니다.

또한 화상 회의 프로그램을 이용한 비대면 만남이 증가했지만, 단순 스크린샷으로 단체사진을 남기기엔 직접 촬영한 사진 같지 않아 아쉬움이 있었습니다.

‘모여봐요 웹캠으로’ 프로젝트는 사용자들에게 웹캠을 이용하여 비대면으로도 실제로 함께 사진을 찍는 것과 같은 경험을 제공하고자 시작되었습니다.

## 프로젝트 기간

**2022.07.11~2022.08.19**

## 순간뽀짝 팀원 소개

🐤**이나경**: 팀장, BE

🐬**김소현**: FE

👶**김승준**: BE

🎅**김우창**: FE

😎**정태윤**: BE, infra

🐲**하미르**: BE

## 사용자 가이드

[링크](https://www.figma.com/file/uPyliDMpivAGupTmqQcgSO/Moweb-%EC%82%AC%EC%9A%A9%EC%9E%90-%EA%B0%80%EC%9D%B4%EB%93%9C?node-id=972%3A329)

## 핵심 기능

- 개인 카메라 배경 지우기
  ![image](https://user-images.githubusercontent.com/97646054/205663330-9f16551d-3fba-4eb1-b045-72d4f757e8e4.png)
- 링크로 방 입장

  ![초대하기](/uploads/4b5ee735ebf244b75a123e5293e9e895/초대하기.gif)
  ![링크로입장](/uploads/fc357bc269de4694dfcb64d0dfcae64c/링크로입장.gif)

- 실시간으로 함께 사진 찍기

  ![촬영](/uploads/39896c97fd8d7fd09bc0c5ce95f59f32/촬영.gif)

- 레이어순서 변경
  ![레이어](/uploads/c18921740a8add9f09becbf60ab0fdb7/레이어.gif)
- 가상 배경 색상 선택 가능
  ![배경선택](/uploads/69bd254b0d9f9d6367567c201902ea4e/배경선택.gif)
- 사진 저장/공유
  ![사진저장](/uploads/5101fa5740fe36b2de0cb1e91a9f92de/사진저장.gif)
  ![공유하기](/uploads/92594423511e9bf48bbfd597e743155c/공유하기.gif)
  ![공유하기2](/uploads/9072cf28f15707fb6e460e400ae14a43/공유하기2.gif)

## 커밋 규칙

```yaml
Init: 파일/폴더 생성
Delete: 파일/폴더 삭제
Rename: 파일/폴더 이름 변경
Feature: 기능 구현
Add: 추가
Update: 코드 수정
Remove: 코드 삭제
Test: 테스트
Fix: 버그 수정
Refactor: 리팩토링
Docs: 문서 작업
Design: 디자인 작업
Help: 도움 요청
```

## 개발 환경 및 기술 스택

<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">

<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">

<img src="https://img.shields.io/badge/vuetify-1867C0?tyle=for-the-badge&logo=Vuetify&logoColor=white">

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">

<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white">

<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white">

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">

<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">

### 백엔드

- **IDE** : IntelliJ
- **Framework** : Spring boot
- **JAVA** : 17
- **Build** : Gradle
- **WAS** : Tomcat
- **DBMS** : MySql
- **DB API** : JPA
- WebSocket
- OpenVidu

### 프론트엔드

- **IDE** : Vscode
- **Framework/Library** :
  - Vue2
  - Vuetify
  - mediapipe

### 서버

- AWS
- Docker
- nginx
- jenkins

## 배포 가이드

[링크](exec/서울_5반_a507_빌드및배포문서.pdf)

## 외부 서비스 사용 문서

[링크](exec/서울_5반_a507_외부서비스사용문서.pdf)

## 버전 어플리케이션 업데이트 정보

version 1.0 release : 2022.08.13.

version 1.1 release : 2022.08.15.

-결과 사진 생성 방식 변경

## 버그

- 이미지 결과화면 업로드 시 모니터 해상도가 매우커서(4K++) 이미지 용량을 넘을 경우
  → 결과화면 이미지 해상도를 고정해서 만드는 방식으로 수정 준비중
