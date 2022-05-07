# redis-sorted-set-practice

## 기프티콘 선착순 이벤트 (1초마다 N명한테 대기열 표출)
### 테스트 목록

![image](https://user-images.githubusercontent.com/76584547/166141916-32a4eaf5-fb94-42e5-85ee-803caf2aa71b.png)


### 100명이 대기열 참여
> 가장 빨리 참여한 "Thread-2", "Thread-3"가 치킨 기프티콘을 먼저 받는 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/76584547/166142550-8ac6666d-b0bc-4c5d-878f-593d08e0b7c8.png)

![image](https://user-images.githubusercontent.com/76584547/166142561-eb93bff1-5301-4875-97dc-bbd8b54d53cc.png)

![image](https://user-images.githubusercontent.com/76584547/166142577-a081f3c5-99d6-4fc2-8daf-82a4242008b4.png)


### 대기열 순번 노출
> 치킨을 받지 못한 사람들의 화면에 표출될 순번을 표출하고 있다.

![image](https://user-images.githubusercontent.com/76584547/166142606-9130ab84-a8ca-48a1-9455-afe27f8499bb.png)


### 선착순 이벤트 종료
> 100명의 사람중 30명이 치킨 기프티콘을 받아서 이벤트가 종료된 것을 확인할 수 있다.

![image](https://user-images.githubusercontent.com/76584547/166142647-318c2d27-ba52-469f-b44c-19920386bfa1.png)


### 참고
+ http://redisgate.kr/redis/command/zrank.php
+ http://redisgate.kr/redis/command/zrange.php

### 블로그 정리
+ https://velog.io/@hgs-study/redis-sorted-set
