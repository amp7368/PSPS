package apple.discord.psps.database.passcode;

import io.ebean.Model;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passcode")
public class DPasscode extends Model {

    @Id
    private UUID uuid;

    @Column(nullable = false)
    private Timestamp createdAt;
    @Column
    private Timestamp revokedAt;
    @Column
    private String passcode;

    public DPasscode(String passcode) {
        this.passcode = passcode;
        this.createdAt = Timestamp.from(Instant.now());
    }

    public String getPasscode() {
        return this.passcode;
    }
}
