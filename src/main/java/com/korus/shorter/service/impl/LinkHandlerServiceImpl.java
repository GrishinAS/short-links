package com.korus.shorter.service.impl;

import com.korus.shorter.model.CreateLinkRequest;
import com.korus.shorter.model.dao.Link;
import com.korus.shorter.model.dao.LinkCrudService;
import com.korus.shorter.service.LinkHandlerService;
import com.korus.shorter.service.LinkShorterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class LinkHandlerServiceImpl implements LinkHandlerService {

    private final LinkCrudService linkCrudService;
    private final LinkShorterService linkShorterService;

    @Autowired
    public LinkHandlerServiceImpl(LinkCrudService linkCrudService, LinkShorterService linkShorterService) {
        this.linkCrudService = linkCrudService;
        this.linkShorterService = linkShorterService;
    }

    @Override
    public String doShort(CreateLinkRequest request) {
      String address = request.getAddress();
        String shortLink = linkShorterService.encode(address);
        Link createdLink = Link.builder()
                .fullLink(address)
                .shortLink(shortLink)
                .createTime(Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC"))))
                .build();
        linkCrudService.create(createdLink);
        return shortLink;
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
