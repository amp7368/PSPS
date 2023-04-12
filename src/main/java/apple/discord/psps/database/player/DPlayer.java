package apple.discord.psps.database.player;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class DPlayer {

    @Id
    public UUID id;
    @Column
    public String username;
    @Column
    public String displayName;

    public DPlayer(UUID id, String username, String displayName) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
    }
}
