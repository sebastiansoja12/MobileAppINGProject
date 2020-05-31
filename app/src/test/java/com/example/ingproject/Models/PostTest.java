package com.example.ingproject.Models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostTest {

    Post post, post2;
    User user;
    @Test
    public void getFakePost() {
        post = new Post(1,1, "Title", "body");
        post2 = new Post(1,2 ,"Title2", "body2");
        String exptected = "Title";
        Assert.assertEquals(exptected, getTitle());
    }
    public String getTitle(){
        post.setTitle("Title");
        return post.getTitle();
    }

    @Test
    public void getFakeUser()  {
        user = new User(1, "Test", "userTest", "test@test.pl", "712375698", "test.pl");
    }

}