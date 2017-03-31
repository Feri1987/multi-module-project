package com.test.service;

import com.test.commons.entity.User;
import com.test.dao.SelectUsers;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {


    public static void main(String[] args) {

        ApplicationContext context =  new AnnotationConfigApplicationContext(ConfigurationContext.class);

        SelectUsers selectUsers = (SelectUsers) context.getBean("selectUsers");
        List<User> users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();
         users = selectUsers.selectCachedUsers();


        System.out.println(users.toString());
    }


}
