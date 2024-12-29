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
  public ResponseEntity<List<Listing>> getAllListings(@RequestParam(required = false) String title) {
    try {
        List<Listing> listings = new ArrayList<>();

        if (title == null)
            listingRepository.findAll().forEach(listings::add);
        else
            listingRepository.findByTitleContaining(title).forEach(listings::add);

        if (listings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(listings, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/listings/{id}")
public ResponseEntity<Listing> getListingById(@PathVariable("id") long id) {
    Optional<Listing> listingData = listingRepository.findById(id);

    if (listingData.isPresent()) {
        return new ResponseEntity<>(listingData.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@PostMapping("/listings")
public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
    try {
        Listing _listing = listingRepository
                .save(new Listing(listing.getTitle(), listing.getDescription(), false));
        return new ResponseEntity<>(_listing, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PutMapping("/listings/{id}")
public ResponseEntity<Listing> updateListing(@PathVariable("id") long id, @RequestBody Listing listing) {
    Optional<Listing> listingData = listingRepository.findById(id);

    if (listingData.isPresent()) {
        Listing _listing = listingData.get();
        _listing.setTitle(listing.getTitle());
        _listing.setDescription(listing.getDescription());
        _listing.setPublished(listing.isPublished());
        return new ResponseEntity<>(listingRepository.save(_listing), HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/listings/{id}")
public ResponseEntity<HttpStatus> deleteListing(@PathVariable("id") long id) {
    try {
        listingRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@DeleteMapping("/listings")
public ResponseEntity<HttpStatus> deleteAllListings() {
    try {
        listingRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

@GetMapping("/listings/published")
public ResponseEntity<List<Listing>> findByPublished() {
    try {
        List<Listing> listings = listingRepository.findByPublished(true);

        if (listings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listings, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}