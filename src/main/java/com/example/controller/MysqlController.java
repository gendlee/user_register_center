package com.example.controller;

import com.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MysqlController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping(value = "/getId", method = RequestMethod.GET)
    public String getId(@RequestParam(value = "id") Long id) {
        /*String sql = "SELECT nick_name FROM demo.user_register WHERE id = ?";

        String nickName = (String) jdbcTemplate.queryForObject(sql, new Object[]{1}, String.class);
*/

        return userRegisterService.getUserById(id).toString();
    }

}
