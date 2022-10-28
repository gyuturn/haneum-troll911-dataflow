# backend-dataflow
- 프로젝트 상세 설명: https://github.com/gyuturn/hameum-backend
- Read.me 참고
- 현재 비용문제로 인해 서버 닫힌 상태

### 사용목적
- kafka와 spring scheduler를 통한 약 만명의 유저 전적 저장
- 일반적인 RiotAPI 호출로는 유저의 전적검색시에 30초이상이 소요 -> 비동기로 데이터를 저장 -> kafka 사용

### 전체 DATAFLOW 흐름 구성도
![image](https://user-images.githubusercontent.com/87477702/197346763-4a6205f2-72e7-4b12-87bf-e99a0e6cb912.png)
- SpringBoot에서 Kafka 사용
- Json형식의 유저 전적 데이터를 produce
- consumer는 Json데이터를 바로 DB에 JSON 형태 그대로 저장
- NiFi를 이용하여 전용 ConsumerGROUP을 생성하고, JSON데이터를 RDB에 맞게 변형
- DB에는 JSON 형태의 데이터+JSON데이터를 RDB에 맞게 변형한 데이터 둘다 보관

### kafka 구성도
![image](https://user-images.githubusercontent.com/87477702/197346112-33f02600-ed7d-487a-984e-5a2e4badf991.png)
- fullSearch, UserInfo, MostChampion, LineInfo와 같이 4개의 topic으로 구분지어 사용


### 롤 닉네임 S3
- https://troller-bucket.s3.ap-northeast-2.amazonaws.com/mixLolname.txt
- 크롤링을 통해 롤 닉네임을 S3에 보관 
- https://github.com/gyuturn/haneum-ml -> 크롤링 정보 참고

### NiFi 
![image](https://user-images.githubusercontent.com/87477702/192939423-f2fe73d9-bb9f-44aa-b05b-b5d22481bac5.png)
- 실제 NiFi Process진행 흐름도
- http://52.79.58.170:8443/nifi


### 진행과정
- https://gyuturn.tistory.com/category/%EB%A1%A4%20%EC%A0%84%EC%A0%81%EA%B2%80%EC%83%89%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8
- 해당 블로그에 이슈 및 진행상황 공유 및 기록


