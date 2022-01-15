package coop.parti.chaeyun17.blog.post.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

  private Long id;
  private String title;
  private String content;
  private Long authorId;
  private LocalDateTime createdOn;
  private LocalDateTime updatedOn;

}
