package com.paintingscollectors.repository;

import com.paintingscollectors.model.entity.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
    List<Painting> findByOwnerId(Long ownerId);
    List<Painting> findByOwnerIdNot(Long ownerId);
}
