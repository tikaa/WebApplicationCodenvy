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
package com.codenvy.ide.tutorial.wizard.inject;

import com.codenvy.ide.api.extension.ExtensionGinModule;
import com.codenvy.ide.api.ui.wizard.DefaultWizard;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import com.google.inject.AbstractModule;

import com.codenvy.ide.tutorial.wizard.projecttype.WSO2ProjectTypeDescriptionExtension;
import com.codenvy.ide.tutorial.wizard.projecttype.WSO2ProjectTypeExtension;

/**
 * GIN module for 'Wizard Tutorial' extension.
 *
 * @author <a href="mailto:aplotnikov@codenvy.com">Andrey Plotnikov</a>
 */
@ExtensionGinModule
public class WizardTutorialGinModule extends AbstractGinModule {
    /** {@inheritDoc} */
    @Override
    protected void configure() {
        bind(DefaultWizard.class).annotatedWith(SimpleWizard.class).toProvider(SimpleWizardProvider.class).in(Singleton.class);
        bind(WSO2ProjectTypeDescriptionExtension.class);
        bind(WSO2ProjectTypeExtension.class);

    }
}