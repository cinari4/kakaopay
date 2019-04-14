# 1. POST /attractions/v1.0/attractions/csvfile/{filename}
데이터 파일에서 각 레코드를 데이터 베이스에 저장하는 API

## example1. success
### - request
```
POST /attractions/v1.0/attractions/csvfile/data2.csv HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":true}
```

## example2. fail
### - request
```
POST /attractions/v1.0/attractions/csvfile/data1.csv HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 500 
{"code":"FILE_NOT_FOUND","message":"파일을 찾을 수 없습니다.","data":null}
```

****

# 2. GET /attractions/v1.0/attraction/{id}
생태 관광정보 데이터를 조회하는 API

## example1. success
### - request
```
GET /attractions/v1.0/attraction/1 HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":{"id":1,"programName":"자연과 문화를 함께 즐기는 설악산 기행","theme":"문화생태체험,자연생태체험,","location":"강원도 속초","programBrief":"설악산 탐방안내소, 신흥사, 권금성, 비룡폭포","programDetail":" 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다."}}
```

## example2. fail
### - request
```
GET /attractions/v1.0/attraction/1111 HTTP/1.1
Host: localhost:8080
```

### - reponse
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":null}
```

****

# 3. POST /attractions/v1.0/attraction/{id}
생태 관광정보 데이터를 수정하는 API

## example1. success
### - request
```
POST /attractions/v1.0/attraction/1 HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: localhost:8080

{"id":1,"programName":"\uc790\uc5f0\uacfc \ubb38\ud654\ub97c \ud568\uaed8 \uc990\uae30\ub294 \uc124\uc545\uc0b0 \uae30\ud589","theme":"\ubb38\ud654\uc0dd\ud0dc\uccb4\ud5d8,\uc790\uc5f0\uc0dd\ud0dc\uccb4\ud5d8,","location":"\uac15\uac15\uac15\uac15","programBrief":"\uc124\uc545\uc0b0 \ud0d0\ubc29\uc548\ub0b4\uc18c, \uc2e0\ud765\uc0ac, \uad8c\uae08\uc131, \ube44\ub8e1\ud3ed\ud3ec","programDetail":" \uc124\uc545\uc0b0\uc740 \uc65c \uc124\uc545\uc0b0\uc774\uace0, \uc2e0\ud765\uc0ac\ub294 \uc65c \uc2e0\ud765\uc0ac\uc77c\uae4c\uc694? \uc124\uc545\uc0b0\uc5d0 \ub300\ud574 \uc815\ud655\ud788 \uc54c\uace0, \ubc30\uc6b0\uace0, \ub290\ub084 \uc218 \uc788\ub294 \ub2f9\uc77c\ud615 \uc0dd\ud0dc\uad00\uad11\uc785\ub2c8\ub2e4."}
```

### - response
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
{"code":"OK","message":"요청이 성공하였습니다.","data":true}
```

## example2. fail (update할 id를 찾지 못할경우)
### - request
```
POST /attractions/v1.0/attraction/1111 HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: localhost:8080

{"id":1111,"programName":"\uc790\uc5f0\uacfc \ubb38\ud654\ub97c \ud568\uaed8 \uc990\uae30\ub294 \uc124\uc545\uc0b0 \uae30\ud589","theme":"\ubb38\ud654\uc0dd\ud0dc\uccb4\ud5d8,\uc790\uc5f0\uc0dd\ud0dc\uccb4\ud5d8,","location":"\uac15\uac15\uac15\uac15","programBrief":"\uc124\uc545\uc0b0 \ud0d0\ubc29\uc548\ub0b4\uc18c, \uc2e0\ud765\uc0ac, \uad8c\uae08\uc131, \ube44\ub8e1\ud3ed\ud3ec","programDetail":" \uc124\uc545\uc0b0\uc740 \uc65c \uc124\uc545\uc0b0\uc774\uace0, \uc2e0\ud765\uc0ac\ub294 \uc65c \uc2e0\ud765\uc0ac\uc77c\uae4c\uc694? \uc124\uc545\uc0b0\uc5d0 \ub300\ud574 \uc815\ud655\ud788 \uc54c\uace0, \ubc30\uc6b0\uace0, \ub290\ub084 \uc218 \uc788\ub294 \ub2f9\uc77c\ud615 \uc0dd\ud0dc\uad00\uad11\uc785\ub2c8\ub2e4."}
```

### - response
```
HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
{"code":"OK","message":"요청이 성공하였습니다.","data":false}
```

## example3. fail (파라미터가 누락되었을 경우)
### - request
```
POST /attractions/v1.0/attraction/1111 HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: localhost:8080

{"id":1111,"programName":"\uc790\uc5f0\uacfc \ubb38\ud654\ub97c \ud568\uaed8 \uc990\uae30\ub294 \uc124\uc545\uc0b0 \uae30\ud589","location":"\uac15\uac15\uac15\uac15","programBrief":"\uc124\uc545\uc0b0 \ud0d0\ubc29\uc548\ub0b4\uc18c, \uc2e0\ud765\uc0ac, \uad8c\uae08\uc131, \ube44\ub8e1\ud3ed\ud3ec","programDetail":" \uc124\uc545\uc0b0\uc740 \uc65c \uc124\uc545\uc0b0\uc774\uace0, \uc2e0\ud765\uc0ac\ub294 \uc65c \uc2e0\ud765\uc0ac\uc77c\uae4c\uc694? \uc124\uc545\uc0b0\uc5d0 \ub300\ud574 \uc815\ud655\ud788 \uc54c\uace0, \ubc30\uc6b0\uace0, \ub290\ub084 \uc218 \uc788\ub294 \ub2f9\uc77c\ud615 \uc0dd\ud0dc\uad00\uad11\uc785\ub2c8\ub2e4."}
```

