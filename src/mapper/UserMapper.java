package mapper;

import java.util.List;

import po.Orders;
import po.User;
import po.UserCustomer;
import po.UserQueryVo;

/**
 *
 * <p>Title: UserMapper</p>
 * <p>Description: mapper接口，相当 于dao接口，用户管理</p>
 * <p>Company: </p>
 * @author
 * @date	2015-4-22下午2:45:12
 * @version 1.0
 */
public interface UserMapper {

	public List<UserCustomer> findUserList(UserQueryVo userQueryVo)throws Exception;
	//根据id查询用户信息
	public User findUserById(int id) throws Exception;


	//根据用户名列查询用户列表
	public List<User> findUserByName(String name)throws Exception;

	//多条件查询
	public List<User> findUserByNameAndSex(UserQueryVo userQueryVo)throws Exception;

	//这个按id查询是利用resultmap和上面的不一样
	public User findUserByIdResultMap(int id) throws Exception;

	//插入用户
	public void insertUser(User user)throws Exception;

	//删除用户
	public void deleteUserById(int id)throws Exception;

	//一对一关联查询
	public List<Orders> findOrdersListResultMap()throws Exception;

	//关联查询一对多 order为一 ordersdetail为多
	public List<Orders> findOrdersDetailList()throws Exception;

	//延迟加载 一对一查询
	public List<Orders> findOrdersList2()throws Exception;

}