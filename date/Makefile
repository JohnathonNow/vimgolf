CC=gcc
CFLAGS=-Wall
EXE=date
.PHONY: all
all: $(EXE)
$(EXE): main.c
	$(CC) $(CFLAGS) $< -o $@
clean:
	rm -f $(EXE)
