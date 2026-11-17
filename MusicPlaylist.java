
class MusicPlaylist {

    // Node represents one song in the doubly linked list.
    // Each node stores the song name, artist, next node and previous node.
    class Node {
        String song, artist;
        Node next, prev;

        Node(String song, String artist) {
            this.song = song;
            this.artist = artist;
            next = null;
            prev = null;
        }
    }

    Node head = null;
    Node tail = null;
    Node current = null;
    int count = 0;

    // Adds a new song at the beginning of the playlist.
    void addAtBeginning(String song, String artist) {
        Node newNode = new Node(song, artist);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        if (current == null)
            current = head;

        count++;
    }

    // Adds a new song at the end of the playlist.
    void addAtEnd(String song, String artist) {
        Node newNode = new Node(song, artist);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        if (current == null)
            current = head;

        count++;
    }

    // Inserts a new song immediately after the specified song.
    boolean insertAfter(String targetSong, String song, String artist) {
        Node temp = searchSong(targetSong);

        if (temp == null)
            return false;

        Node newNode = new Node(song, artist);

        newNode.next = temp.next;
        newNode.prev = temp;

        if (temp.next != null)
            temp.next.prev = newNode;
        else
            tail = newNode;

        temp.next = newNode;
        count++;

        return true;
    }

    // Searches for a song by name and returns its node if found.
    Node searchSong(String song) {
        Node temp = head;

        while (temp != null) {
            if (temp.song.equalsIgnoreCase(song))
                return temp;

            temp = temp.next;
        }

        return null;
    }

    // Deletes the first song matching the given name.
    boolean deleteSong(String song) {
        Node temp = searchSong(song);

        if (temp == null)
            return false;

        if (temp == current)
            current = (temp.next != null) ? temp.next : temp.prev;

        if (temp.prev != null)
            temp.prev.next = temp.next;
        else
            head = temp.next;

        if (temp.next != null)
            temp.next.prev = temp.prev;
        else
            tail = temp.prev;

        count--;

        if (count == 0)
            current = null;

        return true;
    }

    // Sets the selected song as the currently playing song.
    Node playSong(String song) {
        current = searchSong(song);
        return current;
    }

    // Moves to the next song; returns to the first song at the end.
    Node nextSong() {
        if (current == null)
            current = head;
        else if (current.next != null)
            current = current.next;
        else
            current = head;

        return current;
    }

    // Moves to the previous song; goes to the last song at the beginning.
    Node previousSong() {
        if (current == null)
            current = tail;
        else if (current.prev != null)
            current = current.prev;
        else
            current = tail;

        return current;
    }

    // Sorts the playlist alphabetically by song name.
    // It swaps song and artist data without changing the node links.
    void sortPlaylist() {
        if (head == null)
            return;

        Node temp, index;
        String songTemp, artistTemp;

        for (temp = head; temp != null; temp = temp.next) {
            for (index = temp.next; index != null; index = index.next) {
                if (temp.song.compareToIgnoreCase(index.song) > 0) {
                    songTemp = temp.song;
                    artistTemp = temp.artist;

                    temp.song = index.song;
                    temp.artist = index.artist;

                    index.song = songTemp;
                    index.artist = artistTemp;
                }
            }
        }
    }

    // Returns the total number of songs in the playlist.
    int getCount() {
        return count;
    }

    // Returns the currently selected or playing song.
    Node getCurrent() {
        return current;
    }

    // Deletes all songs by resetting the list pointers and count.
    void clearPlaylist() {
        head = null;
        tail = null;
        current = null;
        count = 0;
    }
}