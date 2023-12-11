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

### 3. BackOffice API
| Name | Method | URL | Auth |
|---|---|---|---|
| 가게의 주문 내역 단 건 조회 | GET | /backoffice/stores/{storeId}/orders/{orderId} | seller |
| 가게의 주문 내역 전체 조회 | GET | /backoffice/stores/{storeId}/orders | seller |

### 4. Order API
| Name | Method | URL | Auth |
|---|---|---|---|
| 주문 생성 | POST | /orders | user |
| 주문 취소 | PATCH | /orders/{orderId}/cancel | user |

### 5. Cart API
| Name | Method | URL | Auth |
|---|---|---|---|
| 장바구니 생성 | POST | /carts | user |
| 장바구니 수정 | PATCH | /carts/{cartId} | user |
| 장바구니 삭제 | DELETE | /carts/{cartId} | user |
| 장바구니 조회 | GET | /carts | user |

### 6. Store API
| Name | Method | URL | Auth |
|---|---|---|---|
| 가게 생성 | POST | /stores | seller |
| 유저의 가게 전체 조회 | GET | /stores/user | seller |
| 가게 전체 조회 | GET | /stores/total | seller |
| 가게 단 건 조회 | GET | /stores/{storeId} | seller |
| 가게 삭제 | PATCH | /stores/{storesId}/delete | seller |
| 가게 정보 변경 - 이름 | PATCH | /stores/{storeId}/name | seller |
| 가게 정보 변경 - 카테고리 | PATCH | /stores/{storeId}/category | seller |
| 가게 정보 변경 - 주소 | PATCH | /stores/{storeId}/address | seller |
| 가게 정보 변경 - 전화번호 | PATCH | /stores/{storeId}/phone | seller |
| 가게 정보 변경 - 소개글 | PATCH | /stores/{storeId}/content | seller |
| 가게 정보 변경 - 최소주문금액 | PATCH | /stores/{storeId}/min_delivery_price | seller |
| 가게 정보 변경 - 사진 | PATCH | /stores/{storeId}/picture | seller |

