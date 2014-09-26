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
package com.codenvy.ide.tutorial.wizard.pages.page3;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import com.codenvy.ide.api.resources.model.File;
import com.codenvy.ide.api.resources.model.Folder;
import com.codenvy.ide.api.resources.model.Project;
import com.codenvy.ide.api.resources.model.Resource;
import com.codenvy.ide.api.ui.wizard.AbstractWizardPage;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import static com.codenvy.ide.tutorial.wizard.WizardTutorialExtension.PAGE2_NEXT;


import com.codenvy.api.project.shared.dto.ProjectDescriptor;
import com.codenvy.api.project.gwt.client.ProjectServiceClient;
import com.codenvy.ide.api.resources.ResourceProvider;
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.rest.AsyncRequestCallback;

import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_TYPE;
import static com.codenvy.ide.api.ui.wizard.ProjectWizard.PROJECT_VISIBILITY;
/**
 * The third page into wizard.
 *
 * @author <a href="mailto:aplotnikov@codenvy.com">Andrey Plotnikov</a>
 */
public class Page3Presenter extends AbstractWizardPage implements Page3View.ActionDelegate {
    
   
   

 
    private Page3View view;
    private CommitCallback callback;
   
        private  ResourceProvider     resourceProvider;
        private  ProjectServiceClient projectServiceClient;
        private  DtoFactory           factory;
   //     private SelectionAgent selectionAgent;

    
    @Inject
    public Page3Presenter(Page3View view,
                          ProjectServiceClient projectServiceClient,
                             ResourceProvider resourceProvider,
                             DtoFactory factory) {
        super("Import from Existing Web Application", null);
        this.view = view;
        this.projectServiceClient = projectServiceClient;
        this.resourceProvider = resourceProvider;
        this.factory = factory;
        this.view.setDelegate(this);
    }

    /** {@inheritDoc} */
    @Override
    public String getNotice() {
        return null;
    }

    /** {@inheritDoc} */
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

    // {@inheritDoc} 
    @Override
    public boolean inContext() {
        Boolean data = wizardContext.getData(PAGE2_NEXT);  
        return data != null && !data;
    }

    /** {@inheritDoc} */
     @Override
    public void go(AcceptsOneWidget container) {
        container.setWidget(view);
    }
     
     

    @Override
    public void onSelectProject() {
        
        final ProjectDescriptor projectDescriptor = factory.createDto(ProjectDescriptor.class);
        projectDescriptor.withProjectTypeId(wizardContext.getData(PROJECT_TYPE).getProjectTypeId());

        boolean visibility = wizardContext.getData(PROJECT_VISIBILITY);
        projectDescriptor.setVisibility(visibility ? "public" : "private");
        // TODO Auto-generated method stub
        createProject(callback, projectDescriptor, "pROJECTnAME");
        //create("MyString", parent, project, callback);
        
    }
    
    @Override
    public void commit(@NotNull final CommitCallback callback) {
        final ProjectDescriptor projectDescriptor = factory.createDto(ProjectDescriptor.class);
        projectDescriptor.withProjectTypeId(wizardContext.getData(PROJECT_TYPE).getProjectTypeId());

        boolean visibility = wizardContext.getData(PROJECT_VISIBILITY);
        projectDescriptor.setVisibility(visibility ? "public" : "private");
        // TODO Auto-generated method stub
        createProject( callback, projectDescriptor, "pROJECTnAME");
        
    }
    
    
    
    /**
     * Trying to add a new resource on finish button click!!
     * **/
    
 
    /** {@inheritDoc} */
    public void create(@NotNull String name,@NotNull Folder parent,  @NotNull Project project,
                       @NotNull final AsyncCallback<Resource> callback) {
        String fileName = name + '.' + getCaption();
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" ;
        
       
 
        project.createFile(parent, fileName, content, "MyXML", new AsyncCallback<File>()  {
            
            @Override
            public void onSuccess(File result) {
                callback.onSuccess(result);
            }
 
            @Override
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);
            }
        });
    }
    
    
    
    /**
     * Creating a new project the WSO@ way!**/
    
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
     
     
  /*  private void updateProject(@Nonnull final Project project, @Nonnull ProjectDescriptor projectDescriptor, @Nonnull final CommitCallback callback) {
        projectServiceClient.updateProject(project.getPath(), projectDescriptor, new AsyncRequestCallback<ProjectDescriptor>() {
            @Override
            protected void onSuccess(ProjectDescriptor result) {
                resourceProvider.getProject(project.getName(), new AsyncCallback<Project>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        callback.onFailure(caught);
                    }

                    @Override
                    public void onSuccess(Project result) {
                        callback.onSuccess();
                    }
                });
            }

            @Override
            protected void onFailure(Throwable exception) {
                callback.onFailure(exception);
            }
        });
    }*/
    
  


    
}