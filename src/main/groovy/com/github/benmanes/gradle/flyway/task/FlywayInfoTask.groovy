/*
 * Copyright 2013 Ben Manes. All Rights Reserved.
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
package com.github.benmanes.gradle.flyway.task

import com.googlecode.flyway.core.Flyway
import com.googlecode.flyway.core.info.MigrationInfoDumper

/**
 * @author Ben Manes (ben.manes@gmail.com)
 */
class FlywayInfoTask extends AbstractFlywayTask {

  FlywayInfoTask() {
    description = 'Prints the details and status information about all the migrations.'
  }

  def run(String name, Flyway flyway) {
    def result
    switch (infoLevel.toLowerCase()) {
        case "pending":
            result = flyway.info().pending()
            break
        case "current":
            result = flyway.info().current()
            break
        case "applied":
            result = flyway.info().applied()
            break
        default:
            result = flyway.info().all()
    }
    logger.lifecycle("Info (${infoLevel}): ${name}")
    logger.quiet(MigrationInfoDumper.dumpToAsciiTable(result))
  }
}
