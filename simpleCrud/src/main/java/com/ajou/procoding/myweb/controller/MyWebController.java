package com.ajou.procoding.myweb.controller;

import com.ajou.procoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.procoding.myweb.dto.MusicList;
import com.ajou.procoding.myweb.entity.FavoriteMusic;
import com.ajou.procoding.myweb.repository.FavoriteRepository;
import com.ajou.procoding.myweb.service.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
public class MyWebController {

    @Autowired
    MusicService service;

    @GetMapping(value = "/musicSearch/{name}")
    public MusicList musicSearchByPath(@PathVariable String name){
        return service.searchMusic(name);
    }

    @GetMapping(value="/musicSearch")
    public MusicList musicSearchByParam(@RequestParam(value="term") String name) {
        return service.searchMusic(name);
    }
    @GetMapping(value="/likes")  //Get Favorite Music list from Database
    public List<FavoriteMusic> getLikes() {
        return service.getLikes();
    }

    @PostMapping(value="/likes")
    public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
        return service.saveFavorite(favorite);
    }
    @DeleteMapping(value = "/likes/{id}")
    public void deleteid(@PathVariable String id){
         service.deleteFavorite(id);
    }


}


