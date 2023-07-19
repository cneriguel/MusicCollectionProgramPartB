/**VERSION 2
 * Description: This is a Music Collection program which allows users to create an album and
 * add new songs with details such as artist name, title, genre, and song duration.
 * This program also allows the user to add or delete songs and albums.
 * The user can only create 3 albums and each album can only have upto 4 songs.
 * Author: Liz Tulio & Charlene Eriguel
 * Student Number: C3250794 - C3383599
 * Created: 23 Mar 2023
 * Modified: 27/4/23 
 * Submitted: 5/6/23
**/
import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;
 
public class SongCollection
{   
        // Instance variables
        private Album[] albums = new Album[4];
        private Song [] songs;
        private int MAX_ALBUM = 4;
        private int songCount = 5;
        private int totalAlbums;


        //Album a = new Album(); //creates an object of the album class
        int option;
        Scanner console = new Scanner(System.in); 
        
        
        public void run() // Controls the flow of program
        {        
            //Displays main menu containing program functionalities 
            do
                {
                System.out.println("\n          ﾟ ♬˚₊ ♪｡⋆♫ Select and enter ♫⋆｡♪ ₊˚♬ ﾟ\n");
                System.out.println("1 - Create Album");
                System.out.println("2 - Enter a new song into an album");
                System.out.println("3 - Show songs using name");
                System.out.println("4 - Show all songs from an album ");
                System.out.println("5 - Show all albums ");
                System.out.println("6 - Show songs using duration ");
                System.out.println("7 - Show songs using genre");
                System.out.println("8 - Delete an album");
                System.out.println("9 - Delete a song from an album ");
                //System.out.println("10- Input album and songs from external file");
                System.out.println("0 - Exit");
                //Displays error message when user enters invalid input
                while(console.hasNextInt() != true){
                    System.out.println("\n Note: Please enter a menu number only... ");
                    console.next(); 
                    backToMenu();
                }
                option = console.nextInt();
            
                //Calls the appropriate method based on user selection 
                switch(option)
                {   
                    case 1: addAlbum();
                    break;
                    case 2: addSongToAlbum(); 
                    break;
                    case 3: searchName();
                    break;
                    case 4: viewSongList();
                    break;
                    case 5: printAlbum();
                    break;
                    case 6: searchDuration();
                    break;
                    case 7: searchGenre();
                    break;
                    case 8: deleteAlbum();
                    break;
                    case 9: deleteSong();          
                    break;
                    /**Possible additional feature:
                    case 10: addExternalFile(); 
                    break;**/
                    case 0: break;
                    default: System.out.println("Invalid Selection"); 
                    backToMenu();
                    return;
                }
                    break;
                }
            while (option!=0);
        }        
        //This method allows user to create new album
        public void addAlbum() {
            Scanner console = new Scanner(System.in);
            Album a = new Album();

            System.out.println("ﾟ ♬˚₊ ♪｡⋆♫ How do you want to add the album and songs? ♫⋆｡♪ ₊˚♬ ﾟ");
            System.out.println("1 - Add manually");
            System.out.println("2 - Add from an external file");

            int inputMethod = console.nextInt();
            console.nextLine(); 
            

            switch (inputMethod) {
                case 1:
                    // Allow user to add manually
                    System.out.print("ﾟ ♬˚₊ ♪｡⋆♫ Enter Album name: ♫⋆｡♪ ₊˚♬ ﾟ\n");
                    String name = console.nextLine();

                    // Checks if album already exists
                    for (Album album : albums) {
                        if (album != null && album.getName().equals(name)) {
                            System.out.println("The album already exists. Please try again");
                            backToMenu();
                            return;
                        }
                    }

                    a.setName(name);

                    // Finds an empty slot in the albums array to add the new album
                    for (int i = 0; i < MAX_ALBUM; i++) {
                        if (albums[i] == null) {
                            albums[i] = a;
                            System.out.println("Album: " + a.getName() + " has been added\n");
                            backToMenu();
                            return;
                        }
                    }

                    System.out.println("You can't create a new album: Max limit reached\n");
                    backToMenu();
                    break;
                case 2:
                    // Allow user to add from external file
                    addExternalFile();
                    break;
                default:
                System.out.println("Invalid selection");
                backToMenu();
                break;
            }
        }

