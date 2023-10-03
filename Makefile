JFLEX  = jflex
JAVAC  = javac

# targets:

all: Parser.class

clean:
	rm -f *~ *.class Yylex.java

Parser.class: Parser.java Yylex.java
	$(JAVAC) Parser.java

Yylex.java: mini_java.flex
	$(JFLEX) mini_java.flex

