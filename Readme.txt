Tried the advent of code Day 8 challenge

PART ONE

In the challenge we get an input of a 2d array that represents a grid of trees and we need to find how many of them are
visible from each direction.

So first I needed to make sure I convert the input file that is provided to a 2d integer array so that I could compare
each tree's height with the others.
After I make a boolean function that returns true if the tree is visible and count the results. That's about it in the main
method.
Now on the isVisible() function I get a tree as input and check if it is taller than the tallest tree in each direction.
On the tallest function I find the max values of the columns and rows of the array that I get as input everytime.

PART TWO

On the second part of the challenge I need to find the tree that has the largest view, so I make the getView() function
that returns the product of the trees that I can see from the tree in each direction. Depending on the direction I want
to go I call the numOfTrees() with the appropriate direction value.
This function counts the trees that I can see iterating through the array to this direction.



