package com.atanas.kanchev.testframework.core.handlers.wrappers;

import com.atanas.kanchev.testframework.core.handlers.Interact;
import com.atanas.kanchev.testframework.core.handlers.JSExecutor;

/**
 * @author Atanas Ksnchev
 */
public interface IInteract {

    default Interact interact() {
        return new Interact();
    }

    default JSExecutor js(){
        return new JSExecutor();
    }
}
