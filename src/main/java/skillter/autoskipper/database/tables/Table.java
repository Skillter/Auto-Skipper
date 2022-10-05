package skillter.autoskipper.database.tables;

public class Table {

    public long time = 0;
    public String uuid = null;
    public String playerNickname = null;

    public Table(String uuid, String playerNickname) {
        this.time = System.currentTimeMillis();
        this.uuid = uuid;
        this.playerNickname = playerNickname;
    }

}
