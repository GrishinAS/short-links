package com.korus.shorter.rest;

import com.korus.shorter.model.CreateLinkRequest;
import com.korus.shorter.service.LinkHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class ShorterController {

  private final LinkHandlerService linkHandlerService;

  @Autowired
  public ShorterController(LinkHandlerService linkHandlerService) {
    this.linkHandlerService = linkHandlerService;
  }

  @PostMapping("/link") //TODO authorization
  public String createLink(@RequestBody CreateLinkRequest request) {
    return linkHandlerService.doShort(request);
  }

  @GetMapping("/link")
  public String getLink(@RequestParam String shortLink) {
    return linkHandlerService.getLink(shortLink);
  }

  @GetMapping("/redirect")
  public RedirectView redirectToLink(@RequestParam String shortLink) {
    String link = linkHandlerService.getLink(shortLink);
    return new RedirectView(link);
  }
}
