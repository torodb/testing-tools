ToroDB Testing utilities
========================

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.torodb.testing/testing-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.torodb.testing/testing-parent)

This repository contains several Maven projects used to test ToroDB projects
that are general enough to be useful on third party software, so they have been
released under Apache 2.0 licese to make it easier to use on any enviroment.

The projects contained on this repository are:
* `testing-core`: which contains some utilities used by other projects.
* `core-junit5`: which contains some utilities to create JUnit 5 tests.
* `docker-core`: which contains some utilities to instanciate Docker containers.
* `sql-docker`: which contains interfaces to generalize Docker containers that 
can be used to run SQL databases.
* `postgres-docker`: which contains classes that can be used to run PostgreSQL 
on a Docker container.
* `postgres-junit-5`: which contains JUnit5 extensions and annotations to automatically
run a PostgreSQL instance each time a test or test case is executed.
* `mongodb-docker`: which contains classes that can be used to run MongoDB on 
Docker containers. Single mongod, replica sets and sharded topologies are
provided out of the box.
* `mongodb-junit-5`: which contains JUnit5 extensions and annotations to automatically
run MongoDB instances, replica sets or shard each time a test or test case is 
executed.

How to use it
-------------

This utilities are built with Maven. Released artifacts are hosted on [Maven Central][4]
and snapshot artifacts on [Nexus Repository Management][5]. 
You may build the source code by running "mvn package" on the root directory.

Currently the project is on beta version, there are no released versions and the
code is on the devel branch. Please use consider it the main branch of this repository
until the first release is done.

## Code QA
 * Master branch: 
[![Build Status](https://travis-ci.org/torodb/testing-tools.svg?branch=master)](https://travis-ci.org/torodb/testing-tools)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/445845b64bf54e31be9d4c2d188f803c?branch=master)](https://www.codacy.com/app/torodb/testing-tools?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=torodb/testing-tools&amp;utm_campaign=Badge_Grade)
[![Codacy Coverage](https://api.codacy.com/project/badge/coverage/445845b64bf54e31be9d4c2d188f803c?branch=master)](https://www.codacy.com/app/torodb/testing-tools?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=torodb/testing-tools&amp;utm_campaign=Badge_Grade)
 * Devel branch:
[![Build Status](https://travis-ci.org/torodb/testing-tools.svg?branch=devel)](https://travis-ci.org/torodb/testing-tools)
[![Codacy Badge](https://api.codacy.com/project/badge/grade/445845b64bf54e31be9d4c2d188f803c?branch=devel)](https://www.codacy.com/app/torodb/testing-tools?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=torodb/testing-tools&amp;utm_campaign=Badge_Grade)
[![Codacy Coverage](https://api.codacy.com/project/badge/coverage/445845b64bf54e31be9d4c2d188f803c?branch=devel)](https://www.codacy.com/app/torodb/testing-tools?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=torodb/testing-tools&amp;utm_campaign=Badge_Grade)

[1]: http://docs.mongodb.org/meta-driver/latest/legacy/mongodb-wire-protocol/
[2]: http://netty.io/
[3]: http://www.torodb.com
[4]: http://search.maven.org/
[5]: https://oss.sonatype.org/content/groups/public/com/torodb/testing-tools/
