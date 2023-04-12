package apple.discord.psps.database.subscription;

import apple.discord.psps.database.payment.DPayment;
import apple.discord.psps.database.player.DPlayer;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subscription")
public class DSubscription {

    @Id
    private UUID id;
    @ManyToOne
    @Column(nullable = false)
    private DPlayer player;
    @Column(nullable = false)
    private Timestamp purchasedAt;
    @Column(nullable = false)
    private Timestamp expiration;
    @OneToMany
    private List<DPayment> renewals;
}
