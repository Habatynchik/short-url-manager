package com.habatynchik.shorturlmanager.controller;

import com.habatynchik.shorturlmanager.model.entity.URL;
import com.habatynchik.shorturlmanager.service.URLService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class UrlController {
    private final URLService urlService;

    @Operation(
            summary = "Redirect",
            description = "Redirect to the original URL based on the provided short code.",
            parameters = @Parameter(
                    in = ParameterIn.PATH,
                    name = "shortCode",
                    description = "The shortened link code",
                    schema = @Schema(
                            type = "string",
                            example = "OXcdi1"
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "303", description = "Redirects to a URL"),
            @ApiResponse(responseCode = "404", description = "Error",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{shortCode}")
    public ResponseEntity<String> redirectURL(@PathVariable String shortCode) throws URISyntaxException {
        URL url = urlService.getByShortCode(shortCode);

        URI link = new URI(url.getLongUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(link);

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
