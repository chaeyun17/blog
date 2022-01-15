package coop.parti.chaeyun17.blog.common.domain.search;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.domain.Pageable;

public class SearchParamToReqConverter {

  public final static String regexPatternStr = "(\\w+?)(:|<|>|~)([가-힣a-zA-Z0-9|ㄱ-ㅎ|ㅏ-ㅣ]+?),";

  public static SearchReq convert(String searchString, Pageable pageable) {
    searchString += ",";
    List<SearchCondition> searchConditionList = new ArrayList<>();
    Pattern pattern = Pattern.compile(regexPatternStr);
    Matcher matcher = pattern.matcher(searchString);
    while (matcher.find()) {
      SearchCondition condition = new SearchCondition();
      condition.setName(matcher.group(1));
      condition.setOperation(matcher.group(2));
      condition.setValue(matcher.group(3));
      searchConditionList.add(condition);
    }

    SearchReq req = new SearchReq();
    req.setConditions(searchConditionList);
    req.setPageable(pageable);

    return req;
  }

}