        //backToMenu method asks the user if they want to go back to menu or continue
        public void backToMenu()
        {
            System.out.println("\n          ﾟ ♬˚₊ ♪｡⋆♫♫⋆｡♪ ₊˚♬ﾟ♬˚₊ ♪｡⋆♫♫⋆｡♪ ₊˚♬ ﾟ\n");
            run();
        }

        //This method allows user to add song into an album
        public void addSongToAlbum()
        {
            //checks if there are any existing albums
                if(checkallAlbum())
                {
                    System.out.println("**********************************");
                    System.out.println("**    Create an album first     **");
                    System.out.println("**********************************");
                    System.out.println("\n");
                    backToMenu();
                    return;
                }
                //if there is an album, proceed to add song
                else {                 
                    addSong(); //calls the method to add song
                }
        }

        //This method allows user to enter song and its details: title, artist, genre, duration 
        public void addSong() {
            Scanner console = new Scanner(System.in);

            System.out.print("\n          ﾟ ♬˚₊ ♪｡⋆♫ Enter song title:  ♫⋆｡♪ ₊˚♬ ﾟ \n");
            String title = console.nextLine();

            // Check if the song already exists in any album
            for (int i = 0; i < MAX_ALBUM; i++) {
                Album album = albums[i];
                if (album != null && album.checkSongExists(title)) {
                    System.out.println("The song already exists in the album: " + album.getName());
                    System.out.println("If you would like to continue press 1, if not press 2");
                    int inputNext = console.nextInt();
                    console.nextLine(); 
                    switch (inputNext) {
                    case 1:
                        addSong2(title);
                        break;
                    case 2:
                        backToMenu();
                        break;
                default:
                    System.out.println("Invalid selection");
                    backToMenu();
                    return;
            }
            return;
                }else{addSong2(title);}
            }
        }
        
        //Allow user to continue entering song details
        public void addSong2(String title) {
            Scanner console = new Scanner(System.in);
            //String title;
            System.out.print("\n          ﾟ ♬˚₊ ♪｡⋆♫ Enter artist name:  ♫⋆｡♪ ₊˚♬ ﾟ \n");
            String artist = console.nextLine();

            System.out.print("\n          ﾟ ♬˚₊ ♪｡⋆♫ Enter song genre:  ♫⋆｡♪ ₊˚♬ ﾟ \n");
            System.out.print("\n         Type: Rock | Pop | Hip-hop | Bossa nova   \n");
            String genre = console.nextLine();
            //Prints an error message when user enters wrong genre 
                if (!genre.equals("Rock") && !genre.equals("Pop") && !genre.equals("Hip-hop") && !genre.equals("Bossa nova")) {
                        System.out.println(" \n Invalid genre.\n Only choose the Type given ");
                        System.out.print("\n          ﾟ ♬˚₊ ♪｡⋆♫ Enter the exact song genre selection:  ♫⋆｡♪ ₊˚♬ ﾟ \n");
                        genre = console.nextLine();
                        
                    }

            System.out.print("\n          ﾟ ♬˚₊ ♪｡⋆♫ Enter song duration in seconds:  ♫⋆｡♪ ₊˚♬ ﾟ \n");
            //Prints an error message whenuser enters invalid input 
                while(console.hasNextInt() != true){
                    System.out.println("\n Note: Please enter the number of seconds only... ");
                    console.next(); 
                }
            double duration = console.nextDouble();
            console.nextLine();
            
            //Prints an error message if new song reached max duration
                if (duration > 1200){
                    System.out.println("\n Can't add song. Duration is over the limit \n");
                }
                else {
                    
                    // Find an album to add the song
                    System.out.print("\n ﾟ ♬˚₊ ♪｡⋆♫ Name album you would like to add the song in?  ♫⋆｡♪ ₊˚♬ ﾟ \n");
                    String albumName = console.nextLine();
                    Album album = null;
        
                    for (int i = 0; i < MAX_ALBUM; i++) {
                        if (albums[i] != null && albums[i].getName().equals(albumName)) {
                            album = albums[i];
                            break;
                        }
                    }
        
                    if (album == null) {
                        System.out.println("Album not found.");
                        backToMenu();
                        return;
                    }
        
                    album.addSong(title,artist, genre, duration);
                       // album.addSong(title);
                     //   album.addSong2(artist, genre, duration);
                    backToMenu();
                    return;
                }
            }


