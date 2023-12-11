# 🐟 팔팔잇츠 - 아직 팔팔하조?
### 팔팔한 싱싱함, 빠른 배송, 팔팔잇츠!
<img src="https://github.com/palpalTeam/Palpal-Eats/assets/82515938/16cd8b2f-0a8c-4e6b-9992-0513ed3c8fe0" alt="팔팔잇츠 아이콘" width="300px">

## 🐟 UI/UX
<img width="779" alt="image" src="https://github.com/palpalTeam/Palpal-Eats/assets/82515938/05484013-f64e-411b-80ad-551569dc924a">

## 🐟 ERD
![image](https://github.com/palpalTeam/Palpal-Eats/assets/82515938/d9df1ab7-c57b-4b22-96cb-d8c2617988b2)

## 🐟 API 명세서
### 1. User API
| Name | Method | URL | Auth |
|---|---|---|---|
| 회원가입 | POST | /users/signup | none |
| 로그인 | POST | /users/login | none |
| 로그아웃 | PATCH | /users/logout | user |

### 2. MyInfo API
| Name | Method | URL | Auth |
|---|---|---|---|
| 주소 변경 | PATCH | /myinfo/address | none |
| 닉네임 변경 | PATCH | /myinfo/nickname | none |
| 비밀번호 변경 | PATCH | /myinfo/password | user |
| 내 정보 조회 | GET | /myinfo | none |
| 내 주문 내역 단 건 조회 | GET | /myinfo/orders/{orderId} | user |
| 내 주문 내역 전체 조회 | GET | /myinfo/orders | user |
