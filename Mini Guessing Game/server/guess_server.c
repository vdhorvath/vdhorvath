#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <time.h>
#include <ctype.h>


int secret_num;
int generated_secret_num;  // Server
int num_client;  // Client
int MAX_NUM = 50;
int LOW_NUM = 1;
int try_counter = 0;
int invalid_input_counter = 0;




int generate_num(int secret_num) {
  srand(time(0) + getpid());
  secret_num = (rand() % (MAX_NUM - LOW_NUM + 1)) + LOW_NUM;
  return secret_num;
}



int do_open() {
        int s;

        // Step 1: Get Address stuff
        struct addrinfo hints, *result;

        // Setting up the hints struct...
        memset(&hints, 0, sizeof(struct addrinfo));
        hints.ai_family = AF_INET;
        hints.ai_socktype = SOCK_STREAM;
        hints.ai_flags = AI_PASSIVE;

        s = getaddrinfo("localhost", "1234", &hints, &result);
        if (s != 0) {
                fprintf(stderr, "getaddrinfo: %s\n", gai_strerror(s));
                exit(1);
        }

        // Step 2: Create the socket
        int sock_fd = socket(AF_INET, SOCK_STREAM, 0);


        // Step 3: Bind the socket
        if (bind(sock_fd, result->ai_addr, result->ai_addrlen) != 0) {
                perror("bind()");
                exit(1);
        }

        // Step 4: Listen
        if (listen(sock_fd, 10) != 0) {
                perror("listen()");
                exit(1);
        }
      struct sockaddr_in *result_addr = (struct sockaddr_in *) result->ai_addr;

        // Step 5: Accept a connection

  printf("Waiting for connection...\n");
        int client_fd = accept(sock_fd, NULL, NULL);
        printf("Connection made: client_fd=%d\n", client_fd);
	freeaddrinfo(result);
        return client_fd;
}


void recieve_message(int client_fd) {
        read(client_fd, &num_client, sizeof(num_client));
        printf("CLIENT: %d \n", num_client);
}

void send_message(char *msg, int sock_fd) {
        printf("SERVER %s\n", msg);
        printf("===\n");
        write(sock_fd, msg, strlen(msg));
}



int main(int argc, char **argv) {
  char server_buffer[1000];  // Server char*
  char client_buf[1000];  // Client
  int client_socket_id = do_open();
  char won_buf[100];



        // Step 6: Read and write

  printf("============================================================\n");
  printf("============================================================\n");
  printf("============================================================\n");
  printf("============================================================\n");
        printf("Server: Welcome! I've chosen a number between 1 and 50.\n");


  // Generate a secret number //

  generated_secret_num = generate_num(secret_num);
  printf("The secret number => %d\n",  generated_secret_num);
        printf("====================================================\n");

  while (1) {
  try_counter++;
  recieve_message(client_socket_id);

  // Convert the generated number to string.

  sprintf(server_buffer, "%d", generated_secret_num);

  // Convert the client number to string

  sprintf(client_buf, "%d", num_client);

  // Check for string input


  if (num_client == generated_secret_num) {
    int won = sprintf(won_buf, "And it took you %d guesses..",try_counter);
    send_message("You guessed my secret number! ", client_socket_id);
    send_message(won_buf, client_socket_id);
    if (invalid_input_counter > 0) {
      send_message(" plus something I didn't understand. ", client_socket_id);
    }
    break;

  } else if (num_client == 0) {
      send_message("I don't recognize that number.\n", client_socket_id);
      send_message("Only numbers between 1 & 50 inc.\n", client_socket_id);
      invalid_input_counter++;

  } else if (num_client > generated_secret_num) {
      send_message("Too high.\n", client_socket_id);

  } else {
      send_message("Too Low.\n", client_socket_id);
      }
  }
  close(client_socket_id);
  return 0;
}
