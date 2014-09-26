package com.codenvy.ide.tutorial.wizard.pages.page2;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;


public class Page2ViewImpl extends Composite implements Page2View {
    
    interface Page2ViewImplUiBinder extends UiBinder<Widget, Page2ViewImpl> {
    }
    
     @UiField
    TextBox ProjectName;
    private ActionDelegate delegate;

    
    @Inject
    public Page2ViewImpl(Page2ViewImplUiBinder ourUiBinder) {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
    
   
    @Override
    public void setDelegate(ActionDelegate delegate) {
        this.delegate = delegate;
    }
    
    @UiHandler("ProjectName")
    public void onPageNameSet(ClickEvent event) {
        delegate.onPageNameSet();
    }
    
}