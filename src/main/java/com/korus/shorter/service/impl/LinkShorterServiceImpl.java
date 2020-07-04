package com.korus.shorter.service.impl;

import com.korus.shorter.service.LinkShorterService;
import org.springframework.stereotype.Service;

@Service
public class LinkShorterServiceImpl implements LinkShorterService {

    @Override
    public String encode(String longLink) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] split = longLink.split("[?/\\\\ #]");
        for (String part : split) {
            if (!part.isEmpty()) {
                stringBuilder.append(part, 0, 1);
                //stringBuilder.append(part, part.length() - 1, part.length()); To decrease collisions
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String decode(String link) {
        return null; //Do I need this?
    }
}
