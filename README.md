# jdk.compiler standalone repository

## What

This repository contains the source code for the "jdk.compiler" module of certain Java JDKs.

The source code is taken directly from src.zip via

```
jar xvf src.zip jdk.compiler
```

and stored under the `java` directory.

## Why

A typical way of using the Java Compiler API is to rely on the presence of such
API in the Java VM that runs the code requiring it.

Starting with Java 16, using the Java Compiler API requires a series of
`--add-opens` incantations to the running VM. The reason is clear: Eventually,
we cannot rely on the presence of said API in the JDK, since it is an internal
component that we really shouldn't touch.

Since the Compiler API is licensed as GPLv2+Classpath-Exception, let's make it
a separate component that we can use regardless of what's available in the
current VM.

## How

To make the code independently usable, we have to make some additional changes
to the code.

In order to track these changes, and still support multiple versions of the
Compiler API, this repository has several branches, both for the original
unmodified code, and for modifications necessary for repackaging.

The code in this repository is used by another repository as "submodules",
pointing to the corresponding branch. That other repository,
"jdk.compiler.standalone", then creates the Maven jars for the standalone
version.

## Who

This repository has been packaged by Christian Kohlschütter.

The code itself carries the original license, GNU General Public License
version 2 only, subject to the "Classpath" exception as provided in
the LICENSE file that accompanies this code.
