# Pseudocode of Brute Force Algorithm to solve Travelling Salesperson Problem 

1. Start
2. function main()
    1.    Start
    2.    Declare cityDistance , path , cityNum and minDistance
    3.    Read number of cities (cityNum) and name of cities (cityNames) from user
    4.    Initialize cityDistance matrix of size (cityNum x cityNum)
    5.    Initialize path array of size cityNum
    6.    Initialize minDistance to infinity
    7.    Read distance between each cities (cityDistance) from user
    8.    Call function initializePath()
    9.    Call function generatePermutations(1)
    10.   Print minDistance
    11.   Print "Path taken:"
    12.   Call function printPath()
    13.   End
    
3. function initializePath()
    1. Start
    2. for (i = 0; i< to cityNum; i++) {
            path[i] = i
        }
    3. End

4. function generatePermutations(index)
    1. Start
    2. if index == cityNum {
        	then cost = calculateCost()
        	if cost < minDistance
       	      then minDistance = cost
       }
    3. for i = index to cityNum-1 {
            swap(index, i)
            generatePermutations(index + 1)
            swap(index, i) }
    4. End

5. function calculateCost()
    1. Start
    2. return calculateCostRecursively(0, 1, 0)
    3. End

6. function calculateCostRecursively(currentCity, count, currentCost)
    1.  Start
    2. if count == cityNum
       then return currentCost + cityDistance[currentCity][0]
    3. minCost = infinity
    4. for (nextCity = 1 ; nextCity<cityNum; nextCity++) {
            if path[nextCity] != -1 then {
                cost = cityDistance[currentCity][nextCity]
                path[nextCity] = -1
                temp = calculateCostRecursively(nextCity, count + 1, currentCost +         
                cost)
                path[nextCity] = nextCity
                minCost = min(minCost, temp)
            }
         }
    5. return minCost
    6. End

7. function swap(i, j)
    1. Start 
    2. temp = path[i]
    3. path[i] = path[j]
    4. path[j] = temp
    5. End

8. function printPath()
    1. Start
    2. for (i = 0 ; i<cityNum ; i++)
            print (path[i] + 1) + " -> "
    3. print path[0] + 1 
    4. End

9. End 

# Analysis of Brute Force ALgorithm

* This method employs the recursive backtracking algorithm paradigm, which generates every possible permutation of cities and computes the total distance travelled for each permutation.
> The minimal distance is determined and updated with each permutation. 

* The part in the algorithm that uses recursion is the ‘generatePermutations’ function which is responsible for generating all possible permutations of cities.
  
* The function will take the index ‘1’ assuming the first city is fixed. Then it will continue generating possible permutations by swapping the current cities with the remaining ones and invoking itself in a recursive manner with an increased index to produce permutations for the remaining cities. Until all cities have been taken into account, this recursive process continues. 

* The function for optimisation in this algorithm is ‘calculateCost’. It calculates the cost (total distance) of a given permutation of cities. The optimisation occurs within the ‘calculateCostRecursively’ function, which is called recursively to calculate the cost of each subpath within a permutation.

> These functions work together to find the shortest distance that can be formed from the permutation of cities in the most efficient manner. 

## Using brute force to show the algorithm correctness
- Number of cities : 4
- Name of cities : Kuala Lumpur, Klang, Johor Bahru, Subang Jaya

- City distance matrix : 
# ![image](https://github.com/benthen/algo/assets/111986781/2b0478b4-db1e-4397-b1f5-6599adda5caf)

Possible permutations : 4! = 24

- [ Kuala Lumpur, Klang, Johor Bahru ,Subang Jaya ] = 39 + 351 + 338 + 26 = 754
- [ Kuala Lumpur, Klang, Subang Jaya, Johor Bahru ] = 39 + 24 + 338 + 335 = 736
- [ Kuala Lumpur, Johor Bahru, Klang, Subang Jaya ] = 335 + 351 + 24 + 26 = 739
- [ Kuala Lumpur, Johor Bahru, Subang Jaya, Klang ] = 335 + 338 + 24 + 39 = 736
- [ Kuala Lumpur, Subang Jaya, Klang, Johor Bahru ] = 26 + 24 + 351 + 335 = 736
- [ Kuala Lumpur, Subang Jaya, Johor Bahru, Klang ] = 26 + 338 + 351 + 39 = 754
- [ Klang, Kuala Lumpur, Johor Bahru, Subang Jaya ] = 39 + 335 + 338 + 24 = 736
- [ Klang, Kuala Lumpur, Subang Jaya, Johor Bahru ] = 39 + 26 + 338 + 351 = 754
- [ Klang, Johor Bahru, Kuala Lumpur, Subang Jaya ] = 351 + 335 + 26 + 24 = 736
- [ Klang, Johor Bahru ,Subang Jaya, Kuala Lumpur ] = 351 + 338 + 26 + 39 = 754
- [ Klang, Subang Jaya, Kuala Lumpur, Johor Bahru ] = 24 + 26 + 335 + 351 = 736
- [ Klang, Subang Jaya, Johor Bahru, Kuala Lumpur ] = 24 + 338 + 335 + 39 = 736
- [ Johor Bahru, Kuala Lumpur, Klang, Subang Jaya ] = 335 + 39 + 24 + 338 = 736
- [ Johor Bahru, Kuala Lumpur, Subang Jaya, Klang ] = 335 + 26 + 24 + 351 = 736
- [ Johor Bahru, Klang, Kuala Lumpur, Subang Jaya ] = 351 + 39 + 26 + 338 = 754
- [ Johor Bahru, Klang, Subang Jaya, Kuala Lumpur ] = 351 + 24 + 26 + 335 = 736
- [ Johor Bahru, Subang Jaya, Kuala Lumpur, Klang ] = 338 + 26 + 39 + 351 = 754
- [ Johor Bahru, Subang Jaya, Klang, Kuala Lumpur ] = 338 + 24 + 39 + 335 = 736
- [ Subang Jaya, Kuala Lumpur, Klang, Johor Bahru ] = 26 + 39 + 351 + 338 = 754
- [ Subang Jaya, Kuala Lumpur, Johor Bahru, Klang ] = 26 + 335 + 351 + 24 = 736
- [ Subang Jaya, Klang, Kuala Lumpur, Johor Bahru ] = 24 + 39 + 335 + 338 = 736
- [ Subang Jaya, Klang, Johor Bahru, Kuala Lumpur ] = 24 + 351 + 335 + 26 = 736
- [ Subang Jaya, Johor Bahru, Kuala Lumpur, Klang ] = 338 + 335 + 39 + 24 = 736
- [ Subang Jaya, Johor Bahru, Klang, Kuala Lumpur ] = 338 + 351 + 39 + 26 = 754

> - The minimum distance travelled: 736
> - There are many path can be taken for the minimum distance of 736 km and below is one of the path:
> - Path taken: Kuala Lumpur -> Johor Bahru -> Subang Jaya -> Klang -> Kuala Lumpur


## Algorithm complexity:
- Best case
    - O(k) -> k=0-> O(0) -> O(1)
    - Best case would be when the cityNum is 0. The algorithm will not have to do any recursive call and will terminate right away. 
- Average and worst case 
    - O(n!)
    - Average and worst case is when the algorithm has to find minimum distance for every permutation which is n!, the number of distances to calculate. 



