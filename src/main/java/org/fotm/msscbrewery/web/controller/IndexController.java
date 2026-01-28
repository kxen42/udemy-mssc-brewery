package org.fotm.msscbrewery.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller(value = "/api/{version}")
public class IndexController {

  @GetMapping(value = {"", "/", "/index"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView index() {
    log.info("Index page requested");
    return new ModelAndView("index");
  }

}
