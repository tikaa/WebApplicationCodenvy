package com.codenvy.ide.tutorial.wizard.pages.page2;

import com.codenvy.ide.api.mvp.View;
import com.google.inject.ImplementedBy;


@ImplementedBy(Page2ViewImpl.class)
public interface Page2View extends View<Page2View.ActionDelegate> {
    /** Required for delegating functions in view. */
    public interface ActionDelegate {
        /** Performs some actions in response to a user's choosing entering a Project Name on the wizardS. */
        void onPageNameSet();

    }
    
      
    
}