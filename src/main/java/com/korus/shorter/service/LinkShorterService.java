package com.korus.shorter.service;

public interface LinkShorterService {
    String encode(int num);
    int decode(String link);
}
