package com.habatynchik.shorturlmanager.service.impl;

import com.habatynchik.shorturlmanager.dto.CustomUrlDto;
import com.habatynchik.shorturlmanager.exception.UrlAlreadyExistedException;
import com.habatynchik.shorturlmanager.exception.UrlNotFoundException;
import com.habatynchik.shorturlmanager.model.entity.URL;
import com.habatynchik.shorturlmanager.model.entity.URLInfo;
import com.habatynchik.shorturlmanager.repository.URLInfoRepository;
import com.habatynchik.shorturlmanager.repository.URLRepository;
import com.habatynchik.shorturlmanager.service.URLService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class URLServiceImpl implements URLService {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 7;

    private final URLRepository urlRepository;
    private final URLInfoRepository urlInfoRepository;

    Random random = new Random();

    @Override
    public URL generateShortURL(CustomUrlDto urlDto) {
        String shortCode = generateShortCode();

        URL urlMapping = URL.builder()
                .longUrl(urlDto.getLongURL())
                .shortCode(shortCode)
                .build();

        urlRepository.save(urlMapping);

        URLInfo urlInfo = URLInfo.builder()
                .url(urlMapping)
                .createdDate(LocalDate.now())
                .expiredDate(urlDto.getExpiredDate())
                .user(null)
                .build();

        urlInfoRepository.save(urlInfo);

        return urlMapping;
    }

    @Override
    public URL generateCustomURL(CustomUrlDto urlDto) {
        if (urlRepository.findByShortCode(urlDto.getShortCode()).isPresent()) {
            throw new UrlAlreadyExistedException();
        }

        URL url = URL.builder()
                .longUrl(urlDto.getLongURL())
                .shortCode(urlDto.getShortCode())
                .build();
        urlRepository.save(url);

        URLInfo urlInfo = URLInfo.builder()
                .url(url)
                .createdDate(LocalDate.now())
                .expiredDate(urlDto.getExpiredDate())
                .user(null)
                .build();

        urlInfoRepository.save(urlInfo);

        return url;
    }

    @Override
    public URL getByShortCode(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));
    }

    @Override
    public void delete(String shortCode) {
        URL url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new UrlNotFoundException(shortCode));

        urlRepository.delete(url);
    }


    private String generateShortCode() {
        StringBuilder shortCode = new StringBuilder();

        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            shortCode.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return urlRepository.findByShortCode(shortCode.toString()).isPresent() ? generateShortCode() : shortCode.toString();
    }
}
