#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>
#include <signal.h>
#include <ctype.h>
#define MAXLINE 100


char cmdlineData[MAXLINE];
char *input;
char resp[1000];
char *input_server;
char *game_over = ("You guessed my secret number!");


int do_connect(char *host, char *port) {
        int s;
        int sock_fd = socket(AF_INET, SOCK_STREAM, 0);

        struct addrinfo hints, *result;


        memset(&hints, 0, sizeof(struct addrinfo));
        hints.ai_family = AF_INET; /* IPv4 only */
        hints.ai_socktype = SOCK_STREAM; /* TCP */

        s = getaddrinfo(host, port, &hints, &result);

        // If I can't get the address, write an error.
        if (s != 0) {
                        fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(s));
                        exit(1);
        }

        // Try to connect; if I can't, write an error.
        if (connect(sock_fd, result->ai_addr, result->ai_addrlen) == -1) {
                perror("connect");
                exit(2);
        } else {
                printf("Connection is good!\n");
        }
  freeaddrinfo(result);
        return sock_fd;
}

void send_message(int msg, int sock_fd) {
  if (msg == 0) {
     printf("CLIENT: %s\n", input);
  } else {
     printf("CLIENT: %d\n", msg);
}
  write(sock_fd, &msg, sizeof(msg));
}





/** Read the input from the command line
*/



char *Fgets(char *ptr, int n, FILE *stream) {
  char *rptr;
  if (((rptr = fgets(ptr, n, stream)) == NULL) && ferror(stream)) {
    fprintf(stderr, "%s\n", "fgets error");
    exit(0);
  }
  return rptr;
}




void read_response(int sock_fd) {
  int len = read(sock_fd, resp, 999);
  resp[len] = '\0';
  printf("SERVER: %s\n", resp);
}



/**
  * Signal handler
  */

void handler(int num) {
  write(1, "  guessing game... terminated\n", 36);
  exit(0);
}







int main(int argc, char **argv) {
  signal(SIGINT, handler);
  printf("===========================================================\n");
  printf("===========================================================\n");
  printf("===========================================================\n");
  printf("===========================================================\n");
  printf("Server: Welcome! I've chosen a number between 1 and 50.\n");
  printf("Guess it if you can!\n");



  int sock_fd = do_connect("localhost", "1234");
  printf("socket fd (client): %d\n", sock_fd);

  while (1) {
  printf("ENTER YOUR GUESS HERE==========>");

  input = Fgets(cmdlineData, MAXLINE, stdin);



  // convert integer //

  int num_guess = atoi(input);

  // sends message //

  send_message(num_guess, sock_fd);

  // reading the response //

  read_response(sock_fd);

  if (strncmp(resp, game_over, sizeof(strlen(game_over))) == 0) {
    printf("========Game_Over===========\n");
    printf("============================\n");
    printf("============================\n");
    break;
    }
  }
  close(sock_fd);
  return 0;
}
