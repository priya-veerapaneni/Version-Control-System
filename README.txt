Name: Sankaran Shanmugavelayudam, Tejas Tundulwar, Priya Veerapaneni

Project Name: SCM

Team Initials: TVS

Contact Info: sankaran22.5@gmail.com, ttejas@rocketmail.com, priya.veerapaneni3@gmail.com

Section: CECS 543-01

Class number: 8612

Project part: Part 3

Intro: This is the third part of our SCM (Source Code Management) project. In this project part, we add the ability to merge two project trees (that are based on the same repo). Note that we already have a natural branching effect due to check-out (of a checked-in project version into a new project tree, AKA
the Kid project version) coupled with tracking that project version's parent (AKA the Mom) version (as identified in the Kid's repo manifest).

The merge ability helps the user to merge a project tree (the R version) that is already in the repo (as represented by a manifest file) into a target project tree (the T version) outside the repo. We will assume that the T version has also just been checked-in, so that there is an up-to-date T version manifest file in the repo.

For example, Fred can merge Jack's changes (the R version, checked-in from Jack's project tree) that are in the repo into Fred's current project tree (the T version). If the merge succeeds (standard merge software is only able to handle simple file differences, but this could be the case with Fred and Jack)
then Fred can quickly run some tests on the merged resulting project tree and check-in his resulting merged project tree for others to use. This is how a branch is merged back into mainline.

For project 3, we will only issue a command result that there are conflicting file versions and also leave copies of the conflicting file versions in the target project tree, so that Fred could look at the conflicting file versions and manually run his choice of merge software on the files that need it.

External Requirements: Java version 1.8 is required for the SCM project to run.

Build, Installation and Setup: 
	Refer the following to build and run the project:
	1. Open a command prompt and change directory to src\
	2. Type the following command to build all the java files:
		javac com\ase\vcs\*.java 
	3. Type the following command to run the project:
		java com.ase.vcs.Main

Usage:
From the 543-p3_TVS\src directory:
For build: javac com\ase\vcs\*.java
To Run: java com.ase.vcs.Main

Sample invocation and results:







Extra Features: None

Bugs: None detected