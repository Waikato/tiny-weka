# How to make a release

* Switch to Java 8 (`. java8`)

* Run the following command to deploy the artifact:

  ```
  mvn release:clean release:prepare release:perform
  ```

* push out all changes

* update the version of the Maven release artifact in `README.md`

* add new release on github

  * go to https://github.com/Waikato/tiny-weka/releases
  * create new release from Maven-generated tag
  * name it `Release vX.Y.Z`
  * upload `.pom`, `.jar`, `-sources.jar`, `-javadoc.jar` 
    (download them from [Maven Central](https://central.sonatype.com/artifact/nz.ac.waikato.cms.weka/tiny-weka/versions))
