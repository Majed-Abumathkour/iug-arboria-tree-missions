# Arboria Tree Missions

Console-based Java project that solves five tree-focused missions for the `CSCI2109` practical assignment.

## Student Information

- Name: `Majed Abumathkour`
- Student ID: `120221624`
- Course: `Data Structures and Algorithms Practical (CSCI2109)`

## Project Video (YouTube)

- Explanation + manual testing demo: [Arboria Tree Missions](https://youtu.be/ByI1ik9-_GE?si=fwXYg5mngSh2m9LF)`

## Project Overview

This project provides a menu-driven console application where each menu option runs one mission related to binary trees or binary search trees.

The missions are:

1. Emergency Broadcast Levels: Traverse active nodes level-by-level from a level-order array.
2. Forest Gate Check: Validate whether a level-order array represents a valid BST.
3. Drone Delivery Zone Sum: Build a BST from insertion order and sum values inside a range `[L, R]`.
4. Nearest Shared Supervisor: Build a BST and find the Lowest Common Ancestor (LCA) of two values.
5. Archaeology Reconstruction: Reconstruct a tree from preorder and inorder traversals, then output postorder.

## Features

- Interactive main menu with repeated runs until `exit`.
- Input validation for numeric data, array sizes, and range constraints.
- Custom data structures:
  - `LinkedList<E>`
  - `Queue<E>` (used in mission 1 BFS traversal)
- Mission implementations separated into dedicated classes.
- Clear error handling in the main loop so one bad input does not crash the program.

## Tech Stack

- Language: `Java`
- Build tool: `Apache Ant` (NetBeans project structure)
- IDE compatibility: `NetBeans` (project metadata included)

## Project Structure

```text
ArboriaTreeMissions/
├─ src/
│  └─ arboriatreemissions/
│     ├─ ArboriaTreeMissions.java
│     ├─ datastructures/
│     │  ├─ LinkedList.java
│     │  └─ Queue.java
│     └─ missions/
│        ├─ Mission1EmergencyBroadcastLevels.java
│        ├─ Mission2ForestGateCheck.java
│        ├─ Mission3DroneDeliveryZoneSum.java
│        ├─ Mission4NearestSharedSupervisor.java
│        └─ Mission5ArchaeologyReconstruction.java
├─ build.xml
├─ manifest.mf
└─ nbproject/
```

## Prerequisites

- JDK installed and configured in `PATH`.
- Ant installed (optional if running through NetBeans).

Note:

- This project is currently configured with `javac.source=25` and `javac.target=25` in `nbproject/project.properties`.
- If your environment uses a different Java version, update those values accordingly.

## How to Run

### Option 1: Run with NetBeans

1. Open the project folder in NetBeans.
2. Build the project.
3. Run the project.
4. Use the console menu to select missions.

### Option 2: Run with Ant (Terminal)

```powershell
ant clean
ant run
```

### Option 3: Compile and run with `javac`/`java`

```powershell
mkdir out
javac -d out src\arboriatreemissions\datastructures\*.java src\arboriatreemissions\missions\*.java src\arboriatreemissions\ArboriaTreeMissions.java
java -cp out arboriatreemissions.ArboriaTreeMissions
```

## Menu Flow

At startup, the application displays:

- Mission 1 to Mission 5 options
- `exit` option to terminate

For each selected mission, the app asks for the required inputs, prints the result, and returns to the menu.

## Mission Details

### Mission 1: Emergency Broadcast Levels

- Input: level-order array (`-1` means missing node)
- Output: active nodes by BFS levels using format `LevelX:...`
- Core idea: queue of array indexes (`left = 2*i+1`, `right = 2*i+2`)

Example output:

```text
Level0:10
Level1:6 14
Level2:4 8 16
```

### Mission 2: Forest Gate Check (Validate BST)

- Input: level-order array (`-1` means missing node)
- Output: `YES` if valid BST, otherwise `NO`
- Core idea: recursive bounds check `(minExclusive, maxExclusive)` per node

### Mission 3: Drone Delivery Zone Sum

- Input:
  - insertion sequence for BST
  - two integers `L` and `R` (`L <= R`)
- Output: sum of all BST values in inclusive range `[L, R]`
- Core idea: BST range-sum recursion with pruning
- Duplicate policy: duplicate inserted values are ignored

### Mission 4: Nearest Shared Supervisor (LCA)

- Input:
  - insertion sequence for BST
  - values `p` and `q`
- Output: value of Lowest Common Ancestor in the BST
- Validation: both `p` and `q` must exist in tree, or an input error is shown

### Mission 5: Archaeology Reconstruction

- Input:
  - preorder traversal
  - inorder traversal
- Output: postorder traversal (space-separated)
- Core idea:
  - root comes from preorder pointer
  - split inorder segment at root
  - recursively build left/right subtrees

## Error Handling Notes

- Non-integer values prompt retry messages.
- Wrong number of integers in a line prompts the user again.
- Mission 3 range validation enforces `L <= R`.
- Mission 4 checks target presence before LCA.
- Any mission-level runtime input error is caught by the main loop and reported without exiting.

## Complexity Summary

- Mission 1 (BFS over represented nodes): `O(n)` time, `O(w)` queue space (`w` = max width).
- Mission 2 (recursive validation): `O(n)` time, `O(h)` recursion space (`h` = tree height).
- Mission 3 (BST insert + range sum): average `O(n log n)` build and `O(k + h)` range traversal with pruning; worst-case skewed tree can degrade.
- Mission 4 (BST insert + LCA): average `O(n log n)` build, `O(h)` LCA.
- Mission 5 (current reconstruction): `O(n^2)` worst-case due to linear search for root in inorder segment at each step.

## Future Improvements

- Add automated unit tests per mission (`test/` source root is already configured).
- Optimize mission 5 to `O(n)` using a value-to-index map for inorder traversal positions.
- Add file-based input mode for batch testing.
- Add CI build checks (compile and tests on push).

