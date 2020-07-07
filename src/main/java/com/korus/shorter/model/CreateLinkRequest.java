package com.korus.shorter.model;

import lombok.Data;

@Data
public class CreateLinkRequest {
  private CreateLinkRequest() {
  }

  private String address;
}
