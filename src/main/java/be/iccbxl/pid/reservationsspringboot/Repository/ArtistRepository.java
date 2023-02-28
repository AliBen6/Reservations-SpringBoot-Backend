package be.iccbxl.pid.reservationsspringboot.Repository;

import be.iccbxl.pid.reservationsspringboot.Model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
