/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.core.api.control.command;


import org.wirez.core.api.control.rule.RuleManager;

/**
 * Manager to handle execution of commands.
 */
public interface CommandManager<C extends Command> {

    /**
     * Check whether command can be executed and return results. Graph elements are not mutated.
     * @param ruleManager
     * @param command
     * @return
     */
    boolean allow(final RuleManager ruleManager,
                  final C command);
    
    /**
     * Execute a command and return the results. Graph elements can be mutated.
     * @param ruleManager
     * @param command
     * @return
     */
    CommandResults execute(final RuleManager ruleManager,
                           final C... command);

    /**
     * Undo the most recent command.
     * @param ruleManager
     * @return
     */
    CommandResults undo(final RuleManager ruleManager);

}
