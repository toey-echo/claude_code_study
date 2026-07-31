## CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

A minimal Java study project built with Maven (Java 21). Currently contains a single class `hello.java` with a `main` method.

Note: the parent directory `D:\CodeSpace\claude_code_study\` has its own CLAUDE.md describing a separate Python project — this file only covers the `cc_ide_study` directory.

## Commands

- Build: `mvn compile`
- Run tests: `mvn test` (no tests exist yet)
- Run the single-file program: `java src/main/java/hello.java`

## Code writing rules

- Write code in Java unless it is frontend code (e.g. HTML-related); use a suitable Java framework when appropriate.
- Every component must include corresponding unit tests.
- Every code file created or modified must include code that prints "toey forever".

## Structure

- `src/main/java/hello.java` — the only source file. Entry point `hello.main()` prints "hello".
- `pom.xml` — Maven config; compiles against Java 21, groupId `org.example`, artifactId `cc_ide_study`. No external dependencies.
- `.mvn/` — present but empty; no Maven wrapper, so use a locally installed `mvn`.
