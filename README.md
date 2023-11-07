# Travel_Blog ✈
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
  * [게시글 조회수, 좋아요](#게시글-조회수,-좋아요)
  * [댓글&답글 작성 및 삭제](#댓글&답글-작성-및-삭제)
* ✍ [프로젝트 후 느낀 점](#-프로젝트-후-느낀-점)
* ✔ [추후 유지 보수 및 기능 추가](#-추후-유지-보수-및-기능-추가)
  * [유지 보수](#유지-보수)
  * [배포](#배포)
  * [기능 추가](#기능-추가)

<br/>

## 📃 프로젝트 개요

> <b>주제</b> : 여행 블로그
> 
> <b>프로젝트 종류</b> : 개인 프로젝트
> 
> <b>수행인원</b> : 1명
> 
> <b>기간</b> : 2023.09.19 - (진행중)
>
> <b>목적</b>
>   1. 경험해보지 않은 기술 활용 (Spring Boot, JPA, REST API 등)
>   2. 팀 프로젝트에서 안해보았던 기능 구현 (로그인, 회원가입, 지도 API 사용 등)
>   3. 여행 도중에도 간단하게 작성할 수 있는 블로그
>   4. 다른 사람들과 여행 정보 및 후기 공유
>   5. 인스타, 트위터같은 SNS 기능도 같이 추가 예정
>
> <b>문의</b> : sjo6382@naver.com

<br/>

## 🛠 사용한 기술
  * <b>BACK-END</b>
      * Java, Spring Boot, JPA, Lombok, Json, Ajax, Maven
  * <b>API</b>
      * Naver Login API, Google Map API, REST API
  * <b>FRONT-END</b>
      * JavaScript, JQuery, Thymeleaf, CSS, Bootstrap
  * <b>DB</b>
      * MySQL, H2 Database
  * <b>SERVER</b>
      * Tomcat
  * <b>TOOLS & COMMUNICATION</b>
      * STS Eclipse, GitHub

<br/>

## 💡 주요 기능
  * <b>로그인</b>
    * 아이디, 비밀번호 일치 확인
    * 네이버 회원가입 및 로그인
  * <b>회원가입</b>
    * 아이디 중복 검사
    * 비밀번호 확인
    * 이메일 중복&유효성 검사
  * <b>블로그 생성 및 수정</b>
  * <b>계층형 카테고리 생성</b>
  * <b>양식에 따른 게시글 작성 (간단 양식, 기본 양식)</b>
    * 카테고리 선택
    * 구글 지도 API를 이용한 여행 장소 검색
    * 이미지 로컬 파일에 저장
  * <b>댓글, 답글 작성 및 삭제</b>
  * <b>게시글 조회수, 좋아요</b>
  * <b>인기글, 최신 댓글</b>

<br/>

## 🔍 기능 설명

- ### 로그인
  * 로그인 아이디, 비밀번호 일치 확인
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/5fdad99c-ce23-43d5-89d8-250c43665426"/><br/>
- ### 회원가입
  * 아이디 중복 검사, 비밀번호 확인, 이메일 유효성 검사
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/f31d469e-9733-49ba-9a07-954bfcc979cf"/><br/>
- ### 블로그
  * 생성
    
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/f6c4ddfb-ef8c-4191-993a-f2cf8892f7ff"/><br/>
  * 수정
    
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/cd271293-c890-41d7-9651-f69e6e1762a9"/><br/>
  * 삭제
    
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/4c9dbbba-793c-4e1e-af0a-05604031475e"/><br/>
- ### 계층형 카테고리
  * 생성
    
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/55229103-107c-4f43-9da2-d413a4398bd7"/><br/>
- ### 게시글 작성
  * 게시글 기본 정보 입력
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/b36484ca-77d7-4129-a27e-021299ad5229"/><br/>
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
- ### 게시글 조회수, 좋아요
  * 
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/3e3a592a-03f2-45a3-8ad7-00c9edf36d51"/><br/>
- ### 댓글&답글
  * 댓글 및 답글 작성, 삭제
    <img src="https://github.com/HYA6/Travel_Blog/assets/130038444/1c004cb9-5618-4854-bbf7-6eaf018bb5d9"/><br/>

<br/>

## ✍ 프로젝트 후 느낀 점

* ### 좋았던 점
  * 
* ### 아쉬웠던 점
  * 

<br/>

## ✔ 추후 유지 보수 및 기능 추가

* ### 유지 보수
  * 발견된 문제점
     * 계층형 카테고리 구현 시 하나씩 저장하는 기능
  * 해결 방안
     * 계층형 카테고리를 전부 작성 후 한번에 저장(JavaScript와 Ajax 이용)

* ### 배포
  * 배포 관련하여 공부 후 적용 예정

* ### 기능 추가
  * 

