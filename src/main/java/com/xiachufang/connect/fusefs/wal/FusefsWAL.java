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

package com.xiachufang.connect.fusefs.wal;

import io.confluent.connect.hdfs.storage.HdfsStorage;
import io.confluent.connect.hdfs.wal.WAL;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.errors.ConnectException;

public class FusefsWAL implements WAL {
  public FusefsWAL(String logsDir, TopicPartition topicPart, HdfsStorage storage) {

  }

  @Override
  public void acquireLease() throws ConnectException {

  }

  @Override
  public void append(String s, String s1) throws ConnectException {

  }

  @Override
  public void apply() throws ConnectException {

  }

  @Override
  public void truncate() throws ConnectException {

  }

  @Override
  public void close() throws ConnectException {

  }

  @Override
  public String getLogFile() {
    return null;
  }
}
