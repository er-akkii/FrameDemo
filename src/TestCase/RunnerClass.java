package TestCase;

import Generic_Methods.GenericMethods;

public class RunnerClass {

	private static GenericMethods gobj;

	public static void main(String[] args) {

		TestCase_1st testCase = new TestCase_1st(gobj);
		testCase.goAndVlidateMarketingAccounts();
//			testCase.goAndValidateMarketingLeads();

	}
}
