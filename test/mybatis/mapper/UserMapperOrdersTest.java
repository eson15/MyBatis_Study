package mybatis.mapper;

import java.io.InputStream;
import java.util.List;

import mybatis.po.Orders;
import mybatis.po.OrdersCustom;
import mybatis.po.User;

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
	
	@Test
	public void testFindOrdersAndOrderDetailResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<Orders> list = userMapperOrders.findOrdersAndOrderDetailResultMap();
		System.out.println(list);
	}
	
	@Test
	public void testFindUserAndItemsResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		List<User> list = userMapperOrders.findUserAndItemsResultMap();
		System.out.println(list);
	}
	
	@Test
	public void testFindOrdersUserLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapperOrders userMapperOrders = sqlSession.getMapper(UserMapperOrders.class);
		//查询订单表（单表）
		List<Orders> list = userMapperOrders.findOrdersUserLazyLoading();
		
		//遍历上边的订单列表
		for(Orders orders : list) {
			//执行getUser()去查询用户信息，这里实现按需加载
			User user = orders.getUser();
			System.out.println(user);
		}
	}
	
	@Test
	public void testCache1() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();//创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//下边查询使用一个SqlSession
		//第一次发起请求，查询id为1的用户
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);
		
//		如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
		
		//更新user1的信息
		user1.setUsername("测试用户22");
		userMapper.updateUser(user1);
		//执行commit操作去清空缓存
		sqlSession.commit();
		
		//第二次发起请求，查询id为1的用户
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		
		sqlSession.close();
		
	}
	
	@Test
	public void testCache2() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();
		// 创建代理对象
		UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
		// 第一次发起请求，查询id为1的用户
		User user1 = userMapper1.findUserById(1);
		System.out.println(user1);
		
		//这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
		sqlSession1.close();
		
		//sqlSession3用来清空缓存的，如果要测试二级缓存，需要把该部分注释掉
		//使用sqlSession3执行commit()操作
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user  = userMapper3.findUserById(1);
		user.setUsername("倪升武");
		userMapper3.updateUser(user);
		//执行提交，清空UserMapper下边的二级缓存
		sqlSession3.commit();
		sqlSession3.close();
		
		

		UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
		// 第二次发起请求，查询id为1的用户
		User user2 = userMapper2.findUserById(1);
		System.out.println(user2);

		sqlSession2.close();

	}


}
