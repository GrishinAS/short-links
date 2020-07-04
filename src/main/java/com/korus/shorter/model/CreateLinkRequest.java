package com.korus.shorter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.beans.ConstructorProperties;

@Data
public class CreateLinkRequest {
  private CreateLinkRequest() {
  }

  private String address;
}