### - response
```
HTTP/1.1 400 
{"code":"BAD_PARAMETER","message":"요청 파라미터가 잘못되었습니다.","data":null}
```

****

# 4. POST "/attractions/v1.0/attraction"
생태 관광정보 데이터를 추가하는 API

## example1. success
### - request
```
POST /attractions/v1.0/attraction HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: localhost:8080

{"programName":"\uc790\uc5f0\uacfc \ubb38\ud654\ub97c \ud568\uaed8 \uc990\uae30\ub294 \uc124\uc545\uc0b0 \uae30\ud58922222","theme":"\ubb38\ud654\uc0dd\ud0dc\uccb4\ud5d8,\uc790\uc5f0\uc0dd\ud0dc\uccb4\ud5d8,","location":"\uac15\uc6d0\ub3c4 \uc18d\ucd08","programBrief":"\uc124\uc545\uc0b0 \ud0d0\ubc29\uc548\ub0b4\uc18c, \uc2e0\ud765\uc0ac, \uad8c\uae08\uc131, \ube44\ub8e1\ud3ed\ud3ec","programDetail":" \uc124\uc545\uc0b0\uc740 \uc65c \uc124\uc545\uc0b0\uc774\uace0, \uc2e0\ud765\uc0ac\ub294 \uc65c \uc2e0\ud765\uc0ac\uc77c\uae4c\uc694? \uc124\uc545\uc0b0\uc5d0 \ub300\ud574 \uc815\ud655\ud788 \uc54c\uace0, \ubc30\uc6b0\uace0, \ub290\ub084 \uc218 \uc788\ub294 \ub2f9\uc77c\ud615 \uc0dd\ud0dc\uad00\uad11\uc785\ub2c8\ub2e4."}
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":true}
```

## example2. fail (파라미터가 누락되었을 경우)
### - request
```
POST /attractions/v1.0/attraction HTTP/1.1
Content-Type: application/json; charset=utf-8
Host: localhost:8080

{"programName":"\uc790\uc5f0\uacfc \ubb38\ud654\ub97c \ud568\uaed8 \uc990\uae30\ub294 \uc124\uc545\uc0b0 \uae30\ud58922222","location":"\uac15\uc6d0\ub3c4 \uc18d\ucd08","programBrief":"\uc124\uc545\uc0b0 \ud0d0\ubc29\uc548\ub0b4\uc18c, \uc2e0\ud765\uc0ac, \uad8c\uae08\uc131, \ube44\ub8e1\ud3ed\ud3ec","programDetail":" \uc124\uc545\uc0b0\uc740 \uc65c \uc124\uc545\uc0b0\uc774\uace0, \uc2e0\ud765\uc0ac\ub294 \uc65c \uc2e0\ud765\uc0ac\uc77c\uae4c\uc694? \uc124\uc545\uc0b0\uc5d0 \ub300\ud574 \uc815\ud655\ud788 \uc54c\uace0, \ubc30\uc6b0\uace0, \ub290\ub084 \uc218 \uc788\ub294 \ub2f9\uc77c\ud615 \uc0dd\ud0dc\uad00\uad11\uc785\ub2c8\ub2e4."}
```

### - response
```
HTTP/1.1 400 
{"code":"BAD_PARAMETER","message":"요청 파라미터가 잘못되었습니다.","data":null}
```

****

# 5. GET /attractions/v1.0/attraction/location/{location}/programTheme
서비스 지역 칼럼을 기준으로 특정 지역에서 진행되는 프로그램명과 테마를 출력하는 API

## example1. success
### - request
```
GET /attractions/v1.0/attraction/location/%EC%86%8D%EC%B4%88/programTheme HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":[{"programName":"자연과 문화를 함께 즐기는 설악산 기행","theme":"문화생태체험,자연생태체험,"},{"programName":"[설악산] 설악산에서 길을 묻다(설악을 내 품에)","theme":"아동·청소년 체험학습,"}]}
```

## example2. fail
### - request
```
GET /attractions/v1.0/attraction/location/not_exist_keyword/programTheme HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":[]}
```

****

# 6. GET /attractions/v1.0/attraction/programBrief/{keyword}/regionCount
프로그램 소개에서 특정 문자열이 포함된 레코드에서 서비스 지역 개수를 세어 출력하는 API

## example1. success
### - request
```
GET /attractions/v1.0/attraction/programBrief/%EC%84%B8%EA%B3%84%EB%AC%B8%ED%99%94%EC%9C%A0%EC%82%B0/regionCount HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":{"keyword":"세계문화유산","regionCount":[{"region":"경상북도 경주시","count":2}]}}
```

## example2. fail
### - request
```
GET /attractions/v1.0/attraction/programBrief/not_exist_keyword/regionCount HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":{"keyword":"not_exist_keyword","regionCount":[]}}
```

****

# 7. GET /attractions/v1.0/attraction/programDetail/{keyword}/count
상세 정보를 읽어와서 입력 단어의 출현빈도수를 계산하여 출력하는 API

## example1. success
### - request
```
GET /attractions/v1.0/attraction/programDetail/%EB%AC%B8%ED%99%94/count HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":{"keyword":"문화","count":59}}
```

## example2. fail
### - request
```
GET /attractions/v1.0/attraction/programDetail/not_exist_keyword/count HTTP/1.1
Host: localhost:8080
```

### - response
```
HTTP/1.1 200 
{"code":"OK","message":"요청이 성공하였습니다.","data":{"keyword":"not_exist_keyword","count":0}}
```
