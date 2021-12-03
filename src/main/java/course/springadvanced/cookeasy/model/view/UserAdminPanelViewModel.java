package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.GenderEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;

public class UserAdminPanelViewModel {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private GenderEntity genderEntity;
    private LevelEntity levelEntity;
    private boolean isAdmin;

    public UserAdminPanelViewModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public GenderEntity getGenderEntity() {
        return this.genderEntity;
    }

    public void setGenderEntity(GenderEntity genderEntity) {
        this.genderEntity = genderEntity;
    }

    public LevelEntity getLevelEntity() {
        return this.levelEntity;
    }

    public void setLevelEntity(LevelEntity levelEntity) {
        this.levelEntity = levelEntity;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}