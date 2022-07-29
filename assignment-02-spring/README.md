## Spring 입문 개인 과제

### 유즈케이스
<img src="https://user-images.githubusercontent.com/15075501/180895854-c4400dd1-4874-48ef-90f9-5d6cef358be5.png" height="400px" />

### API 명세
| METHOD | URL | TYPE | STATUS | REQUEST | RESPONSE | DESCRIPTION |
|:---:|:---|:---:|:---:|---|---|:---:|
| GET | /api/posts | JSON | 200 | | <pre>{<br/>　"data": [<br/>　　{<br/>　　　"id": 게시글 아이디,<br/>　　　"title": 제목,<br/>　　　"autor": 작성자,<br/>　　　"createdAt": 작성 날짜<br/>　　}<br/>　]<br/>}</pre> | 전체 게시글 목록 조회 |
| GET | /api/posts/{id} | JSON | 200 | | <pre>{<br/>　"data": {<br/>　　"id": 게시글 아이디,<br/>　　"title": 제목,<br/>　　"autor": 작성자,<br/>　　"contents": 작성 내용,<br/>　　"createdAt": 작성 날짜,<br/>　　"modifiedAt": 수정 날짜<br/>　}<br/>}</pre> | 게시글 조회 |
| POST | /api/posts | JSON | 200 | <pre>{<br/>　"title": 제목,<br/>　"autor": 작성자,<br/>　"contents": 작성 내용,<br/>　"password": 비밀번호<br/>}</pre> | <pre>{<br/>　"msg": "작성 성공"<br/>　"id": 게시글 아이디<br/>}</pre> | 게시글 작성 |
| POST | /api/posts/{id} | JSON | 200 | <pre>{<br/>　"password": 비밀번호 <br/>}</pre> | <pre>{<br/>　"msg": "비밀번호가 일치합니다."<br/>}</pre> | 게시글 비밀번호 확인 |
| PUT | /api/posts/{id} | JSON | 200 | <pre>{<br/>　"title": 제목,<br/>　"autor": 작성자,<br/>　"contents": 작성 내용,<br/>　"password": 비밀번호<br/>}</pre> | <pre>{<br/>　"msg": "수정 성공"<br/>　"id": 게시글 아이디<br/>}</pre> | 게시글 수정 |
| DELETE | /api/posts/{id} | JSON | 200 | | <pre>{<br/>　"msg": "삭제 성공"<br/>}</pre> | 게시글 삭제 |