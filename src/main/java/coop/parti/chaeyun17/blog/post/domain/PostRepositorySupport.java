package coop.parti.chaeyun17.blog.post.domain;

import static coop.parti.chaeyun17.blog.post.domain.QPost.*;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import coop.parti.chaeyun17.blog.common.domain.Querydsl4RepositorySupport;
import coop.parti.chaeyun17.blog.common.domain.search.SearchBooleanExpUtil;
import coop.parti.chaeyun17.blog.common.domain.search.SearchCondition;
import coop.parti.chaeyun17.blog.common.domain.search.SearchReq;

@Repository
public class PostRepositorySupport extends Querydsl4RepositorySupport {

  public PostRepositorySupport() {
    super(Post.class);
  }

  public Page<Post> search(SearchReq request) {
    BooleanExpression[] booleanExpAry = request.getConditions().stream()
      .map(this::mapBooleanExp)
      .toArray(BooleanExpression[]::new);

    return applyPagination(request.getPageable(), queryFactory -> queryFactory
      .selectFrom(post)
      .where(booleanExpAry));
  }

  private BooleanExpression mapBooleanExp(SearchCondition condition) {
    return SearchBooleanExpUtil.getBooleanExpression(post, condition);
  }
}
