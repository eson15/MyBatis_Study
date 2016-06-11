2. 原始dao的开发方法——程序员需要写dao接口和实现类

思路：
程序员需要写dao接口和dao实现类
需要向dao实现类中注入sqlSessionFactory，在方法体内通过sqlSessionFactory创建sqlSession
	为什么在方法体内创建呢？因为sqlSession是线程不安全的。
	如果在外面创建，可能会引发线程安全问题。
	
1. 写UserDao接口 (UserDao.java)
2. 写UserDaoImpl实现类(UserDaoImpl.java)
3. 写单元测试UserDaoImplTest

4. 原始dao开发存在的问题
	4.1 dao接口实现类方法中存在大量模板方法，从设计上来看，应该抽取
	4.2  调用sqlSession方法时，将satement的id硬编码了，即写死了
	4.3 sqlSession的方法中，要求传入的参数是Object类型的(泛型)，也就是说如果我传错了参数，
		编译不会报错，执行的时候才会报错，不利于开发。