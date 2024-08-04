package com.ajou.procoding.myweb.dto;

import com.ajou.procoding.myweb.entity.FavoriteMusic;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteMusicRequestDto {
    private String collectionId;;
    private String collectionType;
     private String artistId;
     private String artistName;
     private String artistViewUrl;
    private String collectionName;
    private String collectionViewUrl;
    public FavoriteMusic toEntity() {
        FavoriteMusic music = new FavoriteMusic();
        music.setCollectionId(this.collectionId);
        music.setCollectionType(this.collectionType);
        music.setArtistId(this.artistId);
        music.setArtistViewUrl(this.artistViewUrl);
        music.setArtistName(this.artistName);
        music.setCollectionName(collectionName);
        music.setCollectionViewUrl(collectionViewUrl);
        return music;
    }
}