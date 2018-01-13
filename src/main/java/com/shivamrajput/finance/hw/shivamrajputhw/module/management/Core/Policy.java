package com.shivamrajput.finance.hw.shivamrajputhw.module.management.Core;

/**
 * Policy interface , its used to apply rules to users when the apply for rules
 */
public interface Policy {
   void execute(PolicyDTO policyDTO);
}
