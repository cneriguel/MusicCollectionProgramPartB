/**VERSION 2
 * Description: The Java class - Album represents the album that contains music. There can only be 3 albums and 4 songs inside each album. 
 * Author: Liz Tulio & Charlene Eriguel
 * Student Number: C3250794 - C3383599
 * Created: 23 Mar 2023
 * Modified: 27/4/23
 * Submitted: 5/6/23
 **/

public class Album
{   
    //Private fields
    private Song [] songs;
    private String name;
    private final int MAX_TIME = 1200, MAX_SONGS = 5; //20 minutes and 5 songs max
    private double totalTime;
    private double newTotalTime;
    private int totalSongs;
    private static int totalAlbums;
    
    public Album()
    {
        //Initialises name of album into empty string
        this.name = " ";
        //Creates four instances of song class
        this.songs = new Song[MAX_SONGS];
        //Initiales totalTime to zero
        this.totalTime = 0;
        //Initialises totalSongs to zero
        this.totalSongs = 0;
        //Initialises totalAlbums to zero
        totalAlbums = 0;
        // Increment operator
        totalAlbums++;
    }
    
    //Returns array of objects of type Song
    public Song[] getSongs() {
        return songs;
    }

    //Set album name
    public void setName(String albumName)
    {
        name = albumName;    
    }
    
    //Get album name
    public String getName()
    {
        return name;
    }

    //Represents total number of songs
    public int getNumSongs() {
        return totalSongs;
    }

     // Increment totalSongs
    public void incrementNumSongs() {
        totalSongs++;
    }

    // Static method to get the total number of albums
    public static int getTotalAlbums() {
        return totalAlbums;
    }

    // Add a song to the song collection
    public void addSong(String title, String artist, String genre, double duration) {
        if (totalSongs < MAX_SONGS) {
            Song song = new Song(title, artist, genre, duration);
            songs[totalSongs] = song;
            totalSongs++;
            System.out.println("Song added successfully!");
        } else {
            System.out.println("Song collection is full. Cannot add more songs.");
        }
    }

    //Search Name
    public String getSpecificSong(String title) {
        String returnSong = "";
        for (int i = 0; i < totalSongs; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                returnSong += "Title: " + songs[i].getTitle() + "\n";
                returnSong += "Artist: " + songs[i].getArtist() + "\n";
                returnSong += "Genre: " + songs[i].getGenre() + "\n";
                returnSong += "Duration: " + songs[i].getDuration() + " seconds\n";
                returnSong += "\n";
            }
        }
        if (returnSong.equals("")) {
            returnSong = "Songs found under the name: " + title;
        }
        return returnSong;
    }

    //Deletes song at specific index
    public void deleteSong(int index) {
        if (index >= 0 && index < totalSongs) {
            Song deletedSong = songs[index];
            removeSong(index);
            System.out.println("Song " + deletedSong.getTitle() + " has been removed");
        } 
    }

    // Remove song details by index
    public void removeSong(int index) {
        if (index >= 0 && index < totalSongs) {
            for (int i = index; i < totalSongs - 1; i++) {
                songs[i] = songs[i + 1];
            }
            songs[totalSongs - 1] = null;
            totalSongs--;
            System.out.println("Song has been removed");
        } else {
            System.out.println("Invalid song index");
        }
    }

    // View all songs in the album
    public void viewAllSongs() {
        if (totalSongs == 0) {
            System.out.println("No songs available");
        } else {
            System.out.println("All Songs:");
            for (int i = 0; i < totalSongs; i++) {
                System.out.println("[" + (i + 1) + "] SONG NAME: " + songs[i].getTitle());
            }
        }
    }

    // Delete album song by index
    public void deleteAlbumSong(int index) {
        if (index >= 1 && index <= totalSongs) {
            Song deletedSong = songs[index - 1];
            if (deletedSong == null) {
                System.out.println("Song doesn't exist...");
            } else {
                System.out.println(deletedSong.getTitle() + " has been removed");
                removeSong(index);
            }
        } else {
            System.out.println("Invalid song index");
        }
    }

    //Checks if song with a specific title exists
    public boolean checkSongExists(String title) {
        for (Song song : songs) {
            if (song != null && song.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    //Searches for song less than duration in songs
    public void searchDuration(double minutes) {
        boolean songsFound = false;
        
        System.out.println("\nSongs with duration less than " + minutes + " seconds:\n");

        for (int i = 0; i < MAX_SONGS; i++) {
            Song song = songs[i];
            if (song != null && song.getDuration() < minutes) {
                System.out.println((i + 1) + ". " + song.getTitle());
                songsFound = true;
            }
        }

        if (!songsFound) {
            System.out.println("No songs found.");
        }
    }
    
    //Checks if there is any songs with same name
    public static boolean songsWithSameNameExist(Song[] songs) {
        for (int i = 0; i < songs.length - 1; i++) {
            String currentName = songs[i].getTitle();
            for (int j = i + 1; j < songs.length; j++) {
                if (currentName.equals(songs[j].getTitle())) {
                    return true; // Songs with the same name exist
                }
            }
        }
        return false; // No songs with the same name
    }
    
    // Display song list with details
    public void getSongList() {
            if (totalSongs == 0) {
                System.out.println("No songs available");
            } else {
                System.out.println("Song List:");
                for (int i = 0; i < totalSongs; i++) {
                    Song song = songs[i];
                    if (song != null) {
                        System.out.println("SONG NAME: " + song.getTitle());
                        System.out.println("ARTIST: " + song.getArtist());
                        System.out.println("GENRE: " + song.getGenre());
                        System.out.println("DURATION: " + song.getDuration() + " seconds");
                        System.out.println();
                    }
                }
            }
    }
}