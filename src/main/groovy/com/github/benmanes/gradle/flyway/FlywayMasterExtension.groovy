/*
 * Copyright 2013 Ben Manes. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
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
package com.github.benmanes.gradle.flyway

/**
 * The flyway's configuration properties.
 *
 * @author Ben Manes (ben.manes@gmail.com)
 * @see http://flywaydb.org/documentation/commandline
 */
public class FlywayMasterExtension extends FlywayExtension {
  
  
  /** The map of database-specific Flyway configurations */
  Map<String, FlywayExtension> dbConfigurations = [:]
  
  /** Indicates the order to concatenate the schemas: if true, schemas form the generic 
      (parent) will be appended first; if false, the database-specific schemas will be appended first */
  Boolean schemaGenericFirst = new Boolean(true)

  /** The dependencies that all flyway tasks depend on. */
  List<Object> dependsOnTasks = []

  /** @see http://www.gradle.org/docs/current/javadoc/org/gradle/api/Task.html#dependencies */
  def dependsOnTasks(Object... paths) {
    dependsOnTasks += paths
  }

  def getDependsOnTasks() {
    dependsOnTasks
  }
  
  def addDbConfig(dbName) {
     dbConfigurations[dbName] = new FlywayExtension(this)
  }
  public FlywayMasterExtension() {
    super()
/*    dependsOnTasks = []
    schemas = []
    locations = []
    placeholders = [:]*/
  }
}