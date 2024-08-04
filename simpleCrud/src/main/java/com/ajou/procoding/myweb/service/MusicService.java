package com.ajou.procoding.myweb.service;

import com.ajou.procoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.procoding.myweb.dto.MusicList;
import com.ajou.procoding.myweb.entity.FavoriteMusic;
import com.ajou.procoding.myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate=new RestTemplate();

    public MusicList searchMusic(String name) {
        //https://itunes.apple.com/search?term=aespa&entity=album
        String url ="https://itunes.apple.com/search?term="+name+"&entity=album";
        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response, MusicList.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new MusicList();
        }
    }
    public int saveFavorite(FavoriteMusicRequestDto favoriteDto) {
        FavoriteMusic favoriteMusic = favoriteDto.toEntity();
        albumsRepo.save(favoriteMusic);
        return 1;
    }
    public List<FavoriteMusic> getLikes() {
        return albumsRepo.findAll();
    }
    public void deleteFavorite(String id) {
        try {
            albumsRepo.deleteById(id);
        } catch (DataAccessException e) {

            throw new RuntimeException("Failed to delete the favorite with ID: " + id, e);
        }
    }
}
