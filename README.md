# vimgolf
Project 2 for Hackers: Vim Golf, written in Clojure

## To do:

- [x] Vim as a Service
- [x] vimgolf frontend
- [x] vimgolf backend

## Parts:

### VaaS
**V**im **a**s **a** **S**ervice - meant to provide a webservice where
documents can be sent and edited with a given set of vim commands.

### vimgolf
A game in which users attempt to change one document into another in as few
keystrokes as possible. Built upon VaaS.

## Setup:
In this root directory, run `make`. You will need docker installed, as well as
leiningen and a compatible JDK. Then, cd into VaaS and run `lein run` and
cd into backend and run `lein run`. You should change the URLs in the
backend and frontend source files.

## Clojure Features:
The program is written utilizing functional programming. Pure functions
can be run simultaneously with no risk as they should never be modifying
the same memory. So, our servers are trivially parallelized.
We also dabbled with Macros. Many LISPs allow LISP code to modify itself, as
a key feature of LISP is "code is data." So, to make things a tad bit easier for
us, we wrote a macro to turn a vector of containers into a vector of
transient forms of said container. We needed this to be able to store our
high scores for each hole.
