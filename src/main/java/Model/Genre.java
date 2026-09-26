package Model;

public class Genre {
    private String genreName;
    public Genre(String genreName) {
        setGenreName(genreName);
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
