package ru.shmoylova.tracker.extra;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import ru.shmoylova.tracker.util.HibernateUtil;

/**
 *
 * @author Ksiona
 */
@SuppressWarnings("serial")
public class ReqRespListener implements PhaseListener {

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW ) {
            HibernateUtil.openSession();
        }
    }

    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE ) {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
