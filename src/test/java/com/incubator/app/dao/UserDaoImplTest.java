//package com.incubator.app.dao;
//
//import com.incubator.app.entity.Literature;
//import com.incubator.app.entity.Question;
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
//import java.util.HashSet;
//import java.util.Set;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"/applicationContext.xml"})
//public class UserDaoImplTest {
//
//    @Autowired
//    private UserDao userDaoImpl;
//
//    @Autowired
//    private QuestionDao questionDao;
//
//
////    @Test
////    public void findByLoginTest(){
////        PasswordEncoder encoder = new BCryptPasswordEncoder();
////        System.out.println(encoder.encode("212011"));
////        String login = "admin";
////        User expectedUser = new User();
////        expectedUser.setLogin("admin");
////        //expectedUser.setPassword("$2y$10$WKTCFq5opTjl/1pcKg971ueOGalbx7AMhTGghO6aSEpNFc7aPxEze");
////        expectedUser.setPassword("$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.");
////        expectedUser.setRole(new Role(1, "ROLE_ADMIN"));
////        expectedUser.setFirstName("Anton");
////        expectedUser.setLastName("Shtabnoy");
////        expectedUser.setIsDeleted(0);
////        expectedUser.setId(1L);
////        User actualUser = userDaoImpl.findByLogin(login);
////        Assert.assertEquals(expectedUser, actualUser);
////    }
////
////
////    @Test
////    public void insert(){
////        User newUser = new User();
////        newUser.setFirstName("Vika");
////        newUser.setLastName("Azarenka");
////        newUser.setIsDeleted(0);
////        newUser.setPassword("$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.");
////        newUser.setLogin("Aza");
////        newUser.setRole(new Role(3, "ROLE_USER"));
////        userDaoImpl.insert(newUser);
////        User expectedUser = userDaoImpl.findByLogin("Aza");
////        Assert.assertEquals(expectedUser, newUser);
////    }
////
////    @Test
////    public void delete(){
////        long[] ids = {2};
////        userDaoImpl.deleteSeveralUsers(ids);
////    }
////    @Test
////    public void isEditUser(){
////        User expectedUser = new User();
////        expectedUser.setLogin("admin");
////        //expectedUser.setPassword("$2y$10$WKTCFq5opTjl/1pcKg971ueOGalbx7AMhTGghO6aSEpNFc7aPxEze");
////        expectedUser.setPassword("$2a$10$uAUdbabp1tIv8KyvpwF0P.Yb/Py4xb/TeEpaAGu2YNqOxdkIjjdT.");
////        expectedUser.setRole(new Role(1, "ROLE_ADMIN"));
////        expectedUser.setFirstName("Yura");
////        expectedUser.setLastName("Shtabnoy");
////        expectedUser.setIsDeleted(0);
////        expectedUser.setId(1L);
////
////        User user = userDaoImpl.findById(1L);
////        user.setFirstName("Yura");
////        userDaoImpl.update(user);
////
////        Assert.assertEquals(expectedUser, userDaoImpl.findById(1L));
////    }
//    @Test
//    public void insertQuestion(){
//        Question question = new Question();
//        question.setDescription("KIU");
//        question.setIsDeleted(0);
//        com.incubator.app.entity.Test test = new com.incubator.app.entity.Test();
//        test.setId(1);
//        question.setTest(test);
//        Set<Literature> literatureSet = new HashSet<>();
//        Literature literature = new Literature();
//        literature.setQuestion(question);
//        literature.setDescription("L");
//        literatureSet.add(literature);
//        question.setLiteratureList(literatureSet);
//        questionDao.insert(question);
//    }
//
//
//}
//
