package com.korus.shorter.service;

public interface LinkShorterService {
    String encode(String longLink);
    String decode(String shortLink);
}
