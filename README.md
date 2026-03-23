# Name: Shawn Wilkinson

# Project1 - Triangle Checker

## Overview
This project is a Java console application that determines whether three sides form a valid triangle and identifies the triangle type as Equilateral, Isosceles, Scalene, or Invalid.

## Files
- `src/TriangleChecker.java` - Contains the triangle classification logic
- `src/TriangleApp.java` - Console application for user input and output
- `test/TriangleCheckerTest.java` - Unit tests for triangle validation and classification
- `screenshots/` - Contains screenshots of program execution and successful JUnit tests

## How to Run
1. Open the project in IntelliJ IDEA
2. Run `TriangleApp.java`
3. Enter three side lengths when prompted
4. View the triangle type result in the console

## How to Run Tests
1. Open `TriangleCheckerTest.java`
2. Run the test class
3. Verify that all tests pass

## Test Cases Included
- Valid triangle test
- Scalene triangle test
- Scalene triangle permutation test
- Zero-length side test

## Test Case Justification

The selected unit tests were designed to validate both normal and boundary conditions of triangle classification logic.

- The valid triangle test verifies that the program correctly identifies an equilateral triangle.
- The scalene triangle test confirms classification when all sides differ.
- The scalene permutation test ensures the order of input values does not affect classification.
- The zero-length side test checks boundary input validation and confirms invalid triangle detection.

These tests ensure the triangle classification logic behaves correctly under both valid and invalid input scenarios.

## Test Case Design Based on The Art of Software Testing (Chapter 1)

The unit tests for this project were designed following principles from Chapter 1 of *The Art of Software Testing* by Glenford Myers. The goal of testing is not only to confirm correct behavior but also to reveal potential errors.

The following testing strategies were applied:

- Valid input testing:
  A test case using equal side lengths (5,5,5) verifies correct identification of a valid equilateral triangle.

- Scalene triangle testing:
  A test case using different side lengths (4,5,6) confirms correct classification when no sides are equal.

- Permutation testing:
  The scalene permutation test (6,4,5) ensures the order of inputs does not affect classification results.

- Boundary testing:
  A test case using a zero-length side (0,5,5) verifies that invalid triangle inputs are correctly rejected.

These test cases demonstrate both functional correctness and error-detection capability consistent with Myers' testing philosophy.