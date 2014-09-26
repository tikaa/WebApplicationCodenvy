package com.codenvy.ide.tutorial.wizard.pages.page3;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;


public class Page3ViewImpl extends Composite implements Page3View {
    
    interface Page3ViewImplUiBinder extends UiBinder<Widget, Page3ViewImpl> {
    }
    
    
    
    
     @UiField
    TextBox SelectProject;
    private ActionDelegate delegate;

    
     @Inject
    public Page3ViewImpl (Page3ViewImplUiBinder ourUiBinder) {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    
   
    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }
    
    @UiHandler("SelectProject")
    public void onSelectProject(ClickEvent event) {
        delegate.onSelectProject();
    }
    
    
}