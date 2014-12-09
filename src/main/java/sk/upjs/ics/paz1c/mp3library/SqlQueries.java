package sk.upjs.ics.paz1c.mp3library;

class SqlQueries {

    public static class Song {

        public static String DELETE
                = "DELETE FROM songs WHERE song_id = ?";
        public static String UPDATE
                = "UPDATE songs SET title = :title, artist_id = :artist_id, "
                + "album_id = :album_id, year = :year, "
                + "track = :track, disc = :disc, genre_id = :genre_id, "
                + "rating = :rating, file_path = :file_path, cover = :cover"
                + "quality = :quality, format = :format"
                + " WHERE song_id = :song_id";
        public static String INSERT
                = "INSERT INTO songs VALUES((SELECT IFNULL(MAX(song_id), 0) FROM songs) + 1, "
                + ":title, :artist_id, :album_id, :year, :track, :disc, "
                + ":genre_id, :rating, :file_path, :cover, :quality, :format);";
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
        public static String FIND_ONE_BY_FILE_PATH
                = "SELECT * FROM songs WHERE file_path = ?";
    }

    public static class Album {

        public static String DELETE
                = "DELETE FROM albums WHERE album_id = ?";
        public static String UPDATE
                = "UPDATE albums SET artist_id = :artist_id, name = :name, tracs = :tracs, discs = :discs WHERE id = :id";
        public static String INSERT
                = "INSERT INTO albums VALUES((SELECT IFNULL(MAX(album_id), 0) FROM albums)+1, :artist_id, :name, :tracs, :discs);";
        public static String FIND_ALL
                = "SELECT * FROM albums";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM albums WHERE album_id = ?";
        public static String FIND_ONE_BY_NAME
                = "SELECT * FROM albums WHERE name = ?";
    }

    public static class Artist {

        public static String DELETE
                = "DELETE FROM artists WHERE artist_id = ?";
        public static String UPDATE
                = "UPDATE artists SET name = :name, wiki = :wiki WHERE artist_id = :artist_id";
        public static String INSERT
                = "INSERT INTO artists  VALUES((SELECT IFNULL(MAX(artist_id), 0) FROM artists)+1, :name, :wiki);";
        public static String FIND_ALL
                = "SELECT * FROM artists";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM artists WHERE artist_id = ?";
        public static String FIND_ONE_BY_NAME
                = "SELECT * FROM artists WHERE name = ?";
    }

    public static class Genre {

        public static String DELETE
                = "DELETE FROM genres WHERE genre_id = ?";
        public static String UPDATE
                = "UPDATE genres SET name = :name WHERE genre_id = :genre_id";
        public static String INSERT
                = "INSERT INTO genres  VALUES((SELECT IFNULL(MAX(genre_id), 0) FROM genres)+1, :name);";
        public static String FIND_ALL
                = "SELECT * FROM genres";
        public static String FIND_ONE_BY_ID
                = "SELECT * FROM genres WHERE genre_id = ?";
    }
}
