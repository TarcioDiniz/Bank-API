package com.api.bank.BankModelLayer.Domain;

public class Bank {
    private int ID;
    private int AGENCY;
    private int ACCOUNT_ID;

    public Bank(int ID, int AGENCY, int ACCOUNT_ID) {
        this.ID = ID;
        this.AGENCY = AGENCY;
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAGENCY() {
        return AGENCY;
    }

    public void setAGENCY(int AGENCY) {
        this.AGENCY = AGENCY;
    }

    public int getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(int ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }
}
