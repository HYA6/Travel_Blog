# TravelB ✈
## 📔 목차
* 📃 [프로젝트 개요](#-프로젝트-개요)
* 🛠 [사용한 기술](#-사용한-기술)
* 💡 [주요 기능](#-주요-기능)
* 🔍 [기능 설명](#-기능-설명)
  * [로그인](#로그인)
  * [회원가입](#회원가입)
  * [블로그](#블로그)
  * [계층형 카테고리](#계층형-카테고리)
  * [게시글 작성](#게시글-작성)
  * [게시글 옵션](#게시글-옵션)
  * [댓글 및 답글](#댓글-및-답글)
* ✍ [프로젝트 후 느낀 점](#-프로젝트-후-느낀-점)

<br/>

## 📃 프로젝트 개요
<img src="https://github.com/HYA6/Travel_Blog/assets/130038444/318bb23d-c18c-44cc-9319-20ee37dd02aa"/>

> <b>주제</b> : 여행 블로그
> 
> <b>프로젝트 종류</b> : 개인 프로젝트
> 
> <b>수행인원</b> : 1명
> 
> <b>기간</b> : 2023.09 - 2023.10
>
> <b>목적</b>
>   1. 경험해보지 않은 기술 활용 (Spring Boot, JPA)
>   2. 팀 프로젝트에서 안해보았던 기능 구현 (로그인, 회원가입, 네이버 로그인 및 회원가입, 지도 API)
>   3. 여행 도중에도 간단하게 작성할 수 있는 블로그
>
> <b>문의</b> : sjo6382@naver.com

<br/>

## 🛠 사용한 기술
  * <b>BACK-END</b>
      * Java, Spring Boot, JPA, Lombok, Ajax, Maven
  * <b>API</b>
      * Naver Login API, Google Map API
  * <b>FRONT-END</b>
      * JavaScript, JQuery, Thymeleaf, CSS, Bootstrap
  * <b>DB</b>
      * MySQL, H2 Database
  * <b>SERVER</b>
      * Tomcat
  * <b>TOOLS</b>
      * STS, GitHub

<br/>

## 💡 구현한 주요 기능
  * <b>로그인</b>
  * <b>회원가입, 회원탈퇴</b>
  * <b>블로그 생성 및 수정, 삭제</b>
  * <b>계층형 카테고리 생성</b>
  * <b>게시글 작성 (간단 양식, 기본 양식)</b>
  * <b>게시글 조회수 자동 증가, 좋아요 표시</b>
  * <b>댓글, 답글 작성 및 삭제</b>
  * <b>좋아요 수에 따른 인기글 목록 표시, 최신 댓글 목록 표시</b>

<br/>

## 🔍 기능 설명

- ### 로그인
  * 로그인 아이디, 비밀번호 일치 확인
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/5fdad99c-ce23-43d5-89d8-250c43665426"/><br/>
---
- ### 회원가입
  * 아이디 중복 검사, 비밀번호 확인, 이메일 유효성 검사
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/f31d469e-9733-49ba-9a07-954bfcc979cf"/><br/>
---
- ### 블로그
  * 생성<br/>
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/f6c4ddfb-ef8c-4191-993a-f2cf8892f7ff"/><br/>
    - 블로그 URL 중복 확인
  * 수정<br/>
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/cd271293-c890-41d7-9651-f69e6e1762a9"/><br/>
    - 블로그 URL 중복 확인
  * 삭제<br/>
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/4c9dbbba-793c-4e1e-af0a-05604031475e"/><br/>
    - 삭제 후 블로그 생성 페이지로 이동합니다.
---
- ### 계층형 카테고리
  * 생성<br/>
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/55229103-107c-4f43-9da2-d413a4398bd7"/><br/>
---
- ### 게시글 작성
  * 게시글 기본 정보 입력
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/b36484ca-77d7-4129-a27e-021299ad5229"/><br/>
    - 생성된 카테고리 중에서 선택 가능합니다.
    - 구글 Map API를 이용하여 장소 검색 기능을 구현하였습니다.
  * 간단 양식 게시글 작성
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/89f62443-556f-4f46-82ea-f7b4e406266f"/><br/>
  * 기본 양식 게시글 작성
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/579ed153-5ada-4cfa-802c-73de77184458"/><br/>
  * 대표 이미지 및 태그 작성
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/0d08459d-61ab-44fa-9c94-ed1173e54076"/><br/>
  * 간단 양식 게시글 결과
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/423b90ab-f450-48f0-bf5f-c7ceb4aa6e42"/><br/>
  * 기본 양식 게시글 결과
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/8293a8da-b9a5-4b88-9fa1-2e3c19c06355"/><br/>
---
- ### 게시글 옵션
  * 조회수 증가 및 좋아요 표시
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/3e3a592a-03f2-45a3-8ad7-00c9edf36d51"/><br/>
    - 조회수는 회원 아이디, 조회한 날짜에 따라 증가합니다. (날이 바뀌면 조회 수 증가)
    - 좋아요는 회원 아이디에 따라 표시 가능하며 좋아요 수에 따라 인기글에 표시됩니다.
---
- ### 댓글 및 답글
  * 댓글 및 답글 작성, 삭제
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/1c004cb9-5618-4854-bbf7-6eaf018bb5d9"/><br/>

<br/>

## ✍ 프로젝트 후 느낀 점

* ### 좋았던 점
  * 팀 프로젝트 때 해보지 못했던 로그인과 회원가입 기능을 구현해볼 수 있는 기회였습니다.
  * 외부 API를 사용해볼 수 있었고 추후 채팅 및 메신저 기능 또한 추가할 예정입니다.
  * 로컬 이미지 저장을 온전히 혼자 해볼 수 있었으며, 실무에서는 서버에 이미지를 저장하여 사용한다는 점을 알게 되었습니다.
  * 템플릿, 데이터베이스, 프레임워크 등 하나하나 비교하면서 신중하게 선택하고 공부할 수 있었습니다.
  * Ajax와 REST API를 주로 사용하여 로직을 구성하다보니 그에 관련하여 더 깊이 공부할 수 있었습니다.
* ### 아쉬웠던 점
  * Ajax 비동기 처리 순서를 정리하는 데 어려움이 있었습니다.
  * 프로젝트를 문서화시키는 작업도 중요하다는 것을 깨달았습니다.

