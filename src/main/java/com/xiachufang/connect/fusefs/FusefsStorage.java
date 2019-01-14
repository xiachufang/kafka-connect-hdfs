/**
 * Copyright 2015 Xiachufang Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 **/

package com.xiachufang.connect.fusefs;

import com.xiachufang.connect.fusefs.wal.FusefsWAL;
import io.confluent.connect.hdfs.HdfsSinkConnectorConfig;
import io.confluent.connect.hdfs.storage.HdfsStorage;
import io.confluent.connect.hdfs.wal.WAL;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;

public class FusefsStorage extends HdfsStorage {
  public FusefsStorage(HdfsSinkConnectorConfig conf, String url) throws IOException {
    super(conf, url);
  }

  public WAL wal(String topicsDir, TopicPartition topicPart) {
    return new FusefsWAL(topicsDir, topicPart, this);
  }
}
