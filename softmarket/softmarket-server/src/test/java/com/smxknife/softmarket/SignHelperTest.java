package com.smxknife.softmarket;

import com.smxknife.softmarket.web.controller.SignHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest(classes = BootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class SignHelperTest {

  @Autowired
  SignHelper signHelper;

  @Test
  public void checkSignatureTest() {
    String signature = "61202fc706ff6c29be6eebbdfd76293b544dd466";
    String timestamp = "1520398553";
    String notice = "613396428";
    String echostr = "17026579936650983677";
    assert signHelper.checkSignature(signature, timestamp, notice) == true;
  }
}
