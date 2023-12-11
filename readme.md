# 🐟 팔팔잇츠 - 아직 팔팔하조?
### 팔팔한 싱싱함, 빠른 배송, 팔팔잇츠!
<img width="300px" alt="팔팔잇츠 아이콘" src="https://github.com/palpalTeam/Palpal-Eats/assets/82515938/16cd8b2f-0a8c-4e6b-9992-0513ed3c8fe0">

## 🐟 Demo Video

## 🐟 Personal Role
| Name | Role                                |
|------|-------------------------------------|
| 조원호  | Auth API, User API, MyInfo API      |
| 이지선  | Cart API, Order API, BackOffice API |
| 김종규  | Store API, Menu API                 |
| 김대영  | Review API                          |

## 🐟 Commit Convention
| Tag Name | Description |
|---|---|
| Feat | 새로운 기능을 추가 |
| Fix | 버그 수정 |
| Design | CSS 등 사용자 UI 디자인 변경 |
| !BREAKING CHANGE | 커다란 API 변경 |
| !HOTFIX | 급하게 치명적인 버그를 고쳐야하는 경우 |
| Style | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| Refactor | 프로덕션 코드 리팩토링 |
| Comment | 필요한 주석 추가 및 변경 |
| Test | 테스트 코드, 리펙토링 테스트 코드 추가, Production Code(실제로 사용하는 코드) 변경 없음 |
| Chore | 빌드 업무 수정, 패키지 매니저 수정, 패키지 관리자 구성 등 업데이트, Production Code 변경 없음 |
| Rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
| Remove | 파일을 삭제하는 작업만 수행한 경우 |

## 🐟 UI/UX
<img width="779" alt="image" src="https://github.com/palpalTeam/Palpal-Eats/assets/82515938/05484013-f64e-411b-80ad-551569dc924a">

## 🐟 ERD
![image](https://github.com/palpalTeam/Palpal-Eats/assets/82515938/d9df1ab7-c57b-4b22-96cb-d8c2617988b2)

## 🐟 API 명세서
### 0. API 설계 기준
- UI/UX 기준으로 API 구조 설계
- 와이어 프레임을 참고로 순차적으로 작동하도록 설계 함

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

## 🐟 Technical Decision
### 1. S3 기술 도입
- 메뉴, 가게 이미지 관리
- 객체 스토리지 서비스 사용
- S3를 통한 저장, 삭제, 조회

### 2. isDeleted 사용
- 삭제된 데이터 관리 최적화
- Boolean 값 ‘isDeleted'

### 3. Redis DB 사용
- 로그아웃 구현
- 빠른 데이터 접근
- Stateful하나 Stateless적인 속도를 위해 사용

## 🐟 Trouble Shooting
### 1. Not possible to fast-forward, aborting 에러
- 원인 추론
  - ```git config pull.ff only``` 를 통해 pull의 기본 옵션을 ff-only로 설정해놓은 경우 발생할 수 있는 에러
  - pull 하려는 원격저장소의 브랜치와 로컬저장소의 브랜치가 Fast-Forward 관계가 아닐때 발생 -> 즉,원격저장소의 새로운 commit이 존재하는데 git pull을 하지 않은 상태에서 로컬저장소에 새로운 commit을 했다면 해당 에러가 발생
- 해결 방안
  - 근본적인 해결
    - 근본적으로 해결하려면 fast-forward only 옵션을 ```git config --unset pull.ff```
- 관련 자료 
  - https://velog.io/@eunddodi/Not-possible-to-fast-forward-aborting.-%EC%97%90%EB%9F%AC-%ED%95%B4%EA%B2%B0

### 2. getWriter() has already been called for this response 에러
- 문제 정의
- 유저 회원가입 테스트 중 getWriter() has already been called for this response 출력
- ExceptionHandeler에서 문제가 났다고 하길래, Exception을 안 내면 되겠다 생각
- Exception을 안 내도 getWriter 오류 발생

- 원인 추론
  - response를 반환해야하는데, 이미 getWriter()가 쓰였기 때문에 안 된다는 의미 같은데
  - JwtAuthorizationFilter에서 오류가 난다고 한다
  - 이 부분에서 난다.

### 3. API url에서 Id 값을 못 찾는 에러


