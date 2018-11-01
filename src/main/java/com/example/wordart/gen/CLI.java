package com.example.wordart.gen;

import java.io.InputStream;
import java.util.EnumSet;

public class CLI {

  /**
   * Simple example of using the API that can run from the command line
   */
  public static void main(String[] args) throws Exception {

    String text = args[0];
    Generator generator = new ImageMagickGenerator();
    EnumSet<Job.Effect> effects;

    effects = EnumSet.noneOf(Job.Effect.class);
    effects = EnumSet.of(Job.Effect.SHADOW_REFLECT);
    Job job = new Job(Job.Format.PNG, text, effects, Job.Colour.BLACK, Job.Font.FREE_MONO);
    InputStream input = generator.generateImage(job);

    int read;
    while ((read = input.read()) != -1) {
      System.out.write(read);
    }
  }
}
