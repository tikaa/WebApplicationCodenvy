package com.codenvy.ide.tutorial.wizard.projecttype;

import com.codenvy.api.project.server.ProjectTypeDescriptionExtension;
import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.shared.AttributeDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.google.inject.Singleton;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;


import static com.codenvy.ide.tutorial.wizard.shared.Constants.ESB_CONFIGURATION_PROJECT_ID;
import static com.codenvy.ide.tutorial.wizard.shared.Constants.ESB_CONFIGURATION_PROJECT_NAME;
import static com.codenvy.ide.tutorial.wizard.shared.Constants.WSO2_PROJECT_ID;

/**
 * Register WSO2 extension {@link com.codenvy.api.project.server.ProjectTypeDescriptionExtension} to register project types.
 *
 * @author Valeriy Svydenko
 */
@Singleton
public class WSO2ProjectTypeDescriptionExtension implements ProjectTypeDescriptionExtension {
    @Inject
    public WSO2ProjectTypeDescriptionExtension(ProjectTypeDescriptionRegistry registry) {
        registry.registerDescription(this);
    }

    @Override
    public List<ProjectType> getProjectTypes() {
        return Arrays.asList(new ProjectType(ESB_CONFIGURATION_PROJECT_ID, ESB_CONFIGURATION_PROJECT_NAME, WSO2_PROJECT_ID));
    }

    @Override
    public List<AttributeDescription> getAttributeDescriptions() {
        return Arrays.asList(new AttributeDescription("language"),
                             new AttributeDescription("framework"));
    }
}