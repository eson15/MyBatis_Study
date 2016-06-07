3. mapper代理方法开发dao

程序员需要编写mapper.xml映射文件
程序员需要编写mapper接口(相当于dao接口)，需要遵循一些开发规范，这样mybatis可以自动生成mapper接口实现类的代理对象

思路：(开发规范)
1. 在mapper.xml中，使namespace等于mapper接口的地址（完全限定名）
2. mapper.java接口中的方法名和mapper.xml中statement的id一致
3. mapper.java接口中方法的输入参数类型和mapper.xml中statement的parameterType指定的类型一致
4. mapper.java接口中方法返回值类型和mapper.xml中statement的resultType指定的类型一致

以上的规范主要是对类似下边这些代码进行统一生成
User user = sqlSession.selectOne("test.findUserById, id);
sqlSession.insert("test.insertUser", user);

别忘了在全局配置文件SqlMapConfig.xml中配置刚刚的UserMapper.xml
<mappers>
		<mapper resource="sqlmap/User.xml" />
		<mapper resource="mapper/UserMapper.xml" />
</mappers>

如果mapper方法返回单个pojo对象（非集合对象），代理对象内部通过selectOne来查询数据库
如果mapper方法返回一个非集合对象，代理对象内部通过selectList来查询数据库
