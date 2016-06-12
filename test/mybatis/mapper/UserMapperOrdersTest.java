package mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import mybatis.po.OrdersCustom;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserMapperOrdersTest {

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
	public void testUserMapperOrders() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<OrdersCustom> list = userMapperOrders.findOrdersUser();
		System.out.println(list);
	}
	
	@Test
	public void testUserMapperOrdersResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<OrdersCustom> list = userMapperOrders.findOrdersUserResultMap();
		System.out.println(list);
	}

}
