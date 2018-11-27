package de.homedev.primefaces.api.model;

import java.io.Serializable;

public class BlzSearchDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String blz;
    private String bic;
    private String bankname;

    public BlzSearchDto() {
        super();
    }

    public BlzSearchDto(String blz, String bic, String bankname) {
        super();
        this.blz = blz;
        this.bic = bic;
        this.bankname = bankname;
    }

    public String getBlz() {
        return blz;
    }

    public void setBlz(String blz) {
        this.blz = blz;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

}
