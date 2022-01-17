package coop.parti.chaeyun17.blog.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

  @GetMapping({
    "{path:(?!index\\.html|swagger-ui\\.html|ttf|woff|woff2|js|img|css|api|oauth|h2-console).*$}"
    , "{path:(?!index\\.html|swagger-ui\\.html|ttf|woff|woff2|js|img|css|api|oauth|h2-console).*$}/**"
  })
  public String index() {
    return "forward:/index.html";
  }
}
