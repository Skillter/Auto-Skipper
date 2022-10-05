package skillter.autoskipper.database.tables;

public class SkywarsTable extends Table {

    public static float kd = 0;
    public static float level = 0;

    public SkywarsTable(String uuid, String playerNickname, float kd, float level) {
        super(uuid, playerNickname);
        this.kd = kd;
        this.level = level;
    }
}
