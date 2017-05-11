/**
 * Copyright 2016-2017 The Reaktivity Project
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
package org.reaktivity.nukleus.http.internal.streams.server.rfc7230;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;
import org.reaktivity.reaktor.test.NukleusRule;

public class ArchitectureIT
{
    private final K3poRule k3po = new K3poRule()
            .addScriptRoot("route", "org/reaktivity/specification/nukleus/http/control/route")
            .addScriptRoot("client", "org/reaktivity/specification/http/rfc7230/architecture")
            .addScriptRoot("server", "org/reaktivity/specification/nukleus/http/streams/rfc7230/architecture");

    private final TestRule timeout = new DisableOnDebug(new Timeout(5, SECONDS));

    private final NukleusRule nukleus = new NukleusRule("http")
        .directory("target/nukleus-itests")
        .commandBufferCapacity(1024)
        .responseBufferCapacity(1024)
        .counterValuesBufferCapacity(1024);

    @Rule
    public final TestRule chain = outerRule(nukleus).around(k3po).around(timeout);

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.and.response/client",
        "${server}/request.and.response/server" })
    public void shouldCorrelateRequestAndResponse() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.uri.with.percent.chars/client",
        "${server}/request.uri.with.percent.chars/server" })
    public void shouldAcceptRequestWithPercentChars() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.version.1.2+/client",
        "${server}/request.version.1.2+/server" })
    public void shouldRespondVersionHttp11WhenRequestVersionHttp12plus() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.header.host.missing/client" })
    public void shouldRejectRequestWhenHostHeaderMissing() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.version.invalid/client" })
    public void shouldRejectRequestWhenVersionInvalid() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.version.missing/client" })
    public void shouldRejectRequestWhenVersionMissing() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.version.not.1.x/client" })
    public void shouldRejectRequestWhenVersionNotHttp1x() throws Exception
    {
        k3po.finish();
    }

    @Test
    @Specification({
        "${route}/input/new/controller",
        "${client}/request.uri.with.user.info/client", })
    public void shouldRejectRequestWithUserInfo() throws Exception
    {
        k3po.finish();
    }

}
