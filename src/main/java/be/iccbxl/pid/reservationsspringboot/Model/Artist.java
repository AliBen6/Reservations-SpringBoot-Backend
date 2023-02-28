package be.iccbxl.pid.reservationsspringboot.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="artists")
@Data
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;

}
