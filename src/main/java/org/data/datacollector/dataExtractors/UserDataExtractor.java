package org.data.datacollector.dataExtractors;

import org.data.datacollector.dataExtractors.dataHolders.dataFromPERSO.UserPERSO;
import org.data.datacollector.dataExtractors.dataHolders.dataFromUTETPF.UserUTETPF;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataExtractor extends DataExtractor {

    public List<UserPERSO> extractUserPERSO(){
        return extractData("PERSO" , UserPERSO.class);
    }

    public List<UserUTETPF> extractUserUTETPF(){
        return extractData("UsersThatExistInThePERSOFile" , UserUTETPF.class);
    }
}
