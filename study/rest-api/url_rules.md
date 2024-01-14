# URL Rules

### **1. 마지막에 / 포함하지 않는다.**  
BAD  
`http://nocy.com/products/`  
GOOD  
`http://nocy.com/products`  

---
### **2. _(underbar) 대신 -(dash)를 사용한다.**  
-(dash)의 사용도 최소한으로 설계한다.  
정확한 의미나 표현을 위해 단어의 결합이 불가피한 경우 반드시 -(dash) 사용한다.  

BAD  
`http://nocy.com/users/postCommnets`  
GOOD  
`http://nocy.com/users/post-commnets`  

---
### **3. 소문자를 사용한다.**
BAD  
`http://nocy.com/Products`  
GOOD  
`http://nocy.com/products`  

---
### **4. 행위(method)는 URL에 포함하지 않는다.**   
BAD  
`POST http://nocy.com/delete-carts/{cart_id}`  
GOOD  
`DELETE http://nocy.com/carts/{cart_id}`  

---
### **5. 컨르롤 자원을 의미하는 URL 예외적으로 동사를 허용한다.**  
함수처럼, 컨트롤 리소스를 나타내는 URL은 동작을 포함하는 이름을 짓는다.  

BAD  
`http://nocy.com/posts/duplicating`  
GOOD  
`http://nocy.com/posts/duplicate`  
