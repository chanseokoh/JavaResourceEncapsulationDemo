/*open*/ module my.awesome.module {
  exports com.example;

  // Let others access "com/example/foo/at-com-example-foo.txt". Resources
  // in sub-directories are not affected.
  opens com.example.foo;
}
