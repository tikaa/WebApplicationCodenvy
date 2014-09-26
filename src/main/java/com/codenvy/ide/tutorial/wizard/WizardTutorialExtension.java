package com.codenvy.ide.tutorial.wizard;

import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.ui.action.ActionManager;
import com.codenvy.ide.api.ui.action.DefaultActionGroup;
import com.codenvy.ide.api.ui.wizard.DefaultWizard;
import com.codenvy.ide.api.ui.wizard.WizardContext;
import com.codenvy.ide.api.ui.workspace.WorkspaceAgent;
import com.codenvy.ide.tutorial.wizard.action.OpenSimpleWizardAction;
import com.codenvy.ide.tutorial.wizard.inject.SimpleWizard;
import com.codenvy.ide.tutorial.wizard.pages.page1.Page1Presenter;
import com.codenvy.ide.tutorial.wizard.pages.page2.Page2Presenter;
import com.codenvy.ide.tutorial.wizard.pages.page3.Page3Presenter;
import com.codenvy.ide.tutorial.wizard.pages.page4.Page4Presenter;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import static com.codenvy.ide.api.ui.action.IdeActions.GROUP_MAIN_MENU;
/** Extension used to demonstrate the Wizard feature. */
@Singleton
@Extension(title = "Wizard tutorial", version = "1.0.0")
public class WizardTutorialExtension {
    public static final WizardContext.Key<Boolean> PAGE2_NEXT = new WizardContext.Key<Boolean>("Page 2 next");
    public static final WizardContext.Key<Boolean> PAGE4_SKIP = new WizardContext.Key<Boolean>("Page 4 skip");

    @Inject
    public WizardTutorialExtension(@SimpleWizard DefaultWizard simpleWizard,
                                   ActionManager actionManager,
                                   Provider<Page1Presenter> page1,
                                   Provider<Page2Presenter> page2,
                                   Provider<Page3Presenter> page3,
                                   Provider<Page4Presenter> page4,
                                   OpenSimpleWizardAction openSimpleWizardAction,
                                   WorkspaceAgent workspaceAgent
                                   ) {
       // workspaceAgent.openPart(howToPresenter, EDITING);

        DefaultActionGroup mainMenu = (DefaultActionGroup)actionManager.getAction(GROUP_MAIN_MENU);

        DefaultActionGroup group = new DefaultActionGroup("WSO2", true, actionManager);
        actionManager.registerAction("Web Application Development", group);

        group.add(openSimpleWizardAction);
        mainMenu.add(group);

        simpleWizard.addPage(page1);
        simpleWizard.addPage(page2);
        simpleWizard.addPage(page3);
        simpleWizard.addPage(page4);
    }
}