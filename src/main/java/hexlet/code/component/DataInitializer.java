package hexlet.code.component;

import hexlet.code.model.Label;
import hexlet.code.model.TaskStatus;
import hexlet.code.model.User;
import hexlet.code.repository.LabelRepository;
import hexlet.code.repository.TaskStatusRepository;
import hexlet.code.repository.UserRepository;
import hexlet.code.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public final class DataInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initAdmin(args);
        initTaskStatuses(args);
        initLabels(args);
    }

    private void initAdmin(ApplicationArguments args) {
        var email = "admin@admin.com";
        var user = new User();
        user.setEmail(email);
        user.setPasswordDigest("qwerty");
        userDetailsService.createUser(user);
    }

    private void initTaskStatuses(ApplicationArguments args) {
        initTaskStatus("Draft", "draft");
        initTaskStatus("ToReview", "to_review");
        initTaskStatus("ToBeFixed", "to_be_fixed");
        initTaskStatus("ToPublish", "to_publish");
        initTaskStatus("Published", "published");
    }

    private void initTaskStatus(String name, String slug) {
        var ts = new TaskStatus();
        ts.setName(name);
        ts.setSlug(slug);
        taskStatusRepository.save(ts);
    }

    private void initLabels(ApplicationArguments args) {
        initLabel("feature");
        initLabel("bug");
    }

    private void initLabel(String name) {
        var label = new Label();
        label.setName(name);
        labelRepository.save(label);
    }

}
