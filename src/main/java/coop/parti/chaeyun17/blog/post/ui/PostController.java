package coop.parti.chaeyun17.blog.post.ui;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coop.parti.chaeyun17.blog.common.controller.BaseUrl;
import coop.parti.chaeyun17.blog.post.application.PostService;
import coop.parti.chaeyun17.blog.post.dto.PostCreateRequest;
import coop.parti.chaeyun17.blog.post.dto.PostResponse;
import coop.parti.chaeyun17.blog.post.dto.PostUpdateRequest;

@RestController
public class PostController {

  public static final String BASE_URL = BaseUrl.V1 + "/posts";

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping(BASE_URL)
  public ResponseEntity<Void> create(@RequestBody @Valid PostCreateRequest request) {
    PostResponse response = postService.create(request);
    return ResponseEntity.created(URI.create(BASE_URL + "/" + response.getId())).build();
  }

  @GetMapping(BASE_URL)
  public ResponseEntity<Page<PostResponse>> getList(@PageableDefault Pageable pageable) {
    Page<PostResponse> list = postService.getList(pageable);
    return ResponseEntity.ok(list);
  }

  @GetMapping(BASE_URL + "/{id}")
  public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
    PostResponse response = postService.getById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping(BASE_URL + "/{id}")
  public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody @Valid PostUpdateRequest request) {
    return ResponseEntity.ok(postService.update(id, request));
  }

  @DeleteMapping(BASE_URL + "/{id}")
  public ResponseEntity<Void> inActive(@PathVariable Long id) {
    postService.inActiveById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(BASE_URL + "/{id}/delete")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    postService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(BASE_URL + "/{id}/viewCount")
  public ResponseEntity<PostResponse> increaseViewCount(@PathVariable Long id) {
    PostResponse response = postService.increaseViewCount(id);
    return ResponseEntity.ok(response);
  }

}
