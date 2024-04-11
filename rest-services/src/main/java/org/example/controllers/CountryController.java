package org.example.controllers;

import java.util.List;
import org.example.dto.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CountryController {
    @GetMapping("/countries/india")
    public ResponseEntity<Country> india() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Continent", "Asia")
                .header("Capital", "Delhi")
                .body(Country.of("India", 1400));
    }

    @GetMapping("/countries")
    public List<Country> all() {
        return List.of(
                Country.of("India", 1400),
                Country.of("US", 360)
        );
    }
}
