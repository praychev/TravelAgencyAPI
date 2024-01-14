package com.example.TravelAgency.controllers;

import com.example.TravelAgency.dtos.CreateLocationDTO;
import com.example.TravelAgency.dtos.ResponseLocationDTO;
import com.example.TravelAgency.dtos.UpdateLocationDTO;
import com.example.TravelAgency.entities.Location;
import com.example.TravelAgency.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    public LocationController(){
    }
    @PostMapping("/locations")
    public ResponseEntity <ResponseLocationDTO> createLocation(@RequestBody CreateLocationDTO locationDTO){
        Location location = new Location();
        location.setStreet(locationDTO.getStreet());
        location.setNumber(locationDTO.getNumber());
        location.setCity(locationDTO.getCountry());
        location.setCountry(locationDTO.getCountry());
        location.setImageUrl(locationDTO.getImageUrl());
        Location createdLocation = locationRepository.save(location);
        return ResponseEntity.ok(createdLocation.toResponseDTO());
    }

    @DeleteMapping("/locations/{locationId}")
    public boolean deleteLocation(@PathVariable Long locationId){
        Location location = locationRepository.getById(locationId);
        if(location == null){
            return true;
        }
        locationRepository.delete(location);
        return !locationRepository.existsById(locationId);
    }

    @GetMapping("/locations")
    public ResponseEntity<List<ResponseLocationDTO>> getLocations(){
        List<Location> locations = locationRepository.findAll();
        List<ResponseLocationDTO> locationsResponse = new ArrayList<>();
        for (Location location: locations){
            locationsResponse.add(location.toResponseDTO());
        }
        return ResponseEntity.ok(locationsResponse);
    }

    @GetMapping("/locations/{locationId}")
    public ResponseEntity <ResponseLocationDTO> getLocation(@PathVariable Long locationId){
        Optional<Location> foundLocation = locationRepository.findById(locationId);
        if(!foundLocation.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundLocation.get().toResponseDTO());
    }

    @PutMapping("/locations")
    public ResponseEntity<ResponseLocationDTO> putLocations(@RequestBody UpdateLocationDTO locationDTO){
        Optional<Location> location = locationRepository.findById(locationDTO.getId());
        Location locationToUpdate = new Location();
        if (location.isPresent()){
            if(!locationDTO.getId().equals(null)){
                locationToUpdate.setId(locationDTO.getId());
            }else {
                return ResponseEntity.notFound().build();
            }
            if(locationDTO.getStreet() != null){
                locationToUpdate.setStreet(locationDTO.getStreet());
            }
            if(locationDTO.getNumber() != null) {
                locationToUpdate.setNumber(locationDTO.getNumber());
            }
            if(locationDTO.getCity() != null) {
                locationToUpdate.setCity(locationDTO.getCity());
            }
            if(locationDTO.getCountry() != null) {
                locationToUpdate.setCountry(locationDTO.getCountry());
            }
            if(locationDTO.getImageUrl() != null) {
                locationToUpdate.setImageUrl(locationDTO.getImageUrl());
            }
            Location updatedLocation = locationRepository.save(locationToUpdate);
            return ResponseEntity.ok(updatedLocation.toResponseDTO());
        }return ResponseEntity.notFound().build();
    }
}
