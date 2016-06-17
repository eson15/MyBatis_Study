package mybatis.mapper;


import java.io.InputStream;
import java.util.Date;
import java.util.List;

import mybatis.po.Items;
import mybatis.po.ItemsExample;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class ItemsMapperTest {

	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setUp() throws Exception {
		// 创建sqlSessionFactory
		String resource = "SqlMapConfig.xml"; // mybatis配置文件

		// 得到配置文件的流
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂SqlSessionFactory,要传入mybaits的配置文件的流
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

	}

	@Test
	public void testInsert() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		Items items = new Items();
		items.setName("手机");
		items.setPrice(5000f);
		items.setCreatetime(new Date());
		itemsMapper.insert(items);
		sqlSession.commit();
	}

	//自定义查询
	@Test
	public void testSelectByExample() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		
		ItemsExample itemsExample = new ItemsExample();
		ItemsExample.Criteria criteria = itemsExample.createCriteria();
		criteria.andNameEqualTo("背包");
		List<Items> itemsList = itemsMapper.selectByExample(itemsExample);
		System.out.println(itemsList);
	}

	@Test
	public void testSelectByPrimaryKey() {
		ItemsMapper itemsMapper = sqlSessionFactory.openSession().getMapper(ItemsMapper.class);
		Items items = itemsMapper.selectByPrimaryKey(1);
		System.out.println(items);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
		Items items = itemsMapper.selectByPrimaryKey(1);
		items.setPrice(3540f);
		itemsMapper.updateByPrimaryKey(items);
		sqlSession.commit();
	}

}
