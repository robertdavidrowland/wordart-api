# Java Coding Challenge - Word-Art API

Our engineers have developed a state of the art Word-Art generator, using some
of the best open-source fonts from around the web to provide high quality images
from text for use on your community newsletters, group emails and board
presentation slides.  Your challenge is to build an API that uses HTTP to allow
our customers to request and fetch their images using our word art library.

You are free to spend as much time as you want on the challenge.  The key is to
send us something we can talk about in a further interview.

We have supplied a basic maven build script that can build a WAR file and run
the application with the jetty plugin.  Feel free to use it, or replace it with
something you feel comfortable using.  Just make sure we have instructions to run
it that can run on a recent version of debian based linux.

# Requirements

Your API, which you are free to design as you wish [1], must be able to accept a
string of text, along with formatting parameters, and be able to serve up the
generated images as PNG or JPEG images.

1 - Information about the formatting parameters can be found in the word art
    generator API javadocs

# CLI Test

A simple CLI is available to test the word art generation, it accepts only a
single parameter, the text, and outputs the result to stdout.  You can run it
with:

```
$ java -cp target/classes CLI foo > foo.png
```

# ImageMagick

The given conversion code relies on the popular imagemagick library being installed
and available on your system's path.  You can check it is installed correctly by 
running `which convert`.

# A Word of Warning

One of our engineers was having a bad day while making major contributions to
this application.  There might be a few broken bits along the way.  Feel free
to bring these issues up with us, work around them, or fix them.

# Solution

This has been set up as Spring Boot application built with Maven.  It serves a simple 
API with a single /image endpoint.  

Parameters are set by query string:

- text - the text to be rendered.
- format - can be be PNG, JPEG, TIFF or ASCII.  If ommitted or set to TIFF or ASCII it will default to PNG
- effects - one or more effects can be SHADOW_HARD, SHADOW_REFLECT, GRADIENT or OUTLINE
- colour - one of RED, GREEN, BLUE, BLACK, GREY and WHITE
- font - only FREE_MONO accepted

# Usage

To compiile and run:

```
> mvn package
> java -jar target/wordart-1.0-SNAPSHOT.jar
```

Example use:

```
http://localhost:8080/image/?text=Grovey%20Baby!&format=JPEG&colour=RED&effect=OUTLINE&effect=GRADIENT&effect=SHADOW_REFLECT
```

If you have problem compiling check the path to your ImageMagick binary in src/main/resources/application.properties

# Timesheet

29.10.2018 - 0.5 hours
- Skim over problem (read README)
- Download and test imagemagick
- Fail at getting CLI to run on windows

31.10.2018 - 0.5 hours
- Prep debian linux server on AWS
- Succeed at getting CLI to run on Windows

01.11.2018 - 5 hours
- Fix Generator to run on windows, Add simple tests for generator
- Spring MVC controller and tests, Unbreak generator on windows
- And some more unbreaking(!), Unbreak it on linux
- Test minimal API locally and on debian
- First pass at full API

02.11.2018 - 1 hour
- Tidy up and add notes to README.md
