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

	//��������ܺͲ�ѯ����İ�װ��
	public UserCustomer getUsercustomer() {
		return usercustomer;
	}

	public void setUsercustomer(UserCustomer usercustomer) {
		this.usercustomer = usercustomer;
	}
	
}
