# Social Payment Parser

A Java command-line utility that sums payment amounts by social number.

<p>
  <a href="https://buymeacoffee.com/lucky_1uck">
    <img src="https://img.shields.io/badge/Support_the_project-FFDD00?style=for-the-badge&amp;logo=buymeacoffee&amp;logoColor=black" alt="Support the project" width="260" height="40">
  </a>
</p>

## Input and output

Each input line contains a social number and an amount separated by a delimiter:

```text
123456;10.00
123457;8.00
123456;14.00
```

The output contains one total per social number; row order is unspecified:

```text
123456;24.0
123457;8.0
```

## Run

Install JDK 8 or newer and Maven. From the repository root:

```bash
mvn package
java -cp target/classes com.zufar.socialpaymentparcer.App input.txt output.txt ";"
```

The arguments are, in order: **input file**, **output file**, **separator**.
Quote the separator so the shell passes it as one argument. It is interpreted
as a Java regular expression by `String.split`.

## Implementation

[App.java](src/main/java/com/zufar/socialpaymentparcer/App.java) reads input with
`Files.lines`, merges totals into a `HashMap`, then writes the aggregate output.
Input is streamed, but memory still grows with the number of distinct social
numbers. Amounts currently use `Double`, and output is not formatted to two
decimal places.

The [original Russian task](readme.txt) asks for processing input and output
larger than memory. The current implementation covers the aggregation exercise;
it does not implement disk-backed aggregation for an unbounded number of keys.

## ☕ Support the project

If this project helps you learn or build, you can support its development.

<p>
  <a href="https://buymeacoffee.com/lucky_1uck">
    <img src="https://img.shields.io/badge/Support_the_project-FFDD00?style=for-the-badge&amp;logo=buymeacoffee&amp;logoColor=black" alt="Support the project" width="260" height="40">
  </a>
</p>