        // Lists all songs and its details using a specific name
        public void searchName() {
            boolean albumExists = false;
            for (int i = 0; i < MAX_ALBUM; i++) {
                Album album = albums[i];
                if (album != null) {
                    albumExists = true;
                    break;
                }
            }

                if (albumExists) {
                    Scanner console = new Scanner(System.in);
                    System.out.println("Enter the name of the song:");
                    String title = console.nextLine();

                    for (int i = 0; i < MAX_ALBUM; i++) {
                        Album album = albums[i];
                        if (album != null) {
                            String songsFound = album.getSpecificSong(title);
                            if (!songsFound.equals("")) {
                                System.out.println(songsFound);
                            }
                        }
                    }
                }
            backToMenu();
        }

        //Checks if song exists
        public boolean checkSongExists(String title, Album album) {
            for (Song song : album.getSongs()) {
                if (song != null && song.getTitle().equals(title)) {
                    return true;
                }
            }
            return false;
        }


        //Allows user to request a list of all songs (and the details of each song) from an album.
        public void viewSongList()
        {
            int albumNum; // variable for selecting album number
            
            System.out.println("\n          ♬˚₊♪⋆♫ Select an album ♫⋆♪₊˚♬\n");
            
            // Check if album exists and display error message if not
            for (int i = 0; i < MAX_ALBUM; i++) {
                if (albums[i] == null) {
                    System.out.println((i + 1) + ". No album created");
                } else {
                    System.out.println((i + 1) + ". " + albums[i].getName());
                }
            }
            
            // Check if user entered a valid album number
            while (!console.hasNextInt()) {
                System.out.println("\n Note: Please enter the album number only... ");
                console.next(); // consume the invalid input
            }
            albumNum = console.nextInt();
            
            // If album exists, print song list from the selected album
            if (albumNum >= 1 && albumNum <= MAX_ALBUM) {
                Album selectedAlbum = albums[albumNum - 1];
                if (selectedAlbum != null) {
                    System.out.println("Album " + selectedAlbum.getName() + " contains:");
                    selectedAlbum.getSongList();
                }
            }
            
            backToMenu();
            
        }

        //This method prints list of all albums
        public void printAlbum()
        {
            System.out.println("\n          ♬˚₊♪⋆♫ Here's a list of all albums ♫⋆♪₊˚♬\n");
    
            // Check if album exists and print error message if not
            // Display list of songs if album exists
            for (int i = 0; i < MAX_ALBUM; i++) {
                if (albums[i] == null) {
                    System.out.println("Album " + (i + 1) + " does not exist yet...");
                } else {
                    System.out.println(albums[i].getName());
                    albums[i].getSongList();
                }
            }
              backToMenu();
        }


