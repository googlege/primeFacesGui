package de.homedev.primefaces.api.model;

import de.homedev.primefaces.api.converter.RolleConverter;
import de.homedev.primefaces.api.enums.CurrentPermission;
import de.homedev.primefaces.api.enums.Permission;
import de.homedev.primefaces.api.enums.Rolle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity extends AbstractMandantEntity implements UserDetails {

	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "users";

	@Id
	@TableGenerator(name = TABLE_NAME, table = "BT_SEQUENCES", pkColumnName = "SEQUENCENAME", valueColumnName = "SEQUENCEVALUE", pkColumnValue = TABLE_NAME, initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", length = AppConstants.USERNAME_MAX_LENGTH, nullable = false)
	private String username;

	@Column(name = "password", length = AppConstants.USERPASSWORD_MAX_LENGTH, nullable = false)
	private String password;

	@Column(name = "display_name", length = AppConstants.USERDISPLAYNAME_MAX_LENGTH, nullable = false)
	private String displayName;
	
    @Column(name = "rolle", length = AppConstants.AUTHORITY_MAX_LENGTH, nullable = false)
    @Convert(converter = RolleConverter.class)
    private Rolle rolle = Rolle.UNKNOWN;
    /**
     * If default permission is allowed then after record is created it is "not allowed", but
     * if  default permission "not allowed" then after record is created it is allowed. 
     * This is the posibility declaring per default disabled permissions.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REFRESH)
    private Set<InvertedPermission> invertedPermissions;

	public UserEntity() {
		super();
	}

	public UserEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    @Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", password=" + password + ", deleted=" + isDeleted() + ", version=" + getVersion()
				+ ", userRole=" + rolle + "]";
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<>(1);
        result.add(rolle.getGrantedAuthority());
		return result;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isDeleted();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isDeleted();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isDeleted();
	}

	@Override
	public boolean isEnabled() {
		return !isDeleted();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMandantName() {
		return this.getMandant().getName();
	}
	
	public  InvertedPermission findInvertedPermission(Permission permission) {
	    for (InvertedPermission perm:invertedPermissions) {
	        if (perm.getPermission()==permission) {
	            return perm;
	        }
	    }
	    return null;
	}
	
	public boolean hasPermission(Permission permission) {
	    CurrentPermission currentPermission=rolle.findPermission(permission);
	    if (currentPermission==null) {
	        return false;
	    }
	    if (!currentPermission.isEditable()||findInvertedPermission(permission)==null) {
	        return currentPermission.isPermited();
	    }
	    return !currentPermission.isPermited();
	}
	
	public Boolean hasPermissionBlzRead() {
        return hasPermission(Permission.BLZ_READ);
    }
	
	public Boolean hasPermissionBlzWrite() {
        return hasPermission(Permission.BLZ_WRITE);
    }
	
	public Boolean hasPermissionBlzDelete() {
        return hasPermission(Permission.BLZ_DELETE);
    }

}
