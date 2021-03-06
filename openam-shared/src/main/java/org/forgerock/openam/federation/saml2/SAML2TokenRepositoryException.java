/*
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License (the License). You may not use this file except in compliance with the
 * License.
 *
 * You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
 * specific language governing permission and limitations under the License.
 *
 * When distributing Covered Software, include this CDDL Header Notice in each file and include
 * the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
 * Header, with the fields enclosed by brackets [] replaced by your own identifying
 * information: "Portions copyright [year] [name of copyright owner]".
 *
 * Copyright 2014-2015 ForgeRock AS.
 */

package org.forgerock.openam.federation.saml2;

/**
 * This class is used to wrap Exceptions thrown by implementations of the SAML2TokenRepository.
 */
public class SAML2TokenRepositoryException extends Exception {

    /**
     * Constructs a new SAML2TokenRepositoryException with the specified detail message and
     * cause.
     *
     * @param error The detail message.
     * @param cause The cause.
     */
    public SAML2TokenRepositoryException(String error, Throwable cause) {
        super(error, cause);
    }

    /**
     * Constructs a new SAML2TokenRepositoryException with the specified detail message.
     *
     * @param error The detail message.
     */
    public SAML2TokenRepositoryException(String error) {
        super(error);
    }
}
