package coop.parti.chaeyun17.blog.common.domain.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SearchCondition {

  private String name;
  private Object value;
  private String operation;

}

