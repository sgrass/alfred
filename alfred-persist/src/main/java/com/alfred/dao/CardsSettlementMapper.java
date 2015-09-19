package com.alfred.dao;

import com.alfred.model.CardsSettlement;
import java.util.List;

public interface CardsSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CardsSettlement cardsSettlement);

    List<CardsSettlement> selectByParam(CardsSettlement cardsSettlement);

    CardsSettlement selectByPrimaryKey(Integer id);

    int updateById(CardsSettlement cardsSettlement);
}