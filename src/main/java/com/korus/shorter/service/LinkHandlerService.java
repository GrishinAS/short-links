package com.korus.shorter.service;

import com.korus.shorter.model.CreateLinkRequest;

import java.util.List;

public interface LinkHandlerService {


  String doShort(CreateLinkRequest request);

  String getLink(String shortLink);
}
