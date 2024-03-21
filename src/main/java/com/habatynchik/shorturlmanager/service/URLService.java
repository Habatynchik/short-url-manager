package com.habatynchik.shorturlmanager.service;

import com.habatynchik.shorturlmanager.dto.CustomUrlDto;
import com.habatynchik.shorturlmanager.model.entity.URL;

public interface URLService {
    URL generateShortURL(CustomUrlDto urlDto);

    URL generateCustomURL(CustomUrlDto customUrlDto);

    URL getByShortCode(String shortCode);

    void delete(String shortCode);
}
