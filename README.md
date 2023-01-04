# Food Ordering System

![dependency-graph.png](system-graph%2Fdependency-graph.png)


### Using 

  - Clean Architecture
  
  - DDD

  - KAFKA



**Run this command to generate a system module image:**

    mvn com.github.ferstl:depgraph-maven-plugin:aggregate -DcreateImage=true -DreduceEdges=false -Dscope=compile "-Dincludes=com.vakaks.food-ordering-system*:*"
