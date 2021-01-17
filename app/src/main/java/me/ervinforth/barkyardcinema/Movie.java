package me.ervinforth.barkyardcinema;

import java.util.Map;

public class Movie {
    private String id;
    private String title;
    private String rated;
    private String plot;
    private String availability;
    private String poster;
    private String language;
    private String duration;
    private String genre;
    private String releaseDate;
    private String year;

    public Movie() {
    }

    public Movie(String id, String title, String rated, String plot, String availability,
                 String poster, String language, String duration, String genre, String releaseDate,
                 String year) {
        this.id = id;
        this.title = title;
        this.rated = rated;
        this.plot = plot;
        this.availability = availability;
        this.poster = poster;
        this.language = language;
        this.duration = duration;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.year = year;
    }

    public Movie(String id, String title, String language, String duration, String genre,
                 String releaseDate) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.duration = duration;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    //I/loadDataFromFireStore: {
// rated=R, duration=97 min, releaseDate= 9 October 2020,  plot=A woman returns .,
// year=2020, genre=Action, language=English, availability=now showing, title=The Doorman,
// poster=https:...384_.jpg
// }
    public static Movie fromMapToMovie(Map<String, Object> map) {
        Movie m = null;

        if (map != null) {

            m = new Movie();

            if (map.containsKey("id")) m.setId(map.get("id").toString());
            if (map.containsKey("rated")) m.setRated(map.get("rated").toString());
            if (map.containsKey("duration")) m.setDuration(map.get("duration").toString());
            if (map.containsKey("releaseDate")) m.setReleaseDate(map.get("releaseDate").toString());
            if (map.containsKey("plot")) m.setPlot(map.get("plot").toString());
            if (map.containsKey("year")) m.setYear(map.get("year").toString());
            if (map.containsKey("genre")) m.setGenre(map.get("genre").toString());
            if (map.containsKey("language")) m.setLanguage(map.get("language").toString());
            if (map.containsKey("availability")) m.setAvailability(map.get("availability").toString());
            if (map.containsKey("title")) m.setTitle(map.get("title").toString());
            if (map.containsKey("poster")) m.setPoster(map.get("poster").toString());
        }

        return m;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rated='" + rated + '\'' +
                ", plot='" + plot + '\'' +
                ", availability='" + availability + '\'' +
                ", poster='" + poster + '\'' +
                ", language='" + language + '\'' +
                ", duration='" + duration + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
