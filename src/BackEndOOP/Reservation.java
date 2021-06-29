package BackEndOOP;

public class Reservation {
    private int ReservationID;
    private boolean VIPBOX;
    private int MatchID;

    public Reservation() {

    }

    public Reservation(int reservationID, boolean VIPBOX, int matchID) {
        ReservationID = reservationID;
        this.VIPBOX = VIPBOX;
        MatchID = matchID;
    }

    public Reservation(Reservation r) {
        ReservationID = r.ReservationID;
        this.VIPBOX = r.VIPBOX;
        MatchID = r.MatchID;
    }

    public int getReservationID() {
        return ReservationID;
    }

    public void setReservationID(int reservationID) {
        ReservationID = reservationID;
    }

    public boolean isVIPBOX() {
        return VIPBOX;
    }

    public void setVIPBOX(boolean VIPBOX) {
        this.VIPBOX = VIPBOX;
    }

    public int getMatchID() {
        return MatchID;
    }

    public void setMatchID(int matchID) {
        MatchID = matchID;
    }
}
