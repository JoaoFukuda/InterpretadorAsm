all: clear
	javac src/Main.java

clear:
ifdef OS
	ls *.class -Recurse | foreach {rm $_}
else
ifeq ($(shell uname), Linux)
	find -name "*.class" -delete
endif
endif

jar: all
	jar cvf IntAsm.jar *
	java -cp IntAsm.jar src.Main

pull:
	git fetch
	git pull

push:
	git add .
	git commit
	git push
