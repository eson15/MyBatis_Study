package mybatis.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {
	
	private static final long serialVersionUID = -4637590911523717296L;
	
	private Integer id;
	private String username;
	private Date birthday;
	private String sex;
	private String address;
	
	private List<Orders> ordersList;
	
		
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthday="
				+ birthday + ", sex=" + sex + ", address=" + address
				+ ", ordersList=" + ordersList + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, Date birthday, String sex, String address) {
		super();
		this.username = username;
		this.birthday = birthday;
		this.sex = sex;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public List<Orders> getOrdersList() {
		return ordersList;
	}


	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}

}
