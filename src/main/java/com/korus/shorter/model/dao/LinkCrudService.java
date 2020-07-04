package com.korus.shorter.model.dao;

import org.apache.commons.collections4.IterableUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Service
public class LinkCrudService {
  private final LinkRepository repo;
  @PersistenceContext
  private EntityManager entityManger;

  @Autowired
  public LinkCrudService(LinkRepository repo) {
    this.repo = repo;
  }

  public int create(Link link) {
    return this.repo.save(link).getId();
  }

  @Transactional
  public Link read(int id) {
    return this.repo.findById(id).orElse(null);
  }


  @Transactional
  public Collection<Link> readAll() {
    return IterableUtils.toList(this.repo.findAll());
  }

  @Transactional
  public Link update(Link object) {
    return this.repo.save(object);
  }

  @Transactional
  public void delete(int id) {
    this.repo.deleteById(id);
  }

  public Link findByShortLink(String shortLink) {
    Query query = entityManger.createQuery("select l from Link l where short_link=:link", Link.class);
    query.setParameter("link", shortLink);
    return (Link) query.getSingleResult();
  }
}
