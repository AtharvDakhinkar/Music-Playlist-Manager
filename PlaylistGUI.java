import java.awt.*;
import java.awt.event.*;

public class PlaylistGUI extends Frame implements ActionListener {

    // Object of MusicPlaylist connects the GUI to the linked list.
    MusicPlaylist playlist = new MusicPlaylist();

    // GUI components used for entering and displaying song details.
    TextField songField, artistField, searchField;
    java.awt.List songList;

    Label infoLabel, nowPlayingLabel;

    Button addButton, insertButton, deleteButton;
    Button searchButton, playButton, nextButton;
    Button previousButton, sortButton, reverseButton, clearButton;

    // Stores whether the playlist is currently displayed in reverse order.
    boolean reverseView = false;

    // Constructor creates and arranges all GUI components.
    PlaylistGUI() {
        setTitle("Music Playlist Manager");
        setSize(850, 600);
        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(24, 28, 38));

        // Header section.
        Panel header = new Panel(new GridLayout(2, 1));
        header.setBackground(new Color(24, 28, 38));

        Label title = new Label("MUSIC PLAYLIST MANAGER", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setForeground(Color.WHITE);

        nowPlayingLabel = new Label("Now Playing: None", Label.CENTER);
        nowPlayingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nowPlayingLabel.setForeground(Color.LIGHT_GRAY);

        header.add(title);
        header.add(nowPlayingLabel);

        add(header, BorderLayout.NORTH);

        // Main section containing input fields and playlist display.
        Panel main = new Panel(new GridLayout(1, 2, 15, 15));
        main.setBackground(new Color(24, 28, 38));

        // Left panel for song input and playlist operations.
        Panel left = new Panel(new GridLayout(0, 1, 8, 8));
        left.setBackground(new Color(35, 41, 54));

        Label inputTitle = new Label("ADD OR MANAGE SONGS");
        inputTitle.setForeground(Color.WHITE);
        inputTitle.setFont(new Font("Arial", Font.BOLD, 16));

        songField = new TextField();
        artistField = new TextField();

        styleField(songField);
        styleField(artistField);

        left.add(inputTitle);
        left.add(new Label("Song Name:"));
        left.add(songField);
        left.add(new Label("Artist Name:"));
        left.add(artistField);

        addButton = makeButton("Add Song");
        insertButton = makeButton("Insert After Selected");
        deleteButton = makeButton("Delete Selected");
        sortButton = makeButton("Sort Playlist");
        reverseButton = makeButton("Reverse View");
        clearButton = makeButton("Clear Playlist");

        left.add(addButton);
        left.add(insertButton);
        left.add(deleteButton);
        left.add(sortButton);
        left.add(reverseButton);
        left.add(clearButton);

        // Right panel for playlist display and playback controls.
        Panel right = new Panel(new BorderLayout(8, 8));
        right.setBackground(new Color(35, 41, 54));

        Label playlistTitle = new Label("YOUR PLAYLIST", Label.CENTER);
        playlistTitle.setForeground(Color.WHITE);
        playlistTitle.setFont(new Font("Arial", Font.BOLD, 16));

        songList = new java.awt.List();
        songList.setBackground(new Color(45, 52, 67));
        songList.setForeground(Color.WHITE);
        songList.setFont(new Font("Arial", Font.PLAIN, 14));

        Panel searchPanel = new Panel(new GridLayout(1, 2, 5, 5));
        searchField = new TextField();
        styleField(searchField);

        searchButton = makeButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        Panel playbackPanel = new Panel(new GridLayout(1, 3, 5, 5));
        playButton = makeButton("Play");
        previousButton = makeButton("Previous");
        nextButton = makeButton("Next");

        playbackPanel.add(previousButton);
        playbackPanel.add(playButton);
        playbackPanel.add(nextButton);

        right.add(playlistTitle, BorderLayout.NORTH);
        right.add(songList, BorderLayout.CENTER);

        Panel rightBottom = new Panel(new GridLayout(2, 1, 5, 5));
        rightBottom.add(searchPanel);
        rightBottom.add(playbackPanel);

        right.add(rightBottom, BorderLayout.SOUTH);

        main.add(left);
        main.add(right);

        add(main, BorderLayout.CENTER);

        // Footer displays the total number of songs.
        infoLabel = new Label("Total Songs: 0", Label.CENTER);
        infoLabel.setForeground(Color.LIGHT_GRAY);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        add(infoLabel, BorderLayout.SOUTH);

        // Registers this class to handle button click events.
        addButton.addActionListener(this);
        insertButton.addActionListener(this);
        deleteButton.addActionListener(this);
        searchButton.addActionListener(this);
        playButton.addActionListener(this);
        nextButton.addActionListener(this);
        previousButton.addActionListener(this);
        sortButton.addActionListener(this);
        reverseButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Closes the application when the window is closed.
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Creates a styled button and assigns its action listener.
    Button makeButton(String name) {
        Button button = new Button(name);
        button.setBackground(new Color(70, 110, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(this);
        return button;
    }

    // Applies common font and background styling to a text field.
    void styleField(TextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBackground(new Color(235, 238, 245));
    }

    // Refreshes the song list after an operation.
    // It displays songs from head to tail or tail to head.
    void refreshPlaylist() {
        songList.removeAll();

        if (!reverseView) {
            MusicPlaylist.Node temp = playlist.head;

            while (temp != null) {
                songList.add(temp.song + " - " + temp.artist);
                temp = temp.next;
            }
        } else {
            MusicPlaylist.Node temp = playlist.tail;

            while (temp != null) {
                songList.add(temp.song + " - " + temp.artist);
                temp = temp.prev;
            }
        }

        updateInfo();
        updateNowPlaying();
    }

    // Finds the linked-list node corresponding to the selected GUI list item.
    // The traversal direction depends on the current display order.
    MusicPlaylist.Node getSelectedNode() {
        int selectedIndex = songList.getSelectedIndex();

        if (selectedIndex == -1)
            return null;

        MusicPlaylist.Node temp;

        if (!reverseView) {
            temp = playlist.head;

            for (int i = 0; i < selectedIndex; i++)
                temp = temp.next;
        } else {
            temp = playlist.tail;

            for (int i = 0; i < selectedIndex; i++)
                temp = temp.prev;
        }

        return temp;
    }

    // Updates the total number of songs shown in the footer.
    void updateInfo() {
        infoLabel.setText("Total Songs: " + playlist.getCount());
    }

    // Updates the label showing the currently playing song.
    void updateNowPlaying() {
        MusicPlaylist.Node temp = playlist.getCurrent();

        if (temp == null)
            nowPlayingLabel.setText("Now Playing: None");
        else
            nowPlayingLabel.setText(
                "Now Playing: " + temp.song + " - " + temp.artist
            );
    }

    // Handles all button clicks and performs the corresponding playlist operation.
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Adds a song at the end of the playlist.
        if (command.equals("Add Song")) {
            String song = songField.getText().trim();
            String artist = artistField.getText().trim();

            if (!song.isEmpty() && !artist.isEmpty()) {
                playlist.addAtEnd(song, artist);
                songField.setText("");
                artistField.setText("");
                refreshPlaylist();
            } else {
                showMessage("Enter both song and artist name.");
            }
        }

        // Inserts a new song after the selected song.
        else if (command.equals("Insert After Selected")) {
            MusicPlaylist.Node selected = getSelectedNode();

            String song = songField.getText().trim();
            String artist = artistField.getText().trim();

            if (selected == null) {
                showMessage("Select a song first.");
            } else if (song.isEmpty() || artist.isEmpty()) {
                showMessage("Enter both song and artist name.");
            } else {
                boolean result = playlist.insertAfter(
                    selected.song, song, artist
                );

                if (result) {
                    songField.setText("");
                    artistField.setText("");
                    refreshPlaylist();
                }
            }
        }

        // Deletes the selected song from the playlist.
        else if (command.equals("Delete Selected")) {
            MusicPlaylist.Node selected = getSelectedNode();

            if (selected == null) {
                showMessage("Select a song to delete.");
            } else {
                playlist.deleteSong(selected.song);
                refreshPlaylist();
            }
        }

        // Searches for a song and sets it as the current song if found.
        else if (command.equals("Search")) {
            String song = searchField.getText().trim();

            MusicPlaylist.Node found = playlist.searchSong(song);

            if (found != null) {
                playlist.playSong(song);
                updateNowPlaying();
                showMessage("Song found: " + found.song);
            } else {
                showMessage("Song not found.");
            }
        }

        // Sets the selected song as the currently playing song.
        else if (command.equals("Play")) {
            MusicPlaylist.Node selected = getSelectedNode();

            if (selected == null) {
                showMessage("Select a song to play.");
            } else {
                playlist.playSong(selected.song);
                updateNowPlaying();
            }
        }

        // Moves playback to the next song.
        else if (command.equals("Next")) {
            if (playlist.getCount() > 0) {
                playlist.nextSong();
                updateNowPlaying();
            }
        }

        // Moves playback to the previous song.
        else if (command.equals("Previous")) {
            if (playlist.getCount() > 0) {
                playlist.previousSong();
                updateNowPlaying();
            }
        }

        // Sorts the playlist alphabetically by song name.
        else if (command.equals("Sort Playlist")) {
            playlist.sortPlaylist();
            refreshPlaylist();
            showMessage("Playlist sorted alphabetically.");
        }

        // Changes the display direction without changing the linked list.
        else if (command.equals("Reverse View")) {
            reverseView = !reverseView;
            refreshPlaylist();
        }

        // Removes all songs from the playlist.
        else if (command.equals("Clear Playlist")) {
            playlist.clearPlaylist();
            refreshPlaylist();
        }
    }

    // Displays a message in a separate dialog window.
    void showMessage(String message) {
        Dialog dialog = new Dialog(this, "Message", true);
        dialog.setLayout(new BorderLayout(10, 10));

        Label label = new Label(message, Label.CENTER);
        Button okButton = new Button("OK");

        okButton.addActionListener(e -> dialog.dispose());

        dialog.add(label, BorderLayout.CENTER);
        dialog.add(okButton, BorderLayout.SOUTH);

        dialog.setSize(300, 120);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Main method starts the Music Playlist Manager application.
    public static void main(String[] args) {
        new PlaylistGUI();
    }
}