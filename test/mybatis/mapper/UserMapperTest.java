package mybatis.mapper;
import java.io.InputStream;
import java.util.List;

import mybatis.mapper.UserMapper;
import mybatis.po.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTest {

private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setUp() throws Exception {
		//创建sqlSessionFactory
		String resource = "SqlMapConfig.xml"; //mybatis配置文件
	
		//得到配置文件的流
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	
	@Test
	public void testFindUserById() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = userMapper.findUserById(1);
		System.out.println(user);
	}
	
	@Test
	public void testFindUserByName() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//创建UserMapper对象，mybatis自动生成mapper代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findUserByName("倪升武");
		sqlSession.close();
		System.out.println(list);
	}

}