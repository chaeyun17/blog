package coop.parti.chaeyun17.blog.post.dto;

import coop.parti.chaeyun17.blog.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostCreateRequest {

  private String title;
  private String content;
  private Long authorId;

  public Post toEntity() {
    return Post.of(title, content, authorId);
  }
}
