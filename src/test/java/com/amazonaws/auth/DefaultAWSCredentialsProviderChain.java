package com.amazonaws.auth;

import static java.lang.System.getenv;

/*
 * Copyright 2011-2020 Amazon Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copy of the original class for testing {@link io.fabric8.maven.docker.util.aws.AwsSdkAuthConfigFactory}.
 * Based on <a href="https://github.com/aws/aws-sdk-java/blob/1.11.707/aws-java-sdk-core/src/main/java/com/amazonaws/auth/DefaultAWSCredentialsProviderChain.java">com.amazonaws:aws-java-sdk-core:1.11.707</a> (also APL licensed).
 * We can't use a direct dependency here, as we have
 * to keep d-m-p agnostic of the AWS SDK and only access it via reflection.
 */

public final class DefaultAWSCredentialsProviderChain {

    public AWSCredentials getCredentials() {
        String accessKeyId = getenv("AWSCredentials.AWSAccessKeyId");
        if (accessKeyId == null) {
            return null;
        }
        String secretKey = getenv("AWSCredentials.AWSSecretKey");
        String sessionToken = getenv("AWSSessionCredentials.SessionToken");
        return sessionToken == null
                ? new AWSCredentials(accessKeyId, secretKey)
                : new AWSSessionCredentials(accessKeyId,secretKey,sessionToken);
    }

}