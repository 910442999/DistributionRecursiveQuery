package com.yc.distribution.controller;

import com.yc.distribution.base.Constants;
import com.yc.distribution.base.page.PageBean;
import com.yc.distribution.base.page.PageParam;
import com.yc.distribution.base.utils.PageParamHelper;
import com.yc.distribution.model.User;
import com.yc.distribution.model.UserRelation;
import com.yc.distribution.service.UserRelationService;
import com.yc.distribution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserController
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRelationService mUserRelationService;

    @PostMapping("save")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            result.put("state", "success");
            int changeCount = this.userService.save(user);
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
            int changeCount = this.userService.update(user);
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
            int changeCount = this.userService.delete(user.getId());
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
            PageBean pageInfo = this.userService.listPage(pageParam);
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("queryDistribution")
    public ResponseEntity<Map<String, Object>> queryDistribution(@RequestParam String id, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<User> users = new ArrayList<>();

        //查找的第一个父类
        List<User> parent = getParent(users, id);
        //查找的第二个父类
        getParent(users, parent.get(0).getId());

//        for (int i = 0; i < users.size(); i++) {
//
//        }
        result.put("data", users);
        return ResponseEntity.ok(result);
    }

    public List<User> getParent(List<User> users, String childUserId) {
        UserRelation userRelationTemp = new UserRelation();
        userRelationTemp.setChildId(childUserId);
        List<UserRelation> childUser = mUserRelationService.listByObj(userRelationTemp);
        if (!CollectionUtils.isEmpty(childUser)) {
            String parentId = childUser.get(0).getParentId();
            User parentUser2 = userService.getById(parentId);
            if (parentUser2 != null) {
                String parentUser1Level = parentUser2.getUserLevel();
                if (Constants.USER_LEVEL_PRIMARY_AGENT.equals(parentUser1Level)
                        || Constants.USER_LEVEL_HIGH_AGENT.equals(parentUser1Level)
                        || Constants.USER_LEVEL_COMPANY_PARTENER.equals(parentUser1Level)) {
                    users.add(parentUser2);
                    return users;
                } else {
                    //如果上级不是合伙人，接着把找到的节点，作为子节点在找上级
                    List<User> parent = getParent(users, parentUser2.getId());
                    return parent;
                }
            }
        }
        return null;
    }
}