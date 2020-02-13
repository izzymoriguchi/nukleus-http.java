/**
 * Copyright 2016-2020 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.nukleus.http.internal.util;

public final class HttpUtil
{
    public static void appendHeader(
        StringBuilder payload,
        String name,
        String value)
    {
        StringBuilder nameBuilder = new StringBuilder(name);
        int i = 0;
        do
        {
            nameBuilder.replace(i, i + 1, nameBuilder.substring(i, i + 1).toUpperCase());
            i =  nameBuilder.indexOf("-", i) + 1;
        } while (i > 0 && i < nameBuilder.length());

        payload.append(nameBuilder).append(": ").append(value).append("\r\n");
    }

    private HttpUtil()
    {
        // utility class, no instances
    }
}
