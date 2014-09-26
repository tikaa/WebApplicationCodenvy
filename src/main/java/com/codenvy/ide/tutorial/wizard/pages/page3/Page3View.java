package com.codenvy.ide.tutorial.wizard.pages.page3;

import javax.validation.constraints.NotNull;

import com.codenvy.ide.api.mvp.View;
import com.codenvy.ide.api.ui.wizard.WizardPage.CommitCallback;
import com.google.inject.ImplementedBy;


@ImplementedBy(Page3ViewImpl.class)
public interface Page3View extends View<Page3View.ActionDelegate> {
    /** Required for delegating functions in view. */
    public interface ActionDelegate {
        /** Performs some actions in response to a user's choosing entering a Project Name on the wizardS. */
        void onSelectProject();
        
        

    }
    
    
    
      
    
}