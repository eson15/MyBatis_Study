package mybatis.po;

import java.util.List;

public class UserQueryVo {
	
	//在这里添加所需要的查询条件
	
	//传入多个id
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	//用户查询条件
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
	
	//可以包装其他的查询条件，比如订单、商品等
	//......
	
}
