/*
 * Copyright 2014 patrik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sk.upjs.ics.paz1c.mp3library;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author patrik
 */
public class SqliteMigration {

    private JdbcTemplate jdbcTemplate;
    private Connection c;
    
    
    public SqliteMigration(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void execute(String sql) throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }

    private void m000_initial_create() {
        try {
            /*==============================================================*/
            /* Table: albums                                                */
            /*==============================================================*/
            execute("create table albums"
                    + "("
                    + "album_id             int not null,"
                    + "artist_id            int,"
                    + "name                 longtext,"
                    + "tracs                int,"
                    + "discs                int,"
                    + "primary key (album_id)"
                    + ");");

            /*==============================================================*/
            /* Table: artists                                               */
            /*==============================================================*/
            execute("create table artists("
                    + "artist_id            int not null,"
                    + "name                 longtext,"
                    + "wiki                 longtext,"
                    + "primary key (artist_id)"
                    + ");");

            /*==============================================================*/
            /* Table: genres                                                */
            /*==============================================================*/
            execute("create table genres("
                    + "genre_id             int not null,"
                    + "name                 longtext,"
                    + "primary key(genre_id)"
                    + ");");

            /*==============================================================*/
            /* Table: songs                                                 */
            /*==============================================================*/
            execute("create table songs("
                    + "song_id              int not null,"
                    + "title                longtext,"
                    + "artist_id            int,"
                    + "album_id             int,"
                    + "year                 int,"
                    + "track                int,"
                    + "disc                 int,"
                    + "genre_id             int,"
                    + "rating               int,"
                    + "file_path            longtext,"
                    + "cover                blob,"
                    + "quality              int,"
                    + "format               char(10),"
                    + "primary key (song_id)"
                    + ");");

            execute("alter table albums add constraint FK_Reference_3 foreign key (artist_id) references artists (artist_id) on delete restrict on update restrict;");
            execute("alter table songs add constraint FK_Reference_1 foreign key (artist_id) references artists (artist_id) on delete restrict on update restrict;");
            execute("alter table songs add constraint FK_Reference_2 foreign key (album_id) references albums (album_id) on delete restrict on update restrict;");
            execute("alter table songs add constraint FK_Reference_4 foreign key (genre_id) references genres (genre_id) on delete restrict on update restrict;");
        } catch (SQLException ex) {

        }

    }

    public void migrate() throws SQLException {
        c = jdbcTemplate.getDataSource().getConnection();
        m000_initial_create();
        c.close();
    }


}
