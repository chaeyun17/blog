package coop.parti.chaeyun17.blog.common.domain.search;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class SearchReq {

  List<SearchCondition> conditions;

  Pageable pageable;

}
