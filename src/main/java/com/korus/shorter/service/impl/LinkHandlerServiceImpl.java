package com.korus.shorter.service.impl;

import com.korus.shorter.model.CreateLinkRequest;
import com.korus.shorter.model.dao.Link;
import com.korus.shorter.model.dao.LinkCrudService;
import com.korus.shorter.service.LinkHandlerService;
import com.korus.shorter.service.LinkShorterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Semaphore;

@Service
@PropertySource("classpath:application.properties")
@Slf4j
public class LinkHandlerServiceImpl implements LinkHandlerService {

    private final LinkCrudService linkCrudService;
    private final LinkShorterService linkShorterService;
    private Semaphore parallelLinkGeneration;

    @Autowired
    public LinkHandlerServiceImpl(LinkCrudService linkCrudService, LinkShorterService linkShorterService, Semaphore parallelLinkGeneration) {
        this.linkCrudService = linkCrudService;
        this.linkShorterService = linkShorterService;
        this.parallelLinkGeneration = parallelLinkGeneration;
    }

    @Override
    public String doShort(CreateLinkRequest request) {
      try {
        parallelLinkGeneration.acquire();
        String address = request.getAddress();
        String shortLink = linkShorterService.encode(address);
        Link createdLink = Link.builder()
                .fullLink(address)
                .shortLink(shortLink)
                .createTime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))))
                .build();
        linkCrudService.create(createdLink);
        parallelLinkGeneration.release();
        return shortLink;
      } catch (InterruptedException e) {
        log.error("Semaphore acquire exception", e);
        throw new RestClientException("");
      }
    }

    @Override
    public String getLink(String shortLink) {
        Link link = linkCrudService.findByShortLink(shortLink);
        Timestamp createTime = link.getCreateTime();
        LocalDateTime tenMinutesBeforeNow = LocalDateTime.now(ZoneId.of("UTC")).minusMinutes(10);
        if(createTime.before(Timestamp.valueOf(tenMinutesBeforeNow)))
            throw new RestClientException("Link is outdated");
        return link.getFullLink();
    }
}
