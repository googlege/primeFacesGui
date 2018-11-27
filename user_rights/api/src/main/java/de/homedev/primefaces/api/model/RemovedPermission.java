package de.homedev.primefaces.api.model;

import de.homedev.primefaces.api.converter.PermissionConverter;
import de.homedev.primefaces.api.enums.Permission;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
@Table(name = RemovedPermission.TABLE_NAME)
public final class RemovedPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "removed_permissions";

    @Id
    @TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE",
        pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "id")
    private Long id;

    @Column(name = "permission", nullable = false)
    @Convert(converter = PermissionConverter.class)
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    public RemovedPermission() {
        super();

    }

    public RemovedPermission(final Permission permission) {
        super();
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(final Permission permission) {
        this.permission = permission;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
