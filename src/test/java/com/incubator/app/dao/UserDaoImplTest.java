//package com.incubator.app.dao;
//
//import com.incubator.app.entity.Role;
//import com.incubator.app.entity.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"/applicationContext.xml"})
//public class UserDaoImplTest {
//
//    @Autowired
//    private UserDao userDaoImpl;

//
//    @Test
//    public void findByLoginTest(){
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("212011"));
//        String login = "admin";
//        User expectedUser = new User();
//        expectedUser.setLogin("admin");
//        //expectedUser.setPassword("$2y$10$WKTCFq5opTjl/1pcKg971ueOGalbx7AMhTGghO6aSEpNFc7aPxEze");
//        expectedUser.setPassword("$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.");
//        expectedUser.setRole(new Role(1, "ROLE_ADMIN"));
//        expectedUser.setFirstName("Anton");
//        expectedUser.setLastName("Shtabnoy");
//        expectedUser.setIsDeleted(0);
//        expectedUser.setId(1L);
//        User actualUser = userDaoImpl.findByLogin(login);
//        Assert.assertEquals(expectedUser, actualUser);
//    }

//
//    @Test
//    public void insert(){
//        User newUser = new User();
//        newUser.setFirstName("Vika");
//        newUser.setLastName("Azarenka");
//        newUser.setIsDeleted(0);
//        newUser.setPassword("$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.");
//        newUser.setLogin("Aza");
//        newUser.setRole(new Role(3, "ROLE_USER"));
//        userDaoImpl.insert(newUser);
//        User expectedUser = userDaoImpl.findByLogin("Aza");
//        Assert.assertEquals(expectedUser, newUser);
//    }

//    @Test
//    public void delete(){
//        long[] ids = {2};
//        userDaoImpl.deleteSeveralUsers(ids);
//    }
//    @Test
//    public void isEditUser(){
//        User expectedUser = new User();
//        expectedUser.setLogin("admin");
//        //expectedUser.setPassword("$2y$10$WKTCFq5opTjl/1pcKg971ueOGalbx7AMhTGghO6aSEpNFc7aPxEze");
//        expectedUser.setPassword("$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.");
//        expectedUser.setRole(new Role(1, "ROLE_ADMIN"));
//        expectedUser.setFirstName("Yura");
//        expectedUser.setLastName("Shtabnoy");
//        expectedUser.setIsDeleted(0);
//        expectedUser.setId(1L);
//
//        User user = userDaoImpl.findById(1L);
//        user.setFirstName("Yura");
//        userDaoImpl.update(user);
//
//        Assert.assertEquals(expectedUser, userDaoImpl.findById(1L));
//    }
//}
