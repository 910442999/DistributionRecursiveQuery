package com.yc.distribution.controller;

import com.yc.distribution.base.page.PageBean;
import com.yc.distribution.base.page.PageParam;
import com.yc.distribution.base.utils.PageParamHelper;
import com.yc.distribution.model.User;
import com.yc.distribution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userFacade;

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result.put("state", "success");
            int changeCount = this.userFacade.save(user);
            if (changeCount != 1) {
                result.put("state", "error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("update")
    public ResponseEntity<Map<String, Object>> updateUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int changeCount = this.userFacade.update(user);
            result.put("state", "success");
            if (changeCount != 1) {
                result.put("state", "error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("delete")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            int changeCount = this.userFacade.delete(user.getId());
            result.put("state", "success");
            if (changeCount != 1) {
                result.put("state", "error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("state", "error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
        return ResponseEntity.ok(result);
    }


    @PostMapping("query")
    public ResponseEntity<PageBean> queryUser(HttpServletRequest request) {
        try {
            PageParam pageParam = PageParamHelper.getPageParam(request);
            PageBean pageInfo = this.userFacade.listPage(pageParam);
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}