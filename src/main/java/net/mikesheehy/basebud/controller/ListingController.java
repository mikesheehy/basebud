package net.mikesheehy.basebud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.mikesheehy.basebud.model.Listing;
import net.mikesheehy.basebud.repository.ListingRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ListingController {

  @Autowired
  ListingRepository listingRepository;

  @GetMapping("/listings")
  public ResponseEntity<List<Listing>> getAllTutorials(@RequestParam(required = false) String title) {
    ...
  }

  @GetMapping("/listings/{id}")
  public ResponseEntity<Listing> getTutorialById(@PathVariable("id") long id) {
    ...
  }

  @PostMapping("/listings")
  public ResponseEntity<Listing> createTutorial(@RequestBody Listing listing) {
    ...
  }

  @PutMapping("/listings/{id}")
  public ResponseEntity<Listing> updateTutorial(@PathVariable("id") long id, @RequestBody Listing listing) {
    ...
  }

  @DeleteMapping("/listings/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    ...
  }

  @DeleteMapping("/listings")
  public ResponseEntity<HttpStatus> deleteAllListings() {
    ...
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Listing>> findByPublished() {
    ...
  }
}
