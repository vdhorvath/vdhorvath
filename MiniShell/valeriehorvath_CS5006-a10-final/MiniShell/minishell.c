#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <sys/wait.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#define MAXLINE 8182


  int stat_loc;
  pid_t child_pid;
  char **command;
  char *input;
  char cmdlineData[MAXLINE];
  char *path= "/bin/";
  char progpath[20];





/**
 * Clear Screen for mini shell prompt.
 * Referenced https://stackoverflow.com/questions/2347770/how-do-you-clear-the-console-screen-in-c
 */


void clearScreen() {
  const char * CLEAR_SCREEN_ANSI = "\e[1;1H\e[2J";
  write(STDOUT_FILENO, CLEAR_SCREEN_ANSI, 12);
}



/** Process the input from the command line
*/



char **processInput(char *input) {
  char **command = malloc(80 * sizeof(char *));
  char *splitCommand = " ";
  char *parsed;
  int index = 0;

  parsed = strtok(input, splitCommand);
  while (parsed != NULL) {
    command[index] = parsed;
    index++;
    parsed = strtok(NULL, "\n");
    }

  return command;
}


/** Read the information from the command line.
 */


char *Fgets(char *ptr, int n, FILE *stream) {
  char *rptr;
  if (((rptr = fgets(ptr, n, stream)) == NULL) && ferror(stream)) {
    fprintf(stderr, "%s\n", "fgets error");
    exit(0);
  }
  return rptr;
}

/**
 * Help function, inspired by https://www.geeksforgeeks.org/making-linux-shell-c/
 */

void openHelp() {
  puts("\n***K-SEA Shell (ksesh) HELP***"
        "\nList of Commands supported:"
        "\n>cd"
        "\n>help"
        "\n>exit"
        "\n>clear"
        "\n>pwd"
        "\n>ls"
        "\n>touch"
        "\n>This command line also supports commands within the /bin/");

    return;
}


/**
 * Commands created for the the shell.
 * Resources used : https://www.youtube.com/watch?v=S7K9y-A0fxc,
 * https://www.youtube.com/watch?v=qmw9K4oHOog
 *
 */



int builtin_command(char **command) {
  int COUNT = 5;
  int switch_cmd = 0;
  char *cmd_check[] = {"cd", "help\n", "exit\n", "clear\n", "\0"};

  for (int i = 0; i < COUNT; i++) {
    if (strcmp(command[0], cmd_check[i]) == 0) {
      switch_cmd = i + 1;
      break;
  }
}

  switch (switch_cmd) {
    case 1:
      chdir(command[1]);
      printf("Directory changed, use pwd to check where you are.\n");
        return 1;
    case 2:
      openHelp();
      return 1;
    case 3:
      printf("\nEnjoy the rest of your day!\n");
      exit(0);
    case 4:
      clearScreen();
      return 1;
    default:
      break;
    }
    return 0;
}



/**
  * Signal handler
  */

void handler(int num) {
  write(1, "  k-sea-shell terminated\n", 26);
  exit(0);

}



int main() {
  signal(SIGINT, handler);

  while (1) {
  printf("k-sea-shell>");
  input = Fgets(cmdlineData, MAXLINE, stdin);
  command = processInput(input);
  builtin_command(command);


  child_pid = fork();
  if (child_pid < 0) {
    perror("Fork failed");
    exit(1);
  } else if (child_pid == 0) {
    strcpy(progpath, path);
    strcat(progpath, command[0]);

  for (int i = 0; i < strlen(progpath); i++) {
    if (progpath[i] == '\n') {
       progpath[i] = '\0';
     }
  }

  execvp(progpath, command);
  exit(0);

} else {
        waitpid(child_pid, &stat_loc, WUNTRACED);
  }
  }
  return 0;
}
