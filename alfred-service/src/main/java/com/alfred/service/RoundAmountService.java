package com.alfred.service;

import java.util.HashMap;
import com.alfred.model.RoundAmount;

public interface RoundAmountService {
	
    RoundAmount queryRoundValue(HashMap<String, Object> map) throws Exception;


}
