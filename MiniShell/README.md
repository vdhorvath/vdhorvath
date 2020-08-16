# README


Mini Shell in C 


Shell's sometimes called command-line interfaces are text based applications that serve as an intermediate between the user and the operating system. The shell is also a tool for how a user interacts with the operating system. P

Assignment Specs:

Implement a signal handler

As a refresher, a signal handler is a software interrupt that is sent to a process. Provided below is an example of sending a software interrupt to a process running a shell when you press 'Ctrl+C'. Signal handlers are handy for communicating with the operating system how to handle a process that may be misbehaving, or perhaps to handle some other event. You will the <signal.h> library


At a high level, implementing Mini shell takes the following components:

- You will have a function that reads in commands a user types.
- Your program will execute in an infinite loop.
- Your program will execute child processes from the shell (the shell is the parent process) based on the command.
- Create a Built-in command of your choice
- Create the Built-in command (exit, cd, help)





                                                                                                                                

