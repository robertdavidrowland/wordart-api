package com.example.wordart.gen;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link Generator} can produce a stream of bytes for an image, according
 * to the specifics of the given job.
 */
public interface Generator {

  /**
   * @param job the job parameters that define the image to be produced.
   * @return an input stream that yields the bytes that form the image
   * @throws IOException if there was some underlying issue with image
   * generation
   */
  InputStream generateImage(Job job) throws IOException;

}
