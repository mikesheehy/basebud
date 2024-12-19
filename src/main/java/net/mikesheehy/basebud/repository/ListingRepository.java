package net.mikesheehy.basebud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.mikesheehy.basebud.model.Listing;

public interface ListingRepository extends JpaRepository<Listing, Long> {
  List<Listing> findByPublished(boolean published);
  List<Listing> findByTitleContaining(String title);
}
