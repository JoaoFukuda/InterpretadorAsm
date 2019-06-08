all:
	javac src/Main.java

test: all
	java src.Main

# Só funciona com Linux
clear:
	find -name "*.class" -delete
