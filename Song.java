/**VERSION 2
 * Description: The Java class - Song represents the song user wants to add in an album. 
 * Author: Liz Tulio & Charlene Eriguel
 * Student Number: C3250794 - C3383599
 * Created: 23 Mar 2023
 * Modified: 27/4/23
 * Submitted: 5/6/23
 **/
public class Song
{
    //Instance variables    
    private String title; 
    private String artist; 
    private String genre; 
    private double duration;
    
    //initialise song object
    public Song()
    {
        title =""; //song title
        artist = ""; //artist name
        genre = ""; //genre
        duration = 0; // length of the song   
    }
    
    //to set value based on user input
    public Song(String newTitle, String newArtist, String newGenre, double newDuration)
    {
       title = newTitle;
       artist = newArtist;
       genre = newGenre;
       duration = newDuration;
    }
    
    //Set value to song title
    public void setTitle(String newTitle)
    {
         title = newTitle;
    }
    
    //returns value to song title
    public String getTitle()
    {
        return title;
    }
    
    //set value to artist name
    public void setArtist(String newArtist)
    {
         artist = newArtist;
    }
    
    //returns value to artist name
    public String getArtist()
    {
        return artist;
    } 
    
    //set value to genre
    public void setGenre(String newGenre)
    {
         genre = newGenre;
    }
    
    //returns value to genre
    public String getGenre()
    {
        return genre;
    } 
    
    //set value to song duration
    public void setDuration(double newDuration)
    {
         duration = newDuration;
    }
    
    //returns value to song duration
    public double getDuration()
    {
        return duration;
    }   
}