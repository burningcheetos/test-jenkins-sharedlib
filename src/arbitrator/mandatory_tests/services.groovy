package arbitrator.mandatory_tests

import arbitrator.mandatory_tests.db
import arbitrator.mandatory_tests.frontend
// import arbitrator.optional_tests.mail

class services {

    def run_tests(app){
        switch (app) {
            case "db":
                def result = new db().testA();
                return result;
                break;
            case "frontend":
                def result = new frontend().testFront();
                return result;
                break;
            default:
                return "no tests defined";
                break;
        }
    }
}