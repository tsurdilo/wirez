package org.wirez.core.api.rule.impl;

import org.wirez.core.api.rule.Rule;
import org.wirez.core.api.rule.RuleManager;

public abstract class AbstractWrappedRuleManager<R extends Rule, M extends RuleManager<R>> implements RuleManager<R> {

    protected abstract M getWrapped();
   
    @Override
    public boolean supports(final Rule rule) {
        return getWrapped().supports( rule );
    }

    @Override
    public RuleManager addRule(final R rule) {
        return getWrapped().addRule( rule );
    }

    @Override
    public RuleManager clearRules() {
        getWrapped().clearRules();
        return this;
    }
    
}
