Maze.class: Edge.class Vertex.class Graph.class GridGraph.class MazePanel.class Maze.java
	javac Maze.java

Edge.class: Edge.java
	javac Edge.java

Vertex.class: Vertex.java
	javac Vertex.java

Graph.class: Graph.java
	javac Graph.java

GridGraph.class: GridGraph.java
	javac GridGraph.java

MazePanel.class: MazePanel.java
	javac MazePanel.java

clean:
	rm *.class

run: Maze.class
	java Maze