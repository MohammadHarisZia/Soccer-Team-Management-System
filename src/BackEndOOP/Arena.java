package BackEndOOP;

import oracle.sql.TIMESTAMP;

public class Arena {
    private int ArenaID;
    private int ArenaName;
    private String MaintenanceDueIn;
    private String VisitingHoursFrom;
    private String VisitingHoursTo;

    public Arena() {
    }

    public Arena(int arenaID, int arenaName, String maintenanceDueIn, String visitingHoursFrom, String visitingHoursTo) {
        ArenaID = arenaID;
        ArenaName = arenaName;
        MaintenanceDueIn = maintenanceDueIn;
        VisitingHoursFrom = visitingHoursFrom;
        VisitingHoursTo = visitingHoursTo;
    }

    public Arena(Arena a) {
        ArenaID = a.ArenaID;
        ArenaName = a.ArenaName;
        MaintenanceDueIn = a.MaintenanceDueIn;
        VisitingHoursFrom = a.VisitingHoursFrom;
        VisitingHoursTo = a.VisitingHoursTo;
    }

    public int getArenaID() {
        return ArenaID;
    }

    public void setArenaID(int arenaID) {
        ArenaID = arenaID;
    }

    public int getArenaName() {
        return ArenaName;
    }

    public void setArenaName(int arenaName) {
        ArenaName = arenaName;
    }

    public String getMaintenanceDueIn() {
        return MaintenanceDueIn;
    }

    public void setMaintenanceDueIn(String maintenanceDueIn) {
        MaintenanceDueIn = maintenanceDueIn;
    }

    public String getVisitingHoursFrom() {
        return VisitingHoursFrom;
    }

    public void setVisitingHoursFrom(String visitingHoursFrom) {
        VisitingHoursFrom = visitingHoursFrom;
    }

    public String getVisitingHoursTo() {
        return VisitingHoursTo;
    }

    public void setVisitingHoursTo(String visitingHoursTo) {
        VisitingHoursTo = visitingHoursTo;
    }
}
