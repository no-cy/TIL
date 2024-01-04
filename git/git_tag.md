# git tag 
무언가를 표시하기 위한 기능, 특정 커밋을 태그 -> 특정 커밋을 가리키는 링크
 
**특징**
 * 수백개의 커밋들 중에 중요한 것만 태그를 지정해서 가독성을 높일 수 있음
 * 커밋과 차이점은 커밋의 경우 checkout 하여 내용 수정이 가능하나 태그는 수정이 불가능 함
 * 일반적으로 소프트웨어 버전을 릴리즈 할 때 사용

**tag 조회**  
 tag 명령어를 이용하여 현재 로컬 저장소의 모든 태그를 조회할 수 있다

**생성**  
 git의 태그는 생성할때 **lightweight**와 **annotated** 두 종류가 있다.
 
 **Lightweight**  
  - 특정 커밋을 가르키는 역할 (포인터 역할)
  - 둔선히 버전같은 태그이름만을 남기는 태그
 
**Annotated**  
  - 만든 사람, 이메일, 날짜 메시지를 객체로 따로 저장한다.
  - GPG(GNU Privacy Guard)로 서명할 수 있다.
  - 따라서 Lightweight 태그와는 달리 고유의 저장공간이 또 생긴다.

Lightweight 태그만을 남기면 이게 어떤 태그인지 누가 만든것인지에 대한 정보들을 알 수 없기 때문에
Annotated 태그를 사용하는 것이 일반적이다.
그러나 임시적으로 사용하기 위해 만드는 태그인 경우 Lightweight 태그를 사용하기도 한다.

**Lightweight 태그 생성**  
``$ git tag <TAG NAME>``

**특정 커밋 선택 태그 지정**  
 이전 히스토리를 검색해서 이전 커밋에 대해 태그를 지정할 수도 있다.
``$ git tag <TAG NAME> <커밋번호>``

**Annotated 태그 생성**  
lightweight 태그는 태그에 대한 정보는 없기 때문에 태그를 남기면서 정보를 같이 남기고 싶은 경우 Annotated 태그를 사용한다. (커밋 메세지와 동일)
Annotated 태그를 생성하는 방법은 ``tag -a`` 옵션을 사용한다.

* 커밋과 마찬가지로 태그를 남길때에도 메시지를 남겨야 하며 -m 옵션을 사용하면 태깅과 동시에 메시지를 작성할 수 있다.
* -a 는 Annotated 태그를 의미한다.
* -m 옵션은 메시지를 함께 저장할 때 사용한다. 붙이지 않으면 자동으로 Git 편집기를 실행한다.

**lightweight vs annotated 차이**  
show-ref --tags 명령어를 그냥 사용하면, 태그 레퍼런스들을 보여준다.  
그러나 이렇게만 조회하면 어떤게 lightweight 태그인지 annotated 태그인지 사실상 분간을 할수가 없다.  

--dereference 옵션과 함께 사용하면, 태그 객체를 dereferencing한 결과로, 태그 객체가 기리키고 있는 커밋ID도 보여준다.  
> Dereferencing
C 등의 언어에서, 포인터 값 앞에 붙여서 해당 레퍼런스가 갖고있는 내용을 가져오는 * 연산자를 dereference operator라 하며, 이 동작을
포인터를 dereferencing한다고 부른다.

``git show-ref --tags --dereference``  
* show-ref : 로컬 리포지토리의 레퍼런스들을 보여주는 명령어이다.  
* --dereference : 문자 그대로 태그를 dereferencing하여 태그가 가리키고 있는 내용이다.  
* 이 경우 ^{}를 뒤에 붙여서 보여준다.

깃에서 annotated 태그는 태그하고 있는 객체의 메타데이터와 SHA-1를 포함하고 있으며, 그 자체의 메시지와 ID를 갖고있는 객체이다.  

lightweight 태그는 태그 객체가 아니고, 태그한 객체를 그저 가르킨다.  

**lightweight 태그**
- 커밋번호가 곧 태그이다. lightweight 태그는 그냥 커밋번호를 가리키는 것이기 때문이다.
1. v0.4 : c782f1 (태그이자 커밋 번호)
2. v1.0.0 : d07c24 (태그이자 커밋 번호)

**annotated 태그**
- 자체 메세지, 이메일 등 데이터를 가지고 있는 객체를 가지고 있기 때문에, 태그번호와 커밋번호로 나뉘어져있다.
- ^{} 붙어있지 않은건 자체 태그번호, ^{} 붙어있는 것은 annotated태그가 가리키는 커밋 번호이다.
> 1.1 : ab6bac (annotated 자체 태그번호)  
v1.0 : ed4449 (annotated 자체 태그번호)  
1.1^{} :b84df1 (커밋 번호)  
v1.0^{} : 428d64 (커밋 번호)  

**태그 삭제**  
로컬 저장소의 태그를 삭제하려면 -d 또는 --delete 옵션을 사용한다  
``$ git tag -d v0.1.0``

**태그 수정**  
**lightweight 태그 수정** 
```
git tag <NEW TAG NAME> <OLD TAG NAME>  
git tag -d <OLD TAG NAME>  
```

**annotated 태그 수정**  
``git show-ref --tags --dereference``
```
# git tag -a <NEW TAG NAME> <OLD TAG NAME>^{} -m "<NEW TAG MESSAGE>"
$ git tag -a v1.3.0 v1.2.0^{} -m "<NEW TAG MESSAGE>"``
```

이렇게 commit checksum 정보로 새로운 tag 를 생성하는 것과 동일하다
```
# git push origin <태그 이름> (태그 원격저장소 push 하기)
$ git tag -a v1.3.0 88c34a1 -m "<NEW TAG MESSAGE>"
```
git push 명령은 자동으로 리모트 서버에 태그를 전송하지 않는다.  
고로 태그를 만들었으면 서버에 별도로 Push 해야 한다.

**git push origin <태그 이름>**  
``$ git push origin v1.0``

만약 한 번에 태그를 모두 Push 하고 싶다면 --tags 옵션을 추가하여 git push 명령을 실행한다.

**모든 태그를 한꺼번에 push하기**  
``$ git push origin --tags``

원격 저장소 태그 삭제
태그 삭제에는 3가지 명령어로 가능하다.
```
$ git push <REMOTE> :refs/tags/<TAG NAME>
$ git push <REMOTE> :<TAG NAME>
$ git push <REMOTE> -d <TAG NAME>
```
