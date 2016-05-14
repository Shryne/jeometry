[![Build Status](https://travis-ci.org/HDouss/jeometry.svg?branch=master)](https://travis-ci.org/HDouss/jeometry)
[![Coverage Status](https://coveralls.io/repos/github/HDouss/jeometry/badge.svg?branch=master)](https://coveralls.io/github/HDouss/jeometry?branch=master)

<img style="float: left;" alt="Logo" src="https://github.com/HDouss/jeometry/blob/gh-pages/images/path3479.png" />
# jeometry
jeometry is a Java 2D geometric figures construction library.
It provides a simple API to:
* Build a geometric figure based on renderable shapes and relations between these shapes
* Output the figure to the screen

jeometry is easily extensible for a developer who wants to enhance the library with custom shapes, as well as to add more geometric figure output medium (for example: svg file, but there is no limit to imagination).

#Quick start
Before digging into depper details, let's begin with a quick start to clarify what could jeometry be for.
Supposing we have this geometric figure to draw:

```
Let P be a random point and L be a random line.
Let M be the parallel line to L passing by P.
Let Q be a random point of M.
Let N be the perpendicular line to L passing by Q.
Let R be the intersection point of L and N.
Let C be the circle of center R and with radius 2. 
```

This is a simple figure that have no purpose other than showing some of jeometry capabilities.
Drawing this figure using jeometry API is rather straightforward.

```java
final XyPoint ppoint = new RandomPoint();
final Line lline = new PtsLine(new RandomPoint(), new RandomPoint());
final Line mline = new ParallelLine(lline, ppoint);
final XyPoint qpoint = new InLinePoint(mline);
final Line nline = new PerpLine(lline, qpoint);
final XyPoint rpoint = new LineIntersectPoint(lline, nline);
final Circle circle = new DblCircle(rpoint, 2.);
final Figure figure = new Figure().add(ppoint)
    .add(lline).add(mline)
    .add(qpoint).add(nline)
    .add(rpoint).add(circle);
```

```java
new Awt().render(figure);
```
