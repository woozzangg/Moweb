# README

# 모여봐요 웹캠으로 by 순간뽀짝

UCC 예정

## 직접 만나지 않고도 서로 다른 장소에서 같은 공간에 함께 있는 것처럼 사진을 촬영할 수 있는 서비스

## **기획 배경**

코로나19 상황으로 인해 모여서 단체사진을 찍을 수 있는 기회가 많이 사라졌습니다. 누군가와 만나더라도 마스크를 착용한 채로 사진을 찍어야 합니다.

또한 화상 회의 프로그램을 이용한 비대면 만남이 증가했지만, 단순 스크린샷으로 단체사진을 남기기엔 직접 촬영한 사진 같지 않아 아쉬움이 있었습니다. 

‘모여봐요 웹캠으로’ 프로젝트는 사용자들에게 웹캠을 이용하여 비대면으로도 실제로 함께 사진을 찍는 것과 같은 경험을 제공하고자 시작되었습니다.

## **순간뽀짝 팀원 소개**

🐣**이나경**: 팀장, BE

🐬**김소현**: FE

👶**김승준**: BE

🎅**김우창**: FE

😎**정태윤**: BE, infra

🐲**하미르**: BE

# 사용자 가이드

[링크](https://www.figma.com/file/uPyliDMpivAGupTmqQcgSO/Moweb-%EC%82%AC%EC%9A%A9%EC%9E%90-%EA%B0%80%EC%9D%B4%EB%93%9C?node-id=972%3A329)

## **핵심 기능**

- 개인 카메라 배경 지우기
    
    ![배경제거](/uploads/4d309dad0ca7a651999aca3b89babd77/배경제거.png)
    
- 링크로 방 입장

![링크_초대_창](/uploads/4fc6ab9fe11fb58e89296a6c411121df/링크_초대_창.png)

- 실시간으로 함께 사진 찍기
    - ucc 찍은거에서 가져오기 사진 or gif
- 레이어순서 변경
    
    ![레이어변경](/uploads/39d6c57bc634d12c94fd278570075c2b/레이어변경.png)
    
- 가상 배경 색상 선택 가능
    
    ![배경_색_선택](/uploads/be64c4c376cae45bcba5f3b9661df468/배경_색_선택.png)
    
- 사진 저장/공유
    
    ![공유](/uploads/522be82a09d9bde7d6c1938225084de1/공유.png)
    

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
Refactor : 리팩토링
Docs : 문서 작업
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

## 버전 어플리케이션 업데이트 정보

version 1.0 release : 2022.08.13.

version 1.1 release : 2022.08.15.

-결과 사진 생성 방식 변경

## 버그 정도

- 이미지 결과화면 업로드 시 모니터 해상도가 매우커서(4K++) 이미지 용량을 넘을 경우
    
    → 결과화면 이미지 해상도를 고정해서 만드는 방식으로 수정 준비중
