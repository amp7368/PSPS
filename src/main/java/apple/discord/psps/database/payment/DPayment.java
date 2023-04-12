package apple.discord.psps.database.payment;

import apple.discord.psps.database.subscription.DSubscription;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class DPayment {

    @Id
    private UUID id;
    @ManyToOne
    private DSubscription subscription;
    @Column
    private int secondsPurchased;
    @Column(nullable = false)
    private int payment;
}
