package test;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import po.Orders;
import po.User;
import po.UserCustomer;
import po.UserQueryVo;

import java.util.Date;
import java.util.List;


public class TestMyBatis {
    static SqlSessionFactory sqlSessionFactory = null;

    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    public static void main(String[] args) throws Exception {
        //  getUser();
        //getUserByName();
        //getUserByNameAndSex();
        //getUserByIdResultMap();
        //insertUser();
        //deleteUser();
        //findOrdersList();
        //findOrdersDetailList();
        findOrders2();
        test();
    }

    private static void test() {
        /**
         * @Author: xiaojianyu
         * @Method: test
         * @Description: 第一次进行intellij idea测试
         * @Date: 16:35 2017/7/30
         * @Return: void
         * @Param: []
         */
        for (int i = 0; i < 5; i++) {
            i++;
            Integer i2 = new Integer(13);
            String s = i2.toString();
        }
    }

    /**
     * 延迟加载的一对一查询
     */
    public static void findOrders2() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Orders> list = userMapper.findOrdersList2();
            for (Orders orders : list) {
                System.out.println(orders.getUser().getUsername());
            }
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 一对多关联查询
     * order类有orderdetail类的list集合
     */
    public static void findOrdersDetailList() throws Exception {
        //<editor-fold desc="折叠">
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Orders> list = userMapper.findOrdersDetailList();
            for (Orders orders : list) {
                System.out.println(orders.getOrderdetails().size());
            }
        } finally {
            sqlSession.close();
        }
        //</editor-fold>
    }

    /**
     * 一对一关联查询
     * order有user类的实例
     *
     * @throws Exception
     */
    public static void findOrdersList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //<editor-fold desc="折叠代码段选择ctrl+alt+t">
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Orders> list = userMapper.findOrdersListResultMap();
            for (Orders orders : list) {
                System.out.println(orders.getUser().getUsername());
            }
        } finally {
            sqlSession.close();
        }
        // </editor-fold>
    }

    /**
     * 利用id进行删除
     *
     * @throws Exception
     */
    public static void deleteUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteUserById(30);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


    /**
     * 插入测试 插入paramtype为user  有主键返回
     */
    private static void insertUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // TODO Auto-generated method stub
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setAddress("广东");
            user.setBirthday(new Date());
            user.setUsername("辣鸡");
            user.setSex("2");
            userMapper.insertUser(user);
            //插入的一个难点就是 你在xml中写入插入后的主键返回 需要的时候直接用插入后的对象获取主键值
            //uuid和自增主键的区别 uuid要自己设置故用before而自增主键是数据库定义的故用after
            System.out.println(user.getId());
            sqlSession.commit();// 这里一定要提交，不然数据进不去数据库中
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 利用resulttype进行 id查询
     */
    public static void getUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findUserById(10);
            System.out.println(user.getUsername() + user.getAddress());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 利用resulttype进行 中文名称综合查询
     */
    public static void getUserByNameAndSex() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            UserCustomer userCustomer = new UserCustomer();
            userCustomer.setUsername("张三");
            userCustomer.setSex("1");
            UserQueryVo userQueryVo = new UserQueryVo();
            userQueryVo.setUsercustomer(userCustomer);
            List<User> list = userMapper.findUserByNameAndSex(userQueryVo);
            System.out.println(list.get(0).getUsername() + list.get(0).getSex());


        } finally {
            sqlSession.close();
        }
    }

    /**
     * resulttype
     * 进行多条件查询 usercustomer为继承user的类
     */
    public static void getUserByName() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = userMapper.findUserByName("张三");
            System.out.println(list.get(0).getAddress() + list.get(0).getUsername());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 根据resultmap用id来查询
     */
    public static void getUserByIdResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.findUserByIdResultMap(10);
            System.out.println(user.getUsername() + user.getId());
        } finally {
            sqlSession.close();
        }
    }
}