package org.data.datacollector.dataExtractors.extractors;

import org.data.datacollector.dataExtractors.DataExtractor;
import org.data.datacollector.dataExtractors.dataHolders.dataFromTRITRE.UserSuiviTRITRE;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCAT2.UserCAT2;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCRM.UserCRM;
import org.data.datacollector.dataExtractors.dataHolders.dataFromCtrlEL.UserEL;
import org.data.datacollector.dataExtractors.dataHolders.dataFromDG.UserDG;
import org.data.datacollector.dataExtractors.dataHolders.dataFromELP.UserELP;
import org.data.datacollector.dataExtractors.dataHolders.dataFromIR.UserIR;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSC.UserSC;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSS.UserSS;
import org.data.datacollector.dataExtractors.dataHolders.dataFromSimu.UserSimu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UnknownUserDataExtractor extends DataExtractor {

    public List<UserSimu> extractUserSimu(){
        return extractData("data2" , UserSimu.class);
    }

    public List<UserEL> extractUserEL(){
        return extractData("data2" , UserEL.class);
    }

    public List<UserCRM> extractUserCRM(){
        return extractData("data2" , UserCRM.class);
    }

    public List<UserSS> extractUserSS(){
        return extractData("data2" , UserSS.class);
    }

    public List<UserDG> extractUserDG(){
        return extractData("data2" , UserDG.class);
    }

    public List<UserSC> extractUserSC(){
        return extractData("data2" , UserSC.class);
    }

    public List<UserCAT2> extractUserCAT2(){
        return extractData("data2" , UserCAT2.class);
    }

    public List<UserIR> extractUserIR(){
        return extractData("data2" , UserIR.class);
    }

    public List<UserELP> extractUserELP(){
        return extractData("data2" , UserELP.class);
    }

    public List<UserSuiviTRITRE> extractUserSuiviTRITRE(){
        return extractData("data2" , UserSuiviTRITRE.class);
    }
}
