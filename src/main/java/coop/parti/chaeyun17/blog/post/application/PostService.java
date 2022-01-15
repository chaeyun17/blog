package coop.parti.chaeyun17.blog.post.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coop.parti.chaeyun17.blog.exception.AppException;
import coop.parti.chaeyun17.blog.exception.ErrorCode;
import coop.parti.chaeyun17.blog.post.domain.Post;
import coop.parti.chaeyun17.blog.post.domain.PostRepository;
import coop.parti.chaeyun17.blog.post.dto.PostCreateRequest;
import coop.parti.chaeyun17.blog.post.dto.PostResponse;
import coop.parti.chaeyun17.blog.post.dto.PostUpdateRequest;

@Transactional
@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public PostResponse create(PostCreateRequest request) {
    Post post = request.toEntity();
    post = postRepository.save(post);
    return post.toResponse();
  }

  @Transactional(readOnly = true)
  public Page<PostResponse> getList(Pageable pageable) {
    Page<Post> list = postRepository.findAll(pageable);
    return list.map(Post::toResponse);
  }

  @Transactional(readOnly = true)
  public PostResponse getById(Long id) {
    Post post = findById(id);
    return post.toResponse();
  }

  public PostResponse update(Long id, PostUpdateRequest request) {
    Post post = findById(id);
    post.update(request);
    return post.toResponse();
  }

  private Post findById(Long id) {
    return postRepository.findById(id)
      .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND, "찾을 수 없습니다"));
  }

  public void inActiveById(Long id) {
    Post post = findById(id);
    post.inActive();
  }

  public void delete(Long id) {
    Post post = findById(id);
    postRepository.delete(post);
  }

  public PostResponse increaseViewCount(Long id) {
    Post post = findById(id);
    post.increaseViewCount();
    return post.toResponse();
  }
  
}
