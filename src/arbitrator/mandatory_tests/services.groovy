package arbitrator

import arbitrator.mandatory_tests.db
import arbitrator.optional_tests.mail

class services {

    def run_tests(app){
        switch (app) {
            case "db":
                def result = new db().testA();
                return result;
                break;
            case "mail":
                def result = new mail.send();
                return result;
                break;
            default:
                return "no tests defined";
                break;
        }
    }
    
}