package com.saxena.vaibhav.globomart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * Once the service confifuartion server has read the configuration files, they can be accessed here using the @Value annotation passing in the key. 
 * 
 * @author Vaibhav.Saxena
 *
 */
@Component
public class ServiceConfig{

  @Value("${example.property}")
  private String exampleProperty;

  public String getExampleProperty(){
    return exampleProperty;
  }
}