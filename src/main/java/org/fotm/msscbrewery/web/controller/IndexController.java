package org.fotm.msscbrewery.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping(value = "/web/{version}")
@Controller
public class IndexController {

  @GetMapping(value = {"", "/", "/index"}, version = "v1", produces = MediaType.TEXT_HTML_VALUE)
  public String index() {
    log.info("Index page requested");
    return "index";
  }

}
