package cz.muni.fi.pv243.musiclib.service;

import cz.muni.fi.pv243.musiclib.dao.GenreDao;
import cz.muni.fi.pv243.musiclib.entity.Genre;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
@Stateless
public class GenreServiceImpl implements GenreService {

    @Inject
    private GenreDao genreDAO;

    @Inject
    private SongService songService;

    @Override
    public Genre create(Genre genre) {
        return genreDAO.create(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return genreDAO.update(genre);
    }

    @Override
    public void remove(Genre genre) {
        //song cannot exist without associated genre
        songService.searchByGenre(genre)
                .forEach(song -> songService.remove(song));

        genreDAO.remove(genre.getId());
    }

    @Override
    public Genre findById(Long id) {
        return genreDAO.find(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreDAO.findAll();
    }

    @Override
    public List<Genre> searchByTitle(String title) {
        return genreDAO.searchByTitle(title);
    }
}
