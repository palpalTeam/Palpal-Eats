# 🐟 팔팔잇츠 - 아직 팔팔하조?
### 팔팔한 싱싱함, 빠른 배송, 팔팔잇츠!
<img width="300px" alt="팔팔잇츠 아이콘" src="https://github.com/palpalTeam/Palpal-Eats/assets/82515938/16cd8b2f-0a8c-4e6b-9992-0513ed3c8fe0">

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
| 주소 변경 | PATCH | /myinfo/address | user |
| 닉네임 변경 | PATCH | /myinfo/nickname | user |
| 비밀번호 변경 | PATCH | /myinfo/password | user |
| 내 정보 조회 | GET | /myinfo | user |
| 내 주문 내역 단일 조회 | GET | /myinfo/orders/{orderId} | user |
| 내 주문 내역 전체 조회 | GET | /myinfo/orders | user |

### 3. BackOffice API
| Name | Method | URL | Auth |
|---|---|---|---|
| 가게의 주문 내역 단일 조회 | GET | /backoffice/stores/{storeId}/orders/{orderId} | seller |
| 가게의 주문 내역 전체 조회 | GET | /backoffice/stores/{storeId}/orders | seller |

### 4. Store API
| Name | Method | URL | Auth |
|---|---|---|---|
| 가게 생성 | POST | /stores | seller |
| 가게 수정 - 이름 | PATCH | /stores/{storeId}/name | seller |
| 가게 수정 - 카테고리 | PATCH | /stores/{storeId}/category | seller |
| 가게 수정 - 주소 | PATCH | /stores/{storeId}/address | seller |
| 가게 수정 - 전화번호 | PATCH | /stores/{storeId}/phone | seller |
| 가게 수정 - 소개글 | PATCH | /stores/{storeId}/content | seller |
| 가게 수정 - 최소주문금액 | PATCH | /stores/{storeId}/min_delivery_price | seller |
| 가게 수정 - 사진 | PATCH | /stores/{storeId}/picture | seller |
| 가게 삭제 | PATCH | /stores/{storesId}/delete | seller |
| 유저 소유의 가게 전체 조회 | GET | /stores/user | seller |
| 가게 전체 조회 | GET | /stores/total | none |
| 가게 단일 조회 | GET | /stores/{storeId} | none |

### 5. Menu API
| Name | Method | URL | Auth |
|---|---|---|---|
| 메뉴 생성 | POST | /stores/{storeId}/menu | seller |
| 메뉴 수정 - 이름 | PATCH | /stores/{storeId}/menu/name | seller |
| 메뉴 수정 - 가격 | PATCH | /stores/{storeId}/menu/price | seller |
| 메뉴 수정 - 카테고리 | PATCH | /stores/{storeId}/menu/category | seller |
| 메뉴 수정 - 사진 | PATCH | /stores/{storeId}/menu/picture | seller |
| 메뉴 삭제 | PATCH | /stores/{storeId}/menu/{menuId}/delete | seller |
| 해당 가게의 메뉴 전체 조회 | GET | /stores/{storeId}/menu | none |
| 해당 가게의 메뉴 단일 조회 | GET | /stores/{storeId}/menu/{menuId} | none |

### 6. Order API
| Name | Method | URL | Auth |
|---|---|---|---|
| 주문 생성 | POST | /orders | user |
| 주문 취소 | PATCH | /orders/{orderId}/cancel | user |

### 7. Cart API
| Name | Method | URL | Auth |
|---|---|---|---|
| 장바구니 생성 | POST | /carts | user |
| 장바구니 수정 | PATCH | /carts/{cartId} | user |
| 장바구니 삭제 | DELETE | /carts/{cartId} | user |
| 장바구니 조회 | GET | /carts | user |

### 8. Review API
| Name | Method | URL | Auth |
|---|---|---|---|
| 리뷰 작성 | POST | /orders/{orderId}/review | user |
| 리뷰 수정 | PATCH | /reviews/{reviewId} | user |
| 리뷰 삭제 | DELETE | /reviews/{reviewId} | user |
| 유저 리뷰 단일 조회 | GET | /users/{userId}/reviews/{reviewId} | none |
| 유저 리뷰 전체 조회 | GET | /users/{userId}/reviews | none |
| 가게 리뷰 단일 조회 | GET | /stores/{storeId}/reviews/{reviewId} | none |
| 가게 리뷰 전체 조회 | GET | /stores/{storeId}/reviews | none |
