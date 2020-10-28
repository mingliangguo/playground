// credit: https://stackoverflow.com/questions/34233853/im-trying-to-read-a-line-from-a-file-in-c-and-dynamically-allocate-memory-but-t
//
#include <stdio.h>
#include <stdlib.h>

#define NCHAR 64

char *readline (FILE *fp);

int main (int argc, char **argv) {

    char *line = NULL;
    int idx = 0;
    FILE *fp = fopen (argv[1], "r");
    if (!fp) {
        fprintf (stderr, "error: file open failed '%s'.\n", argv[1]);
        return 1;
    }

    while ((line = readline (fp)) != NULL) {  /* read each line in 'fp' */
        printf (" line[%d] : %s\n", idx++, line);
        free (line);
    }
    if (fp != stdin)
      fclose (fp);

    return  0;
}

/* read line from 'fp' allocate *buffer NCHAR in size
 * realloc as necessary. Returns a pointer to *buffer
 * on success, NULL otherwise.
 */
char *readline (FILE *fp) 
{
    int ch;
    size_t buflen = 0, capacity = 64;

    char *buffer = malloc (capacity);    /* allocate buffer nchar in length */
    if (!buffer) {
        fprintf (stderr, "readline() error: memory exhausted.\n");
        return NULL;
    }

    while ((ch = fgetc(fp)) != '\n' && ch != EOF) 
    {
        (buffer)[buflen++] = ch;

        if (buflen + 1 >= capacity) {  /* realloc */
            buffer = realloc (buffer, capacity * 2);
            if (!buffer) {
                fprintf (stderr, "error: realloc failed, "
                                "returning partial buffer.\n");
                (buffer)[buflen] = 0;
                return buffer;
            }
            capacity *= 2;
        }
    }
    (buffer)[buflen] = 0;           /* nul-terminate */

    if (buflen == 0 && ch == EOF) {  /* return NULL if nothing read */
        free (buffer);
        buffer = NULL;
    }

    return buffer;
}
