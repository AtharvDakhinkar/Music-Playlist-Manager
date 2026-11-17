# Music Playlist Manager Using Doubly Linked List in Java

## 1. Project Overview

The Music Playlist Manager is a Java-based desktop application that allows users to manage their music playlists through a graphical user interface.

The project uses a **Doubly Linked List** to store and manage song information. Each node contains the song name, artist name, and references to the previous and next nodes.

The graphical interface is developed using **Java AWT**, while all playlist operations are implemented manually without using built-in collection classes.

## 2. Features

- **Add Song:** Add a new song to the end of the playlist.
- **Insert After Selected:** Insert a song after a selected song.
- **Delete Song:** Remove a selected song from the playlist.
- **Search Song:** Search for a song by its name.
- **Play Song:** Select a song as the currently playing song.
- **Next Song:** Move to the next song in the playlist.
- **Previous Song:** Move to the previous song.
- **Sort Playlist:** Arrange songs alphabetically by name.
- **Reverse View:** Display the playlist in reverse order.
- **Clear Playlist:** Remove all songs from the playlist.
- **Song Counter:** Display the total number of songs.

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Java AWT | Graphical User Interface |
| Doubly Linked List | Playlist data structure |
| AWT Event Handling | Button click operations |

## 4. Data Structure Used

A Doubly Linked List is used to store the songs.

Each node contains:

- `song` – Stores the song name.
- `artist` – Stores the artist name.
- `next` – Points to the next node.
- `prev` – Points to the previous node.

### Node Representation

```text
NULL <- [Song 1] <-> [Song 2] <-> [Song 3] -> NULL
```

The playlist maintains three main pointers:

- `head` – Points to the first song.
- `tail` – Points to the last song.
- `current` – Points to the currently selected song.

## 5. Project Structure

```text
MusicPlaylistProject/
│
├── MusicPlaylist.java
│
├── PlaylistGUI.java
│
└── README.md
```

### File Description

**MusicPlaylist.java**

Contains the doubly linked list implementation and playlist operations such as insertion, deletion, searching, sorting, and navigation.

**PlaylistGUI.java**

Contains the Java AWT graphical interface and connects the GUI buttons with the methods defined in `MusicPlaylist.java`.

## 6. Requirements

- Java Development Kit (JDK 8 or later)
- Any Java IDE, such as:
  - Visual Studio Code
  - IntelliJ IDEA
  - Eclipse
  - NetBeans

## 7. How to Run the Project

### Step 1: Open the Project Folder

Make sure both Java files are present in the same directory.

### Step 2: Open the Terminal

Navigate to the project directory.

### Step 3: Compile the Java Files

```bash
javac MusicPlaylist.java PlaylistGUI.java
```

### Step 4: Run the Application

```bash
java PlaylistGUI
```

The Music Playlist Manager graphical interface will open.

## 8. Working of the Application

1. Enter a song name and artist name.
2. Click **Add Song** to add the song to the playlist.
3. Select a song from the playlist to perform operations such as delete, insert after, or play.
4. Use the Search option to find a particular song.
5. Use Next and Previous to navigate between songs.
6. Click Sort Playlist to arrange songs alphabetically.
7. Use Reverse View to display the songs in reverse order.
8. Click Clear Playlist to remove all songs.

## 9. Advantages

- Dynamic memory allocation using linked list nodes.
- Efficient insertion and deletion without shifting elements.
- Forward and backward traversal.
- Easy navigation between songs.
- Simple and user-friendly graphical interface.

## 10. Limitations

- Songs are stored in memory and are not permanently saved.
- Actual audio playback is not implemented.
- The application manages song names and artist information only.

## 11. Future Enhancements

- Add actual audio playback functionality.
- Implement file handling to save playlists.
- Add support for multiple playlists.
- Add song duration and album information.
- Improve the interface with additional controls.

## 12. Conclusion

The Music Playlist Manager was successfully developed using Java and a Doubly Linked List. It provides operations such as insertion, deletion, searching, sorting, and navigation through a graphical user interface. This project demonstrates the practical application of linked list concepts in a real-world music playlist management system.
