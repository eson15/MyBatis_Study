package mybatis.mapper;

import java.util.List;

import mybatis.po.OrdersCustom;

public interface UserMapperOrders {
	
	//查询订单，关联查询用户信息
	public List<OrdersCustom> findOrdersUser() throws Exception;
	
	//查询订单，关联查询用户信息,使用resultMap
	public List<OrdersCustom> findOrdersUserResultMap() throws Exception;

}
