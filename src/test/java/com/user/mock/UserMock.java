package com.user.mock;

import com.user.model.RootResponse;
import com.user.model.User;

public class UserMock {

    public static User getUserResponse(){
        User user = new User();
        user.setId(Long.valueOf(1));
        user.setName("Francisco");
        user.setUserName("fhernandez");
        user.setEmail("fhernandez204@gmail.com");
        user.setPhone("04147979906");
        return user;
    }
    public static User getNewUser(){
        User user = new User();
        user.setName("Antonio Dos Santos");
        user.setUserName("aSantos");
        user.setEmail("asantos@gmail.com");
        user.setPhone("04147979906");
        return user;
    }

    public static RootResponse getRootResponse(){
        RootResponse rootResponse = new RootResponse();
        rootResponse.setResponseCode("0");
        rootResponse.setDescription("OK");
        rootResponse.setElapsedTime("245");
        rootResponse.setResult(3);
        return rootResponse;
    }

}
