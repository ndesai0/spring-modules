/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springmodules.validation.bean.conf.xml.handler;

import org.springmodules.validation.bean.conf.xml.DefaultXmBeanValidationConfigurationlLoaderConstants;
import org.springmodules.validation.util.condition.Condition;
import org.springmodules.validation.util.condition.Conditions;
import org.w3c.dom.Element;

/**
 * An {@link AbstractPropertyValidationElementHandler} implementation that can handle an element that represents a not-null
 * validation rule.
 *
 * @author Uri Boness
 */
public class NotNullRuleElementHandler extends AbstractPropertyValidationElementHandler
    implements DefaultXmBeanValidationConfigurationlLoaderConstants {

    /**
     * The default error code for the not-null validation rule.
     */
    public static final String DEFAULT_ERROR_CODE = "not.null";

    private static final String ELEMENT_NAME = "not-null";

    /**
     * Constructs a new NotNullRuleElementHandler.
     */
    public NotNullRuleElementHandler() {
        super(ELEMENT_NAME, DEFAULT_NAMESPACE_URL);
    }

    /**
     * Returns {@link #DEFAULT_ERROR_CODE}.
     *
     * @see AbstractPropertyValidationElementHandler#getDefaultErrorCode(org.w3c.dom.Element)
     */
    protected String getDefaultErrorCode(Element element) {
        return DEFAULT_ERROR_CODE;
    }

    /**
     * Creates and returns a condition that checks that an object is not null.
     *
     * @see AbstractPropertyValidationElementHandler#extractCondition(org.w3c.dom.Element)
     * @see org.springmodules.validation.util.condition.Conditions#notNull()
     */
    protected Condition extractCondition(Element element) {
        return Conditions.notNull();
    }

}