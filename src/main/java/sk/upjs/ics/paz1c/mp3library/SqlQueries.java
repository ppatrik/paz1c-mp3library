
package sk.upjs.ics.paz1c.mp3library;

class SqlQueries {

    public static class Song {

        public static String DELETE
                = "DELETE FROM songs WHERE song_id = ?";
        public static String UPDATE
                = "UPDATE songs SET title = :title, artist_id = :artist_id, "
                + "album_id = :album_id, year = :year, "
                + "track = :track, disc = :disc, genre_id = :genre_id, "
                + "rating = :rating, file_path = :file_path, cover = :cover, "
                + "quality = :quality, format = :format"
                + " WHERE song_id = :song_id";
        public static String INSERT
                = "INSERT INTO songs VALUES((SELECT IFNULL(MAX(song_id), 0) FROM songs) + 1, "
                + ":title, :artist_id, :album_id, :year, :track, :disc, "
                + ":genre_id, :rating, :file_path, :cover, :quality, :format);";

        public static String SELECT = "" +
                "songs.song_id, " +
                "songs.title song_title, " +
                "songs.year song_year, " +
                "songs.track song_track, " +
                "songs.disc song_disc, " +
                "songs.rating song_rating, " +
                "songs.file_path song_file_path, " +
                "songs.quality song_quality, " +
                "songs.format song_format, " +
                Artist.SELECT + ", " +
                Album.SELECT + ", "  +
                Genre.SELECT;

        public static String FIND_ALL
                = "SELECT " + SELECT + " " +
                "FROM songs " +
                "JOIN artists ON artists.artist_id = songs.artist_id " +
                "JOIN albums ON albums.album_id = songs.album_id " +
                "JOIN genres ON genres.genre_id = songs.genre_id ";
        public static String FIND_ALL_BY_TITLE
                = FIND_ALL + " WHERE songs.title LIKE '%?%'";
        public static String FIND_ALL_BY_ARTIST
                = FIND_ALL + " WHERE songs.artist_id = ?";
        public static String FIND_ALL_BY_ALBUM
                = FIND_ALL + " WHERE songs.album_id = ?";
        public static String FIND_ALL_BY_GENRE
                = FIND_ALL + " WHERE songs.genre_id = ?";
        public static String FIND_ONE_BY_ID
                = FIND_ALL + " WHERE songs.song_id = ?";
        public static String FIND_ONE_BY_FILE_PATH
                = FIND_ALL + " WHERE songs.file_path = ? LIMIT 1";
    }

    public static class Album {

        public static String SELECT = "" +
                "albums.album_id, " +
                "albums.name album_name, " +
                "albums.tracks album_tracks, " +
                "albums.discs album_discs";

        public static String DELETE
                = "DELETE FROM albums WHERE album_id = ?";
        public static String UPDATE
                = "UPDATE albums SET name = :name, tracks = :tracks, discs = :discs WHERE album_id = :album_id";
        public static String INSERT
                = "INSERT INTO albums VALUES((SELECT IFNULL(MAX(album_id), 0) FROM albums)+1, :name, :tracks, :discs);";
        public static String FIND_ALL
                = "SELECT " + SELECT + " FROM albums ORDER BY lower(name)";
        public static String FIND_ONE_BY_ID
                = "SELECT " + SELECT + " FROM albums WHERE album_id = ?";
        public static String FIND_ONE_BY_NAME
                = "SELECT " + SELECT + " FROM albums WHERE name = ? LIMIT 1";
    }

    public static class Artist {

        public static String SELECT = "" +
                "artists.artist_id, " +
                "artists.name artist_name, " +
                "artists.wiki artist_wiki";

        public static String DELETE
                = "DELETE FROM artists WHERE artist_id = ?";
        public static String UPDATE
                = "UPDATE artists SET name = :name, wiki = :wiki WHERE artist_id = :artist_id";
        public static String INSERT
                = "INSERT INTO artists VALUES((SELECT IFNULL(MAX(artist_id), 0) FROM artists)+1, :name, :wiki);";
        public static String FIND_ALL
                = "SELECT " + SELECT + " FROM artists ORDER BY lower(name)";
        public static String FIND_ONE_BY_ID
                = "SELECT " + SELECT + " FROM artists WHERE artist_id = ?";
        public static String FIND_ONE_BY_NAME
                = "SELECT " + SELECT + " FROM artists WHERE name = ? LIMIT 1";
    }

    public static class Genre {

        public static String SELECT = "" +
                "genres.genre_id, " +
                "genres.name genre_name";

        public static String DELETE
                = "DELETE FROM genres WHERE genre_id = ?";
        public static String UPDATE
                = "UPDATE genres SET name = :name WHERE genre_id = :genre_id";
        public static String INSERT
                = "INSERT INTO genres  VALUES((SELECT IFNULL(MAX(genre_id), 0) FROM genres)+1, :name);";
        public static String FIND_ALL
                = "SELECT " + SELECT + " FROM genres ORDER BY lower(name)";
        public static String FIND_ONE_BY_ID
                = "SELECT " + SELECT + " FROM genres WHERE genre_id = ?";
        public static String FIND_ONE_BY_NAME
                = "SELECT " + SELECT + " FROM genres WHERE name = ? LIMIT 1";
    }
}
