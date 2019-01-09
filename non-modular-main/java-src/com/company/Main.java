package com.company;

import static java.lang.System.out;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class Main {

  private static final List<String> resourceNames = List.of(
      "at-root.txt",
      "non-package-name/at-non-package-name.txt",
      "com/at-com.txt",
      "com/example/at-com-example.txt",
      "com/example/foo/at-com-example-foo.txt",
      "com/example/foo/bar/at-com-example-foo-bar.txt",
      "com/example/baz/at-com-example-baz.txt");

  private static void tryResources(Predicate<String> canAccess) {
    resourceNames.forEach(name -> {
      out.println((canAccess.test(name) ? "SUCCESS" : "FAIL") + ": " + name);
    });
  }

  public static void main(String[] args) {
    out.println("-=-=-= ClassLoader.getSystemResource() =-=-=-");
    tryResources(name -> ClassLoader.getSystemResource(name) != null);
    out.println();

    out.println("-=-=-= <current thread ClassLoader>.getResource() =-=-=-");
    var classLoader = Thread.currentThread().getContextClassLoader();
    tryResources(name -> classLoader.getResource(name) != null);
    out.println();

    out.println("-=-=-= Module.getResource() =-=-=-");
    var module = com.example.Nothing.class.getModule();
    tryResources(
        name -> {
          try (var ignored = module.getResourceAsStream(name)) {
            return true;
          } catch (IOException e) {}
          return false;
        });
    out.println();

    out.println("-=-=-= Module.getResource() (2) =-=-=-");
    var module2 = ModuleLayer.boot().findModule("my.awesome.module");
    tryResources(
        name -> {
          try (var ignored = module.getResourceAsStream(name)) {
            return true;
          } catch (IOException e) {}
          return false;
        });
  }
}
