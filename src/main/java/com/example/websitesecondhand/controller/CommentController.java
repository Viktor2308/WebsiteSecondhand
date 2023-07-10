package com.example.websitesecondhand.controller;

import com.example.websitesecondhand.dto.CommentDto;
import com.example.websitesecondhand.dto.ResponseWrapperCommentDto;
import com.example.websitesecondhand.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "comment", description = "API for work with comments")
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Get comments ads")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = ResponseWrapperCommentDto[].class))))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/ads/{adsId}/comments")
    public ResponseEntity<ResponseWrapperCommentDto> getComments(@PathVariable int adsId) {
        log.info("Get comments ads id: {}", adsId);
        return ResponseEntity.ok().body(commentService.getComments(adsId));
    }

    @Operation(summary = "Add comment for ads")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CommentDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping("/ads/{adsId}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable int adsId, @RequestBody CommentDto commentDto) {
        log.info("Add comments ads id: {}", adsId);
        return ResponseEntity.ok(commentService.addComment(adsId, commentDto));
    }

    @Operation(summary = "Delete comment")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @DeleteMapping("/ads/{adsId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int adsId, @PathVariable int commentId) {
        log.info("Delete comment: {}", adsId);
        if (commentService.deleteComment(adsId, commentId)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Update comment")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CommentDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PatchMapping("/ads/{adsId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int adsId, @PathVariable int commentId, @RequestBody CommentDto commentDto) {
        log.info("Update comment: {}", adsId);
        return ResponseEntity.ok(commentService.updateComment(adsId, commentId, commentDto));
    }
}
