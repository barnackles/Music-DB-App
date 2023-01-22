package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Album;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("select a from Album a left join fetch a.performer left join fetch a.editions")
    List<Album> findAllAlbumsInitializeAllAssociations();

}
