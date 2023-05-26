package com.example.addressbook.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.example.addressbook.model.Info;

@Repository
public class InfoRedis {
    final String USER_ID = "user ID: ";

    @Autowired
    RedisTemplate<String,Object> template;

    public void saveInfo(Info userInfo){
        template.opsForHash().put(USER_ID, userInfo.getID(), userInfo);
        // model.addAttribute("info", userInfo);
    }

    public List<Info> getAllInfo(){
        return template.opsForHash().values(USER_ID).stream().filter(Info.class :: isInstance).map(Info.class :: cast).collect(Collectors.toList());
    }

    public Info getInfoByID(String userid){
        Info info = (Info) template.opsForHash().get(USER_ID, userid);
        return info;
    }
}
