package microservice.cacheAside.demo.API;


import java.io.Serializable;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User implements  Serializable {

@Getter
@Setter
private static final long serialVersionUID = -1L;
private Integer sid;
private Boolean enabled;
private Date  lastModified;
private Integer lastModifiedBy;
private String id;
private String name;
private Integer primaryOrg;
private String customSettings;
private String email;

}

