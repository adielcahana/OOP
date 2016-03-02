# 201568425 204687842
# cahanaa1 engelbo

compile:
	javac -d bin src\*.java
run1:
	java Factorial 5
run2:
	java DescribeNumbers 7 8 9
run3:
	java Sort Asc 5 83 9 2 0
check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml \*.java
