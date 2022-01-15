package coop.parti.chaeyun17.blog.common.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
public class AuditEntity {

  @Column(name = "created_on")
  @CreationTimestamp
  protected LocalDateTime createdOn;

  @Column(name = "updated_on")
  @UpdateTimestamp
  protected LocalDateTime updatedOn;

  @Column(name = "created_by")
  @CreatedBy
  protected String createdBy;

  @Column(name = "updated_by")
  @LastModifiedBy
  protected String updatedBy;

  @Column(name = "attribute", columnDefinition = "text")
  @Type(type = "text")
  protected String attribute;

  @Setter
  protected Boolean isActive;

}
