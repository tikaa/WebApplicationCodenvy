/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 * [2012] - [2013] Codenvy, S.A.
 * All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.ide.tutorial.wizard.pages.page2;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;


import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.api.resources.model.Project;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;

import static com.codenvy.ide.tutorial.wizard.WizardTutorialExtension.PAGE2_NEXT;

import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_TYPE;
import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_VISIBILITY;

/**
 * The second page into wizard.
 *
 * @author <a href="mailto:aplotnikov@codenvy.com">Andrey Plotnikov</a>
 */
public class Page2Presenter extends AbstractWizardPage implements Page2View.ActionDelegate {
    private Page2View view;
   // private CommitCallback callback ;
      private  ResourceProvider     resourceProvider;
        private  ProjectServiceClient projectServiceClient;
        private  DtoFactory           factory;

    @Inject
    public Page2Presenter(Page2View view) {
        super("Create a new Web Application", null);
        this.view = view;
        this.view.setDelegate(this);
    }

    /** {@inheritDoc} */
    @Override
    public String getNotice() {
         return null;
    }
    @Override
    public boolean isCompleted() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public void focusComponent() {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    public void removeOptions() {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    public boolean inContext() {
        Boolean data = wizardContext.getData(PAGE2_NEXT);
        return data != null && data;
    }

    /** {@inheritDoc} */
    @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }

    @Override
    public void onPageNameSet() {
        // TODO Auto-generated method stub
        final CommitCallback MyNewCallback = factory.createDto(CommitCallback.class);
         final ProjectDescriptor projectDescriptor = factory.createDto(ProjectDescriptor.class);
        projectDescriptor.withProjectTypeId(wizardContext.getData(PROJECT_TYPE).getProjectTypeId());

        boolean visibility = wizardContext.getData(PROJECT_VISIBILITY);
        projectDescriptor.setVisibility(visibility ? "public" : "private");
        // TODO Auto-generated method stub
        createProject( MyNewCallback, projectDescriptor, "pROJECTnAME");
    }
    
     @Override
    public void commit(final CommitCallback callback) {
         final CommitCallback MyNewCallback = factory.createDto(CommitCallback.class);
         System.out.println(MyNewCallback.toString());
        final ProjectDescriptor projectDescriptor = factory.createDto(ProjectDescriptor.class);
        projectDescriptor.withProjectTypeId(wizardContext.getData(PROJECT_TYPE).getProjectTypeId());

        boolean visibility = wizardContext.getData(PROJECT_VISIBILITY);
        projectDescriptor.setVisibility(visibility ? "public" : "private");
        // TODO Auto-generated method stub
        createProject( MyNewCallback, projectDescriptor, "pROJECTnAME");
        
    }
    
    private void createProject(@Nonnull final CommitCallback callback,
                               @Nonnull ProjectDescriptor projectDescriptor,
                               @Nonnull final String name) {
        projectServiceClient
                .createProject(name, projectDescriptor,
                               new AsyncRequestCallback<ProjectDescriptor>() {
                                   @Override
                                   protected void onSuccess(ProjectDescriptor result) {
                                       resourceProvider.getProject(name, new AsyncCallback<Project>() {
                                           @Override
                                           public void onSuccess(Project project) {
                                               callback.onSuccess();
                                           }

                                           @Override
                                           public void onFailure(Throwable caught) {
                                               callback.onFailure(caught);
                                           }
                                       });
                                   }

                                   @Override
                                   protected void onFailure(Throwable exception) {
                                       callback.onFailure(exception);
                                   }
                               });
    }
     
}