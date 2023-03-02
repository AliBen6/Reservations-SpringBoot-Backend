package be.iccbxl.pid.reservationsspringboot.Controller;

import be.iccbxl.pid.reservationsspringboot.Model.Artist;
import be.iccbxl.pid.reservationsspringboot.Service.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/V1/artists")
@Validated
public class ArtistController {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getAllArtist() {
        LOG.info("getAllArtist request");
        
        return ResponseEntity.ok(artistService.getAllArtist());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable("id") @Min(1) long id) {
        LOG.info("getArtistById request for artist id : {}",id);
        
        return ResponseEntity.of(artistService.getArtistById(id));
    }

    @PostMapping
    public ResponseEntity<Artist> addArtist(@Valid @RequestBody Artist artist) {
        LOG.info("addArtist request for artist : {}",artist);
        
        Artist createdArtist = artistService.addArtist(artist);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdArtist.getId()).toUri();

        return ResponseEntity.created(location).body(createdArtist);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Artist> updateArtistById(@PathVariable("id") @Min(1) long id,
                                                   @Valid @NonNull @RequestBody Artist artist) {
        LOG.info("updateArtistById request for artist id : {} with content : {}",id,artist);
        
        Artist artistToUpdate = artistService.getArtistById(id).get();
        artistToUpdate.setFirstname(artist.getFirstname());
        artistToUpdate.setLastname(artist.getLastname());
        artistService.updateArtist(artistToUpdate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();

        return ResponseEntity.created(location).body(artistToUpdate);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable("id") @Min(1) long id) {
        LOG.info("deleteArtistById request for artist id : {}",id);
        
        artistService.deleteArtistById(id);
        
        return ResponseEntity.ok().build();
    }
}