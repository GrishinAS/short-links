package com.korus.shorter;

import com.korus.shorter.service.impl.LinkHandlerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StartApplicationTests {

  @Autowired
  private LinkHandlerServiceImpl aggregatorService;


}
