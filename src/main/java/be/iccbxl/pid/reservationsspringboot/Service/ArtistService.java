package be.iccbxl.pid.reservationsspringboot.Service;

import be.iccbxl.pid.reservationsspringboot.Exception.ResourceNotFoundException;
import be.iccbxl.pid.reservationsspringboot.Model.Artist;
import be.iccbxl.pid.reservationsspringboot.Repository.ArtistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
@Validated
public class ArtistService {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistService.class);

    private final ArtistRepository artistRepository;

    /*
     * private ArtistDaoProducer artistDaoProducer;
     */

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    // @Cacheable("Artist")
    public Optional<Artist> getArtistById(long id) {
        /*
         * try { Thread.sleep(2000); } catch (InterruptedException e) {
         * e.printStackTrace(); }
         */
        return artistRepository.findById(id).map(artist -> artistRepository.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Artist with id:" + id + " Not Found"));
    }

    public Artist addArtist(@Valid Artist artist) {

        Artist artistCreated = artistRepository.save(artist);

        LOG.info("Artist created :" + artistCreated);
        // artistDAOProducer.sendNewArtist(artistInserted).orElse(artist));

        return artistCreated;
    }

    public Artist updateArtist(@Valid Artist artist) {

        return artistRepository.save(artist);
    }

    public long deleteArtistById(long id) {

        if (artistRepository.existsById(id))
            artistRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("Artist with id:" + id + " Not Found");

        return id;
    }
}