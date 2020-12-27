# tiny-weka
Stripped down version of [Weka](https://www.cs.waikato.ac.nz/ml/weka/) 3.9.x branch (HEAD) 
that just contains the bare minimum API without any package manager, PMML, XML or user 
interface related code.

In contrast to the regular Weka source code, which is released under [GPLv3](https://www.gnu.org/licenses/gpl-3.0.txt), 
this code is released under the [MIT](LICENSE) license.

## Code base

The code base of *tiny-weka* is synced (manually) with Weka's subversion repository, 
[trunk branch](https://svn.cms.waikato.ac.nz/svn/weka/trunk/) up to the following 
revision:

```
r15754   # svn revision
```

## Maven

Snapshots and releases use the following versioning system:
```
X.Y.Z
```
* `X.Y` - the Weka major/minor release version
* `Z` - the Weka subversion repository revision that the code base was synced to

### Snapshots

Nightly snapshots get pushed out to [Sonatype's snapshot repository](https://oss.sonatype.org/content/repositories/snapshots/com/github/fracpete/tiny-weka/). In order to use these, you need
to add the configuration for this snapshot repository to your `pom.xml`:

```xml 
  <repositories>
    <repository>
      <id>sonatype-nexus-snapshots</id>
      <name>Sonatype Nexus Snapshots</name>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
      <releases>
        <enabled>false</enabled>
      </releases>
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
  </repositories>
```

As for adding *tiny-weka* as a dependency to your project, add the following to your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.fracpete</groupId>
  <artifactId>tiny-weka</artifactId>
  <version>3.9.15754-SNAPSHOT</version><!-- tiny-weka-version -->
</dependency>
``` 

### Releases

Add the following dependency to your `pom.xml` to use the latest [release](https://search.maven.org/search?q=a:tiny-weka) of *tiny-weka*:

```xml
<dependency>
  <groupId>com.github.fracpete</groupId>
  <artifactId>tiny-weka</artifactId>
  <version>3.9.15580</version>
</dependency>
```

## Updating

### Update files
The code base can be synced with Weka's subversion branch using the `update.py`
Python script.

```
usage: update.py [-h] -w, --weka DIR -r, --revision REV [-s, --svn EXECUTABLE]
                 [-n, --dry_run] [-v, --verbose]

Analyzes the svn log from the specified revision on and then updates the code
accordingly. It stores the start/end svn revision in 'update.rev' after
execution.

optional arguments:
  -h, --help            show this help message and exit
  -w, --weka DIR        the directory with the Weka subversion repository
                        (HEAD)
  -r, --revision REV    the svn revision to start from
  -s, --svn EXECUTABLE  the svn executable to use if not on the path
  -n, --dry_run         whether to perform a dry run, i.e., only simulating
                        the update
  -v, --verbose         whether to be verbose with the output
```

Example:

```commandline
python3 update.py \
  -w /some/where/weka-HEAD/ \
  -r 15559 \ 
  -v
```

### Post-process files

* Files that should not end up in the code base need to be added to the 
  `BLACKLISTED_FILES` list
* Directories that should not end up in the code base need to be added to the
  `BLACKLISTED_PATHS`
* Files that were manually modified (e.g., removing package manager code) need 
  to be added to the `MODIFIED_FILES` list. If such a file has been updated
  in the subversion repository, a warning is being output.


### Remove GPL

The `remove_gpl.py` script can be used to remove the GPL preamble from any
Java file that still contains it, e.g., when manually copying over files. 

```
usage: remove_gpl.py [-h] -d, --dir DIR [-r, --recursive] [-n, --dry_run]
                     [-v, --verbose]

Removes the GPL preamble from Java source code files.

optional arguments:
  -h, --help       show this help message and exit
  -d, --dir DIR    the directory with source code files to process
  -r, --recursive  whether to search for source code files recursively
  -n, --dry_run    whether to perform a dry run, i.e., only simulating the
                   removal
  -v, --verbose    whether to be verbose with the output
```


### Commit

* Once compilation and unit tests work, commit all changes
