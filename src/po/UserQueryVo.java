package po;

import java.util.List;

public class UserQueryVo {
	private UserCustomer usercustomer;
	private List<Integer>ids;
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	//这个类是总和查询传入的包装类
	public UserCustomer getUsercustomer() {
		return usercustomer;
	}

	public void setUsercustomer(UserCustomer usercustomer) {
		this.usercustomer = usercustomer;
	}
	
}
