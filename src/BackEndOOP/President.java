package BackEndOOP;

import java.sql.Date;

public class President {
    private int personID;
    private Date ElectedTill;
    private int NetWorth;

    public President() {
    }


    public President(int personID, Date electedTill, int netWorth) {
        this.personID = personID;
        ElectedTill = electedTill;
        NetWorth = netWorth;
    }


    public President(President p) {
        personID = p.personID;
        ElectedTill = p.ElectedTill;
        NetWorth = p.NetWorth;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public Date getElectedTill() {
        return ElectedTill;
    }

    public void setElectedTill(Date electedTill) {
        ElectedTill = electedTill;
    }

    public int getNetWorth() {
        return NetWorth;
    }

    public void setNetWorth(int netWorth) {
        NetWorth = netWorth;
    }
}
