package com.example.websitesecondhand.controller;

import com.example.websitesecondhand.dto.*;
import com.example.websitesecondhand.service.AdsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "ad", description = "API for work with ads")
@AllArgsConstructor
public class AdsController {

    private final AdsService adsService;

    @Operation(summary = "Get all ads")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperAdsDto[].class))
            ))
    @GetMapping("/ads")
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds() {
        return ResponseEntity.ok(adsService.getAllAdsDto());
    }

    @Operation(summary = "Create ads")
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class))
    )
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping(value = "/ads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto> addAd(@RequestPart CreateAdsDto properties, @RequestPart MultipartFile image) {
        log.info("New ads added {}", properties.getTitle());
        return ResponseEntity.ok(adsService.addAds(properties, image));
    }

    @Operation(summary = "Get ad by id")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = FullAdsDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/ads/{adsId}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable int adsId) {
        return ResponseEntity.ok(adsService.getFullAds(adsId));
    }

    @Operation(summary = "Delete ads")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("/ads/{adsId}")
    public ResponseEntity<?> removeAds(@PathVariable int adsId) {
        log.info("Delete ads id: {}", adsId);
        adsService.removeAdsDto(adsId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update ads")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/ads/{adsId}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable int adsId, @RequestBody CreateAdsDto createAdsDto) {
        log.info("Update ads id: {}", adsId);
        return ResponseEntity.ok(adsService.updateAdsDto(adsId, createAdsDto));
    }

    @Operation(summary = "Get an ads from an authorized user")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ResponseWrapperAdsDto[].class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/ads/me")
    public ResponseEntity<ResponseWrapperAdsDto> getAdsMe() {
        log.info("Get an ads from an authorized user");
        return ResponseEntity.ok(adsService.getAllAdsMe());
    }

    @Operation(summary = "Update image ags")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping(value = "/ads/{adsId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> updateImage(@PathVariable int adsId, @RequestPart MultipartFile image) {
        log.info("Update image ags id: {}", adsId);
        adsService.updateImageAdsDto(adsId, image);
        return ResponseEntity.ok().build();
    }
}

