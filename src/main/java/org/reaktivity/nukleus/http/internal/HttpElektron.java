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
package org.reaktivity.nukleus.http.internal;

import static org.reaktivity.nukleus.route.RouteKind.CLIENT;
import static org.reaktivity.nukleus.route.RouteKind.SERVER;

import java.util.EnumMap;
import java.util.Map;

import org.reaktivity.nukleus.Elektron;
import org.reaktivity.nukleus.http.internal.stream.ClientStreamFactoryBuilder;
import org.reaktivity.nukleus.http.internal.stream.HttpServerFactoryBuilder;
import org.reaktivity.nukleus.route.RouteKind;
import org.reaktivity.nukleus.stream.StreamFactoryBuilder;

final class HttpElektron implements Elektron
{
    private final Map<RouteKind, StreamFactoryBuilder> streamFactoryBuilders;

    HttpElektron(
        HttpConfiguration config)
    {
        Map<RouteKind, StreamFactoryBuilder> streamFactoryBuilders = new EnumMap<>(RouteKind.class);
        streamFactoryBuilders.put(CLIENT, new ClientStreamFactoryBuilder(config));
        streamFactoryBuilders.put(SERVER, new HttpServerFactoryBuilder(config));
        this.streamFactoryBuilders = streamFactoryBuilders;
    }

    @Override
    public StreamFactoryBuilder streamFactoryBuilder(
        RouteKind kind)
    {
        return streamFactoryBuilders.get(kind);
    }

    @Override
    public String toString()
    {
        return String.format("%s %s", getClass().getSimpleName(), streamFactoryBuilders);
    }
}
