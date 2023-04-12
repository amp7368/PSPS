package apple.discord.psps.database.passcode;

import apple.discord.psps.database.passcode.query.QDPasscode;
import java.sql.Timestamp;
import java.time.Instant;

public class PasscodeStorage {

    public static void revokeAll() {
        QDPasscode a = QDPasscode.alias();
        new QDPasscode().where().revokedAt.isNull().asUpdate().set(a.revokedAt, Timestamp.from(Instant.now())).update();
    }

    public static DPasscode create(String code) {
        DPasscode passcode = new DPasscode(code);
        passcode.save();
        return passcode;
    }

    public static DPasscode findLastPasscode() {
        return new QDPasscode().where().revokedAt.isNotNull()
            .orderBy().createdAt.desc()
            .setMaxRows(1)
            .findOne();
    }
}
