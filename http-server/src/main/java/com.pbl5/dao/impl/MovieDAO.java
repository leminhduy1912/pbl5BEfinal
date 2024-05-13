package com.pbl5.dao.impl;

import com.pbl5.dao.GenericDAO;
import com.pbl5.dao.IMovieDAO;
import com.pbl5.dtos.Pagination.MoviePaginationDTO;
import com.pbl5.helpers.TimestampConvert;
import com.pbl5.helpers.mapper.CountMapper;
import com.pbl5.helpers.mapper.MovieMapper;
import com.pbl5.helpers.mapper.PosterMovieMapper;
import com.pbl5.helpers.mapper.UserMapper;
import com.pbl5.models.Movie;
import com.pbl5.utils.constants.Pagination;
import static com.pbl5.utils.constants.Pagination.PER_PAGE;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

public class MovieDAO extends AbstractDAO<Movie> implements IMovieDAO {
    private static final Logger logger = Logger.getLogger("MovieDAO");
    @Override
    public List<Movie> findAllMovieIsShowing() {
        logger.info("Find all movie is showing");
        String sql = "SELECT * FROM movies AS M INNER JOIN kindofmovie AS G ON M.kind_id = G.kind_id  " +
                " WHERE M.active = 1 ";
        return query(sql, new MovieMapper());
    }

    @Override
    public void createMovie(Movie movie, String username) {

        logger.info("Create movie");
        String sql="INSERT INTO movies (movie_id,title,kind_id,release_date," +
                "duration,description,active,poster,createdBy,createdAt,sub,for_age,director,actor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        insert(sql,movie.getId(),movie.getTitle(),movie.getKindId(),TimestampConvert.convert(movie.getReleaseDate())
                ,movie.getDuration(),movie.getDescription(),1,movie.getMoviePoster(),username,movie.getCreatedAt()
                ,movie.getSub(),movie.getForAge(),movie.getDirector(),movie.getActor());
    }

    @Override
    public void updateStatusMovie(String movieId) {
        logger.info("update status movie");
        String sql = "UPDATE movies SET active = 0 WHERE movie_id = ?";
        update(sql, movieId);
    }

    @Override
    public void updateMovie(Movie movie, String username) {
        logger.info("update movie");
        String sql = "UPDATE movies SET actor=?,director=?,sub=?,for_age=?,title = ?," +
                " kind_id = ?, release_date = ?, " +
                "duration = ?, description = ?, active = ?, poster = ?, " +
                "modifiedBy = ?, modifiedAt = ? WHERE movie_id = ?";
        Timestamp releaseDateTimestamp = TimestampConvert.convert(movie.getReleaseDate());
        // Execute the update statement
        update(sql,movie.getActor(),movie.getDirector(),movie.getSub(),movie.getForAge(), movie.getTitle(),
                movie.getKindId(), releaseDateTimestamp,
                movie.getDuration(), movie.getDescription(), movie.getActive(), movie.getMoviePoster(),
                username, movie.getModifiedAt(), movie.getId());
    }

    @Override
    public Movie findOneById(String movieId) {
        logger.info("find one by id movie");
        String sql = "SELECT * FROM movies AS M INNER JOIN kindofmovie AS G ON M.kind_id = G.kind_id  " +
                " WHERE M.movie_id = ? ";

        List<Movie> movies = query(sql, new MovieMapper(), movieId);
        return movies.isEmpty()? null: movies.get(0);
    }

    @Override
    public List<Movie> findAllMovieIsShowing(MoviePaginationDTO pagination) {
        String sql = "SELECT * FROM movies AS M INNER JOIN kindofmovie AS G ON M.kind_id = G.kind_id  " +
                " ORDER BY M.createdAt DESC  LIMIT "+ PER_PAGE+" OFFSET " + (pagination.getPage() - 1) * PER_PAGE;
        System.out.println("sql :"+sql);
        return query(sql, new MovieMapper());
    }

    @Override
    public List<Movie> findImageOfAllMovieIsShowing() {
        String sql = "SELECT poster,movie_id FROM movies WHERE active = 1 ";
        return query(sql, new PosterMovieMapper());
    }

    @Override
    public Integer countAllMovie() {
        String sql = "SELECT COUNT(*) as total FROM movies ";
        List<Integer> pages = query(sql, new CountMapper());
        return pages.isEmpty() ? null : pages.get(0);
    }

    @Override
    public List<Movie> findAllMovieShowing() {
        String sql="SELECT * FROM movies WHERE active=1";
        return query(sql,null);
    }

}
