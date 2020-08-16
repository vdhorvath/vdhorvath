all: run

build: minishell
	gcc minishell.c -o minishell

run: build
	./minishell

run_valgrind: build
	valgrind ./minishell

clean: 
	rm minishell
