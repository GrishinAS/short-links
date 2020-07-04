package com.korus.shorter.service.impl;

import com.korus.shorter.service.LinkShorterService;

public class LinkShorterServiceImpl implements LinkShorterService {
    public final String Alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
    public final int Base = Alphabet.length();

    @Override
    public String encode(int num) {
        StringBuilder sb = new StringBuilder();
        while ( num > 0 ) {
            sb.append( Alphabet.charAt( num % Base ) );
            num /= Base;
        }
        return sb.reverse().toString();
    }

    @Override
    public int decode(String link) {
        int num = 0;
        for ( int i = 0; i < link.length(); i++ )
            num = num * Base + Alphabet.indexOf(link.charAt(i));
        return num;
    }
}
