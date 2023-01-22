package com.barnackles.musicDbApp.repository;

import com.barnackles.musicDbApp.model.Edition;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EditionRepository extends JpaRepository<Edition, Long> {

    List<Edition> findEditionsByAlbumId(Long albumId);
    @Query("select e from Edition e left join fetch e.tracks where e.album.id = ?1")
    List<Edition> findEditionsByAlbumIdInitializeAllAssociations(Long albumId);

    @Query("select e from Edition e left join fetch e.album left join fetch e.tracks where e.album.title = ?1 and e.description like ?2%")
    Optional<Edition> findEditionByAlbumTitleAndEditionDescriptionStartsWithInitializeTracks(String albumTitle, String editionDescription);

}