        //This method allows user to search a song within certain duration
        public void searchDuration() {
            double duration;
            
            System.out.println("\n          ♬˚₊♪⋆♫ What is the song duration in seconds? ♫⋆♪₊˚♬\n");
            
            // Consumes invalid input
            while (!console.hasNextDouble()) {
                System.out.println("\n Note: Please enter the number of seconds only...\n");
                console.next();
            }
    
            duration = console.nextDouble();
    
            System.out.println("\n Here is a list of all songs that are under the duration of: " + duration + " seconds \n");
    
            // Checks if album exists and if so it will print album name and any song within certain duration
            for (int i = 0; i < MAX_ALBUM; i++) {
                Album album = albums[i];
                if (album == null) {
                    System.out.println("\n ALBUM " + (i + 1) + " is not available");
                } else {
                    System.out.println("\n ALBUM: " + album.getName());
                    album.searchDuration(duration);
                }
            }
    
            backToMenu();
        }   

        

        //This method allows user to search genre and prints list of all songs in the genre
        public void searchGenre()
        {
            Scanner console = new Scanner(System.in);
            System.out.println("\nPlease enter the genre:");
            String genreSearched = console.nextLine();

            // Call the searchGenre method with the genreSearched parameter
            searchGenre(genreSearched);
        }
        
        public void searchGenre(String genreSearched) {
            boolean songsFound = false;
    
            for (int i = 0; i < MAX_ALBUM; i++) {
                Album album = albums[i];
                if (album != null) {
                    System.out.println("\nALBUM: " + album.getName());
                    Song[] songs = album.getSongs();
                    for (Song song : songs) {
                        if (song != null && song.getGenre().equalsIgnoreCase(genreSearched)) {
                            System.out.println(song.getTitle());
                            songsFound = true;
                        }
                    }
                    }
                }
    
                    if (!songsFound) {
                        System.out.println("No songs found in the genre: " + genreSearched);
                    }
            backToMenu();
        }


        //Allows user to delete an existing album
        public void deleteAlbum() {
            System.out.println("\nList of current albums:\n");

            boolean albumExists = false;

            // Iterate over the albums array to display the existing albums
            for (int i = 0; i < MAX_ALBUM; i++) {
                if (albums[i] != null) {
                    albumExists = true;
                    System.out.println("[" + (i + 1) + "] " + albums[i].getName());
                }
            }

            if (!albumExists) {
                System.out.println("No albums exist to delete.");
                backToMenu();
                return;
            }

            System.out.println("\nWhich album would you like to delete?");

            // Read the user's input for the album index to delete
            int albumIndex = console.nextInt();

            // Validate the input album index
            if (albumIndex >= 1 && albumIndex <= MAX_ALBUM) {
                // Subtract 1 from the album index to align with array indices
                albumIndex--;

                // Delete the album at the specified index
                albums[albumIndex] = null;

                System.out.println("Album deleted successfully!");
            } else {
                System.out.println("Invalid album index.");
            }

            backToMenu();
        }




        // This method deletes a song from a selected album
        public void deleteSong() {
            // Check if there are any existing albums and if not, prompt the user to create one
            if (checkallAlbum()) {
                System.out.println("**********************************");
                System.out.println("**    Create an album first     **");
                System.out.println("**********************************");
                System.out.println("\n");
                backToMenu();
                return;
            }

            System.out.println("\nList of current albums:\n");
            boolean albumExists = false;

            // Iterate over the albums array to display the existing albums
            for (int i = 0; i < MAX_ALBUM; i++) {
                if (albums[i] != null) {
                    albumExists = true;
                    System.out.println("[" + (i + 1) + "] " + albums[i].getName());
                }
            }

            if (!albumExists) {
                System.out.println("No albums exist to delete.");
                backToMenu();
                return;
            }

            System.out.println("\nSelect an album to remove songs:");
            int option = console.nextInt();

            // Validate the input album index
            if (option >= 1 && option <= albums.length) {
                // Subtract 1 from the album index to align with array indices
                int albumIndex = option - 1;

                Album selectedAlbum = albums[albumIndex];

                // Check if the selected album exists
                if (selectedAlbum != null) {
                    int numSongs = selectedAlbum.getNumSongs();

                    if (numSongs == 0) {
                        System.out.println("\nThis album does not currently contain any songs.\n");
                        backToMenu();
                        return;
                    }

                    System.out.println("\nThe current songs in " + selectedAlbum.getName() + " are as follows:\n");
                    selectedAlbum.viewAllSongs();

                    boolean validSongIndex = false;
                    while (!validSongIndex) {
                        System.out.println("\nWhich song would you like to remove?");
                        int songIndex = console.nextInt();

                        // Validate the input song index
                        // Validate the input song index
                        if (songIndex >= 1 && songIndex <= numSongs) {
                            // Subtract 1 from the song index to align with array indices
                            int songArrayIndex = songIndex - 1;

                            // Remove the selected song from the album
                            selectedAlbum.removeSong(songArrayIndex);
                            backToMenu();
                            return;
                        }
                    }
                }
            }
        }

