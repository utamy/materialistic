/*
 * Copyright (c) 2016 Ha Duy Trung
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
 */

package io.github.hidroh.materialistic.data;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.util.ServiceController;

import io.github.hidroh.materialistic.test.TestRunner;
import io.github.hidroh.materialistic.test.shadow.ShadowWebView;

import static org.assertj.core.api.Assertions.assertThat;

@Config(shadows = ShadowWebView.class)
@RunWith(TestRunner.class)
public class WebCacheServiceTest {
    private ServiceController<WebCacheService> controller;
    private WebCacheService service;

    @Before
    public void setUp() {
        controller = Robolectric.buildService(WebCacheService.class);
        service = controller.attach().create().get();
    }

    @Test
    public void testWebCache() {
        ShadowWebView.lastGlobalLoadedUrl = null;
        controller.withIntent(new Intent()
                .putExtra(WebCacheService.EXTRA_URL, "http://example.com"))
                .startCommand(0, 0);
        assertThat(ShadowWebView.getLastGlobalLoadedUrl()).contains("http://example.com");

    }

    @After
    public void tearDown() {
        controller.destroy();
    }
}
