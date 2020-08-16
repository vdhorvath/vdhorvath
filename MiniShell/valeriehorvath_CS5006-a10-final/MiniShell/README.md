# README


Created a Mini Shell in C 


Shell's (Links to an external site.) (or sometimes called command-line interfaces) are text based applications that serve as an intermediate between the user and the operating system. The shell is also a tool for how a user interacts with the operating system. Previously, we have written many scripts that our shell can execute by running various programs. The shell that you build with this assignment will be something you can continually expand on in the future, and perhaps distribute as your own one day! Let us take a closer look at the parts to get started.

Assignment Specs:

Implement a signal handler. 

As a refresher, a signal handler (Links to an external site.) is a software interrupt that is sent to a process. Provided below is an example of sending a software interrupt to a process running a shell when you press 'Ctrl+C'. Signal handlers are handy for communicating with the operating system how to handle a process that may be misbehaving, or perhaps to handle some other event (Links to an external site.).

#include <stdio.h>
#include <stdlib.h>
#include <signal.h> // This is new!

// Create a signal handler
void sigint_handler(int sig){
	// Ask yourself why is 35 the last parameter here?
	write(1,"Terminating through signal handler\n",35); 
	exit(0);
}

int main(){
	// Install our signal handler
	signal(SIGINT, sigint_handler);

	printf("You can only terminate by pressing Ctrl+C\n");

	while(1){
		printf("Running forever!\n");
		sleep(1);
	}

	return 0;
}



At a high level, implementing Mini shell takes the following components:

> You will have a function that reads in commands a user types.
> Your program will execute in an infinite loop.
> Your program will execute child processes from the shell (the shell is the parent process) based on the command.


The specifications for your shell

Built-in command of your choice
Built-in command of your choice (and documentation)e.g. 

This criterion is linked to a Learning Outcome 3 Built-in commands (exit, cd, help)
As documented above.

This criterion is linked to a Learning Outcome A working signal handler
Needs to implement Ctrl-C and any other relevant signals.

This criterion is linked to a Learning Outcome Can successfully execute processes in the foreground






                                                                                                                                

