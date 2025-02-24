package com.dyf.forSomeTest;

import com.dyf.forSomeTest.entity.User;
import com.dyf.forSomeTest.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {





    @Resource
    private UserMapper userMapper;

    //声明式事务
    @org.junit.Test
    @Transactional
    @Commit
    public void testAddTwoUser() {

        User user1 = new User();
        User user2 = new User();
        user1.setUserId("001");
        user1.setUserPassword("001");
        user1.setNickName("001");

        user2.setUserId("002");
        user2.setUserPassword("002");
        user2.setNickName("002");
        System.out.println(userMapper);

        userMapper.insert(user1);
        int a=10/0;
        userMapper.insert(user2);

    }

    @Resource
    private PlatformTransactionManager transactionManager;

    //编程式事务
    @Test
    public void testMy(){
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); //设置事务传播行为
        TransactionStatus transaction = transactionManager.getTransaction(defaultTransactionDefinition);

        try{
            User user1 = new User();
            User user2 = new User();
            user1.setUserId("001");
            user1.setUserPassword("001");
            user1.setNickName("001");

            user2.setUserId("002");
            user2.setUserPassword("002");
            user2.setNickName("002");
            System.out.println(userMapper);

            userMapper.insert(user1);
            //int a=10/0;
            userMapper.insert(user2);
            //提交事务
            transactionManager.commit(transaction);
        }catch (Exception e){
            //回滚事务
            transactionManager.rollback(transaction);
            e.printStackTrace();
        }
    }


    @Resource
    private TransactionTemplate transactionTemplate;
    //编程式事务
    @Test
    public void testMy02(){
//
//        transactionTemplate.execute(new TransactionCallback(){
//            @Override
//            public Object doInTransaction(TransactionStatus transactionStatus) {
//                User user1 = new User();
//                User user2 = new User();
//                user1.setUserId("001");
//                user1.setUserPassword("001");
//                user1.setNickName("001");
//
//                user2.setUserId("002");
//                user2.setUserPassword("002");
//                user2.setNickName("002");
//                System.out.println(userMapper);
//
//                userMapper.insert(user1);
//               // int a=10/0;
//                userMapper.insert(user2);
//                return null;
//            }
//        });

        transactionTemplate.execute(transactionStatus -> {  // transactionTemplate是spring提供的工具类
            User user1 = new User();
            User user2 = new User();
            user1.setUserId("001");
            user1.setUserPassword("001");
            user1.setNickName("001");

            user2.setUserId("002");
            user2.setUserPassword("002");
            user2.setNickName("002");
            System.out.println(userMapper);

            userMapper.insert(user1);
             int a=10/0;
            userMapper.insert(user2);
            return null;
        });
    }
}
