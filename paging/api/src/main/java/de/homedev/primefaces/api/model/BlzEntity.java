package de.homedev.primefaces.api.model;

import java.io.Serializable;

import javax.persistence.*;

import de.homedev.primefaces.api.util.SqlUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = BlzEntity.TABLE_NAME)
public class BlzEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "blz";
    public static final int BLZ_MAX_LENGTH = 8;
    public static final int BIC_MAX_LENGTH = 11;
    public static final int BANKNAME_MAX_LENGTH = 58 + 2;
    private static final String TMP_INSERT = "insert into blz (id,version,blz,bic,bankname) values (";
    private static final char COMMA = ',';
    private static final char AP = '\'';

    // public static final int PLZ_MAX_LENGTH = 256;
    // public static final int ORT_MAX_LENGTH = 256;

    @Id
    @TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "id")
    private Long id;

    @Column(name = "blz", length = BLZ_MAX_LENGTH, nullable = false)
    private String blz;

    @Column(name = "bic", length = BIC_MAX_LENGTH, nullable = true)
    private String bic;

    @Column(name = "bankname", length = BANKNAME_MAX_LENGTH, nullable = true)
    private String bankname;

    //@Column(name = "PLZ", length = PLZ_MAX_LENGTH)
    // private String plz;
    //
    // @Column(name = "ORT", length = ORT_MAX_LENGTH)
    // private String ort;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public BlzEntity() {
    }

    public BlzEntity(String blz, String bic, String bankname, String plz, String ort) {
        super();
        this.blz = blz;
        this.bic = bic;
        this.bankname = bankname;
        // this.plz = plz;
        // this.ort = ort;
    }

    public BlzEntity(String line) {
        super();
        String[] array = line.split(AppConstants.CSV_PARAM_SEPARATOR_STR);
        this.blz = array[AppConstants.BLZ_ID];
        this.bic = array[AppConstants.BIC_ID];
        this.bankname = array[AppConstants.BANKNAME_ID];
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BlzEntity [id=" + id + ", blz=" + blz + ", bic=" + bic + ", bankname=" + bankname + ", version=" + version + "]";
    }

    public byte[] toSqlInsert(long id) {
        StringBuilder sb = new StringBuilder(200);
        sb.append(TMP_INSERT).append(id).append(COMMA);
        sb.append('0').append(COMMA);
        sb.append(AP).append(blz).append(AP).append(COMMA);
        if (bic != null && !bic.isEmpty()) {
            sb.append(AP).append(bic).append(AP).append(COMMA);
        } else {
            sb.append(bic).append(COMMA);
        }
        if (bankname != null && !bankname.isEmpty()) {
            sb.append(AP).append(SqlUtils.checkValue(bankname)).append(AP);
        } else {
            sb.append(SqlUtils.checkValue(bankname));
        }
        sb.append(");\r\n");
        return sb.toString().getBytes(AppConstants.CHARSET);
    }

    public String toCsvInsert() {
        StringBuilder sb = new StringBuilder(200);
        sb.append(blz).append(AppConstants.CSV_PARAM_SEPARATOR);
        if (bic != null && !bic.isEmpty()) {
            sb.append(bic).append(AppConstants.CSV_PARAM_SEPARATOR);
        } else {
            sb.append(AppConstants.CSV_PARAM_SEPARATOR);
        }
        if (bankname != null && !bankname.isEmpty()) {
            sb.append(bankname.replace(AppConstants.CSV_PARAM_SEPARATOR, AppConstants.CSV_PARAM_SEPARATOR_REPLACEMENT));
        }
        sb.append("\r\n");
        return sb.toString();
    }

}
