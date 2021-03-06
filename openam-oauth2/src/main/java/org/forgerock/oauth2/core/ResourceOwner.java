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

package org.forgerock.oauth2.core;

import static org.forgerock.openam.utils.Time.currentTimeMillis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.iplanet.sso.SSOException;
import com.sun.identity.idm.AMIdentity;
import com.sun.identity.idm.IdRepoException;
import org.forgerock.oauth2.core.exceptions.ServerException;
import org.forgerock.openam.utils.StringUtils;

/**
 * Models a OAuth2 resource owner.
 *
 * @since 12.0.0
 */
public class ResourceOwner {

    private final String id;
    private final AMIdentity amIdentity;
    private final long authTime;

    /**
     * Constructs a new OpenAMResourceOwner with their authTime set to now.
     *  @param id The resource owner's id.
     * @param amIdentity The resource owner's identity.
     */
    ResourceOwner(String id, AMIdentity amIdentity) {
        this(id, amIdentity, currentTimeMillis());
    }

    /**
     * Constructs a new OpenAMResourceOwner.
     *  @param id The resource owner's id.
     * @param amIdentity The resource owner's identity.
     * @param authTime Time the resource owner authenticated, in ms.
     */
    ResourceOwner(String id, AMIdentity amIdentity, long authTime) {
        this.id = id;
        this.amIdentity = amIdentity;
        this.authTime = TimeUnit.MILLISECONDS.toSeconds(authTime);
    }

    /**
     * Gets the identifier of the resource owner.
     *
     * @return The resource owner id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the time at which the user last performed an active auth, in seconds.
     *
     * @return The authentication time in seconds.
     */
    public long getAuthTime() {
        return authTime;
    }

    /**
     * Gets the name of the user, if possible.
     * @param settings The settings for the current realm.
     * @return The name of the user, or null.
     */
    public String getName(OAuth2ProviderSettings settings) throws ServerException {
        try {
            final String userDisplayNameAttribute = settings.getUserDisplayNameAttribute();
            if (StringUtils.isNotBlank(userDisplayNameAttribute)) {
                final Set<String> attribute = amIdentity.getAttribute(userDisplayNameAttribute);
                return attribute.isEmpty() ? null : attribute.iterator().next();
            }
            return null;
        } catch (IdRepoException | SSOException e) {
            throw new ServerException(e);
        }
    }

    /**
     * Gets the resource owner's identity.
     *
     * @return The identity.
     */
    public AMIdentity getIdentity() {
        return amIdentity;
    }
}
