# 🐟 팔팔잇츠 - 아직 팔팔하조?
### 팔팔한 싱싱함, 빠른 배송, 팔팔잇츠!
<img width="300px" alt="팔팔잇츠 아이콘" src="https://github.com/palpalTeam/Palpal-Eats/assets/82515938/16cd8b2f-0a8c-4e6b-9992-0513ed3c8fe0">

## 🐟 Demo Video
[![Video Label](http://img.youtube.com/vi/rNcWXk7oPFM/0.jpg)](https://youtu.be/rNcWXk7oPFM)

## 🐟 Personal Role
| Name | Role                                |
|------|-------------------------------------|
| [조원호](https://github.com/wonowonow) | Auth API, User API, MyInfo API      |
| [이지선](https://github.com/jiisuniui) | Cart API, Order API, BackOffice API |
| [김종규](https://github.com/Kim-Jong-Gyu) | S3 API, Store API, Menu API         |
| [김대영](https://github.com/kdy9960) | Review API                          |

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
- **원인 추론**
  - ```git config pull.ff only``` 를 통해 pull의 기본 옵션을 ff-only로 설정해놓은 경우 발생할 수 있는 에러
  - pull 하려는 원격저장소의 브랜치와 로컬저장소의 브랜치가 Fast-Forward 관계가 아닐때 발생 -> 즉,원격저장소의 새로운 commit이 존재하는데 git pull을 하지 않은 상태에서 로컬저장소에 새로운 commit을 했다면 해당 에러가 발생
- **해결 방안**
  - 근본적인 해결
    - 근본적으로 해결하려면 fast-forward only 옵션을 ```git config --unset pull.ff```
- **참고 자료**
  - [Not possible to fast-forward, aborting. 에러 해결](https://velog.io/@eunddodi/Not-possible-to-fast-forward-aborting.-%EC%97%90%EB%9F%AC-%ED%95%B4%EA%B2%B0)

### 2. getWriter() has already been called for this response 에러
- **문제 정의**
  - 유저 회원가입 테스트 중 getWriter() has already been called for this response 출력
  - ExceptionHandeler에서 문제가 났다고 하길래, Exception을 안 내면 되겠다 생각
  - Exception을 안 내도 getWriter 오류 발생

- **원인 추론**
  - response를 반환해야하는데, 이미 getWriter()가 쓰였기 때문에 안 된다는 의미 같은데
  - JwtAuthorizationFilter에서 오류가 난다고 한다
  - 이 부분에서 난다.

### 3. API path에서 Id 값을 못 찾는 에러
- **문제 정의**
  - Postman으로 리뷰 생성 테스트 중에 작성자를 받아오는 orderId값을 받아오지 못하는 상황이 발생
  ![image](https://github.com/palpalTeam/Palpal-Eats/assets/82515938/28b07f0d-073d-446c-91c9-93463320acf0)
  
  - 코드 상으로는 @PathVariable을 이용하여 Long orderId 값을 받아 오도록 작성했으나 원인이 무엇때문인지 Long값을 받아오지 못함
  ![image](https://github.com/palpalTeam/Palpal-Eats/assets/82515938/710c366d-219c-4a57-a54d-e212e756cea1)

- **원인 추론**
  - 오류 부분상 User의 정보는 받아오는것 같지만 리뷰 작성 구조는 User가 장바구니에 메뉴를 담아 주문을 했을경우 작성함으로 주문쪽인 orderId의 값을 받아 오지못하는것으로 추정됨

- **해결 방안**
  - url에 설정해둔 값을 PathVariable로 받아올시 밑에 예제처럼 String id와 같이 이름이 같다면 (”id”) 부분이 생략이 가능하다
  ```java
  @RestController
  public class MemberContoller{
  	@GetMapping("/test/url/{id}")
  	public String findById(@PathVariable("id") String id) {
  		retrun "Id: " + id;
  	}
  }
  ```
  - 하지만 URI 에 있는 특정값을 지정하여 변수로 지정하고 싶다면 위 코드와 같이 @PathVariable뒤에 ( ) 를 넣어 url의 변수명을 넣은후 뒤에 오는 변수명의 타입, 값을 넣어야한다.
  - @PathVariable 뒤에 (”orderId”) 를 넣음으로서 받아야할 값을 받도록 넣음으로서 해결했다.
    ![image](https://github.com/palpalTeam/Palpal-Eats/assets/82515938/72fb32a0-c258-4d8c-9f24-e909a4cb2fe8)

- **참고 자료**
  - [Spring - 사용자가 전달한 값 사용하기](https://galid1.tistory.com/505)
