package com.iqmsoft.docker.thymeleaf.test.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public final class TestUtil {

  /**
   * Private Constructor.
   */
  private TestUtil() {
  }

  /**
   * Transform and object to a json byte array.
   * @param object the object to tranform
   * @return the byte array
   * @throws IOException an IOException
   */
  public static byte[] convertObjectToJsonBytes(final Object object) throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsBytes(object);
  }
}
