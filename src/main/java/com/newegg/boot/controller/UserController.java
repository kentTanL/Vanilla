package com.newegg.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newegg.boot.domain.entity.User;

/**
 * 类描述：这是一个实现操作User对象的RESTful API的demo
 * 
 * <li>GET /users 查询用户列表</li>
 * <li>POST /users 创建一个用户</li>
 * <li>GET /users/id 根据id查询一个用户</li>
 * <li>PUT /users/id 根据id更新一个用户</li>
 * <li>DELETE /users/id 根据id删除一个用户</li>
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Map<Long, User> users = new ConcurrentHashMap<Long, User>();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList() {
        return new ArrayList<User>(users.values());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        return users.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

}
