/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.core.provider.consumer;

import org.junit.Assert;
import org.junit.Test;

import io.servicecomb.core.Const;
import io.servicecomb.core.definition.MicroserviceMeta;
import io.servicecomb.core.definition.schema.ConsumerSchemaFactory;
import mockit.Expectations;
import mockit.Mocked;

public class TestReferenceConfig {
  @Test
  public void constructNoParam(@Mocked MicroserviceMeta microserviceMeta) {
    String microserviceVersionRule = Const.VERSION_RULE_LATEST;
    String transport = Const.ANY_TRANSPORT;

    ReferenceConfig referenceConfig = new ReferenceConfig();
    referenceConfig.setMicroserviceMeta(microserviceMeta);
    referenceConfig.setMicroserviceVersionRule(microserviceVersionRule);
    referenceConfig.setTransport(transport);

    Assert.assertSame(microserviceMeta, referenceConfig.getMicroserviceMeta());
    Assert.assertSame(microserviceVersionRule, referenceConfig.getMicroserviceVersionRule());
    Assert.assertSame(transport, referenceConfig.getTransport());
  }

  @Test
  public void constructWithParam(@Mocked MicroserviceMeta microserviceMeta,
      @Mocked ConsumerSchemaFactory consumerSchemaFactory) {
    String microserviceName = "ms";
    String microserviceVersionRule = Const.VERSION_RULE_LATEST;
    String transport = Const.ANY_TRANSPORT;
    new Expectations() {
      {
        consumerSchemaFactory.getOrCreateMicroserviceMeta(microserviceName, microserviceVersionRule);
        result = microserviceMeta;
      }
    };

    ReferenceConfig referenceConfig =
        new ReferenceConfig(consumerSchemaFactory, microserviceName, microserviceVersionRule, transport);
    Assert.assertSame(microserviceMeta, referenceConfig.getMicroserviceMeta());
    Assert.assertSame(microserviceVersionRule, referenceConfig.getMicroserviceVersionRule());
    Assert.assertSame(transport, referenceConfig.getTransport());
  }
}
