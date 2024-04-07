# statement, pre-statement
- SQL문을 실행할 수 있는 객체
- 가장 큰 차이점은 캐시 사용 여부

**SQL 실행 단계**
1) 쿼리 문장 분석
2) 컴파일 
3) 실행

### **Statement**
```sql
String sqlstr = "SELECT name, memo FROM TABLE WHERE name =" + num
Statement stmt = conn.createStatement();
ResultSet rst = stmt.executeQuery(sqlstr);
```

* **쿼리문을 수행할 때마다 SQL 실행단계 1~3 단계를 거침**
* SQL 문을 수행하는 과정에서 **매번 컴파일을 하기 때문에 성능상 이슈 발생**
* **실행되는 SQL문을 확인 가능**

### Prepared Statement
```java
String sqlstr = "SELECT name, memo FROM TABLE WHERE num = ?"
PreparedStatement stmt = conn.preparedStatement();
stmt.setInt(1, num);
ResultSet rst = stmt.executeQuery(sqlstr);
```

* **컴파일이 미리 되어있기 때문에 Statement에 비해 좋은 성능**
* **바인드 값을 자동으로 파싱**해주기 때문에 **SQL injection 같은 공격을 막을 수 있음**
* **"?" 부분에만 변화를 주어 쿼리문을 수행**하므로 **실행되는 SQL문을 파악하기 어려움**

**Prepared Statement를 사용해야 하는 경우** 
1) 사용자 입력값으로 쿼리문을 실행하는 경우
* 특수 기호가 들어오더라도 알아서 파싱해주므로 이로 인한 에러를 막을 우 있음
2. 쿼리 반복 수행 작업일 경우

**Statement와 PreparedStatement의 아주 큰 차이는 바로 캐시 사용여부이다.**
> **Statement를 사용하면 매번 쿼리를 수행할 때마다 계속적으로 단계를 거치면서 수행**하지만 **PreparedStatement는 처음 한 번만 세 단계를 거친 후 캐시에 담아 재사용을 한다.** **만약 동일한 쿼리를 반복적으로 수행한다면 PrepardStatement가 DB에 훨씬 적은 부하를 주며, 성능도 좋다.**
