package com.newegg.boot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import com.newegg.boot.domain.entity.User;

import net.minidev.json.JSONUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        
        User user_1 = new User(1L, "Test_1", 20);
        
        testGetUserList("[]");
        
        testPostUser(user_1);
        
        testGetUserList(user_1.toJsons());
        
        user_1.setName("Test_updated");
        
        testPutUser(user_1);
        
        testGetUser(1L, user_1.toJson());
    }

    private void testGetUserList(String userJson) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/user/").accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo(userJson)));
    }

    private void testPostUser(User user) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/user/")
                                    .param("id", String.valueOf(user.getId()))
                                    .param("name", user.getName())
                                    .param("age", String.valueOf(user.getAge()));
        mvc.perform(request).andExpect(content().string(equalTo("success")));
    }
    
    private void testPutUser(User user) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/user/" + user.getId())
                .param("name", user.getName())
                .param("age", String.valueOf(user.getAge()));
        mvc.perform(request).andExpect(content().string(equalTo("success")));
    }
    
    private void testGetUser(Long id, String resultJson) throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/user/" + id).accept(MediaType.APPLICATION_JSON);
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo(resultJson)));
    }
}
