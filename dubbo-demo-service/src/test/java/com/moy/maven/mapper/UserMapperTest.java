package com.moy.maven.mapper;

import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.moy.maven.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        User user = createRandomUser();
        Assert.assertTrue(userMapper.insert(user) == 1);
        Assert.assertTrue(user.getId() > 0);
    }

    @Test
    public void deleteTest() {
        User user = createRandomUser();
        Assert.assertTrue(userMapper.insert(user) == 1);
        Assert.assertTrue(userMapper.delete(user.getId()) == 1);
    }

    @Test
    public void updateTest() {
        User user = createRandomUser();
        Assert.assertTrue(userMapper.insert(user) == 1);
        Assert.assertNotNull(user.getPassword());
        user.setPassword(null);
        Assert.assertTrue(userMapper.update(user) == 1);
        Assert.assertNull(user.getPassword());
    }

    @Test
    public void selectTest() {
        List<User> list = userMapper.select(null);
        System.out.println(list);
    }

    @Test
    public void getTest() {
        User user = createRandomUser();
        Assert.assertTrue(userMapper.insert(user) == 1);
        User queryUser = userMapper.get(user.getId());
        Assert.assertTrue(user.getPassword().equals(queryUser.getPassword()));
    }

    @Test
    public void countTest() {
        Integer count = userMapper.count(null);
        System.out.println(count);
    }

    public static User createRandomUser() {
        User user = new User();
        Random random = new Random();
        user.setUsername("用戶" + random.nextInt(1000));
        user.setEmail(random.nextInt(1000) + "@qq.com");
        user.setPassword(String.valueOf(random.nextInt(1000)));
        user.setAddress(String.format("第%s星球", random.nextInt(1000)));
        return user;
    }
}