# Dynamic Reports Core
DynamicReports is an open source Java reporting library based on JasperReports.
It allows to create dynamic report designs, and it doesn't need a visual report designer.
You can very quickly create reports and produce documents that can be displayed, printed
or exported into many popular formats such as PDF, Excel, Word and others.

The original source code was first hosted on [sourceforge](https://sourceforge.net/p/dynamicreports) and
interactions were carried out through [dynamicreports.org]. After
using this project and libraries, we the maintainers felt compelled to support the development for it has
been a great tool. However, the source code has not been easily accessible, and the original hosting website
on which this project was first created is no longer responsive, and we do not want to see a great project
go down and disappear like that.

## Project Goals
This project aims to maintain a fork of the original source code supporting current dependencies.

## Usage
DynamicReports is synchronized with a Maven central repository. For Maven projects, you add a dependency to your maven configuration. In case you would like to use a development version, add a Sonatype Nexus snapshot repository to your maven configuration.

For examples and detailed usage demonstrations of DynamicReports, please refer to the [dynamicreports-examples](https://github.com/unibas-medfak/dynamicreports-examples) repository. This companion project provides a comprehensive collection of working examples that showcase various features and capabilities of the DynamicReports library, helping you get started quickly with creating dynamic reports in your own projects. 

### Maven
#### Core module
```xml
<dependency>
    <groupId>ch.unibas.medizin</groupId>
    <artifactId>dynamicreports-core</artifactId>
    <version>7.0.3</version>
</dependency>
```

## Build
#### Build from source 
Check the release page to download source [release files](https://github.com/unibas-medfak/dynamicreports-core/releases).

Unzip the sources, execute `mvn clean install` from project root directory. This compiles project sources and create project jar files.

### Deploy to maven central

```shell
mvn deploy -P release-sign-artifacts
```
