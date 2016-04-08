package com.mpw.card;

import com.mpw.config.AbstractRepository;
import com.mpw.repetition.QRepetition;
import com.mpw.repetition.Repetition.RepetitionStatus;
import com.mysema.query.types.path.EntityPathBase;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wilk.wojtek@gmail.com.
 */
@Repository
public class CardRepository extends AbstractRepository<Card> {

    private static final QCard CARD = QCard.card;
    private static final QRepetition REPETITION = QRepetition.repetition;

    public List<Card> findAll(){
        return query().list(CARD);
    }

    public List<Card> findPlannedBefore(Date date){
        return query().join(REPETITION).on(REPETITION.cardId.eq(CARD.id))
                .where(REPETITION.status.eq(RepetitionStatus.PLANNED))
                .where(REPETITION.date.before(date))
                .list(CARD);
    }

    @Override
    protected EntityPathBase<Card> getPathBase() {
        return CARD;
    }
}
