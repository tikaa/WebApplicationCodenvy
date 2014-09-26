package com.codenvy.ide.tutorial.wizard.projecttype;

import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.server.ProjectTypeExtension;
import com.codenvy.api.project.shared.Attribute;
import com.codenvy.api.project.shared.ProjectTemplateDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.google.inject.Singleton;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static com.codenvy.ide.tutorial.wizard.shared.Constants.ESB_CONFIGURATION_PROJECT_ID;
import static com.codenvy.ide.tutorial.wizard.shared.Constants.ESB_CONFIGURATION_PROJECT_NAME;
import static com.codenvy.ide.tutorial.wizard.shared.Constants.WSO2_PROJECT_ID;

/**
 * @author Valeriy Svydenko
 */
@Singleton
public class WSO2ProjectTypeExtension implements ProjectTypeExtension {

    @Inject
    public WSO2ProjectTypeExtension(ProjectTypeDescriptionRegistry registry) {
        registry.registerProjectType(this);
    }

    @Override
    public ProjectType getProjectType() {
        return new ProjectType(ESB_CONFIGURATION_PROJECT_ID, WSO2_PROJECT_ID, WSO2_PROJECT_ID);
    }

    @Override
    public List<Attribute> getPredefinedAttributes() {
        return Arrays.asList(new Attribute("language", WSO2_PROJECT_ID),
                             new Attribute("framework", WSO2_PROJECT_ID));
    }

    @Override
    public List<ProjectTemplateDescription> getTemplates() {
        return Arrays.asList(new ProjectTemplateDescription("zip",
                                                            ESB_CONFIGURATION_PROJECT_NAME,
                                                            "This is a simple ESB configuration project.",
                                                            "templates/esbproject.zip"));
    }

}