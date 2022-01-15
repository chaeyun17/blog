package coop.parti.chaeyun17.blog.post.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.util.StringUtils;

import coop.parti.chaeyun17.blog.common.domain.AuditEntity;
import coop.parti.chaeyun17.blog.post.dto.PostResponse;
import coop.parti.chaeyun17.blog.post.dto.PostUpdateRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "blog_post")
@Entity
public class Post extends AuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Lob
  @Column
  @Type(type = "org.hibernate.type.TextType")
  private String content;

  @Column(nullable = false)
  private Long viewCount;

  @Column(nullable = false)
  private Long authorId;

  private Post(Long id, String title, String content, Long authorId, Long viewCount) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.authorId = authorId;
    this.viewCount = viewCount;
  }

  public static Post of(String title, String content, Long authorId) {
    return new Post(0L, title, content, authorId, 0L);
  }

  public static Post of(Long id, String title, String content, Long authorId) {
    return new Post(id, title, content, authorId, 0L);
  }

  public void update(PostUpdateRequest request) {
    if (StringUtils.hasText(request.getContent())) {
      this.content = request.getContent();
    }
    if (StringUtils.hasText(request.getTitle())) {
      this.title = request.getTitle();
    }
  }

  public void increaseViewCount() {
    this.viewCount++;
  }

  public void active() {
    this.isActive = true;
  }

  public void inActive() {
    this.isActive = false;
  }

  public Long getId() {
    return this.id;
  }

  public PostResponse toResponse() {
    return new PostResponse(id, title, content, authorId, createdOn, updatedOn);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    Post post = (Post)o;

    return id.equals(post.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
