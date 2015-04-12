/*
 * Copyright 2014-2015 Rudy De Busscher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package be.c4j.demo.exception.factory;

import be.c4j.demo.exception.BusinessException;
import be.c4j.demo.messages.MessagesService;
import org.apache.deltaspike.core.api.provider.BeanProvider;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

/**
 *
 */
public class BusinessExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;

    public BusinessExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }


    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {

        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext(); ) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            Throwable businessException = getBusinessException(t.getCause());
            if (businessException != null) {

                MessagesService messagesService = BeanProvider.getContextualReference(MessagesService.class);
                messagesService.showErrorMessage(((BusinessException)businessException).getErrorKey());
                i.remove();
            }
        }
        wrapped.handle();
    }

    private Throwable getBusinessException(Throwable t) {
        if (t instanceof BusinessException) {
            return t;
        } else {
            if (t != null) {
                return getBusinessException(t.getCause());
            } else {
                return null;
            }
        }
    }
}
