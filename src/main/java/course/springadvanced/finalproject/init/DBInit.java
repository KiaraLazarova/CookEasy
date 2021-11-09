package course.springadvanced.finalproject.init;

import course.springadvanced.finalproject.service.CategoryService;
import course.springadvanced.finalproject.service.GenderService;
import course.springadvanced.finalproject.service.LevelService;
import course.springadvanced.finalproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final CategoryService categoryService;
    private final GenderService genderService;
    private final LevelService levelService;
    private final RoleService roleService;

    @Autowired
    public DBInit(CategoryService categoryService, GenderService genderService, LevelService levelService, RoleService roleService) {
        this.categoryService = categoryService;
        this.genderService = genderService;
        this.levelService = levelService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initializeCategories();
        this.genderService.initializeGenders();
        this.levelService.initializeLevels();
        this.roleService.initializeRoles();
    }
}