        //Checks if albums are null
        public boolean checkallAlbum() {
            for (Album album : albums) {
                if (album != null) {
                    return false;
                }
            }
            return true;
        }

        //Allows user to input external file to add albums and songs
        public void addExternalFile() {
                totalAlbums = 0;
                Scanner console = new Scanner(System.in);
                System.out.println("Enter the name of the file:");
                String fileName = console.nextLine();

                try {
                    File file = new File(fileName);
                    Scanner reader = new Scanner(file);

                    Album currentAlbum = null;

                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();

                        if (line.startsWith("Album")) {
                            String albumName = line.substring(line.indexOf(" ") + 1);
                            currentAlbum = new Album();
                            currentAlbum.setName(albumName);
                            albums[totalAlbums] = currentAlbum;  // Use totalAlbums instead of totalAlbums - 1
                            totalAlbums++;  // Move this line here
                        } else if (line.equals("Songs")) {
                            // Skip the "Songs" line
                        } else if (line.startsWith("Name")) {
                            String songName = line.substring(line.indexOf(" ") + 1);
                            currentAlbum.getSongs()[currentAlbum.getNumSongs()] = new Song();
                            currentAlbum.getSongs()[currentAlbum.getNumSongs()].setTitle(songName);
                        } else if (line.startsWith("Artist")) {
                            String artistName = line.substring(line.indexOf(" ") + 1);
                            currentAlbum.getSongs()[currentAlbum.getNumSongs()].setArtist(artistName);
                        } else if (line.startsWith("Duration")) {
                            int duration = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
                            currentAlbum.getSongs()[currentAlbum.getNumSongs()].setDuration(duration);
                        } else if (line.startsWith("Genre")) {
                            String genre = line.substring(line.indexOf(" ") + 1);
                            currentAlbum.getSongs()[currentAlbum.getNumSongs()].setGenre(genre);
                            currentAlbum.incrementNumSongs();
                        }
                    }

                    reader.close();

                    System.out.println("File added successfully.");
                    } catch (FileNotFoundException e) {
                    System.out.println("File not found: " + e.getMessage());
            }
            backToMenu();
            return;
            }

            //Delete if not needed
            public void getSongDetails() {
                for (int i = 0; i < songCount; i++) {
                Song song = songs[i];
                    if (song != null) {
                        System.out.println("Song " + (i + 1) + " Details:");
                        System.out.println("Title: " + song.getTitle());
                        System.out.println("Artist: " + song.getArtist());
                        System.out.println("Genre: " + song.getGenre());
                        System.out.println("Duration: " + song.getDuration());
                        System.out.println();
                    }
                }
            }
        

            //Check if song exists in an album
            public boolean checkAlbumExists(String title) {
            for (Song song : songs) {
                if (song != null && song.getTitle().equals(title)) {
                    return true;
                }
            }
            return false;
            }


            public static void main(String[] args) 
            {
                Scanner console = new Scanner(System.in);
                
                SongCollection sg = new SongCollection();
                sg.run();
                    
            
            }

}
        