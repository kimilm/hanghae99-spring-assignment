### **요구사항**

1. 아래의 요구사항을 기반으로 Use Case 그려보기
   - 손으로 그려도 됩니다.
   - cf. [https://narup.tistory.com/70](https://narup.tistory.com/70)
2. 전체 게시글 목록 조회 API
   - 제목, 작성자명, 작성 날짜를 조회하기
   - 작성 날짜 기준으로 내림차순 정렬하기
3. 게시글 작성 API
   - 제목, 작성자명, 비밀번호, 작성 내용을 입력하기
4. 게시글 조회 API
   - 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기
     (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
5. 게시글 비밀번호 확인 API
   - 비밀번호를 입력 받아 해당 게시글의 비밀번호와 일치여부 판단하기
6. 게시글 수정 API
   - 제목, 작성자명, 비밀번호, 작성 내용을 수정되게 하기
7. 게시글 삭제 API
   - 글이 삭제되게 하기

#  
### 유즈케이스
<img src="https://user-images.githubusercontent.com/15075501/180895854-c4400dd1-4874-48ef-90f9-5d6cef358be5.png" height="400px" />

#  
### API 명세
| METHOD | URL | TYPE | STATUS | REQUEST | RESPONSE | DESCRIPTION |
|:---:|:---|:---:|:---:|---|---|:---:|
| GET | /api/posts | JSON | 200 | | <pre>{<br/>　"data": [<br/>　　{<br/>　　　"id": 게시글 아이디,<br/>　　　"title": 제목,<br/>　　　"autor": 작성자,<br/>　　　"createdAt": 작성 날짜<br/>　　}<br/>　]<br/>}</pre> | 전체 게시글 목록 조회 |
| GET | /api/posts/{id} | JSON | 200 | | <pre>{<br/>　"data": {<br/>　　"id": 게시글 아이디,<br/>　　"title": 제목,<br/>　　"autor": 작성자,<br/>　　"contents": 작성 내용,<br/>　　"createdAt": 작성 날짜,<br/>　　"modifiedAt": 수정 날짜<br/>　}<br/>}</pre> | 게시글 조회 |
| GET | /api/posts/{id} | JSON | 200 | <pre>{<br/>　"password": 비밀번호 <br/>}</pre> | <pre>{<br/>　"msg": "비밀번호가 일치합니다."<br/>}</pre> | 게시글 비밀번호 확인 |
| POST | /api/posts | JSON | 200 | <pre>{<br/>　"title": 제목,<br/>　"autor": 작성자,<br/>　"contents": 작성 내용,<br/>　"password": 비밀번호<br/>}</pre> | <pre>{<br/>　"msg": "작성 성공"<br/>　"id": 게시글 아이디<br/>}</pre> | 게시글 작성 |
| PUT | /api/posts/{id} | JSON | 200 | <pre>{<br/>　"title": 제목,<br/>　"autor": 작성자,<br/>　"contents": 작성 내용,<br/>　"password": 비밀번호<br/>}</pre> | <pre>{<br/>　"msg": "수정 성공"<br/>　"id": 게시글 아이디<br/>}</pre> | 게시글 수정 |
| DELETE | /api/posts/{id} | JSON | 200 | | <pre>{<br/>　"msg": "삭제 성공"<br/>}</pre> | 게시글 삭제 |