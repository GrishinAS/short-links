package com.korus.shorter.service.impl;

import com.korus.shorter.service.LinkShorterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkShorterServiceImplTest {
    private LinkShorterService linkShorterService = null;
    @Before
    public void setUp() throws Exception {
        linkShorterService = new LinkShorterServiceImpl();
    }

    @Test
    public void linkReadableAfterDecoding() {
        String initialLink = "https://us-east-2.console.aws.amazon.com/ec2/v2/home?region=us-east-2#Instances:sort=instanceId";
        String encodedLink = linkShorterService.encode(initialLink);
        Assert.assertEquals(encodedLink, "huevhrI");
    }
}