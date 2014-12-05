package sk.upjs.ics.paz1c.mp3library;

class SqlQueries {

    public static class Song {

        public static String DELETE
                = "DELETE FROM songs WHERE id = ?";
        public static String UPDATE
                = "UPDATE songs SET title = :title, publisher_id = :publisher_id, year = :year WHERE id = :id";
        public static String INSERT
                = "INSERT INTO songs SET title = :title, publisher_id = :publisher_id, year = :year";
        public static String FIND_ALL
                = "SELECT * FROM songs";
        public static String FIND_ALL_BY_TITLE
                = "SELECT * FROM songs WHERE title LIKE '%?%'";
        public static String FIND_ALL_BY_ARTIST
                = "SELECT * FROM songs WHERE artist_id = ?";
        public static String FIND_ALL_BY_ALBUM
                = "SELECT * FROM songs WHERE album_id = ?";
        public static String FIND_ALL_BY_ALBUM_ARTIST
                = "SELECT * FROM songs WHERE album_id IN (SELECT id FROM albums WHERE artist_id = ?)";
        public static String FIND_ALL_BY_GENRE
                = "SELECT * FROM songs WHERE genre_id = ?";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM songs WHERE song_id = ?";
    }

    public static class Album {

        /*public static String DELETE
         = "DELETE FROM songs WHERE id = ?";
         public static String UPDATE
         = "UPDATE songs SET title = :title, publisher_id = :publisher_id, year = :year WHERE id = :id";
         public static String INSERT
         = "INSERT INTO songs SET title = :title, publisher_id = :publisher_id, year = :year";*/
        public static String FIND_ALL
                = "SELECT * FROM albums";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM albums WHERE album_id = ?";
    }

    public static class Artist {

        /*public static String DELETE
         = "DELETE FROM songs WHERE id = ?";
         public static String UPDATE
         = "UPDATE songs SET title = :title, publisher_id = :publisher_id, year = :year WHERE id = :id";
         public static String INSERT
         = "INSERT INTO songs SET title = :title, publisher_id = :publisher_id, year = :year";*/
        public static String FIND_ALL
                = "SELECT * FROM artists";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM artists WHERE artist_id = ?";
    }

    public static class Genre {

        /*public static String DELETE
         = "DELETE FROM songs WHERE id = ?";
         public static String UPDATE
         = "UPDATE songs SET title = :title, publisher_id = :publisher_id, year = :year WHERE id = :id";
         public static String INSERT
         = "INSERT INTO songs SET title = :title, publisher_id = :publisher_id, year = :year";*/
        public static String FIND_ALL
                = "SELECT * FROM genres";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM genres WHERE genre_id = ?";
    }
}
