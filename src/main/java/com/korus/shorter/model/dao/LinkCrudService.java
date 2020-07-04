package com.korus.shorter.model.dao;

import org.apache.commons.collections4.IterableUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class LinkCrudService {
  private final LinkRepository repo;
  private SessionFactory sessionFactory;

  @Autowired
  public LinkCrudService(LinkRepository repo, SessionFactory sessionFactory) {
    this.repo = repo;
    this.sessionFactory = sessionFactory;
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
    Session session = sessionFactory.getCurrentSession();
    Query query = session.createQuery("From links where short_link=:link");
    query.setParameter("link", shortLink);
    return (Link) query.uniqueResult();
  }
}
