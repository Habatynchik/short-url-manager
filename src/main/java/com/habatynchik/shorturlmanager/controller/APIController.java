package com.habatynchik.shorturlmanager.controller;

import com.habatynchik.shorturlmanager.dto.CustomUrlDto;
import com.habatynchik.shorturlmanager.model.entity.URL;
import com.habatynchik.shorturlmanager.service.URLService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {
    @Value("${short.url.domain}")
    private String domain;

    private final URLService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenURL(@RequestBody @Valid CustomUrlDto urlDto) {
        URL url = urlService.generateShortURL(urlDto);
        String shortURL = domain + url.getShortCode();

        return new ResponseEntity<>(shortURL, HttpStatus.CREATED);
    }

    @PostMapping("/custom")
    public ResponseEntity<String> customURL(@RequestBody @Valid CustomUrlDto customUrlDto) {
        URL url = urlService.generateCustomURL(customUrlDto);
        String shortURL = domain + url.getShortCode();

        return new ResponseEntity<>(shortURL, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{shortCode}")
    public void deleteURL(@PathVariable String shortCode) {
        urlService.delete(shortCode);
    }
}
