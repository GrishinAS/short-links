package com.korus.shorter.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Link.TABLE_NAME)
@Entity
public class Link {
  public static final String TABLE_NAME = "links";

  @Id
  @GeneratedValue
  private int id;
  @Size(min = 1)
  private String fullLink;
  @Size(min = 1)
  @Column(name = "short_link")
  private String shortLink;
//  @Size(min = 1)
//  private User user; TODO finish connect to users

  private Timestamp createTime;
}
