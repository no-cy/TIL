# JPA N+1 이슈
### **N+1 이슈란?**
연관 관계가 설정된 엔티티를 조회할 경우에 조회된 데이터 갯수(n)만큼 연관관계의 조회 쿼리가 추가로 발생하는 이슈

### N+1 발생 원인
엔티티 조회 시 연관 엔티티까지 조회하게 되면서 발생하는 문제다. 주로 엔티티의 양방향 연관관계가 @ManyToOne 일 경우 발생한다. 즉시 로딩과 지연 로딩 두 설정 모두 N+1 이슈가 발생한다.

즉시 로딩일 경우에는, 엔티티 조회 시 연관 관계인 엔티티 객체가 즉시 로딩되면서 발생  
지연 로딩일 경우에는, 엔티티를 조회하는 단일 쿼리 실행 후 연관 엔티티 객체에 접근할 때 N+1 현상이 발생

### N+1 해결 방법
해결 방법으로는 Fetch Join, EntityGraph 어노테이션, Batch Size 등의 방법이 있다.

**Fetch Join**  
연관된 엔티티를 한 번의 쿼리로 함께 조회하는 기능이다. **`JOIN FETCH`** 구문을 사용하여 연관 엔티티를 즉시 로딩하도록 쿼리를 작성한다. 지연 로딩으로 설정된 연관 엔티티에 대해 별도의 쿼리를 실행하지 않고, 원본 엔티티를 조회할 때 연관 엔티티를 함께 조회하여 N+1 문제를 해결한다.
```sql
SELECT p FROM Post p JOIN FETCH p.comments WHERE p.id = :postId
```
**EntityGraph**  
**`@NamedEntityGraph`** 어노테이션을 사용하여 엔티티를 조회할 때 어떤 연관 엔티티를 함께 로딩할지 정의할 수 있다. 
```java
@Entity
@NamedEntityGraph(name = "Post.comments", attributeNodes = @NamedAttributeNode("comments"))
public class Post {
    // ...
}
```
**Batch Size**  
지연 로딩을 사용할 때 연관 엔티티를 조회하는 쿼리의 수를 줄이기 위해 사용할 수 있는 설정이다. `@BatchSize` 어노테이션을 사용하여 한 번에 로드할 연관 엔티티의 수를 지정할 수 있다.
```java
@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
@BatchSize(size = 10)
private Set<Comment> comments;
```

