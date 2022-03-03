# How to make a release

* Switch to Java 8 (`. java8`)

* Run the following command to deploy the artifact:

  ```
  mvn release:clean release:prepare release:perform
  ```

* push out all changes

* update the version of the Maven release artifact in `README.md`
