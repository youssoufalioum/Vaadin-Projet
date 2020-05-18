package com.vaadintest.app.backend.service;

import com.vaadin.flow.component.*;

@Tag("BGSOUND")
public class BGSound extends Component {

    private static final PropertyDescriptor<String, String> srcDescriptor = PropertyDescriptors
            .attributeWithDefault("src", "");

    public BGSound() {
        super();
        getElement().setAttribute("LOOP", "n");
    }

    public BGSound(String src) {
        setSrc(src);
        getElement().setAttribute("LOOP", "n");
    }

    public String getSrc() {
        return get(srcDescriptor);
    }

    public void setSrc(String src) {
        set(srcDescriptor, src);
    }
}

